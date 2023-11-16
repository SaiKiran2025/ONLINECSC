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
                    <div class="form-group input-group col-sm-4 mx-auto mb-3" >
                          <label class="has-float-label">
                               <input class="form-control" type="text" id="cdetails" name="cdetails" placeholder=" " />
                               <span>Enter Complaint Details in brief</span>
                           </label>
                    </div>
                    <c:if test="${ctype eq '1'}"> 
                      <div class="form-group input-group col-sm-4 mx-auto mb-3">
                          <label class="has-float-label">  
                              <input type="file" name="id_proof" id="id_proof" accept="image/gif, image/jpeg">
                              <span id="id_doc_label" style="padding-top:6px">Document1
                                  <span style="color:red">(JPG/JPEG) Max.Size 100KB</span>
                              </span>
                          </label>
                   	  </div>
                      <div class="form-group input-group col-sm-4 mx-auto mb-3">
                          <label class="has-float-label">  
                              <input type="file" name="registration_proof" id="registration_proof"   accept="application/pdf">
                              <span id="doctype" style="padding-top:6px">Document2
                              	<span style="color:red">(PDF) Max.Size 5MB</span>
                              </span>
                          </label>
                    </div>
                    </c:if>
                    
                    <c:if test="${cnature eq '48'}"> 
                      <div class="form-group input-group col-sm-4 mx-auto mb-3">
                          <label class="has-float-label">  
                              <input type="file" name="id_proof" id="id_proof" accept="image/gif, image/jpeg">
                              <span id="id_doc_label" style="padding-top:6px">ID Proof Copy 
                                  <span style="color:red">(JPG/JPEG) Max.Size 100KB</span>
                              </span>
                          </label>
                   	  </div>
                      <div class="form-group input-group col-sm-4 mx-auto mb-3">
                          <label class="has-float-label">  
                              <input type="file" name="registration_proof" id="registration_proof"   accept="application/pdf">
                              <span id="doctype" style="padding-top:6px">Registered Document 
                              	<span style="color:red">(PDF) Max.Size 5MB</span>
                              </span>
                          </label>
                      </div>
                    </c:if>
                    
                    <c:if test="${cnature eq '81' or cnature eq '65' or cnature eq '45' or cnature eq '70' or cnature eq '64' or cnature eq '62' or cnature eq '68' or cnature eq '48'}"> 
                    <div class="form-group input-group col-sm-4 mx-auto mb-3" style="display:block" >                       
                        <div class="rounded-box" >
	                       	<div class="container p-3">
	                          <div class="row p-1" style="flex-wrap: unset;">
	                              <div class="col-sm-8">Application Fee</div>
	                              <div class="col-sm-4 text-right" id="sc">${cnpm.cnature_af}</div>
	                          </div>
	                          <div class="row p-1" style="flex-wrap: unset;">
	                              <div class="col-sm-8">GST on Application Fee</div>
	                              <div class="col-sm-4 text-right" id="scgst">${cnpm.cnature_afgst}</div>
	                          </div>
	                          <div class="row p-1 bg-light" style="flex-wrap: unset;font-weight:bold">
	                              <div class="col-sm-8">Total</div>
	                              <div class="col-sm-4 text-right" id="sctot">${cnpm.cnature_total}</div>
	                          </div>
	                   		</div>  
	                   	</div>            
                    </div>                    	
                    </c:if>
                    
                    <c:if test="${cnature eq '53' or cnature eq '54' or ctype eq '8' }"> 
                    <div class="form-group input-group col-sm-4 mx-auto mb-3" style="display:block" >                       
                        <div class="rounded-box" >
	                       	<div class="container p-3">
	                          <div class="row p-1" style="flex-wrap: unset;">
	                              <div class="col-sm-8">Service Charges</div>
	                              <div class="col-sm-4 text-right" id="sc">${cnpm.cnature_sc}</div>
	                          </div>
	                          <div class="row p-1" style="flex-wrap: unset;">
	                              <div class="col-sm-8">GST on Service Charges</div>
	                              <div class="col-sm-4 text-right" id="scgst">${cnpm.cnature_scgst}</div>
	                          </div>
	                          <div class="row p-1 bg-light" style="flex-wrap: unset;font-weight:bold">
	                              <div class="col-sm-8">Total</div>
	                              <div class="col-sm-4 text-right" id="sctot">${cnpm.cnature_total}</div>
	                          </div>
	                   		</div>  
	                   	</div>            
                    </div>                    	
                    </c:if>
                    
                    <div class="form-group input-group col-sm-4 mx-auto mb-3" style="display:none" id="pay73" >                       
                        <div class="rounded-box" >
	                       	<div class="container p-3">
	                          <div class="row p-1" style="flex-wrap: unset;">
	                              <div class="col-sm-8">Reconnection Charges</div>
	                              <div class="col-sm-4 text-right" id="af2">75.0</div>
	                          </div>
	                   		</div>  
	                   	</div>            
                    </div>  
                    	
                   	<div class="new_reg_button-container">
                          <div style="display: flex;">
                              <div class="new_reg__button">
                                  <button type="button"  id="btnsubmit1" class="new_reg_btn eng">Proceed</button>
                              </div>
                          </div>
	                </div>
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
   	   
   		$("#btnsubmit1").click(function(e){
  	 		finalSubmit1(1);
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
