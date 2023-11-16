<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="stag" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>TSSPDCL-Navaratri Pandals Service Connection Registration</title>
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
           <!-- header-top -->
           <!-- <div class="header-top">
                <div class="top-inner clearfix">
                    <div class="left-column pull-left">
                        <ul class="info clearfix">
                            <li><i class="far fa-map-marker-alt"></i>Corporate Office, Mint Compound, HYDERABAD, TS</li>
                            <li><i class="far fa-clock"></i>Mon - Sat  9.00 - 18.00</li>
                            <li><i class="far fa-phone"></i><a href="tel:9999999999">+919999999999</a></li>
                        </ul>
                    </div>
                    <div class="right-column pull-right">
                        <ul class="social-links clearfix">
                            <li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
                            <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fab fa-google-plus-g"></i></a></li>
                            <li><a href="#"><span class="fab fa-instagram"></span></a></li>
                        </ul>
                       <div class="sign-box">
                            <a href="#"><i class="fas fa-user"></i>Sign In</a>
                        </div>
                    </div>
                </div>
            </div> -->
            <!-- header-lower -->
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
					 <h1><i class="icon-1"></i>Registration for Navaratri Pandals Service Connection</h1>
                </div>
            </div>
        </section>
        <!--End Page Title-->
        <form name="newreg" id="newreg" method="POST">
		<input type="hidden" name="area_name" />
		<input type="hidden" name="section_code" />
		<input type="hidden" name="ero_code" />
		<input type="hidden" name="csc_number" />
		<input type="hidden" name="area_group" />
		<input type="hidden" name="is_gstverified" id="is_gstverified"/>
		
		<input type="hidden" name="xmobileno" value="${mobileno}">
		<input type="hidden" name="othamount" />
		
        <section class="category-section category-page mr-0 pt-60 pb-90">
            <div class="auto-container">
                <div class="wow slideInLeft animated" data-wow-delay="1000ms" data-wow-duration="1000ms">
                    <div class="container-fluid">
                    	<div class="rounded-box" style="padding:1rem;">
                           	<div class="row g-2">
                            	<div class="col-sm-12">
                            	 	<div class="row d-flex justify-content-center p-2">
                                		<div class="form-group input-group col-sm-3" id="d-adjscno">
                                        	<label class="has-float-label">
                                           		<input class="form-control" type="text" id="adj_uscno" name="adj_uscno" placeholder=" "  maxlength="9" onblur="checkUSCNO(1,this.id,'');" onfocus="changeIcon('');" required/>
                                           		<span>Adjacent Unique Service Number</span>
                                        	</label>
                                        	<span class="input-group-addon">
                                        		<button class="search-button" type="button" data-toggle="tooltip" data-placement="bottom" title="Search Unique SC.No." onclick="checkUSCNO(1,'adj_uscno','');return true;" >
                                        		<span id='adj_icon'><i class='fa fa-search'></i></span>
                                        		<!--<span id='adj_icon'></span><i class="fa fa-search"></i>  -->
                                        		</button>
                                        	</span>
                                        </div>
                                        <div class="form-group input-group col-sm-2" id="d-secname">
                                   			<input class="form-control alert alert-primary custom-alert" type="text" id="adjsecname" name="adjsecname" value="Find your Section Name here" readonly/>
                                        </div>
                                        <div class="form-group input-group col-sm-1 justify-content-center"  id="d-or">-OR-</div>
                                        <div class="form-group input-group col-sm-2" id="circle-div">
                                            <select class="floating-select" name="circle" id="circle"  
                                                onclick="this.setAttribute('value', this.value);"
                                                onchange="this.setAttribute('value', this.value); getSectionList(1);"
                                                value="">
                                                <option value=""></option>
                                            </select>
                                            <label class="floating-label eng">Circle/District</label>
                                        </div> 
                                        <div id="section_spin" ></div> 
                                        <div class="form-group input-group col-sm-3" id="section-div">
                                            <select class="floating-select" name="section" id="section"  
                                                onclick="this.setAttribute('value', this.value);" 
                                                onchange="this.setAttribute('value', this.value); getAreaList(1);"
                                                value="">
                                                <option value=""></option>
                                            </select>
                                            <label class="floating-label eng">Section Name</label>
                                        </div>
                                       	<div class="form-group">
                                        	<button  class="btn btn-light" type="button" data-placement="bottom" title="Help" data-toggle="modal" data-target="#modal-help"><i class="fa fa-question-circle fa-lg"></i></button>
                                         </div> 
                                    </div>
                                </div>       	
                            </div>           	
                            <span id="addscnos"></span>
                        </div>	
                        <br>       
                        <div id="form-container">        
                        <!-- <div class="row g-2 wow slideInLeft animated" data-wow-delay="00ms" data-wow-duration="2000ms"> --> 
                        
                        <div class="row g-2 justify-content-center">
                         	<div class="col-sm-6">     
                            	 <div class="panel-heading eng">Service Request Details</div>
                             		<div class="rounded-box">
                                		 <div class="form-group row p-3">
                                		 	<div class="form-group input-group col-sm-6">
		                                          <label class="has-float-label">
		                                              <input class="form-control" type="text" id="applicant_name" name="applicant_name" placeholder=" " />
		                                              <span>Applicant Name</span>
		                                          </label>
		                                      </div>
		                                	  <div class="form-group input-group col-sm-6">
		                                          <label class="has-float-label">
		                                              <input class="form-control" type="text" id="address1" name="address1" placeholder=" " />
		                                              <span>Door No., Building Name</span>
		                                          </label>
		                                      </div>
		                                      <div class="form-group input-group col-sm-6">
		                                          <label class="has-float-label">
		                                              <input class="form-control" type="text" id="address2" name="address2" placeholder=" " />
		                                              <span>Street</span>
		                                          </label>
		                                      </div>   
		                                  	  <div class="form-group input-group col-sm-6">
		                                          <label class="has-float-label">
		                                              <input class="form-control" type="text" id="address3" name="address3" placeholder=" " />
		                                              <span>Area</span>
		                                          </label>
		                                      </div>
		                                      <div class="form-group input-group col-sm-6">
		                                          <label class="has-float-label">
		                                              <input class="form-control" type="text" id="address4" name="address4" placeholder=" " />
		                                              <span>Town</span>
		                                          </label>
		                                      </div>
		                                      <div class="form-group input-group col-sm-6">
		                                          <label class="has-float-label">
		                                              <input class="form-control" type="number" id="pincode" name="pincode" placeholder=" " />
		                                              <span>PinCode</span>
		                                          </label>
		                                      </div>
				                              <div class="form-group input-group col-sm-6">
		                                          <label class="has-float-label">
		                                              <input class="form-control" type="number" id="reqload" name="reqload" onchange="getPaymentsGanesh()" placeholder=" " />
		                                              <span id="lan_req_load">Required Load in Watts</span>
		                                          </label>
		                                       </div>
                                		 
                                		 </div>
                               	 	</div>
                            </div>

                        </div> 
                        <div class="row g-2 justify-content-center">
                        	<div class="col-sm-6">     
                            	 <div class="panel-heading eng">Payment Details</div>
                             		<div class="rounded-box">
                                		 <div class="form-group row p-3">   
                                		 	<div class="form-group input-group col-sm-6">    
                                		 		Service Charges ( in Rs. )
                                		 	</div>   
                                		 	 <div class="form-group input-group col-sm-6" id="amount">    
                                		 		0.00
                                		 	 </div>                     		            
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
  
                        </div>               
                    	</div>
                    </div> 	
                </div>	
            </div>
            
        </section>
        </form>
	    <!-- category-section end -->
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
       	<!-- main-footer end -->

        <!--Scroll to top-->
       <!--  <button class="scroll-top scroll-to-target" data-target="html">
            <span class="fa fa-angle-up"></span>
        </button> -->
    </div>
    <!-- <a id="whatsappChats" href="https://api.whatsapp.com/send?phone=919515022697" target="_blank" rel="nofollow" class="pushItDown">
        <img loading="lazy" src="${pageContext.request.contextPath}/assets/images/icons/whatsapp-icon.png" width="66" height="66">
        <span class="whatsappChatsText">Chat with us on <strong>WhatsApp</strong></span>
    </a> -->
    <!-- modal load calc begins -->
    <div class="modal fade" id="modal-load-calc"  role="dialog">
    	<!-- <div class="modal-dialog modal-lg  modal-dialog-centered">  for vertical center -->
    	<div class="modal-dialog modal-lg">
      		<div class="modal-content">
        	<!-- Modal Header -->
        		<div class="modal-header">
          			<h4 class="modal-title primary">Load Calculator</h4>
          			<button type="button" class="close" data-dismiss="modal">&times;</button>
        		</div>
        
        		<!-- Modal body -->
        		<div class="modal-body">
         			<table class="table table-bordered table-hover" id="loadTable">
				    <thead>
				    <tr>
				        <th width="5%">Sl.No</th>
                  		<th width="30%">Appliance Type</th>
                  		<th width="15%">Wattage</br>(in Watts)</th>
                  		<th width="15%">Numbers</th>
                  		<th width="15%">Total Load</br>(in Watts)</th>
                  		<th width="10%">Add</th>
                  		<th width="10%">Delete</th>
				    </tr>
				    </thead>
    				<tbody>
    				</tbody>
    				<tfoot>
    				<tr>
    					<input type="hidden" name="gTotal" id="gTotal" value="0" />
       					<th colspan="5" class="text-right bg-light">Total Connected Load </th>
       					<th colspan="2" class="bg-light"><span id="loadTotal">0</span>&nbsp;(in Watts)</th>
    				</tr>
					</tfoot>
  					</table>
        		</div>
        
		        <!-- Modal footer -->
		        <div class="modal-footer justify-content-right">
		          <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
		          <button type="button" class="btn btn-danger" data-dismiss="modal" onClick="captureLoad()" id="capture">Capture</button>
		       
		        </div>
        	</div>
    	</div>
  	</div> 	
  	<!--  modal help -->
  	<div class="modal fade" id="modal-help"  role="dialog">
    	<!-- <div class="modal-dialog modal-lg  modal-dialog-centered">  for vertical center -->
    	<div class="modal-dialog">
      		<div class="modal-content">
        	<!-- Modal Header -->
        		<div class="modal-header">
          			<h4 class="modal-title primary">Help</h4>
          			<button type="button" class="close" data-dismiss="modal">&times;</button>
        		</div>
        
        		<!-- Modal body -->
        		<div class="modal-body">
         			
         			<div type="square">
         				<div>Please Enter your Adjacent Unique Service Number to find your Section Name</div>
         				<div class="text-center">-OR-</div> <br>
         				<div>Choose Circle/District to find your Section Name</div>  <br>      				
         			</div>
        		</div>
        	</div>
    	</div>
  	</div> 	
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
    <script src="${pageContext.request.contextPath}/script/scriptGanesh.js"></script>
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
    	getCircleList(1);	  	
    
    });
    </script>
    <script language="javascript">
    $("#btnsubmit").click(function(e){
    	finalSubmit(1);
    }); 
    </script>    
    
    <script language="javascript">
    function myFunction1() {
        //alert("myDropdown1");
        document.getElementById("myDropdown1").classList.toggle("show");
       
    }
    
    function myFunction2() {
        //alert("myDropdown2");
        document.getElementById("myDropdown2").classList.toggle("show");
    }
  
    // Close the dropdown if the user clicks outside of it
    /* window.onclick = function(event) {
    
        if (!event.target.matches('.dropbtn')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            var i;
           
            for (i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
        
    } */

    </script>
    
</body><!-- End of .page_wrapper -->
</html>
