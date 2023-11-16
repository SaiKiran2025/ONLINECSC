<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*,javax.mail.event.*,javax.mail.internet.*,javax.activation.*,java.sql.*,java.text.*, java.util.zip.CRC32"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="stag" %>
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
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>TSSPDCL-New Service Connection Registration</title>
<!-- Fav Icon -->
<link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico" type="image/x-icon">

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

<!-- Stylesheets -->
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

<!-- Stylesheets -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600;700&amp;display=swap" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/flaticon.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/jquery-ui.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/color/theme-color.css" id="jssDefault" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/responsive.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/bootstrap-float-label.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/whatsappchat.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylepm.css"/>
<script language="JavaScript">
function validate()
{
	document.getElementById("cscsave").disabled=true;
	document.frm.action="https://pgi.billdesk.com/pgidsk/PGIMerchantPayment";
	document.frm.submit();
}
</script>	
</head>
<!-- page wrapper -->
<body>
<form name="frm" method=post>
<input type='hidden' name='msg' value='<%=temp%>'/>
	<div class="boxed_wrapper">
    	<header class="main-header">
        	<div class="header-lower">
            	<div class="outer-box">
                    <div class="main-box">
                        <div class="logo-box logo-box-left">
                            <figure class="logo"><a href="#"><img src="${pageContext.request.contextPath}/assets/images/logo.png" alt=""></a></figure>
                        </div>
                        <div class="logo-box logo-box-right">
                            <figure class="logo"><a href="#"><img src="${pageContext.request.contextPath}/assets/images/tsgovt.png" alt=""></a></figure>
                        </div>
                    </div>
                </div> 
            </div>
        </header>
       
        <!--Page Title-->
        <section class="page-title-two bg-color-1 centred">
            <div class="auto-container">
                
                <div class="header-box-title">
                    <h1><i class="icon-1"></i>Additional Payment</h1>
                  
                </div>
            </div>
        </section>
        <!--End Page Title-->
        <section class="category-section category-page mr-0 pt-60 pb-90">
            <div class="auto-container">
               <div class="container-fluid">
                    <div class="rounded-box" style="padding:1rem;">
                    	<div id="form-container">        
                        	<div class="row" style="justify-content:center"> 
                            	<div class="col-sm-6" >
	                                <div class="panel-heading-payment eng">Payment Details</div>
	                                <div class="panel-heading-payment tel">అవసరమైన చెల్లింపు</div>
	                                <div class="panel-heading-payment hin">आवश्यक भुगतान</div>
	                                <div class="rounded-box" style="min-height:20rem">
                                       	<div class="container p-3">
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8 font-weight-normal">Registration No</div>
	                                            <div class="col-sm-4 font-weight-normal text-right" id="regno"><%=regno%></div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8 font-weight-normal">Applicant Name</div>
	                                            <div class="col-sm-4 font-weight-normal text-right" id="appname"><%=appname%></div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8 font-weight-normal" >Category</div>
	                                            <div class="col-sm-4 font-weight-normal text-right" id="cat"><%=category%></div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8 font-weight-normal">Contract Load</div>
	                                            <div class="col-sm-4 font-weight-normal text-right" id="load"><%=contld%>&nbsp;<%=metric%></div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8 font-weight-normal">Total amount to be paid</div>
	                                            <div class="col-sm-4 font-weight-normal text-right" id="totpaid"><%=actual_amount%></div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8 font-weight-normal">Paid during Registration</div>
	                                            <div class="col-sm-4 font-weight-normal text-right" id="regpaid"><%=paid_amount%></div>
	                                        </div>
	                                        
	                                        <div class="row p-1 bg-light" style="flex-wrap: unset;font-weight:bold">
	                                            <div class="col-sm-8">Balance to be paid</div>
	                                            <div class="col-sm-4 text-right" id="bal"><%=totamt%></div>
	                                        </div>
	                                        <div class="divButton">
											     <button class="btn btn--primary" name="Send" value="Make Payment" id='cscsave' accesskey="s" onClick="return validate();">Proceed to Pay</button>
											</div>
                                   		</div>  
                                    </div>
                                </div>
								
                        	</div>            
                    	</div>
                    </div> 	
                </div>	
            </div>
            
        </section>
        
	    <!-- section end -->
		<!-- main-footer -->
       	<footer class="main-footer">
           
           <div class="footer-bottom">
               <div class="auto-container">
                   <div class="inner-box clearfix">
                       <figure class="footer-logo"><a href="#"><img src="${pageContext.request.contextPath}/assets/images/footer-logo.png" alt=""></a></figure>
                       <div class="copyright pull-left">
                           <p><a href="#">TSSPDCL</a> &copy; 2008 All Right Reserved</p>
                       </div>
                       <ul class="footer-nav pull-right clearfix">
                           <li><a href="#">Terms of Service</a></li>
                           <li><a href="#">Privacy Policy</a></li>
                       </ul>
                   </div>
               </div>
           </div>
       	</footer>
	</div>	
</form>
</body>
</html>
<!-- <form name="f1">
	Enter New Registration Number:<input type="text" id="nrno" name="nrno" value="" required/> NR658233675345 	
	<input type="button" value="Submit" onclick="check();"/>
</form> -->
