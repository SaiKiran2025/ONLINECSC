package com.tsspdcl.onlinecsc.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.tsspdcl.onlinecsc.model.ADLServiceModel;
import com.tsspdcl.onlinecsc.model.CategoryChangeModel;
import com.tsspdcl.onlinecsc.model.CnatureData;
import com.tsspdcl.onlinecsc.model.CompNaturePaymentsModel;
import com.tsspdcl.onlinecsc.model.MeterDetailsModel;
import com.tsspdcl.onlinecsc.model.PreReqInputModelAdl;
import com.tsspdcl.onlinecsc.model.PreReqModel;
import com.tsspdcl.onlinecsc.model.SectionListModel;

public class DBMethodsComplaints {
	
	//For Additional Load
	public ADLServiceModel getServiceData(String ukscno, String scope){
		CSCDB db = new CSCDB();
		String qry = "";		
		ADLServiceModel asm = new ADLServiceModel();
		
		try(Connection con = db.getConnection()){
			qry = "SELECT CTSCNO,CTNAME,nvl(A.CTADD1,'nil'),nvl(A.CTADD2,'nil'),nvl(A.CTADD3,'nil'),nvl(A.CTADD4,'nil'),B.id, B.category, a.ctload, A.CTUKSECCD, C.SECNAME,"
					+ " c.sap_seccd, c.erocd, CTSUBCAT "
					+ "	FROM corporate.all_consumer@ebsrodbl A,CATEGORY_MASTER B,ALL_SECTION C WHERE A.CTSECCD=C.SECCD AND A.CTEROCD=C.EROCD AND A.CTCAT=B.ID "
					+ " AND B.htlt_flag = 'LT' AND A.CTUKSCNO= ? ";
			PreparedStatement ps = con.prepareStatement(qry);	
			ps.setString(1, ukscno);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {
				asm.setValid("Y");
				asm.setName(rs.getString(2));
				if(scope.equals("prereq")) {
					asm.setCategory(rs.getString(7) + "-" + rs.getString(8));
					asm.setCatcode(rs.getString(7));
					if(rs.getString(7).equals("3") || rs.getString(7).equals("5") )
						asm.setLoad(rs.getDouble(9));
					else
						asm.setLoad(1000*rs.getDouble(9));
				}
				if(scope.equals("final")) {
					asm.setName(rs.getString(2));
					asm.setCatcode(rs.getString(7));
					asm.setSubcatcode(rs.getString(14));
					asm.setCategory(rs.getString(7) + "-" + rs.getString(8));
					asm.setMetric(getMetric(rs.getString(7), rs.getString(14)));
					if(rs.getString(7).equals("3") || rs.getString(7).equals("4") || rs.getString(7).equals("5") )
						asm.setLoad(rs.getDouble(9));
					else
						asm.setLoad(1000*rs.getDouble(9));
					
					asm.setSection_name(rs.getString(11));
					asm.setAddr1(rs.getString(3));
					asm.setAddr2(rs.getString(4));
					asm.setAddr3(rs.getString(5));
					asm.setAddr4(rs.getString(6));
					
													
					String qryBill = "select (case when nvl(ctmtrrdgstat,'00') in ('06','99') then 1 else 0 end) blstp_flag from  corporate.all_consumer@ebsrodbl A  WHERE A.CTUKSCNO= ? ";
					PreparedStatement psBill = con.prepareStatement(qryBill);	
					psBill.setString(1, ukscno);
					ResultSet rsBill = psBill.executeQuery();	
					if(rsBill.next()) {
						String billstop_flag = rsBill.getString(1);
						if(billstop_flag.equals("1")) {
							asm.setIs_billstop("Y");
						}
					}
					
					String qryMats = "select nvl(totalamount,0)-nvl(amountreceived,0)-nvl(rf_total,0)-nvl(supchg_total,0)-nvl(splchrs_gst,0),type_irregularity "
							+ " from theft.mats_cases_csc@cscdbrodbl where serv_conn_no= ? and CTSAP_SECCD= ? and erono= ? " ;
					PreparedStatement psMats = con.prepareStatement(qryMats);	
					psMats.setString(1, rs.getString(1));
					psMats.setString(2, rs.getString(12));
					psMats.setString(3, rs.getString(13));
					ResultSet rsMats = psMats.executeQuery();	
					if(rsMats.next()) {
						asm.setIs_mats_case("Y");
						asm.setMats_amount(rsMats.getString(1));
						asm.setMats_case(rsMats.getString(2));
					}
				}
			}else {
				asm.setValid("N");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return asm;
	}
	
	public ADLServiceModel checkComplaint(ADLServiceModel asm, String cnature, String ukscno) {
		CSCDB db = new CSCDB();		
		try(Connection con = db.getConnection()){
		String qryComplaint = "select a.COMPLAINT_NATUREID, complaint_no, b.complaint_nature from complaint_details a, COMPLAINT_NATURE b "
				+ "where status not in (4,6) and a.record_status='ACTIVE' and b.RECORD_STATUS = 'ACTIVE' and a.COMPLAINT_NATUREID = b.id and ukscno = ? ";
		PreparedStatement psComplaint = con.prepareStatement(qryComplaint);		
		psComplaint.setString(1, ukscno);
		ResultSet rs = psComplaint.executeQuery();	
		if(rs.next()) {
			if(rs.getString(1).equals(cnature)) {
				asm.setIs_complaint_exist("same_nature");
			}
			else if(rs.getString(1).equals("40") || rs.getString(1).equals("42")) {
				asm.setIs_complaint_exist((cnature.equals("40") || cnature.equals("42") || cnature.equals("69") || cnature.equals("79")) ? "40_42" : "No");					
			}
			else {
				asm.setIs_complaint_exist("No");
			}
			asm.setComplaint_no(rs.getString(2));
			asm.setExisting_cnature_desc(rs.getString(3));
			asm.setNew_cnature_desc(getCtype(cnature,2));
		}else {
			asm.setIs_complaint_exist("No");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return asm;
		
	}


	public PreReqModel genPreReqAdl(PreReqInputModelAdl prima) {
		CSCDB db = new CSCDB();
		PreReqModel prm = new PreReqModel();
		double af = 0.0, afgst = 0.0, secdp= 0.0, totamt=0.0;
	    double dc = 0.0, dcgst = 0.0;
	    double load = 0.00, totload = 0.00;
		DecimalFormat df = new DecimalFormat("0.00"); 

		try(Connection con = db.getConnection()){
			String qry = "SELECT a.ctcat, nvl(a.ctsubcat,0), a.ctload FROM corporate.all_consumer@ebsrodbl A,CATEGORY_MASTER B,ALL_SECTION C "
					+ "WHERE A.CTSECCD=C.SECCD AND A.CTEROCD=C.EROCD AND A.CTCAT=B.ID AND B.htlt_flag = 'LT' AND A.CTUKSCNO= ? ";
			PreparedStatement ps = con.prepareStatement(qry);	
			ps.setString(1, prima.getUkscno());
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {
				String cat = rs.getString(1);
				String subcat = rs.getString(2);
				/*String qry1 = "SELECT APP_FEE, APP_FEE_GST, SEC_DEP, DEV_CH, DEV_CH_GST FROM INITIAL_PAYMT WHERE CAT = ? and "
						+ "	SUBCAT = ? ";*/
				String qry1 = "SELECT APP_FEE, APP_FEE_GST, SEC_DEP, DEV_CH, DEV_CH_GST FROM INITIAL_PAYMT WHERE CAT = ? ";
				PreparedStatement ps1 = con.prepareStatement(qry1);	
				ps1.setString(1, rs.getString(1));
				//ps1.setString(2, rs.getString(2));
				ResultSet rs1 = ps1.executeQuery();
				if(rs1.next()) {
					if(cat.equals("3") || cat.equals("4") || cat.equals("5") || (( cat.equals("6") &  subcat.equals("5") || subcat.equals("6")|| subcat.equals("9")|| subcat.equals("12") )) ) {
						load = Double.parseDouble(prima.getReq_load_prereq());
					}else {
						if(prima.getReq_load_prereq() != null)
						load = Math.ceil(0.001*Double.parseDouble(prima.getReq_load_prereq()));
					}
					af = rs1.getDouble(1);
					afgst = rs1.getDouble(2);
					secdp = load*rs1.getDouble(3);				
					dc = load*rs1.getDouble(4);
					dcgst  = load*rs1.getDouble(5);				
					totamt = af + afgst + secdp + dc + dcgst ;					
					
					if(cat.equals("8")) {
						//String qry2 = "select CATCODE from newconnection_register where EBSFLAG = 'Y' and ALLOTEDUKSCNO = ? "; 
						String qry2 = "SELECT nvl(ctreleasecat,0) FROM corporate.all_consumer@ebsrodbl where ctukscno = ?";
						PreparedStatement ps2 = con.prepareStatement(qry2);		
						ps2.setString(1, prima.getUkscno());
						ResultSet rs2 = ps2.executeQuery();
						if(rs2.next()) {
							if(rs2.getString(1).equals("1") || rs2.getString(1).equals("2")) {
								dc = load*1200;
								dcgst  = load*216;	
								totamt = af + afgst + secdp + dc + dcgst ;	
							}
							else {
								dc = 0; dcgst = 0;
								totamt = secdp + af + afgst;
							}
						}
					}
					
					if(prima.getExtension().equals("Y")) {
						secdp = 0; dc = 0; dcgst = 0;
						totamt = af + afgst;
					}
					
					//code when estimate required option removed on entryCC2 page
					/*if(cat.equals("1") || cat.equals("2") || cat.equals("7")) { 
						totload = load + rs.getDouble(3);
						if(totload > 15) {
							secdp = 0; dc = 0; dcgst = 0;
							totamt = af + afgst;	
						}
					}
					if(cat.equals("3") || cat.equals("4") || cat.equals("5")) { 
							secdp = 0; dc = 0; dcgst = 0;
							totamt = af + afgst;	
					}*/

					
					prm.setAf_pre(df.format(af));
					prm.setAfgst_pre(df.format(afgst));
					prm.setSd_pre(df.format(secdp));
					prm.setDc_pre(df.format(dc));
					prm.setDcgst_pre(df.format(dcgst));
					prm.setTot_pre(df.format(totamt));
					
				}
			}					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return prm;
	}
	

	
	public List<CategoryChangeModel>  getCatChangeList(String exist_cat) {	
		List<CategoryChangeModel> categoryList=new ArrayList<CategoryChangeModel>();	
		if(exist_cat.equals("1") ) {			
			categoryList.add(new CategoryChangeModel("2", "2-Non-Domestic"));
			categoryList.add(new CategoryChangeModel("3", "3-Industrials"));
			categoryList.add(new CategoryChangeModel("4", "4-Cottage Industries"));
			categoryList.add(new CategoryChangeModel("7", "7-General Purpose"));
			categoryList.add(new CategoryChangeModel("8", "8-Temporary Supply"));
		}	  
		if(exist_cat.equals("2") ) {			
			categoryList.add(new CategoryChangeModel("1", "1-Domestic"));
			categoryList.add(new CategoryChangeModel("3", "3-Industrials"));
			categoryList.add(new CategoryChangeModel("4", "4-Cottage Industries"));
			categoryList.add(new CategoryChangeModel("7", "7-General Purpose"));
			categoryList.add(new CategoryChangeModel("8", "8-Temporary Supply"));
		}	  
		if(exist_cat.equals("3") ) {			
			categoryList.add(new CategoryChangeModel("1", "1-Domestic"));
			categoryList.add(new CategoryChangeModel("2", "2-Non-Domestic"));
			categoryList.add(new CategoryChangeModel("3", "3-Industrials"));
			categoryList.add(new CategoryChangeModel("4", "4-Cottage Industries"));
			categoryList.add(new CategoryChangeModel("7", "7-General Purpose"));
		}		
		if(exist_cat.equals("4") ) {		
			categoryList.add(new CategoryChangeModel("1", "1-Domestic"));
			categoryList.add(new CategoryChangeModel("2", "2-Non-Domestic"));
			categoryList.add(new CategoryChangeModel("3", "3-Industrials"));
			categoryList.add(new CategoryChangeModel("7", "7-General Purpose"));
		}	
		if(exist_cat.equals("5") ||  exist_cat.equals("6") ) {
			categoryList.add(new CategoryChangeModel("2", "2-Non-Domestic"));
		}
		if(exist_cat.equals("7") ) {			
			categoryList.add(new CategoryChangeModel("1", "1-Domestic"));
			categoryList.add(new CategoryChangeModel("2", "2-Non-Domestic"));
			categoryList.add(new CategoryChangeModel("3", "3-Industrials"));
			categoryList.add(new CategoryChangeModel("7", "7-Cottage Industries"));
		}
		if(exist_cat.equals("8") ) {			
			categoryList.add(new CategoryChangeModel("1", "1-Domestic"));
			categoryList.add(new CategoryChangeModel("2", "2-Non-Domestic"));
		}
		for (CategoryChangeModel catList : categoryList) {
			System.out.println(catList);
		}
				
		return categoryList;
		
	}
	
	public PreReqModel getCatChangePayments(int new_cat, String ukscno) {
		PreReqModel prm = new PreReqModel();
		CSCDB db = new CSCDB();
		String qry = "", qry1 = "", qry2 = "",beforenafter ="";	
		int exist_cat = 0 ;
		double exist_load = 0.0, exist_sd = 0.0, exist_dc = 0.0;
		double new_load = 0.0, new_sd = 0.0 , new_dc = 0.0;
		double bal_sd = 0.0 , bal_dc = 0.0 , bal_dcgst = 0.0 , bal_tot = 0.0;
		DecimalFormat df = new DecimalFormat("0.00"); 
		ADLServiceModel asm = new ADLServiceModel();
		
		try(Connection con = db.getConnection()){
			qry = "SELECT B.id, a.ctload, CTSECDPAMT,(case when nvl(ctsupcondt,to_date('01/03/2014','dd/mm/yyyy'))>to_date('15/03/2014','dd/mm/yyyy') then 'AFTER' else 'BEFORE' END)"
					+ " FROM corporate.all_consumer@ebsrodbl A,CATEGORY_MASTER B,ALL_SECTION C  WHERE A.CTSECCD=C.SECCD AND A.CTEROCD=C.EROCD AND A.CTCAT=B.ID "
					+ " AND B.htlt_flag = 'LT' AND A.CTUKSCNO= ? ";
			PreparedStatement ps = con.prepareStatement(qry);
			ps.setString(1, ukscno);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {
				exist_cat = rs.getInt(1);
				exist_load = rs.getDouble(2);
				exist_sd = rs.getDouble(3);
				beforenafter = rs.getString(4);
			}			
			if(exist_cat == 3 || exist_cat == 4 || exist_cat == 5) {
				if(new_cat == 1 || new_cat == 2 || new_cat == 7) {
					new_load = Math.ceil(exist_load * 0.75) ;
				}else {
					new_load = exist_load;
				}
			}
			if(exist_cat == 1 || exist_cat == 2 || exist_cat == 7 || exist_cat == 8) {
				if(new_cat == 3 || new_cat == 4 ) {
					new_load = Math.ceil(exist_load/0.75) ;
				}else {
					new_load = exist_load;
				}				
			}
			qry1 = "SELECT SEC_DEP, DEV_CH, DEV_CH_GST FROM INITIAL_PAYMT WHERE CAT = ?  ";
			PreparedStatement ps1 = con.prepareStatement(qry1);		
			ps1.setInt(1, exist_cat);
			ResultSet rs1 = ps1.executeQuery();	
			if(rs1.next()) {
				if(exist_cat == 1) {
					if(new_cat == 2 || new_cat == 3 || new_cat == 4 || new_cat == 7)
						exist_dc = beforenafter.equals("AFTER")  ?  exist_load * rs1.getDouble(2) : exist_load*1000 ;
				}
				else if(exist_cat == 2 || exist_cat == 7) {
					if(new_cat == 1 || new_cat == 3 || new_cat == 4 || new_cat == 7)
						exist_dc = beforenafter.equals("AFTER")  ?  exist_load * rs1.getDouble(2) : exist_load*2000 ;					
				}
				else if(exist_cat == 5)
					exist_dc = exist_load * 900 ;
				else
					exist_dc = exist_load * rs1.getDouble(2) ;
				
				System.out.println(exist_load);
				System.out.println(exist_dc);
			}
			
			prm.setAf_pre("25.00");
			prm.setAfgst_pre("4.50");
			qry2 = "SELECT SEC_DEP, DEV_CH, DEV_CH_GST FROM INITIAL_PAYMT WHERE CAT = ?  ";
			PreparedStatement ps2 = con.prepareStatement(qry2);	
			ps2.setInt(1, new_cat);
			ResultSet rs2 = ps2.executeQuery();	
			if(rs2.next()) {
				new_sd = new_load * rs2.getDouble(1);
				new_dc = new_load * rs2.getDouble(2);
				
				bal_sd = (exist_sd > new_sd) ?  0.00 :  (new_sd - exist_sd);
				bal_dc = (exist_dc > new_dc) ?  0.00 :  (new_dc - exist_dc);
				
				System.out.println(new_dc);
				bal_dcgst = 0.18 * bal_dc;				
			}
			if(exist_cat == 8) {
				//String qry2 = "select CATCODE from newconnection_register where EBSFLAG = 'Y' and ALLOTEDUKSCNO = ? "; 
				String qry3 = "SELECT nvl(ctreleasecat,0) FROM corporate.all_consumer@ebsrodbl where ctukscno = ?";
				PreparedStatement ps3 = con.prepareStatement(qry3);		
				ps3.setString(1, ukscno);
				ResultSet rs3 = ps3.executeQuery();
				if(rs3.next()) {
					if(rs3.getString(1).equals("1") || rs3.getString(1).equals("2")) {
						bal_dc = 0;
						bal_dcgst  = 0;	
					}
				}
			}
			bal_tot = 29.5 + bal_sd + bal_dc + bal_dcgst;
			
			prm.setSd_pre(df.format(bal_sd));
			prm.setDc_pre(df.format(bal_dc));
			prm.setDcgst_pre(df.format(bal_dcgst));
			prm.setTot_pre(df.format(bal_tot));
			if(new_cat == 1 || new_cat == 2 || new_cat == 7 || new_cat == 8) {
				new_load = new_load * 1000 ;
			}
			prm.setNewload(df.format(new_load));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		//System.out.println(cn_Array);
		return prm;
	}
	
	public PreReqModel  getAppFeePayments(String cnature, String catcode) { //used for only insertCC
		PreReqModel prm = new PreReqModel();
		prm.setAf_pre("25.0");
    	prm.setAfgst_pre("4.5");
    	prm.setSd_pre("0.0");
    	prm.setDc_pre("0.0");
    	prm.setDcgst_pre("0.0");
    	prm.setTot_pre("29.5");
    	
    	if(cnature.equals("41") || cnature.equals("47") || cnature.equals("48")) {
    		if(!(catcode.equals("1") || catcode.equals("5"))) {
    			prm.setAf_pre("50.0");
    	    	prm.setAfgst_pre("9.0");
    	    	prm.setTot_pre("59.0");
    		}
    	}    	
		return prm;
	}
	
	public PreReqModel  getZeroPayments() {
		PreReqModel prm = new PreReqModel();
		prm.setAf_pre("0");
    	prm.setAfgst_pre("0");
    	prm.setSd_pre("0");
    	prm.setDc_pre("0");
    	prm.setDcgst_pre("0");
    	prm.setTot_pre("0");
    	
		return prm;
	}
	
	public CompNaturePaymentsModel getCnaturePayments(String cnature, String ctype, String catcode) {
		CSCDB db = new CSCDB();
		String qry = "";
		CompNaturePaymentsModel cnpm = new CompNaturePaymentsModel();
		cnpm.setIs_appfee("");
		cnpm.setIs_other_amount("");
		String[] cnature_list1 = {"41","47","45","48","62","64","65","70","78","81"};
		for(int i = 0; i < cnature_list1.length; i++) {
			if(cnature.equals(cnature_list1[i]) )
				cnpm.setIs_appfee("Y");
		}
		String[] cnature_list2 = {"53","54","24","59","76"};
		for(int i = 0; i < cnature_list2.length; i++) {
			if(cnature.equals(cnature_list2[i]) || (ctype.equals("4") && !cnature.equals("21") && !cnature.equals("23")) )
				cnpm.setIs_other_amount("Y");
		}
	
		try(Connection con = db.getConnection()){
			qry = "SELECT AMOUNT, OTH_CHARGES1, OTH_CGST, COMPLAINT_NATURE FROM COMPLAINT_NATURE WHERE RECORD_STATUS = 'ACTIVE' AND  ID = ? ";
			PreparedStatement ps = con.prepareStatement(qry);		
			ps.setString(1, cnature);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {
				cnpm.setCnature_desc(rs.getString(4));
				if(cnpm.getIs_appfee().equals("Y")) {					
					if(rs.getDouble(1) == 29.5) {
						cnpm.setCnature_af(25);
						cnpm.setCnature_afgst(4.5);
						cnpm.setCnature_sc(0);
						cnpm.setCnature_scgst(0);
					}
					if(rs.getDouble(1) == 59 ) {
						cnpm.setCnature_af(50);
						cnpm.setCnature_afgst(9);
						cnpm.setCnature_sc(0);
						cnpm.setCnature_scgst(0);
					}
					if(cnature.equals("41") || cnature.equals("47") || cnature.equals("48")) {
			    		if(!(catcode.equals("1") || catcode.equals("5"))) {
			    			cnpm.setCnature_af(50.0);
			    			cnpm.setCnature_afgst(9.0);
			    		}
					}
					cnpm.setCnature_total(cnpm.getCnature_af() + cnpm.getCnature_afgst() + cnpm.getCnature_sc() + cnpm.getCnature_scgst());					
				}
				if(cnpm.getIs_other_amount().equals("Y")) {
					cnpm.setCnature_af(0);
					cnpm.setCnature_afgst(0);
					cnpm.setCnature_sc(rs.getDouble(2));
					cnpm.setCnature_scgst(2*rs.getDouble(3));
					cnpm.setCnature_total(cnpm.getCnature_af() + cnpm.getCnature_afgst() + cnpm.getCnature_sc() + cnpm.getCnature_scgst());
					
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnpm;
	}

	public String getCtype(String cnature, int purpose) {
		CSCDB db = new CSCDB();
		String response = "";
		try(Connection con = db.getConnection()){
			String qry = "SELECT GROUP_ID, COMPLAINT_NATURE FROM COMPLAINT_NATURE WHERE RECORD_STATUS = 'ACTIVE' AND  ID = ? ";
			PreparedStatement ps = con.prepareStatement(qry);		
			ps.setString(1, cnature);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {
				if(purpose == 1)
					response = rs.getString(1);
				if(purpose == 2)
					response = rs.getString(2);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return response;
	}
	
	public MeterDetailsModel getMeterType(String ukscno){
		CSCDB db = new CSCDB();
		MeterDetailsModel mdm = new MeterDetailsModel();
		String qry = "",metertype="";		
		
		try(Connection con = db.getConnection()){
			qry = "SELECT nvl(CTNETMETERFLAG,0), nvl(MPTCTMTRFLAG,0), nvl(CTPREPAIDFLAG,0), nvl(MPTMTRSIDE_HT,0), nvl(CTMTRPHASE,0) "
					+ " FROM corporate.all_consumer@ebsrodbl A,CATEGORY_MASTER B,ALL_SECTION C WHERE A.CTSECCD=C.SECCD AND A.CTEROCD=C.EROCD AND A.CTCAT=B.ID "
					+ " AND B.htlt_flag = 'LT' AND A.CTUKSCNO= ? ";
			System.out.println(qry);
			PreparedStatement ps = con.prepareStatement(qry);		
			ps.setString(1, ukscno);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {
				if(rs.getString(1).equals("1")) {
					if(rs.getString(4).equals("1")) {
						mdm.setMetertype("NTH");
						mdm.setMetertype_desc("Net Meter (HT)");
						mdm.setMetertype_cnature("");
					}
					else if(rs.getString(2).equals("1")) {
						mdm.setMetertype("NTC");
						mdm.setMetertype_desc("Net Meter (CT-Meter)");
						mdm.setMetertype_cnature("95");
					}
					else {
						if(rs.getString(5).equals("1")) {
							mdm.setMetertype("NT1");
							mdm.setMetertype_desc("Net Meter (1-Phase)");
							mdm.setMetertype_cnature("93");
						}
						if(rs.getString(5).equals("3")) {
							mdm.setMetertype("NT3");
							mdm.setMetertype_desc("Net Meter (3-Phase)");
							mdm.setMetertype_cnature("94");
						}
					}
				}					
				else if(rs.getString(2).equals("1")) {
					mdm.setMetertype("CT");
					mdm.setMetertype_desc("CT-Meter");
					mdm.setMetertype_cnature("103");
				}
				else if(rs.getString(3).equals("1")){
					if(rs.getString(5).equals("1")) {
						mdm.setMetertype("PP1");
						mdm.setMetertype_desc("1-Phase PrePaid Meter");
						mdm.setMetertype_cnature("101");
					}
					else {
						mdm.setMetertype("PP2");
						mdm.setMetertype_desc("3-Phase PrePaid Meter");
						mdm.setMetertype_cnature("102");
					}
				}
				else if(rs.getString(4).equals("1")) {
					mdm.setMetertype("HT");
					mdm.setMetertype_desc("HT Meter");
					mdm.setMetertype_cnature(""); // condition yet to be added for HT meter types
				}
				else {
					if(rs.getString(5).equals("1")) {
						mdm.setMetertype("LT1");
						mdm.setMetertype_desc("1-Phase Meter");
						mdm.setMetertype_cnature("19");
					}
					else {
						mdm.setMetertype("LT3");
						mdm.setMetertype_desc("3-Phase Meter");
						mdm.setMetertype_cnature("22");
					}
				}						

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mdm ;
	}
	
	public String generateRegno() {
		CSCDB db = new CSCDB();
		String regno1="",paddingno="";
		java.util.Date now = new java.util.Date();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		String s1 = df.format(now);
		String [] curryear=s1.split("/");
		
		try(Connection con = db.getConnection()){
			String querySEQ="SELECT COMPL_GEN_SEQ.NEXTVAL FROM DUAL";
			PreparedStatement psSEQ = con.prepareStatement(querySEQ);		
			ResultSet rsSEQ=psSEQ.executeQuery();			
			if(rsSEQ.next())
			{
				regno1="" + rsSEQ.getInt(1);
		    }			
			paddingno=curryear[2]+leftPadding(regno1,6);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return paddingno ;
	}
	
	public String leftPadding(String str1,int x){
		String ipadding="";
		for(int i=1;i<=x-str1.length();i++)
		{
			ipadding+="0";
		}

		return (ipadding+str1);
	}
	
	public String removeNull(String str) {
		if(str == null) {
			str = "";
		}
		return str;	
	}
	
	public String getMetric(String cat, String subcat) {
		if(cat.equals("3") || cat.equals("4") || cat.equals("5")|| ( cat.equals("6")&& (subcat.equals("5")|| subcat.equals("6") || subcat.equals("9") || subcat.equals("12"))) )
			return "HP";
		else 
			return "Watts";
	}
	
	/*public String genPaymentsGanesh(String load) {
		CSCDB db = new CSCDB();
		PreReqModel prm = new PreReqModel();
		double af = 0.0, afgst = 0.0, secdp= 0.0, totamt=0.0;
	    double dc = 0.0, dcgst = 0.0;
	    double load1 = 0.00;
	    String amount = "";
		DecimalFormat df = new DecimalFormat("0.00"); 

		try(Connection con = db.getConnection()){
			String qry = "SELECT a.ctcat, a.ctsubcat, a.ctload FROM corporate.all_consumer@ebsrodbl A,CATEGORY_MASTER B,ALL_SECTION C "
					+ "WHERE A.CTSECCD=C.SECCD AND A.CTEROCD=C.EROCD AND A.CTCAT=B.ID AND B.htlt_flag = 'LT' AND A.CTUKSCNO='"+prima.getUkscno()+"' ";
			PreparedStatement ps = con.prepareStatement(qry);		
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {
				String qry1 = "SELECT APP_FEE, APP_FEE_GST, SEC_DEP, DEV_CH, DEV_CH_GST FROM INITIAL_PAYMT WHERE CAT = '"+rs.getString(1)+"' and "
						+ "	SUBCAT = '"+rs.getString(2)+"' ";
				PreparedStatement ps1 = con.prepareStatement(qry1);		
				ResultSet rs1 = ps1.executeQuery();
				if(rs1.next()) {
					if(rs.getString(1).equals("3") || rs.getString(1).equals("5")) {
						load = Double.parseDouble(prima.getReq_load_prereq());
					}else {
						if(prima.getReq_load_prereq() != null)
						load = Math.ceil(0.001*Double.parseDouble(prima.getReq_load_prereq()));
					}
					af = rs1.getDouble(1);
					afgst = rs1.getDouble(2);
					secdp = load*rs1.getDouble(3);				
					dc = load*rs1.getDouble(4);
					dcgst  = load*rs1.getDouble(5);				
					totamt = af + afgst + secdp + dc + dcgst ;
					
					if(prima.getExtension() != null) { 
						if(prima.getExtension().equals("Y")) {
							secdp = 0.00; dc = 0.00; dcgst = 0.00; totamt = af + afgst; 
						}
					}
					
					prm.setAf_pre(df.format(af));
					prm.setAfgst_pre(df.format(afgst));
					prm.setSd_pre(df.format(secdp));
					prm.setDc_pre(df.format(dc));
					prm.setDcgst_pre(df.format(dcgst));
					prm.setTot_pre(df.format(totamt));
					
				}
			}					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return amount;
	}*/
	
	/*public ArrayList<CnatureData>  getCantureList(String ctype) {
		CSCDB db = new CSCDB();
		
		ResultSet rs = null;
		String qry = "";
		ArrayList<CnatureData> cn_Array=new ArrayList<CnatureData>();
		try(Connection con = db.getConnection()){
			Statement stmt = con.createStatement();
			qry = "SELECT ID,COMPLAINT_NATURE FROM COMPLAINT_NATURE where RECORD_STATUS = 'ACTIVE' AND TYPE_ID = "+ctype+" ORDER BY COMPLAINT_NATURE";		
			rs=stmt.executeQuery(qry);
			while(rs.next()) {
				CnatureData cNature = new CnatureData();
				cNature.setCanture_id(rs.getString(1));
				cNature.setCnature_desc(rs.getString(2));
				cn_Array.add(cNature);			
				
			}				

		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(cn_Array);
		return cn_Array;
		
	}*/
}	

	
