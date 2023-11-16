package com.tsspdcl.onlinecsc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tsspdcl.onlinecsc.model.ADLServiceModel;
import com.tsspdcl.onlinecsc.model.AdditionalPaymentModel;
import com.tsspdcl.onlinecsc.model.ApplicationDataModel;
import com.tsspdcl.onlinecsc.model.AreaData;
import com.tsspdcl.onlinecsc.model.CategoryChangeModel;
import com.tsspdcl.onlinecsc.model.CircleListModel;
import com.tsspdcl.onlinecsc.model.CnatureData;
import com.tsspdcl.onlinecsc.model.CompNaturePaymentsModel;
import com.tsspdcl.onlinecsc.model.EbsResponseModel;
import com.tsspdcl.onlinecsc.model.GovtCodeModel;
import com.tsspdcl.onlinecsc.model.HodCodeModel;
import com.tsspdcl.onlinecsc.model.MeterDetailsModel;
import com.tsspdcl.onlinecsc.model.MunicipalCodeModel;
import com.tsspdcl.onlinecsc.model.PreReqInputModel;
import com.tsspdcl.onlinecsc.model.PreReqInputModelAdl;
import com.tsspdcl.onlinecsc.model.PreReqLTMModel;
import com.tsspdcl.onlinecsc.model.PreReqModel;
import com.tsspdcl.onlinecsc.model.SectionListModel;
import com.tsspdcl.onlinecsc.model.SubCatModel;
import com.tsspdcl.onlinecsc.model.SupplyNatureModel;
import com.tsspdcl.onlinecsc.services.AdlPayUpdate;
import com.tsspdcl.onlinecsc.services.CSCDB;
import com.tsspdcl.onlinecsc.services.DBMethods;
import com.tsspdcl.onlinecsc.services.DBMethodsComplaints;
import com.tsspdcl.onlinecsc.services.DocumentsReUpload;
import com.tsspdcl.onlinecsc.services.SelectNR;
import com.tsspdcl.onlinecsc.services.VerifyGstNo;

@Controller
@RequestMapping(path = "")
public class MainController {
	final static Logger logger = Logger.getLogger(MainController.class);
	//private static String otp;

	@GetMapping("")
	public String login(Model model) {
		model.addAttribute("paytype", "online");
		return "loginNR";
	}
	@GetMapping("ltm")
	public String loginLTM() {
		return "loginLTM";
	}
	@GetMapping("AL")
	public String loginAdl() {
		return "loginAL";
	}
	@GetMapping("CC")
	public String loginComplaints() {
		return "loginCC";
	}
	@GetMapping("/addpayment")
	public String getAddPayment() {
		return "loginAdlPay";
	}
	@GetMapping("/error")
	public String get99() {
		return "error";
	}
	@PostMapping("/regNR")
	public String newreg(@RequestParam String mobileno, @RequestParam String paytype, Model model) {
		model.addAttribute("mobileno", mobileno);
		model.addAttribute("paytype", paytype);
		return "entryNR";
	}
	@PostMapping("/regLTM")
	public String newLTMreg(@RequestParam String mobileno, @RequestParam String reg_type,Model model) {
		model.addAttribute("mobileno", mobileno);
		model.addAttribute("reg_type", reg_type);
		if(reg_type.equals("N")) {
			model.addAttribute("type","Apartment");
		}else {
			model.addAttribute("type","Layout");
		}
		return "entryLTM";
	}
	@PostMapping("/regAL")
	public String newAdlreg(@RequestParam String uscno_initial, @RequestParam String mobileno, Model model) {
		model.addAttribute("ukscno", uscno_initial);
		model.addAttribute("mobileno", mobileno);
		return "entryAL";
	}
	@PostMapping("/regCC")
	public String newCCreg(@RequestParam String uscno_initial, @RequestParam String mobileno, Model model) {
		model.addAttribute("ukscno", uscno_initial);
		model.addAttribute("mobileno", mobileno);
		model.addAttribute("cnature", "");
		model.addAttribute("ctype", "");	
		model.addAttribute("load", "");
		model.addAttribute("catcode", "");
		model.addAttribute("category", "");
		DBMethodsComplaints dbmc = new DBMethodsComplaints();
		ADLServiceModel asm = new ADLServiceModel();
		asm = dbmc.getServiceData(uscno_initial, "final"); 
		model.addAttribute("EbsData", asm);
		return "entryCC2";
	}
	
