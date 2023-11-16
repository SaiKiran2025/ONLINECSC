<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="stag" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>TSSPDCL-Additional Load Registration</title>
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
<body onload="showComptype()">
   <div class="boxed_wrapper">
        <!-- preloader Start-->
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

        <!-- main header STart -->
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
        <!-- main header End-->
       
        <!--Page Title Start-->
        <section class="page-title-two bg-color-1 centred">
            <div class="auto-container">
                
                <div class="header-box-title">
                    <h1 id="heading"><i class="icon-1"></i>LT-Service Request/Complaint Registration</h1>
                </div>
            </div>
        </section>
        <!--Page Title End-->
        
        <form name="adl" id="adl" method="POST">
		<input type="hidden" name="area_name" />
		<input type="hidden" name="section_code" />
		<input type="hidden" name="ero_code" />
		<input type="hidden" name="csc_number" />
		<input type="hidden" name="area_group" />
		<input type="hidden" name="is_gstverified" id="is_gstverified"/>		
		<input type="hidden" name="xukscno" id="xukscno" value="${ukscno}">
		<input type="hidden" name="xmobileno" value="${mobileno}">
		<input type="hidden" name="xload" id="xload">
		<input type="hidden" name="xcatcode" id="xcatcode">
		<input type="hidden" name="xcategory" id="xcategory">
		
		<input type="hidden" name="xctype" id="xctype" value="${ctype}">
		<input type="hidden" name="xcnature" id="xcnature" value="${cnature}">
		
		<input type="hidden" name="xclubbedUscnos" id="xclubbedUscnos">
		
		<!-- category-section start -->
        <section class="category-section category-page mr-0 pt-60 pb-90">
            <div class="auto-container">
                <div class="wow slideInLeft animated" data-wow-delay="1000ms" data-wow-duration="1000ms">
                    <div class="container-fluid">                    	
                        <div class="panel-heading eng">Existing Connection Details</div>
                       		<div class="rounded-box pt-2 pb-2">
                        	 	<div class="row d-flex justify-content-center ">
                        	 		<div class="col-sm-4">
	                                   	<div class="container pr-5">
		                                      <div class="row p-1" style="flex-wrap: unset;">
		                                          <div class="col-sm-6">Unique Service No.</div>
		                                          <div class="col-sm-6 text-left font-weight-bold" >${ukscno}</div>
		                                      </div>
		                                      <div class="row p-1" style="flex-wrap: unset;">
		                                          <div class="col-sm-6">Existing load</div>
		                                          <div class="col-sm-6 text-left font-weight-bold" id="exsisting_load"></div>
		                                      </div>
		                                      <div class="row p-1" style="flex-wrap: unset;">
		                                          <div class="col-sm-6">Village/City</div>
		                                          <div class="col-sm-6 text-left font-weight-bold" id="conn_address3"></div>
		                                      </div>
	                                	</div>	    
                                	</div>
                                	<div class="col-sm-4">
	                                   	<div class="container pr-5">
		                                      <div class="row p-1" style="flex-wrap: unset;">
		                                          <div class="col-sm-6">Consumer Name</div>
		                                          <div class="col-sm-6 text-left font-weight-bold" id="cons_name"></div>
		                                      </div>
		                                      <div class="row p-1" style="flex-wrap: unset;">
		                                          <div class="col-sm-6">Door No./Building Name</div>
		                                          <div class="col-sm-6 text-left font-weight-bold" id="conn_address1"></div>
		                                      </div>
		                                      <div class="row p-1" style="flex-wrap: unset;">
		                                          <div class="col-sm-6">Mandal/District</div>
		                                          <div class="col-sm-6 text-left font-weight-bold" id="conn_address4"></div>
		                                      </div>
	                                	</div>	    
                                	</div>
                                	<div class="col-sm-4">
	                                   	<div class="container pr-5">
		                                      <div class="row p-1" style="flex-wrap: unset;">
		                                          <div class="col-sm-6">Existing Category</div>
		                                          <div class="col-sm-6 text-left font-weight-bold" id="category"></div>
		                                      </div>
		                                      <div class="row p-1" style="flex-wrap: unset;">
		                                          <div class="col-sm-6">Street/Landmark</div>
		                                          <div class="col-sm-6 text-left font-weight-bold" id="conn_address2"></div>
		                                      </div>
		                                      <div class="row p-1" style="flex-wrap: unset;">
		                                          <div class="col-sm-6">Section</div>
		                                          <div class="col-sm-6 text-left font-weight-bold" id="section_name"></div>
		                                      </div>
		                                      
	                                	</div>	    
                                	</div>
                             	</div>
                          </div>       	
                     </div>           	
                     <span id="addscnos"></span>
                               
                   	<div class="form-group input-group col-sm-4 mx-auto mt-5 mb-3">
                          <select class="floating-select" id="ctype" name="ctype" 
                              onclick="this.setAttribute('value', this.value);" 
                              onchange="this.setAttribute('value', this.value);getCnatureList(1);" value="">
                              	<option value=""></option>
								<option value="1">BILLING ISSUES</option>
								<option value="5">SUPPLY RELATED</option>
								<option value="7">OTHERS</option>
								<option value="8">METER RELATED</option>
								<option value="9">TRANSFER OF OWNERSHIP AND SERVICE CONVERSION</option>
                          </select>
                          <label class="floating-label">Select Service/Complaint Type</label>	     
                    </div> 
                    <div class="form-group input-group col-sm-4 mx-auto mb-3" >
                          <select class="floating-select" name="cnature" id="cnature"  
                              onclick="this.setAttribute('value', this.value);" 
                              onchange="this.setAttribute('value', this.value); loadFormContainer(1);" value="">
                              <option value=""></option>
                          </select>
                          <label class="floating-label">Select Service/Complaint Nature</label>
                    </div>   
                    <br>
                    <!-- CLUBBING OF SERVICES  -->
                    <c:if test="${cnature eq '69'}">     
                     	<div class="form-group input-group col-sm-4 mx-auto mb-3" >
                          <label class="has-float-label">
                              <input class="form-control" type="number" id="club_nos" name="club_nos" placeholder=" " />
                              <span>No. of Services to be clubbed</span>
                          </label>
                    	</div>
                    </c:if>  
                    <br>
                    
                    <div class="row" style="display:none" id="noustabd">
                   		<div class="col-md-12">
                   			 <div class="table-responsive">
                   			 	 <table class="table table-bordered"  id="noustab" >
                   			 	 <tr class="td info">
                   			 	 	<th style="width:20%">Unique Service No.</th>
                   			 	 	<th>Name</th>
                   			 	 	<th>Address</th>
                   			 	 	<th>Category</th>
                   			 	 	<th>Load</th>
                   			 	 </tr>
                   			 	 </table>
                   			 </div>
                   		</div>
                    </div> 
                    
                    <c:if test="${cnature ne ''}">          
                   	<div class="row g-2">
                         <div class="col-sm-6">     
                             <div class="panel-heading eng">Service Request/Complaint Details</div>
                             <div class="rounded-box">
                                 <div class="form-group row p-3">
                                 	<!-- CLUBBING OF SERVICES  -->
                                     <c:if test="${cnature eq '69'}">     
                                     	<div class="form-group input-group col-sm-6">
                                           <select class="floating-select" id="is_adl_req" name="is_adl_req" 
                                                 onclick="this.setAttribute('value', this.value);" 
                                                 onchange="this.setAttribute('value', this.value);clubAdl();"
                                                 value="">
                                                 <option value=""></option>
                                                 <option value="Y" >Yes</option>
                                                 <option value="N" >No</option>
                                           </select>
                                           <label class="floating-label eng">Is Additional Load Required?</label>
                                     	</div> 
                                     	<div class="form-group input-group col-sm-6">
                                     	
                                     	</div>
                                     </c:if> 
                                 	<!-- ADDITIONAL LOAD OR CLUBBED SERVICES WITH ADDITIONAL LOAD  OR LOAD DERATION-->
                                 	<c:if test="${cnature eq '40' || cnature eq '69' || cnature eq '79'}">
                                 		<div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="number" id="additional_load" name="additional_load" onchange="getPayments()" placeholder=" " />
                                              <span id="lan_req_load">Additional Load Required in Watts</span>
                                          </label>
                                     	</div>
                                     	<c:if test="${cnature eq '40' || cnature eq '79'}">
                                      <div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="number" id="total_load" name="total_load" placeholder=" " readonly/>
                                              <span id="lan_tot_load">Total Load</span>
                                          </label>
                                      </div>
                                      </c:if> 
                                      <c:if test="${cnature eq '69'}">
                                      <div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="number" id="total_club_load" name="total_club_load" placeholder=" " readonly/>
                                              <span id="lan_tot_load">Total Clubbed Load</span>
                                          </label>
                                      </div>
                                      </c:if> 
                                      <div class="form-group input-group col-sm-6">
                                            <select class="floating-select" id="extension" name="extension" 
                                                onclick="this.setAttribute('value', this.value);" 
                                                onchange="this.setAttribute('value', this.value);getPayments();"
                                                value="">
                                                <option value=""></option>
                                                <option value="Y" >Yes</option>
                                                <option value="N" >No</option>
                                            </select>
                                            <label class="floating-label">Is Estimate Required ?</label>	     
                                   </div>    
                                   <div class="form-group input-group col-sm-6">
                                            <select class="floating-select" id="est_type" name="est_type" 
                                                onclick="this.setAttribute('value', this.value);" 
                                                onchange="this.setAttribute('value', this.value);"
                                                value="">
                                                <option value=""></option>
                                                <option value="Turn Key" >Consumer</option>
                                                <option value="Department" >Department</option>
                                            </select>
                                            <label class="floating-label eng">Work Execution by ?</label>	     
                                   </div>
                                 	</c:if>
                                 	<!-- ADDRESS CORRECTION -->
                                 	<c:if test="${cnature eq '41'}">
                                      <div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="text" id="naddress1" name="naddress1" placeholder=" " />
                                              <span>Door No., Building Name</span>
                                          </label>
                                      </div>
                                      <div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="text" id="naddress2" name="naddress2" placeholder=" " />
                                              <span>Street</span>
                                          </label>
                                      </div>   
                                   <div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="text" id="naddress3" name="naddress3" placeholder=" " />
                                              <span>Area</span>
                                          </label>
                                      </div>
                                      <div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="text" id="naddress4" name="naddress4" placeholder=" " />
                                              <span>Town</span>
                                          </label>
                                      </div>
                                     </c:if>
                                     <!-- CATEGORY CHANGE -->
                                     <c:if test="${cnature eq '42'}">
                                      <div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="text" id="exist_load" name="exist_load" placeholder=" " readonly/>
                                              <span>Existing Load</span>
                                          </label>
                                      </div>
                                      <div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="text" id="exist_cat" name="exist_cat" placeholder=" " readonly/>
                                              <span>Existing category</span>
                                          </label>
                                      </div>   
                                   <div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="text" id="new_load" name="new_load" placeholder=" " readonly/>
                                              <span>New Load</span>
                                          </label>
                                      </div>
                                      <div class="form-group input-group col-sm-6">
                                            <select class="floating-select" id="new_cat" name="new_cat" 
                                                onclick="this.setAttribute('value', this.value);" 
                                                onchange="this.setAttribute('value', this.value); afterCatChange();"
                                                value="">
                                                <option value=""></option>
                                            </select>
                                            <label class="floating-label">Required Category</label>	     
                                   </div>  
                                     </c:if>    
                                     <!-- TITLE TRANSFER -->
                                     <c:if test="${cnature eq '47'}">
                                      <div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="text" id="newtitle_name" name="newtitle_name" placeholder=" " />
                                              <span>Transferring title to </span>
                                          </label>
                                      </div>
                                      <div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="text" id="careOf" name="careOf" placeholder=" " />
                                              <span>S/o or W/o</span>
                                          </label>
                                      </div>    
                                     </c:if>  
                                          
                                     
                                     <c:if test="${cnature eq '40' or cnature eq '42' or cnature eq '69'}">    
                                      <div class="form-group input-group col-sm-6">
                                             <select class="floating-select" id="gstn_flag" name="gstn_flag" 
                                                 onclick="this.setAttribute('value', this.value);" 
                                                 onchange="this.setAttribute('value', this.value);gstnYes();"
                                                 value="">
                                                 <option value=""></option>
                                                 <option value="Y" >Yes</option>
                                                 <option value="N" >No</option>
                                             </select>
                                             <label class="floating-label eng">Is GSTIN Available?</label>
                                      </div>                     
                                      <div class="form-group input-group col-sm-6">
                                             <label class="has-float-label">
                                                 <input class="form-control" type="text" id="gstno" name="gstno" placeholder=" "
                                                  onkeyup="this.value = this.value.toUpperCase();"  oncopy="return false" onpaste="return false" onblur="verifygst();" />
                                                  <span id="lan_gst">GSTN Number</span>
                                             </label>
                                      </div>    
                                      <div class="form-group input-group col-sm-6">
                                             <label class="has-float-label">
                                                 <input class="form-control" type="text" id="cnfmgstno" name="cnfmgstno" placeholder=" "
                                                  onkeyup="this.value = this.value.toUpperCase();" oncopy="return false" onpaste="return false" onblur="confirmgst();"/>
                                            		<span id="lan_con_gst">Confirm GSTN Number</span>
                                             </label>
                                      </div> 
                                     </c:if>   
                                     <c:if test="${cnature ne ''}">                               
                                     <div class="form-group input-group col-sm-6">
                                          <label class="has-float-label">
                                              <input class="form-control" type="number" id="pincode" name="pincode" placeholder=" " />
                                              <span>PinCode</span>
                                          </label>
                                     </div> 
                                     </c:if>   
                                     
                                     <c:if test="${cnature eq '47' or cnature eq '41'}">
                                      <div class="form-group input-group col-sm-6">
                                          <select class="floating-select" name="idproof_type" id="idproof_type" 
                                              onclick="this.setAttribute('value', this.value);" 
                                              onchange="this.setAttribute('value', this.value);changeIdNumLabel(1);"
                                              value="">
                                              <option value=""></option>
                                              <option value='Aadhar copy'>Aadhar copy</option>
			                       		<option value='Driving Licence'>Driving Licence</option>
			                       		<option value='PAN Card'>PAN Card </option>
			                       		<option value='Ration Card'>Ration Card </option>
			                       		<option value='Voter Card'>Voter Card</option>
			                       		<option value='Passport'>Passport</option>
                                          </select>
                                          <label class="floating-label eng">ID Proof</label>
                                      </div> 
                                      <div class="form-group input-group col-sm-6">
                                            <select class="floating-select" id="regdoctype" name="regdoctype" 
                                                onclick="this.setAttribute('value', this.value);" 
                                                onchange="this.setAttribute('value', this.value); changeIdNumLabel(1);"
                                                value="">
                                                <option value=""></option>
                                                <option value='Registered Sale Deed'>Registered Sale Deed</option>
                      	                        <option value='Registered Gift Deed'>Registered Gift Deed</option>
			                        <option value='Registered Lease Deed'>Registered Lease Deed</option>
                                            </select>
                                            <label class="floating-label eng">Registration Document Type</label>
                                        </div>
                                        <div class="form-group input-group col-sm-12">
                                          <label class="has-float-label">  
                                              <input type="file" name="id_proof" id="id_proof" onchange="checkFile1()" accept="image/gif, image/jpeg">
                                              <span id="id_doc_label" style="padding-top:6px">ID Proof Copy 
                                                  <span style="color:red">(JPG/JPEG) Max.Size 100KB</span>
                                              </span>
                                          </label>
                                    </div>
                                    <div class="form-group input-group col-sm-12">
                                          <label class="has-float-label">  
                                              <input type="file" name="registration_proof" id="registration_proof"  onchange="checkFile2()" accept="application/pdf">
                                              <span id="doctype" style="padding-top:6px">Registered Document 
                                              	<span style="color:red">(PDF) Max.Size 5MB</span>
                                              </span>
                                          </label>
                                      </div> 
                                     </c:if> 
                                 </div>

                             </div>
                         </div>
                            
                         <c:if test="${cnature eq '47' or cnature eq '41'}">
							<%@include  file="paymentDiv.html"  %>
						 </c:if> 
						 <c:if test="${cnature eq '40' or cnature eq '42' or cnature eq '69'}">
							<%@include  file="paymentDiv2.html"  %>
						 </c:if> 
                    </div> 
                    </c:if>              
                    
                    <c:if test="${cnature ne ''}">  	
                   	<div class="new_reg_button-container">
                            <div style="display: flex;">
                                <div class="new_reg__button">
                                    <button type="button"  id="btnsubmit" class="new_reg_btn eng">Proceed</button>
                                </div>
                            </div>
	                </div>
	                </c:if>  
                    </div> 	
                </div>	
                       
        </section>
        <!-- category-section end -->
        
        </form>

	    
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
    </div>
   
 	
  	<!--  modal help Start-->
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
         				<div>Please Enter your USC No. shown on your Electricity Bill</div>  				
         			</div>
        		</div>
        	</div>
    	</div>
  	</div> 	
  	<!--  modal help End-->
  	
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
    <script src="${pageContext.request.contextPath}/script/scriptCC.js"></script>
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
	   	  	  	  
	   	getServiceDetailsFinal();
	   //	showCatChange();
	   	//setIntialSetup(1);
	   
	   });
	   
   	   $("#btnsubmit").click(function(e){
      	 	finalSubmit(1);
   		});
   	   
	   	var nocusn=0;
		$("#club_nos").blur(function(){
			if(nocusn!=$("#club_nos").val()){
				nocusn=$("#club_nos").val();
				if($("#club_nos").val().length>0){
					$("#noustabd").show();
					var table = document.getElementById("noustab");
					for(var i=1;i<=$("#club_nos").val();i++){
						var row = table.insertRow(i);
				    	var cell1 = row.insertCell(0);
				    	var cell2 = row.insertCell(1);
				    	var cell3 = row.insertCell(2);
				    	var cell4 = row.insertCell(3);
				    	var cell5 = row.insertCell(4);
				    	cell1.innerHTML="<td><div class='col-md-12'><div class='input-group mb-4 form-group' > <input id='clbukscno"+i+"' type='text' name='clbukscno' class='form-control' placeholder='Unique Service No'  required onblur='getClubUkscnoDetails(this.id,"+i+")' inputmode='numeric' pattern='[0-9]*'/><span id='clbtmb"+i+"'></span></div></div></td>";
				    	cell2.innerHTML="<td><span class='text-bold text-success' id='dukname"+i+"'></span><input type='hidden'  id='clbukname"+i+"' name='clbukname'></td>";
				    	cell3.innerHTML="<td><span class='text-bold text-success' id='dukaddr"+i+"'></span><input type='hidden'  id='clbukaddr"+i+"' name='clbukaddr'></td>";
				    	cell4.innerHTML="<td><span class='text-bold text-success' id='dukcat"+i+"'></span><input type='hidden'  id='clbukcat"+i+"' name='clbukcat'></td>";
				    	cell5.innerHTML="<td><span class='text-bold text-success' id='dukload"+i+"'></span><input type='hidden' id='clbukload"+i+"' name='clbukload'></td>";
					}
					$("#club_nos").prop('readonly', true);
					$("#cnature").prop('readonly', true);
					$("#com_type").prop('readonly', true);
				}
			}
		});

    </script>    
    	
    <script>
	    function myFunction1() {
	        //alert("myDropdown1");
	        document.getElementById("myDropdown1").classList.toggle("show");
	       
	    }
	    
	    function myFunction2() {
	        //alert("myDropdown2");
	        document.getElementById("myDropdown2").classList.toggle("show");
	    } 
    </script>
    
</body><!-- End of .page_wrapper -->
</html>
