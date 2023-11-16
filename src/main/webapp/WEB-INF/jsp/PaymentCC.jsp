<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*,javax.mail.event.*,javax.mail.internet.*,javax.activation.*,java.sql.*,java.text.*, java.util.zip.CRC32"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="stag" %>

<%!
	String removeNull(String st)
	{
		if(st==null)
			return "";
		else
			return st;
	}
%>
<%! 
	String leftPadding(String str1,int x)
	{
		String ipadding="";
		for(int i=1;i<=x-str1.length();i++)
		{
			ipadding+="0";
		}

		return (ipadding+str1);
	}
%>
<%
		String header_title=(String)session.getAttribute("header_title");
		String regno=(String)session.getAttribute("regno");
		String appname=(String)session.getAttribute("appname");
		String category=(String)session.getAttribute("category");
		String contld=(String)session.getAttribute("contld");
		String reqld=(String)session.getAttribute("reqld");
		String totld=(String)session.getAttribute("totld");
		String metric=(String)session.getAttribute("metric");
		String regtype=(String)session.getAttribute("regtype");
		String appfee=(String)session.getAttribute("appfee");
		String gst=(String)session.getAttribute("gst");
		String devchg=(String)session.getAttribute("devchg");
		String secdp=(String)session.getAttribute("secdp");
		String totamt=(String)session.getAttribute("totamt");
		String sdpaid=(String)session.getAttribute("sdpaid");
		String bal=(String)session.getAttribute("bal");
		String estpmt=(String)session.getAttribute("estpmt");
		String temp=(String)session.getAttribute("trmsg");
		String appfee_gst=(String)session.getAttribute("appfee_gst");
		String devchg_gst=(String)session.getAttribute("devchg_gst");
		
		String addr1=(String)session.getAttribute("addr1");
		String addr2=(String)session.getAttribute("addr2");
		String addr3=(String)session.getAttribute("addr3");
		String addr4=(String)session.getAttribute("addr4");//address correction 
		
		String cnature=(String)session.getAttribute("cnature");
		String new_cat=(String)session.getAttribute("new_cat");//category change
		
		String new_title=(String)session.getAttribute("new_title");//title transfer
		
		String club_ukscnos=(String)session.getAttribute("club_ukscnos");
		String club_load=(String)session.getAttribute("club_load"); //CLubbing of services
		
		String newload_name="";
		if(cnature.equals("40")){
			newload_name = "Additional Load Requested";
		}
		if(cnature.equals("79")){
			newload_name = "Derated Load Requested";
		}

%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" id="myhtml" lang="en">
<head profile="http://www.w3.org/2005/10/profile">
<title>TSSPDCL-Apartment Service Connection Registration</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<meta name="abstract" content="" />
<link rel="shortcut icon" href="favicon.ico" />
<link rel="preconnect" href="https://fonts.googleapis.com"/>
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500&display=swap" rel="stylesheet"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylepm.css"/>


<script type="text/javascript">
	$(window).load(function(){autoScroller('vmarquee')});
	
	function validate()
	{
		document.getElementById("cscsave").disabled=true;
		document.frm.action="https://pgi.billdesk.com/pgidsk/PGIMerchantPayment";
		document.frm.submit();
	}
</script>
	
</head>
<body lang="en" id="body">
<div class="topbar"></div>
<div class="navbar"> <h3>Registration for <%=header_title%></h3> </div>
 
<div class="tablehead">Payment Details</div>