	@PostMapping("/showCC")
	public String showCCreg1(@RequestParam String xukscno, @RequestParam String xmobileno, @RequestParam String cnature, Model model) {
		DBMethodsComplaints dbmc = new DBMethodsComplaints();
		CompNaturePaymentsModel cnpm = new CompNaturePaymentsModel();
		ADLServiceModel asm = new ADLServiceModel();
		MeterDetailsModel mdm = new MeterDetailsModel();
		
		model.addAttribute("ukscno", xukscno);
		model.addAttribute("mobileno", xmobileno);
		asm = dbmc.getServiceData(xukscno, "final");
		asm = dbmc.checkComplaint(asm, cnature, xukscno);// to check any existing complaint condition
		/*System.out.println( asm.getIs_complaint_exist());
		System.out.println( asm.getIs_billstop());
		System.out.println( asm.getIs_mats_case());*/
		model.addAttribute("EbsData", asm);
	
		String screen="", ctype="", meter_type="" ;
		String[] cnature_list = {"47","41","42","40","79","69","48"}; // "48","62","70", 69 is clubbing of services
		for(int i = 0; i < cnature_list.length; i++) {
			if(cnature.equals(cnature_list[i]))
				screen = "1";
		}
		
		if(cnature.equals("MB") || cnature.equals("MTC") ) {
			mdm = dbmc.getMeterType(xukscno);
			meter_type = mdm.getMetertype_desc();
			if(cnature.equals("MB")){
				mdm.setMetertype_ctype("MB");
				cnature = mdm.getMetertype_cnature();
			}
			if(cnature.equals("MTC")){
				mdm.setMetertype_ctype("MTC");
				if(meter_type.equals("1-Phase Meter")) cnature = "58";
				else if(meter_type.equals("3-Phase Meter")) cnature = "61";
				else if(meter_type.equals("CT-Meter"))  cnature = "82";
				else if(meter_type.equals("HT Meter"))  cnature = "83";
				else {
					return "entryCC3";
				}
			}
			model.addAttribute("mdm", mdm);			
		}

		ctype = dbmc.getCtype(cnature,1);
		cnpm = dbmc.getCnaturePayments(cnature, ctype, asm.getCatcode());
		model.addAttribute("cnpm", cnpm);
		model.addAttribute("cnature", cnature);	
		model.addAttribute("ctype", ctype);	
		System.out.println(cnpm);
		if(screen.equals("1") )
			return "entryCC2";
		else
			return "entryCC3";
	}
	
	/*@PostMapping("/showCC1")
	public String showCCreg(@RequestParam String xukscno, @RequestParam String ctype, @RequestParam String xmobileno, @RequestParam String cnature, Model model) {
		model.addAttribute("ukscno", xukscno);
		model.addAttribute("mobileno", xmobileno);
		model.addAttribute("ctype", ctype);
		model.addAttribute("cnature", cnature);
		
		DBMethodsComplaints dbmc = new DBMethodsComplaints();
		CompNaturePaymentsModel cnpm = new CompNaturePaymentsModel();
		cnpm = dbmc.getCnaturePayments(cnature, ctype);
		model.addAttribute("cnpm", cnpm);
		System.out.println(cnpm);
		
		if(ctype.equals("1") || ctype.equals("5") || (ctype.equals("7") && !cnature.equals("41") ) || ctype.equals("8"))
			return "entryCC2";
		else
			return "entryCC2";
	}*/

