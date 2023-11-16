<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*,javax.mail.event.*,javax.mail.internet.*,javax.activation.*,java.sql.*,java.text.*, java.util.zip.CRC32"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="stag" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>TSSPDCL-Additional Payment</title>
<!-- Fav Icon -->
<link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico" type="image/x-icon">

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

<!-- Stylesheets -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600;700&amp;display=swap" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/flaticon.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/responsive.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/bootstrap-float-label.min.css" rel="stylesheet">
</head>
<!-- page wrapper -->
<body>
<input type='hidden'  id='msg' name='msg' value=''>
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
                        	<div class="row justify-content-center">
                            	<div class="col-sm-6" >
                                	<div class="panel-heading eng">Registration Details</div>
                                	
                                	<div class="rounded-box">
                                    	<div class="row d-flex justify-content-center p-2">
                                		<form name="f1">
                                        	<div class="form-group col-sm-12" >
                                        		<label class="font-weight-bold" for="nregno">New Registration Number</label><span class="text-danger"> *</span>
												<input class="form-control" type="text" id="nrno" name="nrno" placeholder="Registration Number"  onkeyUp="this.value = this.value.toUpperCase();" required/>
													 	
											</div>
                                        	<div class="new_check_button" style="display: flex; justify-content: center" id="d-btn-check">
                                        		<button type="button"  class="new_reg_btn" onclick="checkNR();">Check</button>                                 
                                       		</div>
                                       		<!-- <div ><span id="msg"></span></div> -->
                                       	</form>
                                       	</div>
                                    </div> 
                            	</div> 
                            	<div class="col-sm-6" style="display:none">
                            	<form name="f2" method=post>
	                                <div class="panel-heading-payment eng">Payment Details</div>
	                                <div class="panel-heading-payment tel">అవసరమైన చెల్లింపు</div>
	                                <div class="panel-heading-payment hin">आवश्यक भुगतान</div>
	                                <div class="rounded-box" style="min-height:20rem">
                                       	<div class="container p-3">
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8">Registration No</div>
	                                            <div class="col-sm-4 text-right" id="regno">0.00</div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8">Applicant Name</div>
	                                            <div class="col-sm-4 text-right" id="appname">0.00</div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8">Category</div>
	                                            <div class="col-sm-4 text-right" id="cat">0.00</div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8">Contract Load</div>
	                                            <div class="col-sm-4 text-right" id="load">0.00</div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8">Total amount to be paid</div>
	                                            <div class="col-sm-4 text-right" id="tot">0.00</div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8">Paid during Registration</div>
	                                            <div class="col-sm-4 text-right" id="paid">0.00</div>
	                                        </div>
	                                        
	                                        <div class="row p-1 bg-light" style="flex-wrap: unset;font-weight:bold">
	                                            <div class="col-sm-8">Balance to be paid</div>
	                                            <div class="col-sm-4 text-right" id="bal">0.00</div>
	                                        </div>
                                   		</div>  
                                   		<div class="new_check_button" style="display: flex; justify-content: center" id="d-btn-check">
                                        	<button type="button"  class="new_reg_btn" id='cscsave' onclick="validate();">Make Payment</button>                                 
                                       	</div>
                                   	</div>
                                   	</form>
                                </div>
                            	<div class="col-sm-6" style="display:none" >
	                                <div class="panel-heading-payment eng">Payment Details</div>
	                                <div class="panel-heading-payment tel">అవసరమైన చెల్లింపు</div>
	                                <div class="panel-heading-payment hin">आवश्यक भुगतान</div>
	                                <div class="rounded-box" style="min-height:20rem">
                                       	<div class="container p-3">
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8">Application Fee</div>
	                                            <div class="col-sm-4 text-right" id="af">0.00</div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8">GST on Application Fee</div>
	                                            <div class="col-sm-4 text-right" id="afgst">0.00</div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8">Security Deposit</div>
	                                            <div class="col-sm-4 text-right" id="sd">0.00</div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8">Development Charges</div>
	                                            <div class="col-sm-4 text-right" id="dc">0.00</div>
	                                        </div>
	                                        <div class="row p-1" style="flex-wrap: unset;">
	                                            <div class="col-sm-8">GST on Development Charges</div>
	                                            <div class="col-sm-4 text-right" id="dcgst">0.00</div>
	                                        </div>
	                                        <div class="row p-1 bg-light" style="flex-wrap: unset;font-weight:bold">
	                                            <div class="col-sm-8">Total</div>
	                                            <div class="col-sm-4 text-right" id="tot">0.00</div>
	                                        </div>
                                   		</div>  
                                    </div>
                                </div>
                        	</div>            
                    	</div>
                    </div> 	
                </div>	
            </div>
            <div id="snackbar" style="display:none"></div>
  			<div id="snackbar1" style="display:none"></div>
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
    <!-- jquery plugins -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
   	<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery_1.6.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/script.js"></script>
    <script src="${pageContext.request.contextPath}/script/validation.js"></script>
    
	<script type="text/javascript">
	function checkNR(){
		//alert("hi");
		var nrno = $("#nrno").val();
		if($("#nrno").val().length == 0 || !$("#nrno").val().startsWith("NR"))
		{
			toastmsg("Please Enter Registration Number starting with NR",'error');
			$("#nrno").focus();
			return false;
		}
		if($("#nrno").val().length > 0) {
			var xhttp = new XMLHttpRequest();	
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {	
					const json = JSON.parse(this.responseText);
					if(json.addpay_resp == "VALID"){
						if(json.balance == 0.0){
							toastmsg("You have already made Full Payment! Thank You","success");
						}
						else{
							document.f1.action="saveAdl";	
							document.f1.method="POST";
							document.f1.submit();						
							}
					}
					else{
						toastmsg("Warning! Invalid Registration Number","error");
						$("#nrno").focus();
						return false;
					}
				}	
			};
			xhttp.open("GET", "checkNrno/"+nrno, true); 
		    xhttp.setRequestHeader("Content-type", "application/json");
		    xhttp.send();
		}
		/* var xhttp = new XMLHttpRequest();
			
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {	
					const json = JSON.parse(this.responseText);
					//alert(json);
					if(json.addpay_resp == "VALID"){
						if(json.balance == 0.0){
							toastmsg("You have made Full Payment! Thank You","success");
							//$("#msg").text("You have made Full Payment! Thank You");
						}
						else{
							document.getElementById("regno").innerHTML = json.nrno;
							document.getElementById("appname").innerHTML = json.name;
							document.getElementById("cat").innerHTML = json.category;
							document.getElementById("load").innerHTML = json.load;
							document.getElementById("tot").innerHTML = json.actual_amount;
							document.getElementById("paid").innerHTML = json.paid;
							document.getElementById("bal").innerHTML = json.balance;
							document.getElementById("msg").value = json.trmsg;
							
							}
					}
					else{
						//$("#msg").text("Invalid Registration Number");
						toastmsg("Warning! Invalid Registration Number","error");
						$("#nrno").focus();
						return false;
					}
				}	
			};
			xhttp.open("GET", "checkNrno/"+nrno, true); 
		    xhttp.setRequestHeader("Content-type", "application/json");
		    xhttp.send();
		} */
	}
	function validate()
	{
		document.getElementById("cscsave").disabled=true;
		document.f2.action="https://pgi.billdesk.com/pgidsk/PGIMerchantPayment";
		document.f2.submit();
	}
	</script>
	</div>	
</body>
</html>
<!-- <form name="f1">
	Enter New Registration Number:<input type="text" id="nrno" name="nrno" value="" required/> NR658233675345 	
	<input type="button" value="Submit" onclick="check();"/>
</form> -->
