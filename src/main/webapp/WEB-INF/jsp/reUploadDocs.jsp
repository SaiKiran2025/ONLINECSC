<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="stag" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>TSSPDCL-Documents Re-Submission</title>
<!-- Fav Icon -->
<link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico" type="image/x-icon">

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
<!-- <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/jquery.validate.min.js"></script>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/additional-methods.js"></script>
     <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.js"></script> -->
</head>
<!-- page wrapper -->
<body>
   <div class="boxed_wrapper">
        <!-- preloader -->
        <div class="loader-wrap">
            <div class="preloader">
                <div class="preloader-close"><i class="fa fa-times"></i></div>
                <div id="handle-preloader" class="handle-preloader">
                    <div class="animation-preloader">
                        <div class="spinner"></div>
                        <div class="txt-loading">
                            <span data-text-preloader="T" class="letters-loading">
                                T
                            </span>
                            <span data-text-preloader="S" class="letters-loading">
                                S
                            </span>
                            <span data-text-preloader="S" class="letters-loading">
                                S
                            </span>
                            <span data-text-preloader="P" class="letters-loading">
                                P
                            </span>
                            <span data-text-preloader="D" class="letters-loading">
                                D
                            </span>
                            <span data-text-preloader="C" class="letters-loading">
                                C
                            </span>
                            <span data-text-preloader="L" class="letters-loading">
                                L
                            </span>
                        </div>
                    </div>  
                </div>
            </div>
        </div>
        <!-- preloader end -->

        <!-- main header -->
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

            <!--sticky Header-->
            <div class="sticky-header">
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
					 <h1><i class="icon-1"></i>Re-Submission of Documents</h1>
                </div>
            </div>
        </section>
        <!--End Page Title-->
        <form name="docs" id="docs" method="POST">
		<input type="hidden" id="regno" name="regno" value="${regno}">
		<input type="hidden" id="idproof_flag" name="idproof_flag" value="${idproof}">
		<input type="hidden" id="saledeed_flag" name="saledeed_flag" value="${saledeed}">
		<input type="hidden" id="occupancy_flag" name="occupancy_flag" value="${occupancy}">
		<input type="hidden" id="approvalplan_flag" name="approvalplan_flag" value="${approvalplan}">
		<input type="hidden" id="ibond_flag" name="ibond_flag" value="${ibond}">

        <section class="category-section category-page mr-0 pt-60 pb-90">
            <div class="auto-container">
                <div class="wow slideInLeft animated" data-wow-delay="1000ms" data-wow-duration="1000ms">
                    <div class="container-fluid">   
               			<c:if test="${upload_status eq 'uploaded'}">
                       		 <h4 class="text-center mt-4">File Upload Successful !</h4>
                       	 </c:if> 
                       	 <c:if test="${upload_status eq 'invalid'}">
                       		 <h4 class="text-center mt-4">Invalid Registration Number</h4>
                       	 </c:if>  
                       	 <c:if test="${upload_status eq 'pending'}">
                        <div id="form-container">                                
                        <div class="row g-2 justify-content-center">
                         	<div class="col-sm-6">   
                         		 <div class="panel-heading eng">Applicant Details</div>
                            	 <div class="rounded-box">
                            	 <div class="form-group row px-3 pt-4 justify-content-between"> 
                               		 	<div class="ml-3">Registration Number</div>
                               		 	<div class="mr-3 font-weight-bold">${regno}</div>
                               		 </div>
                               		 <div class="form-group row px-3 pb-4 justify-content-between"> 
                               		 	<div class="ml-3">Applicant Name</div>
                               		 	<div class="mr-3 font-weight-bold">${name}</div>
                               		 </div>
                               	 </div>
                                
                            	 
                            	 <div class="panel-heading eng">Document Details</div>
                             	 <div class="rounded-box">
                                		 <div class="form-group row px-3 py-4">                               		 
                                		 
                                		 <c:if test="${idproof eq 'Y'}">
		                                        <div class="form-group input-group col-sm-12">
			                                            <label class="has-float-label">  
			                                                <input type="file" name="id_proof" id="id_proof" onchange="checkImage(this.id,100)" accept="image/gif, image/jpeg">
			                                                <span id="id_doc_label" style="padding-top:6px">ID Proof Copy 
			                                                    <span style="color:red">(JPG/JPEG) Max.Size 100KB</span>
			                                                </span>
			                                            </label>
			                                    </div>
			                              </c:if>
			                              <c:if test="${saledeed eq 'Y'}">
		                                        <div class="form-group input-group col-sm-12">
			                                            <label class="has-float-label">  
			                                                <input type="file" name="registration_proof" id="registration_proof"  onchange="checkPdf(this.id,5,'MB')" accept="application/pdf">
			                                                <span id="doctype" style="padding-top:6px">Registered Document 
			                                                	<span style="color:red">(PDF) Max.Size 5MB</span>
			                                                </span>
			                                            </label>
			                                    </div>
			                                </c:if>
			                                <c:if test="${occupancy eq 'Y'}">
			                                    <div class="form-group input-group col-sm-12">
			                                          <label class="has-float-label">  
			                                              <input type="file" name="occupancy_cert" id="occupancy_cert"  onchange="checkPdf(this.id,500,'kb')" accept="application/pdf">
			                                              <span style="padding-top:6px">Occupancy Certificate
			                                              	<span style="color:red">(PDF) Max.Size 500KB</span>
			                                              </span>
			                                          </label>
			                                    </div>
		                                    </c:if>
			                                <c:if test="${approvalplan eq 'Y'}">
			                                    <div class="form-group input-group col-sm-12">
			                                            <label class="has-float-label">  
			                                                <input type="file" name="approval_plan" id="approval_plan" onchange="checkPdf(this.id,1,'MB')" accept="application/pdf">
			                                                <span id="app_plan_label" style="padding-top:6px">GHMC/Grampanchayath Approval Plan (or) Layout Plan
			                                                    <span style="color:red">(PDF) Max.Size 1 MB</span>
			                                                </span>
			                                            </label>
			                                    </div>
		                                    </c:if>
			                                <c:if test="${ibond eq 'Y'}">
			                                    <div class="form-group input-group col-sm-12 mb-0">
			                                          <label class="has-float-label">  
			                                              <input type="file" name="indeminity_bond" id="indeminity_bond"  onchange="checkPdf(this.id,500,'kb')" accept="application/pdf">
			                                              <span style="padding-top:6px">Indemnity Bond worth Rs.100/-
			                                              	<span style="color:red">(PDF) Max.Size 500KB</span>
			                                              </span>
			                                          </label>
			                                    </div>	
		                                    </c:if>	  
		                                    	                                  		                                    
                               		 </div>
                               	</div>
                              	
                            </div>
                        </div> 
 
						<div class="new_reg_button-container">
                            <div style="display: flex;">
                                <div class="new_reg__button">
                                    <button type="button"  id="btnsubmit" class="new_reg_btn eng">Proceed</button>
                                </div>
                            </div>
	               		</div>    
	  				</div>
	  				 </c:if> 
                 </div>               
              </div>
           </div>
         </section>
        </form>   	
   </div>
            

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


 	
 	
  	<div id="snackbar" style="display:none"></div>
  	<div id="snackbar1" style="display:none"></div>
    <!-- jquery plugins -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
   	<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>-->
    <script src="${pageContext.request.contextPath}/assets/js/owl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/wow.js"></script>
    <%-- <script src="${pageContext.request.contextPath}/assets/js/validation.js"></script> --%>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.fancybox.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/appear.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/scrollbar.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/isotope.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jQuery.style.switcher.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery_1.6.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/scriptDocs.js"></script>
    <script src="${pageContext.request.contextPath}/script/loadcalculator.js"></script>
    <script src="${pageContext.request.contextPath}/script/validation.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  	  	
    <script type="text/javascript">

    $(document).ready(function (event) {
    	
    	if(event.keyCode == 13) {
    		event.preventDefault();
    	    return false;
    	}
    
    });
    </script>
    <script>
    $("#btnsubmit").click(function(e){
    	finalSubmit();
    }); 
    </script>    

  
</body>
</html>