	@GetMapping("/invalidReg")
	public String get2(Model model) {
		model.addAttribute("invalid", "Invalid Registration Number");
		return "login";
	}

	@GetMapping(path = "/checkLoginRegno/{login_regno}")
	public ResponseEntity<String> checkLoginRegData(@PathVariable("login_regno") String login_regno) {

		String resp = "";
		DBMethods dbm = new DBMethods();
		String mbl = dbm.checkReg(login_regno);
		if (!mbl.equals("")) {
			//otp = dbm.genOTP(mbl);
			resp = "******" + mbl.substring(6, 10);
			return new ResponseEntity<String>(resp, HttpStatus.OK);
		} else {
			resp = "";
			return new ResponseEntity<String>(resp, HttpStatus.OK);
		}

	}

	@GetMapping("/getOTP/{mobileno}")
	public ResponseEntity<String> getOTPMsg(@PathVariable("mobileno") String mobileno) {	
		DBMethods dbm = new DBMethods();
		String otp = dbm.genOTP(mobileno);
		if (!otp.equals("")) {
			return new ResponseEntity<String>(otp, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("BBQEX", HttpStatus.OK);
		}
	}

	/*@GetMapping("/validateOTP/{cotp}")
	public ResponseEntity<String> validateOTP(@PathVariable("cotp") String cotp) {

		if (cotp.equals(otp)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("failed", HttpStatus.OK);
		}

	}*/

	// Dummy
	@GetMapping("/entry")
	public String get4() {
		return "entry";
	}

	@GetMapping("/modal")
	public String modal() {
		return "modal";
	}

	/*
	 * @RequestMapping(value = "services", method = RequestMethod.GET) public
	 * ModelAndView OnlineCSCServices(HttpServletRequest request,
	 * HttpServletResponse response) { ModelAndView mav = new
	 * ModelAndView("services"); return mav; }
	 */

	@RequestMapping(value = "/services", method = RequestMethod.POST) 
	public String OnlineCSCServices(@RequestParam String mobileno, Model model) { 
		 model.addAttribute("mobileno", mobileno); 
		 return "services"; 
	}
	
	
	@GetMapping("/getService/{ukscno}/{scope}")
	public ResponseEntity<ADLServiceModel> getService(@PathVariable String ukscno, @PathVariable String scope) {
		DBMethodsComplaints dbmc = new DBMethodsComplaints();
		return new ResponseEntity<ADLServiceModel>(dbmc.getServiceData(ukscno,scope), HttpStatus.OK);
	}
	
	@PostMapping("/getPreReq")
	public ResponseEntity<PreReqModel> getPre(@RequestBody PreReqInputModel prim) {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<PreReqModel>(dbm.genPreReq(prim), HttpStatus.OK);
	}
	
	@PostMapping("/getPreReqAdl")
	public ResponseEntity<PreReqModel> getPreAdl(@RequestBody PreReqInputModelAdl prima) {
		DBMethodsComplaints dbmc = new DBMethodsComplaints();
		return new ResponseEntity<PreReqModel>(dbmc.genPreReqAdl(prima), HttpStatus.OK);
	}
	
	@PostMapping("/getLTMPreReq")
	public ResponseEntity<PreReqLTMModel> getLTMPre(@RequestBody PreReqLTMModel prlm) {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<PreReqLTMModel>(dbm.genLTMPreReq(prlm), HttpStatus.OK);
	}
	
	@GetMapping("/checkUscno/{uscno}")
	public ResponseEntity<EbsResponseModel> checkUscData(@PathVariable("uscno") String uscno) {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<EbsResponseModel>(dbm.checkUscno(uscno), HttpStatus.OK);
	}

	// Additional Payment
	@GetMapping("/checkNrno/{nrno}")
	public ResponseEntity<AdditionalPaymentModel> checkNRData(@PathVariable("nrno") String nrno) {
		//System.out.println("In Controller");
		AdlPayUpdate dbm = new AdlPayUpdate();
		return new ResponseEntity<AdditionalPaymentModel>(dbm.checkNrno(nrno), HttpStatus.OK);
	}

	@GetMapping("/getAreaName1/{uscno}/{area_code}")
	public ResponseEntity<AreaData> getAreacd(@PathVariable("uscno") String uscno,
			@PathVariable("area_code") String area_code) {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<AreaData>(dbm.getAreaName1(uscno, area_code), HttpStatus.OK);
	}

	@GetMapping("/getCircleList")
	public ResponseEntity<ArrayList<CircleListModel>> getCircleData() {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<ArrayList<CircleListModel>>(dbm.getCircleList(), HttpStatus.OK);
	}

	@GetMapping("/getSectionList/{circle_id}")
	public ResponseEntity<ArrayList<SectionListModel>> getSectionData(@PathVariable String circle_id) {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<ArrayList<SectionListModel>>(dbm.getSectionList(circle_id), HttpStatus.OK);
	}

	@GetMapping("/getAreaList/{section_id}")
	public ResponseEntity<EbsResponseModel> getAreaData(@PathVariable String section_id) {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<EbsResponseModel>(dbm.getAreaList(section_id), HttpStatus.OK);
	}

	@GetMapping("/getAreaName2/{area_code}/{section_code}")
	public ResponseEntity<AreaData> getAreaName2(@PathVariable String area_code, @PathVariable String section_code) {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<AreaData>(dbm.getAreaName2(area_code, section_code), HttpStatus.OK);
	}
	
	@GetMapping("/getCscNo/{section_code}")
	public ResponseEntity<String> getCscNum(@PathVariable("section_code") String section_code) {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<String>(dbm.getCscNo(section_code), HttpStatus.OK);
	}
	
	@GetMapping("/getSubCat/{category}")
	public ResponseEntity<?> getSubCatList(@PathVariable("category") String category) {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<ArrayList<SubCatModel>>(dbm.genSubCategory(category), HttpStatus.OK);
	}

	@GetMapping("/getMcode")
	public ResponseEntity<?> getMunicipalCode() {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<ArrayList<MunicipalCodeModel>>(dbm.genMunicipalCode(), HttpStatus.OK);
	}

	@GetMapping("/getGov")
	public ResponseEntity<?> getGovCodeList() {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<ArrayList<GovtCodeModel>>(dbm.genGovtList(), HttpStatus.OK);

	}

	@GetMapping("/getHod/{govtcd}")
	public ResponseEntity<?> getHodCodeList(@PathVariable("govtcd") String govtcd) {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<ArrayList<HodCodeModel>>(dbm.genHodList(govtcd), HttpStatus.OK);
	}

	@GetMapping("/getSupNat")
	public ResponseEntity<?> getSupplyNature() {
		DBMethods dbm = new DBMethods();
		return new ResponseEntity<ArrayList<SupplyNatureModel>>(dbm.genSupplyNature(), HttpStatus.OK);

	}

	@PostMapping("/Modify")
	public String getEntryPage() {
		return "entry";
	}

	@PostMapping("/Modify1")
	public String getModifyPage(@RequestParam("login_regno") String login_regno, HttpServletRequest request,
			Model model) {
		ApplicationDataModel adm = new ApplicationDataModel();
		SelectNR snr = new SelectNR();
		adm = snr.select(login_regno);
		System.out.println(adm);
		model.addAttribute("adm", adm);
		return "entry1";
	}

	@GetMapping(value = "/verifyGSTNO/{gstno}")
	public ResponseEntity<String> verifyGstNo(@PathVariable("gstno") String gstno) throws SQLException {
		CSCDB db = new CSCDB();
		Connection con = db.getConnection();
		VerifyGstNo vgst = new VerifyGstNo();
		String result = vgst.getVerifyStat(con, gstno);

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/getDocument/{regno}/{code}")
	public void getDocument(HttpServletResponse response, @PathVariable("regno") String regno,
			@PathVariable("code") String code) throws Exception {
		if (code.equals("sdeed") || code.equals("iproof"))
			response.setContentType("application/pdf");
		if (code.equals("aphoto") || code.equals("lphoto"))
			response.setContentType("image/jpg");
		SelectNR snr = new SelectNR();
		InputStream inputStream = snr.selectFile(regno, code);
		if (inputStream != null)
			IOUtils.copy(inputStream, response.getOutputStream());
	}

	@GetMapping(value = "/getBlobPhoto/{id}")
	public ResponseEntity<Blob> getBlobPhoto(HttpServletResponse response, @PathVariable("id") String id)
			throws Exception {
		SelectNR snr = new SelectNR();
		Blob b = snr.selectFile1(id);
		return new ResponseEntity<Blob>(b, HttpStatus.OK);
	}

	/*@GetMapping("/newLTMreg1")
	public String newLTMreg() {
		//model.addAttribute("mobileno", mobileno);
		return "newLTMregistration";
	}*/
	
	/*@GetMapping("/getCnatureList/{ctype}")
	public ResponseEntity<ArrayList<CnatureData>> getCnatureData(@PathVariable String ctype) {
		DBMethodsComplaints dbmc = new DBMethodsComplaints();
		return new ResponseEntity<ArrayList<CnatureData>>(dbmc.getCantureList(ctype), HttpStatus.OK);
	}*/
	
	@GetMapping("/getCatChange/{exist_cat}")
	public ResponseEntity<List<CategoryChangeModel>> getCatChange(@PathVariable String exist_cat) {
		DBMethodsComplaints dbmc = new DBMethodsComplaints();
		return new ResponseEntity<List<CategoryChangeModel>>(dbmc.getCatChangeList(exist_cat), HttpStatus.OK);
	}
	
	@GetMapping(value = "/IndemnityBond Format",produces= MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getTermsConditions() throws Exception {

        String filePath = "E:\\OnlineCSC_Formats\\";
        String fileName = "IndemnityBond.pdf";
        File file = new File(filePath+fileName);
        HttpHeaders headers = new HttpHeaders();
        //headers.add("content-disposition", "attachment;filename=" +fileName); for download
        headers.add("Content-Disposition", "inline; filename=" + fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }
	
	@GetMapping("/reUpload/{regno}")
	public String reUploadDocuments(@PathVariable String regno, Model model) {
		DocumentsReUpload dru = new DocumentsReUpload();
		model.addAttribute("regno", regno);
		
		ArrayList<String> doc_type = dru.getDocType(regno);
		if(doc_type.contains("3"))
			model.addAttribute("idproof", "Y");
		if(doc_type.contains("5"))
			model.addAttribute("saledeed", "Y");
		if(doc_type.contains("8"))
			model.addAttribute("occupancy", "Y");
		if(doc_type.contains("19"))
			model.addAttribute("approvalplan", "Y");
		if(doc_type.contains("7"))
			model.addAttribute("ibond", "Y");
		
		model.addAttribute("upload_status", "pending");
		if(doc_type.size() == 0) {
			model.addAttribute("upload_status", "invalid");
		}
		model.addAttribute("name", dru.applicantDetails(regno));
		return "reUploadDocs";
	}
	
	@GetMapping("/getCatChangePayments/{new_cat}/{ukscno}")
	public ResponseEntity<PreReqModel> getCatChangePayments(@PathVariable int new_cat, @PathVariable String ukscno) {
		DBMethodsComplaints dbmc = new DBMethodsComplaints();
		return new ResponseEntity<PreReqModel>(dbmc.getCatChangePayments(new_cat, ukscno), HttpStatus.OK);
	}
	
	
	@GetMapping("ganesh")
	public String loginGanesh() {
		return "loginGanesh";
	}
	
	@PostMapping("/regGanesh")
	public String newregGanesh(@RequestParam String mobileno, Model model) {
		model.addAttribute("mobileno", mobileno);
		return "entryGanesh";
	}
	
	
}