<form name="frm" method=post>
<input type='hidden' name='msg' value='<%=temp%>'/>
<div align="center" style="max-width : 1140px; margin:auto">
<table border="0" cellspacing="0" align="center"  style="border-collapse: collapse" >
<tr>
	<td>
	<div align="center">
		<table width="99%" border="1" cellspacing='0' cellpadding='5' class='input-table'>		
			<tr>
				<td align="left" width='50%' nowrap>Registration No</td>
				<td align="left">&nbsp;<%=regno%></td>
			</tr>
			<tr>
				<td align="left" nowrap>Applicant Name</td>
				<td align="left">&nbsp;<%=appname%></td>
			</tr>
			<%if(cnature.equals("40") || cnature.equals("79")){%>
				<tr>
					<td align="left" nowrap>Category</td>
					<td align="left">&nbsp;<%=category%></td>
				</tr>
				<tr>
					<td align="left" nowrap>Existing Load</td>
					<td align="left">&nbsp;<%=contld%>&nbsp;<%=metric%></td>
				</tr>
				<tr>
					<td align="left" nowrap><%=newload_name%></td>
					<td align="left">&nbsp;<%=reqld%>&nbsp;<%=metric%></td>
				</tr>
				<tr>
					<td align="left" nowrap>Total Load</td>
					<td align="left">&nbsp;<%=totld%>&nbsp;<%=metric%></td>
				</tr>
			<%}%>
			<%if(cnature.equals("41")){%>
				<tr>
					<td align="left" nowrap>Existing Address</td>
					<td align="left">&nbsp;<%=addr1%>,&nbsp;<%=addr2%>,&nbsp;<%=addr3%>,&nbsp;<%=addr4%></td>
				</tr>
				<tr>
					<td align="left" nowrap>New Address</td>
					<td align="left">&nbsp;${AppData.naddress1},&nbsp;${AppData.naddress2},&nbsp;${AppData.naddress3},&nbsp;${AppData.naddress4}</td>
				</tr>
			<%}%>
			<%if(cnature.equals("42")){%>
				<tr>
					<td align="left" nowrap>Existing Category</td>
					<td align="left">&nbsp;<%=category%></td>
				</tr>
				<tr>
					<td align="left" nowrap>Existing Load</td>
					<td align="left">&nbsp;<%=contld%>&nbsp;<%=metric%></td>
				</tr>
				<tr>
					<td align="left" nowrap>Required Category</td>
					<td align="left">&nbsp;<%=new_cat%></td>
				</tr>			
			<%}%>
			<%if(cnature.equals("47")){%>
				<tr>
					<td align="left" nowrap>Transferring title to</td>
					<td align="left">&nbsp;<%=new_title%></td>
				</tr>
			<%}%>
			<%if(cnature.equals("69")){%>
				<tr>
					<td align="left" nowrap>To be Clubbed Services</td>
					<td align="left">&nbsp;<%=club_ukscnos%></td>
				</tr>
				<tr>
					<td align="left" nowrap>Total Clubbed Load</td>
					<td align="left">&nbsp;<%=club_load%>&nbsp;<%=metric%></td>
				</tr>
			<%}%>
			<tr>
				<td align="left" nowrap>Complaint Type</td>
				<td align="left">&nbsp;<%=header_title%></td>
			</tr>
			
			<%if(cnature.equals("40") || cnature.equals("42") || cnature.equals("69") ){%>
				<tr>
					<td align="left">Security Deposit</td>
					<td align="left">&nbsp;&#8377; <%=secdp%></td>
				</tr>
				<tr>
					<td align="left">Development Charges</td>
					<td align="left">&nbsp;&#8377; <%=devchg%></td>
				</tr>
				<tr>
					<td align="left">GST on Development Charges</td>
					<td align="left">&nbsp;&#8377; <%=devchg_gst%></td>
				</tr>
			<%}%>
			
			<%if(!cnature.equals("79")){%>
			<tr>
				<td align="left" nowrap>Application Fee</td>
				<td align="left">&nbsp;&#8377; <%=appfee%></td>
			</tr>			
			<tr>
				<td align="left" nowrap>GST on Application Fee</td>
				<td align="left">&nbsp;&#8377; <%=appfee_gst%></td>
			</tr>
			<tr style="font-weight:bold">
				<td align="left">Total</td>
				<td align="left">&nbsp;&#8377; <%=totamt%></td>
			</tr>
			<%}%>
		<tr>
			<td width="100%" colspan="2" align="left"><u>Undertaking:</u><br>
				<div id='undtk'>
				<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#99CCFF" width="100%">
			<tr>
			<td align="left">I.&nbsp;&nbsp;&nbsp;I/We  agree that  TSSPDCL assume no liability whatsoever for any monetary or other damage suffered by me/us on account of: (i) the delay, failure, interruption, or corruption of any data or other information transmitted in connection with use of the Payment Gateway or Services in connection thereto; and/ or (ii) any interruption or errors in the operation of the Payment Gateway.</td>
		</tr>
		<tr>
			<td align="left">II.&nbsp;&nbsp;&nbsp;I/We undertake and agree that No claims for refund/transfer shall be made and in the event such claim is made it shall not be entertained.</td>
		</tr>
		<%if(regno.startsWith("NR")||regno.startsWith("CC")||regno.startsWith("AL")){%>
		<tr>
			<td align="left">III.&nbsp;&nbsp;&nbsp;I/We undertake that I am / We are the statutorily bonafide owner for the premises for which <%if(regno.startsWith("CC")&&cnature.equals("47")){%>Title Transfer<%}else{%>new service connection<%}%> is applied for and agree to pay the Tariff and Miscellaneous charges, prescribed by the  Company and to abide by the General Terms and Conditions of Supply notified by the Company from time to time, which shall govern the supply of electricity to me/us in all respects.</td>
		</tr>
		<tr>
			<td align="left">IV.&nbsp;&nbsp;&nbsp;I/We undertake that all the required documents such as Ownership deed and necessary approvals will be submitted <%if(regno.startsWith("NR")||(regno.startsWith("CC")&&!cnature.equals("47"))){%>at the time of fixing of meter (or)<%}%> as and when demanded by the TSSPDCL.</td>
		</tr>
		<%}else{%>
		<tr>
			<td align="left">III.&nbsp;&nbsp;&nbsp;I/We undertake that I am / We are the statutorily bonafide owner for the premises for which Net Meter connection is applied for and agree to pay the Tariff and Miscellaneous charges, prescribed by the Company and to abide by the General Terms and Conditions of Supply notified by the Company from time to time, which shall govern the supply of electricity to me/us in all respects.</td>
		</tr>
		<tr>
			<td align="left">IV.&nbsp;&nbsp;&nbsp;I/We undertake that all the required documents such as Ownership deed, Insuranceand and necessary approvals will be submitted as and when demanded by the TSSPDCL.</td>
		</tr>
		<%}%>
		<tr>
			<td align="left">V.&nbsp;&nbsp;&nbsp;I/We shall undertake to execute an Agreement in the prescribed form, if so called upon by the Company. Whether such an Agreement is executed or not, this acknowledgement itself shall oblige me/us to confirmed abide by the General Terms and Conditions of Supply notified by the Company from time to time.</td>
		</tr>
		<tr>
			<td align="left">VI.&nbsp;&nbsp;&nbsp;I/We certify that I have no dues to the Company either here or anywhere.</td>
		</tr>
		<tr>
			<td align="left">VII.&nbsp;&nbsp;&nbsp;I/We certify that there is no case of Theft of Electricity/Unauthorized use of Electricity, pending against me.</td>
		</tr>
		<%if(regno.startsWith("NR")||regno.startsWith("CC")||regno.startsWith("AL")){%>
		<tr>
			<td align="left">VIII.&nbsp;&nbsp;&nbsp;I/We undertake that if the number of services in the same premises/compound is above four, I will apply for the panel board.</td>
		</tr>
		<tr>
			<td align="left">IX.&nbsp;&nbsp;&nbsp;I/We undertake and agree for recovery from the excess paid amounts, if any against services found to be under disconnection or services with or Unauthorized use of Electricity cases or Theft of Electricity cases found, and also against services clubbed for being in the same premises.</td>
		</tr>
		<tr>
			<td align="left">X.&nbsp;&nbsp;&nbsp;I/We undertake to provide suitable arrangements for "way leave" at my/our own cost.</td>
		</tr>
		<tr>
			<td align="left">XI.&nbsp;&nbsp;&nbsp;I/We request to company to provide meter for measuring the electricity supplied to me/us. I/We will pay the monthly meter rentals, as may be fixed by the Commission from the time to time.</td>
		</tr>
		<tr>
			<td align="left">XII.&nbsp;&nbsp;&nbsp;I/We undertake that perfect earthing is done as advised by the TSSPDCL.</td>
		</tr>
		<tr>
			<td align="left">XIII.&nbsp;&nbsp;&nbsp;I/ We undertake that Meter can be installed outside the house/ Verandah in the Ground Floor and I/We shall provide the visible service wiring upto the metering point without concealed wiring.</td>
		</tr>
		<tr>
			<td align="left">XIV.&nbsp;&nbsp;&nbsp;I/ We undertake to pay the required amounts, if any extension of LT/HT network is required. Further, I/We will take up the work .</td>
		</tr>
		<tr>
			<td align="left">
				<table width='100%' border='0' cellpadding="0" cellspacing="0">
					<tr>
						<td width='25%' align='right'><table width='50%' border='1' cellpadding="0" cellspacing="0"><tr><td>&nbsp</td></tr></table></td>
						
						<td width='25%' align='left'>Through Department</td>
						<td width='25%' align='right'><table width='50%' border='1' cellpadding="0" cellspacing="0"><tr><td>&nbsp</td></tr></table></td>
						<td align='left'>On Turnkey</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td align="left">XV.&nbsp;&nbsp;&nbsp;I/We authorize TSSPDCL to provide information about the progress of application through SMS to my cell phone from time to time.</td>
		</tr>
		<%}else{%>
		<tr>
			<td align="left">VIII.&nbsp;&nbsp;&nbsp;I/We undertake and agree for recovery from the excess paid amounts, if any against services found to be under disconnection or services with or Unauthorized use of Electricity cases or Theft of Electricity cases found, and also against services clubbed for being in the same premises.</td>
		</tr>
		<tr>
			<td align="left">IX.&nbsp;&nbsp;&nbsp;I/We undertake to provide suitable arrangements for "way leave" at my/our own cost.</td>
		</tr>
		<tr>
			<td align="left">X.&nbsp;&nbsp;&nbsp;I/We request to company to provide net meter for measuring the electricity supplied to/by me/us. I/We will pay the monthly meter rentals, as may be fixed by the Commission from the time to time.</td>
		</tr>
		<tr>
			<td align="left">XI.&nbsp;&nbsp;&nbsp;I/We undertake that perfect earthing was done as advised by the TSSPDCL.</td>
		</tr>
		<tr>
			<td align="left">XII.&nbsp;&nbsp;&nbsp;I/ We undertake that Meter can be installed outside the house/ Verandah in the Ground Floor and I shall provide the visible service wiring upto the metering point without concealed wiring.</td>
		</tr>
		<tr>
			<td align="left">XIII.&nbsp;&nbsp;&nbsp;I/ We agree that if on inspection, at the time of release or any periodic inspection thereafter, non-IEC/ISI/BIS certified equipment is found to be part of net metering solution TSSPDCL forth with disconnect the service.</td>
		</tr>
		<tr>
			<td align="left">XIV.&nbsp;&nbsp;&nbsp;I/ We undertake to pay the required amounts, if any extension of LT/HT network is required. Further, I will take up the work On Turnkey and ensure Vendor executing work should be channel partner of MNRE.</td>
		</tr>
		<tr>
			<td align="left">XV.&nbsp;&nbsp;&nbsp;I/ We authorize TSSPDCL to provide information about the progress of application through SMS to my cell phone from time to time.</td>
		</tr>
		<%}%>
		
		</table>
	</td>
</tr>

</table>
</div>
</td>
</tr>
</table>
</div>
<div class="divButton">
	 <%if(cnature.equals("79")){%>
			<input type="button" name="Submit" value="Print"  accesskey='s'  onClick="window.print()" >
     <%}else{%>
     <button class="btn btn--primary" name="Send" value="Make Payment" id='cscsave' accesskey="s" onClick="return validate();">Proceed to Pay</button>
     <%}%>
</div>
</form>


</body>

</html>
