<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="stag" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>TSSPDCL-New Service Connection Registration</title>
<link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico" type="image/x-icon">
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600;700&amp;display=swap" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/login-style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/animate.css" rel="stylesheet">

<style>
.reversed{padding: 3rem 0 0 0;}
.reversed .control-label{margin: -4.5rem 0 0 0; float: left;}

input.is-valid ~ label{color: green;}
input.is-invalid ~ label{color: red;}
</style>
</head>
  
<body>
	<div class="container-fluid">
      <div class="row">
        <div class="authfy-container col-xs-12 col-sm-10 col-md-8 col-lg-6 col-sm-offset-1 col-md-offset-2 col-lg-offset-3">  
        <!--<div class="authfy-container col-xs-12 col-sm-10 col-md-8 col-lg-8  col-sm-offset-1 col-md-offset-2 ">  -->
          <div class="col-sm-6 authfy-panel-left">
            <div class="brand-col">
              <div class="headline">
                <!-- brand-logo start -->
                <div class="brand-logo">
                  <img src="${pageContext.request.contextPath}/images/tsspdcl-logo-1024.png" alt="TSSPDCL"/>
                  
                </div><!-- ./brand-logo -->
                <h1>TSSPDCL</h1>
				<h4>Customer Service Center</h4>
                <p>Please register with Applicant/Consumer Self Mobile Number only for future correspondence in SMS as Status/Alerts shall be sent to this registered Mobile Number only</p>
               	<div class="mt-5 knowmore form-group text-center">
                   <input class="text-center" type="button" name="" onclick="location.href='https://www.tssouthernpower.com/downloads';" value="Download Documents"/>
                </div>     
                <p>Visit Us : <a href="#">www.tssouthernpower.com</a></p>
                <div class="row social-buttons">
                <ul class="social-network social-circle">
                  <li><a href="#" class="icoFacebook" title="Facebook"><i class="fa fa-facebook-f"></i></a></li>
                  <li><a href="#" class="icoTwitter" title="Twitter"><i class="fa fa-twitter"></i></a></li>
                  <li><a href="#" class="icoGoogle" title="Google +"><i class="fa fa-google-plus"></i></a></li>
                </ul>
                </div>   
              </div>
            </div>
          </div>
          <div class="authfy-panel-right">
            <!-- authfy-login start -->
            <div class="authfy-login">
              <!-- panel-login start -->
              <div class="authfy-panel panel-login text-center active wow fadeInDown animated" id="loginPanel">
                <div class="authfy-heading">
                  <h3 class="auth-title">New Service Registration</h3>
                  <p>Fill in your Mobile No. below and we will send you an OTP for verification </p>
                </div>
                <div class="row">
                  <div class="col-xs-12 col-sm-12" id="login">
                    <form id="loginForm" name="loginForm">
                    <input type="hidden" name="paytype" id="paytype" value="${paytype}">
                    <c:if test="${paytype eq 'meeseva'}">
                      <input type="hidden" name="staffcode" id="staffcode" value="${staffcode}">
                      <input type="hidden" name="centrecode" id="centrecode" value="${centrecode}">
                      <input type="hidden" name="requestid" id="requestid" value="${requestid}">                      
                      <input type="hidden" name="msg" id="msg" value="${msg}">
                      <input type="hidden" name="encmsg" id="encmsg" value="${encmsg}">
                    </c:if>  
                      <div class="form-group reversed">
                        <input type="number" class="form-control mobileno" id="mobileno" name="mobileno"
                        placeholder="Mobile Number"  onKeyPress="if(this.value.length==10) return false;">
                      </div>
                      <div style='height:40px' id="mobilecheck">&nbsp;</div>
                      <div class="form-group">
                      	<!-- <button id="otp" class="btn btn-lg btn-primary btn-block lnk-toggler" data-panel=".panel-otp" type="button">Get OTP</button> -->
                      	<input type="button" id="otp" value="Get OTP" class="btn btn-lg btn-primary btn-block">
                      </div>
                                           
                    </form>
                   	  <div class="form-group">
                       	<button class="btn btn-lg btn-success btn-block lnk-toggler" type="button" id="preqPanel" data-panel=".panel-signup">Check Prerequisites</button>
                      </div>
                      <c:if test="${paytype eq 'meeseva'}">
                      <div class="form-group">
                       	<button class="btn btn-lg btn-warning btn-block lnk-toggler" type="button" id="meeseva_reprint" >Reprint Acknowledgement</button>
                      </div>
                      </c:if>
                      <!--  <div class="form-group">
                       	<button class="btn btn-lg btn-success btn-block lnk-toggler" type="button" data-panel=".panel-forgot">Already Registered</button>
                      </div> -->
                      
                   
                  </div>
                </div>
              </div> <!-- ./panel-login -->
              <!-- panel-signup start -->
              <div class="authfy-panel panel-signup text-center">
                <div class="row">
                  <div class="col-xs-12 col-sm-12">
                    <div class="authfy-heading">
                      <h3 class="auth-title">Select Category and Load</h3>
                    </div>
                    <form name="signupForm" class="signupForm" action="#" method="post">                    
                      <div class="form-group">
                      	<select class="form-control" name="category-prereq" id="category-prereq" 
                      		onchange="getSubCategoryPreReq()">
	                        <option value="">Select Category</option>
	                        <option value='1' >1-DOMESTIC</option>
	                        <option value='2' >2-NON-DOMESTIC AND COMMERCIAL</option>
	                        <option value='3' >3-INDUSTRIAL</option>
	                        <option value='4' >4-COTTAGE INDUSTRIES AND DHOBIGHATS</option>
	                        <option value='5' >5-AGRICULTURAL</option>
	                        <option value='6' >6-LOCALBODIES-STREETLIGHT/PWS SCHEME</option>
	                        <option value='7' >7-GENERAL PURPOSE</option>
	                        <option value='8' >8-TEMPORARY SUPPLY</option>
	                        <option value="9" >9-EV CHARGING STATIONS</option>
                    	</select>
                       
                      </div>
                      <div class="form-group">
                      	<select class="form-control" name="sub_category-prereq" id="sub_category-prereq" 
                        		onchange="this.setAttribute('value', this.value); changeMetricPreReq()" value="">
                            <option value="">Select Sub-Category</option>
                        </select>
                       
                      </div>
                      <div class="form-group">
                       	<input class="form-control" name="conn_load-prereq" id="conn_load-prereq" type="number" placeholder="Connected Load in Watts">
                      </div>
                      <div class="form-group">
                      	<select class="form-control" name="regdoc-prereq" id="regdoc-prereq" onchange="this.setAttribute('value', this.value);" value="">
                          <option value="">Have Registration Documents of Premises ?</option>
                          <option value="Y" >Yes</option>
						  <option value="N" >No</option>
                    	</select> 	
                      </div>
                       <p class="text-message bg-light">
                      	In case of line/pole extension required, only application fee will be collected at the time of registration.<br> 
                      	Security deposit and development charges will be collected at the time of payment against estimate.
                      </p>
                      
                      <div class="form-group">
                      	<button class="btn btn-lg btn-success btn-block lnk-toggler" type="button" data-panel=".panel-verify" onclick="getPreReq()">Check</button>
                      </div>
                      
                      <div class="form-group">
                        <a class="btn btn-lg btn-primary btn-block lnk-toggler" onclick="proceedReg()" data-panel=".panel-login" href="#">Proceed to Registration</a>
                      </div>
                      <!--<div class="form-group">
                        <a class="btn btn-lg btn-success btn-block lnk-toggler" data-panel=".panel-forgot" href="#">Already Registered</a>
                      </div> -->
                      <!-- <div class="form-group">  
                        <a class="lnk-toggler" data-panel=".panel-login" href="#">Already have an account?</a>    
                      </div>   -->
                     
                    </form>
                  </div>
                </div>
              </div> <!-- ./panel-signup -->

              <!-- panel-employee verify -->
              <div class="authfy-panel panel-verify text-center">
                <div class="row">
                  <div class="col-xs-12 col-sm-12">
                    <div class="authfy-heading">
                      <h3 class="auth-title">Required Payment and Documents</h3>
                      <!--<p>Fill in your Mobile Number below and we will send you an OTP with further instructions.</p>-->
                    </div>
                    <div class="form-group p-2">
                         <div class="form-group row p-1">
                             <div class="col-sm-8 text-left">Application Fee</div>
                             <div class="col-sm-4 text-right" id="af_pre">0.00</div>
                         </div>
                         <div class="form-group row p-1">
                             <div class="col-sm-8 text-left">GST on Application Fee</div>
                             <div class="col-sm-4 text-right" id="afgst_pre">0.00</div>
                         </div>
                         <div class="form-group row p-1">
                             <div class="col-sm-8 text-left">Security Deposit</div>
                             <div class="col-sm-4 text-right" id="sd_pre">0.00</div>
                         </div>
                         <div class="form-group row p-1">
                             <div class="col-sm-8 text-left">Development Charges</div>
                             <div class="col-sm-4 text-right" id="dc_pre">0.00</div>
                         </div>
                         <div class="form-group row p-1">
                             <div class="col-sm-8 text-left">GST on Development Charges</div>
                             <div class="col-sm-4 text-right" id="dcgst_pre">0.00</div>
                         </div>
                         <div class="form-group row p-1">
                             <div class="col-sm-8 text-left font-weight-bold">Total</div>
                             <div class="col-sm-4 text-right font-weight-bold" id="tot_pre">0.00</div>
                         </div>
                         <div class="form-group row p-1">
                   			<div class="col-sm-12 text-left fs-6">1.ID Proof Copy (JPG/JPEG) Max.Size 100KB</br>
                          		2.Registered Documents of Premises Copy (PDF) Max.Size 5MB
                          	</div>
                         </div>
                         <div class="form-group">
	                       	<button class="btn btn-lg btn-success btn-block lnk-toggler" type="button" id="preqPanel" data-panel=".panel-signup">Check Prerequisites</button>
	                      </div>
                         <div class="form-group">
	                        <a class="btn btn-lg btn-primary btn-block lnk-toggler" onclick="proceedReg()" data-panel=".panel-login" href="#">Proceed to Registration</a>
	                      </div>
	                      <!--  <div class="form-group">
	                        <a class="btn btn-lg btn-success btn-block lnk-toggler" data-panel=".panel-forgot" href="#">Already Registered</a>
	                      </div>-->
                    </div>
                    
                    <div class="form-group p-3">
                         
                     </div>
                    
                    
                  </div>
                </div>
              </div> <!-- ./panel-employee-verify -->
              <!-- panel-otp verify -->
              <div class="authfy-panel panel-otp text-center wow fadeInDown animated" id="otpPanel">
                <div class="row">
                  <div class="col-xs-12 col-sm-12">
                    <div class="authfy-heading">
                      <h3 class="auth-title">OTP Verification</h3>
                      <!--<p>Fill in your Mobile Number below and we will send you an OTP with further instructions.</p>-->
                      <h5 id="error_otp" class='alert alert-danger text-danger text-center'>Invalid OTP! Please Try Again</h5>
                    </div>
                    <p>A verification code has been sent to your Mobile Number</p>
                    <div class="dflex text-center auth-title">
                      <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" fill="currentColor" class="bi bi-asterisk" viewBox="0 0 16 16">
                          <path d="M8 0a1 1 0 0 1 1 1v5.268l4.562-2.634a1 1 0 1 1 1 1.732L10 8l4.562 2.634a1 1 0 1 1-1 1.732L9 9.732V15a1 1 0 1 1-2 0V9.732l-4.562 2.634a1 1 0 1 1-1-1.732L6 8 1.438 5.366a1 1 0 0 1 1-1.732L7 6.268V1a1 1 0 0 1 1-1z"/>
                      </svg>
                      <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" fill="currentColor" class="bi bi-asterisk" viewBox="0 0 16 16">
                          <path d="M8 0a1 1 0 0 1 1 1v5.268l4.562-2.634a1 1 0 1 1 1 1.732L10 8l4.562 2.634a1 1 0 1 1-1 1.732L9 9.732V15a1 1 0 1 1-2 0V9.732l-4.562 2.634a1 1 0 1 1-1-1.732L6 8 1.438 5.366a1 1 0 0 1 1-1.732L7 6.268V1a1 1 0 0 1 1-1z"/>
                      </svg>
                      <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" fill="currentColor" class="bi bi-asterisk" viewBox="0 0 16 16">
                          <path d="M8 0a1 1 0 0 1 1 1v5.268l4.562-2.634a1 1 0 1 1 1 1.732L10 8l4.562 2.634a1 1 0 1 1-1 1.732L9 9.732V15a1 1 0 1 1-2 0V9.732l-4.562 2.634a1 1 0 1 1-1-1.732L6 8 1.438 5.366a1 1 0 0 1 1-1.732L7 6.268V1a1 1 0 0 1 1-1z"/>
                      </svg>
                      <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" fill="currentColor" class="bi bi-asterisk" viewBox="0 0 16 16">
                          <path d="M8 0a1 1 0 0 1 1 1v5.268l4.562-2.634a1 1 0 1 1 1 1.732L10 8l4.562 2.634a1 1 0 1 1-1 1.732L9 9.732V15a1 1 0 1 1-2 0V9.732l-4.562 2.634a1 1 0 1 1-1-1.732L6 8 1.438 5.366a1 1 0 0 1 1-1.732L7 6.268V1a1 1 0 0 1 1-1z"/>
                      </svg>
                      <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" fill="currentColor" class="bi bi-asterisk" viewBox="0 0 16 16">
                          <path d="M8 0a1 1 0 0 1 1 1v5.268l4.562-2.634a1 1 0 1 1 1 1.732L10 8l4.562 2.634a1 1 0 1 1-1 1.732L9 9.732V15a1 1 0 1 1-2 0V9.732l-4.562 2.634a1 1 0 1 1-1-1.732L6 8 1.438 5.366a1 1 0 0 1 1-1.732L7 6.268V1a1 1 0 0 1 1-1z"/>
                      </svg>
                      <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" fill="currentColor" class="bi bi-asterisk" viewBox="0 0 16 16">
                          <path d="M8 0a1 1 0 0 1 1 1v5.268l4.562-2.634a1 1 0 1 1 1 1.732L10 8l4.562 2.634a1 1 0 1 1-1 1.732L9 9.732V15a1 1 0 1 1-2 0V9.732l-4.562 2.634a1 1 0 1 1-1-1.732L6 8 1.438 5.366a1 1 0 0 1 1-1.732L7 6.268V1a1 1 0 0 1 1-1z"/>
                      </svg>
                      <span id="lastfour"></span>
                    </div>
                    <div class="otp-input">
                    	<label for="digit">Type your 4 digit Security Code</label>
                        <div class="d-flex text-center p-3 mt-3">
                        	<input type="number" class="form-control bg-light input_otp" id="otp1" name="otp1" maxlength="1" oninput="this.value=this.value.replace(/[^0-9]/g,'');" placeholder=""/>
                            <input type="number" class="form-control bg-light input_otp" id="otp2" name="otp2" maxlength="1" oninput="this.value=this.value.replace(/[^0-9]/g,'');" pattern="[0-9]+" placeholder=""/>
                            <input type="number" class="form-control bg-light input_otp" id="otp3" name="otp3" maxlength="1" oninput="this.value=this.value.replace(/[^0-9]/g,'');" pattern="[0-9]+" placeholder=""/>
                            <input type="number" class="form-control bg-light input_otp" id="otp4" name="otp4" maxlength="1" oninput="this.value=this.value.replace(/[^0-9]/g,'');" pattern="[0-9]+" placeholder=""/>
                            
                            <!-- <input class="input_otp" type="text" oninput="vaildateNum(this)" onkeyup="shiftInput1(1)" id="a" maxlength="1">
                    		<input class="input_otp" type="text" oninput="vaildateNum(this)" onkeyup="shiftInput1(2)" id="b" maxlength="1">
                    		<input class="input_otp" type="text" oninput="vaildateNum(this)" onkeyup="shiftInput1(3)" id="c" maxlength="1">
                    		<input class="input_otp" type="text" oninput="vaildateNum(this)" onkeyup="shiftInput1(4)" id="d" maxlength="1"> -->
                        </div>
                   	</div>  
                   	</br>
                   	
                   	<!-- <div class="text-center">Time Left : <span id="timer"></span></div></br> -->
                    <div class="form-group">
						<button type="button" id="verifyotp" class="btn btn-primary">Verify &amp; Proceed</button>
					</div>
					</br>
                    <div class="fw-normal text-center text-muted">
                       	Didn't Receive the OTP? <br>
                       <!-- 	<a href="#" class="text-primary font-weight-bold text-decoration-none">Resend</a>    -->
                       	<button type="button" id="resendotp" class="btn btn-warning">Resend OTP will be enabled in <span id="timer"></span></button>
                    </div>
                  </div>
                </div>
              </div> <!-- ./panel-otp-verify -->
            </div> <!-- ./authfy-login -->
          </div>
        </div>
      </div> <!-- ./row -->
    </div> <!-- ./container -->
    <div id="snackbar" style="display:none"></div>
    <!-- Javascript Files -->

    <script src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/script/custom.js"></script>
	<script src="${pageContext.request.contextPath}/script/scriptNR.js"></script> 
	<script src="${pageContext.request.contextPath}/script/validation.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	
	<script language="javascript">
	$(document).ready(function(){
       	//$("#mobilecheck").hide();
       
       	$("#otpPanel").hide();
       	$("#resendotp").attr("disabled", true); 
       	$("#verifyotp").attr("disabled", true); 
   		
       	
    	let mobileError = true;
    	
    	$("#otp").click(function(event){
    		validateMobile();
    		//setInterval(downTimer,1000);
    		//event.preventDefault();
        });
    	$("#meeseva_reprint").click(function(event){
    		document.loginForm.action="https://www.tssouthernpower.com/searchRegsByMobile";
			document.loginForm.submit();
    		//setInterval(downTimer,1000);
    		//event.preventDefault();
        });
		$("#resendotp").click(function(event){
			$("#otp1").val("");
			$("#otp2").val("");
			$("#otp3").val("");
			$("#otp4").val("");
			$("#resendotp").attr("disabled", true); 
			$("#resendotp").html('Resend OTP will be enabled in <span id="timer"></span>' );
		    $("#otp1").focus();
		    validateMobile();
        });
		$("#preqPanel").click(function(event){
			//$("#otp1").val('');
			$("#loginPanel").hide(); 
			$("#preqPanel").show(); 
        });
		$("#verifyotp").click(function(event){
			
			//$("#error_otp").show();
			validateOTP();
    		//event.preventDefault();
        });
		
	    function validateMobile() {
	       	let mobileValue = $("#mobileno").val();
	       	//alert(mobileValue);
	       	if (mobileValue.length == 0) {
	           	//$("#mobilecheck").show();
	           	//$("#mobilecheck").html("<h5 class='alert alert-danger' style='color: red;text-align:left'>**Mobile Number can't be Left Blank</h5>");
	           	$("#mobilecheck").html("<h5 style='color: red;text-align:left'>**Mobile Number can't be Left Blank</h5>");
	           	//$("#mobileno").addClass('error');
	           	mobileError = false;
	           	return false;
	       	} else if (mobileValue.length < 10) {
	           	//$("#mobilecheck").show();
	           	//$("#mobilecheck").html("<h5 class='alert alert-danger' style='color: red;text-align:left'>**Invalid Length of Mobile Number</h5>");
	           	$("#mobilecheck").html("<h5 style='color: red;text-align:left'>**Invalid Length of Mobile Number</h5>");
	           	mobileError = false;
	           	return false;
	       	} else {
	       		let lastFour = mobileValue.substr(mobileValue.length - 4);
	       		$("#lastfour").html(lastFour);
	       		sendOTP();
	           	return true;
	       	}
	    }
	});
	let timerOn = true;
	    
	function timer(remaining) {
	    //alert(remaining);
	    var m = Math.floor(remaining / 60);
	    var s = remaining % 60;
	    //alert("m="+m+"s="+s);
	    m = m < 10 ? '0' + m : m;
	    s = s < 10 ? '0' + s : s;
	    //alert("m="+m+"s="+s);
	    document.getElementById('timer').innerHTML = m + ':' + s;
	        
	    remaining -= 1;
	      
	    if(remaining >= 0 && timerOn) {
	        setTimeout(function() {
	            timer(remaining);
	        }, 1000);
	        return;
	    }
	    
	    if(!timerOn) {
	        // Do validate stuff here
	        return;
	    }
	      
	    // Do timeout stuff here
	    alert('Timeout for OTP');
	}
	    
	
	            
	$(function() {
	
	    $('#otp1,#otp2,#otp3,#otp4').keyup(function(e) {
	        //alert("hi");
	        if($(this).val().length==$(this).attr('maxlength'))
	            $(this).next(':input').focus()
	            
	        })
	    $('#otp4').keyup(function(e) {
	        
	        if($(this).val().length==$(this).attr('maxlength'))
	        	$("#verifyotp").removeAttr("disabled");
	            
	        })
	    }
	)
    </script>
</body>		
</html>
