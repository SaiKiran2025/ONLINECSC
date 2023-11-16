<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*,javax.mail.event.*,javax.mail.internet.*,javax.activation.*,java.sql.*,java.text.*, java.util.zip.CRC32"%>

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
		String title="";
		String regno=(String)session.getAttribute("regno");
		String appname=(String)session.getAttribute("appname");
		String category=(String)session.getAttribute("category");
		String contld=(String)session.getAttribute("contld");
		String metric=(String)session.getAttribute("metric");
		String regtype=(String)session.getAttribute("regtype");
		String actual_amount=(String)session.getAttribute("actual_amount");
		String paid_amount=(String)session.getAttribute("paid_amount");
		String totamt=(String)session.getAttribute("totamt");
		String sdpaid=(String)session.getAttribute("sdpaid");
		String bal=(String)session.getAttribute("bal");
		String estpmt=(String)session.getAttribute("estpmt");
		String temp=(String)session.getAttribute("trmsg");
		System.out.println("temp="+temp);
		if(estpmt.equals("Y")){
			title="Payment against Estimate";
		}
		else{
		if(regno.startsWith("NT"))
		title="Net Meter Registration Payment";
		else if(regno.startsWith("NR"))
		title="New Connection Payment";
		else
		{
			if(!regtype.equals("TT"))
			title="Load Extention/Category Change Payment";
			else
			title="Title transfer Payment";
		}
		}

%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" id="myhtml" lang="en">
<head profile="http://www.w3.org/2005/10/profile">
<title>TSSPDCL-New Service Connection Registration</title>
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
</script>
<script language=JavaScript>
<!--

function clickIE4(){
	if (event.button==2){
		alert(message);
		return false;
	}
}

function clickNS4(e){
	if (document.layers||document.getElementById&&!document.all){
		if (e.which==2||e.which==3){
			alert(message);
			return false;
		}
	}
}

if (document.layers){
	document.captureEvents(Event.MOUSEDOWN);
	document.onmousedown=clickNS4;
}
else if (document.all&&!document.getElementById){
	document.onmousedown=clickIE4;
}
document.oncontextmenu=new Function("return false")

// --> 
</script>

<script language="JavaScript">
var count=2;
function getAttach()
{
	addRow('attachtable');
}
function addRow(in_tbl_name)
{
	
	 if(document.getElementById("uploadfile2")==null&&document.getElementById("uploadfile3")==null)
		{
			count=2;		 
		}
	if(count<=3)
	{
	var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
	
	// create row
	var row = document.createElement("TR");
	
	// create table cell 1
	var td1 = document.createElement("<td width='700%' align='right'>");
	var strHtml1 = "&nbsp;"+count+".<input id='uploadfile"+count+"' type='file' name='uploadfile"+count+"'  size='40'/></td><br><a href='#' onClick='getDelete("+count+")'>Remove</a>";
	td1.innerHTML = strHtml1.replace(/!count!/g,count);

	// append data to row
	row.appendChild(td1);

	count=count+1;
	tbody.appendChild(row);
	}
}
function getDelete(j)
{
	var current = document.getElementById("uploadfile"+j);
	//here we will delete the line
	while ( (current = current.parentElement)  && current.tagName !="TR");
		current.parentElement.removeChild(current);
	
}
function getDisplay()
{
	document.getElementById("t1").style.display='';
	document.getElementById("t2").style.display='';
}
function getDispaly1()
{
	document.getElementById("t1").style.display='none';
	document.getElementById("t2").style.display='none';
}

/********************************************* Email Validation ***********************************/
var testresults

function checkemail(){
 var str=document.mail.from.value
 var filter=/^.+@.+\..{2,3}$/

 if (filter.test(str))
    testresults=true
 else {
    alert("Please input a valid email address!")
		document.mail.from.focus();
    testresults=false
}
 return (testresults)
}
function checkbae(){
if (document.layers||document.all)
return checkemail()
else
return true
}


function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
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
<div class="navbar"> <h3>LT - New Service Connection Registration</h3> </div>
    
<div class="tablehead">New Service Registration Additional Payment Details</div>

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
		<tr>
			<td align="left" nowrap>Category</td>
			<td align="left">&nbsp;<%=category%></td>
		</tr>
		<tr>
			<td align="left" nowrap>Contract Load</td>
			<td align="left">&nbsp;<%=contld%>&nbsp;<%=metric%></td>
		</tr>
		<tr>
			<td align="left">Total amount to be paid</td>
			<td align="left">&nbsp;<%=actual_amount%></td>
		</tr>
		<tr>
			<td align="left">Paid during Registration</td>
			<td align="left">&nbsp;<%=paid_amount%></td>
		</tr>
		<tr>
			<td align="left">Balance to be paid</td>
			<td align="left">&nbsp;<%=totamt%></td>
		</tr>

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
			<td align="left">III.&nbsp;&nbsp;&nbsp;I/We undertake that I am / We are the statutorily bonafide owner for the premises for which <%if(regno.startsWith("CC")&&regtype.equals("TT")){%>Title Transfer<%}else{%>new service connection<%}%> is applied for and agree to pay the Tariff and Miscellaneous charges, prescribed by the  Company and to abide by the General Terms and Conditions of Supply notified by the Company from time to time, which shall govern the supply of electricity to me/us in all respects.</td>
		</tr>
		<tr>
			<td align="left">IV.&nbsp;&nbsp;&nbsp;I/We undertake that all the required documents such as Ownership deed and necessary approvals will be submitted <%if(regno.startsWith("NR")||(regno.startsWith("CC")&&!regtype.equals("TT"))){%>at the time of fixing of meter (or)<%}%> as and when demanded by the TSSPDCL.</td>
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
     <button class="btn btn--primary" name="Send" value="Make Payment" id='cscsave' accesskey="s" onClick="return validate();">Proceed to Pay</button>
</div>
</form>


</body>

</html>
