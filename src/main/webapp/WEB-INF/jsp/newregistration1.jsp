<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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
                    <h1><i class="icon-1"></i>LT-New Service Connection Registration</h1>
                   <!--  కొత్త సర్వీస్ కనెక్షన్ నమోదు
                    नई सेवा कनेक्शन पंजीकरण -->
                    <div class="language-picker" id="languagePicker">
                        <button onclick="myFunction1()" class="dropbtn">
                            <i class="fa fa-globe"></i>
                            English
                            <i class="fa fa-caret-down"></i>
                        </button>
                        <div id="myDropdown1" class="dropdown-content">
                            <a href="#" id="en">English</a>
                            <a href="#" id="te">తెలుగు</a>
                            <a href="#" id="hi">हिंदी</a>
                        </div>
                    </div>
                    <div class="language-picker" id="languagePicker_Icon">
                        <button onclick="myFunction2()" class="dropbtn">
                            <i class="fa fa-globe"></i>
                        </button>
                       
                        <div id="myDropdown2" class="dropdown-content">
                            <a href="#" id="en">English</a>
                            <a href="#" id="te">తెలుగు</a>
                            <a href="#" id="hi">हिंदी</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--End Page Title-->
        <form name="newreg" method="POST">
		<input type="hidden" name="area_name" />
		<input type="hidden" name="section_code" />
		<input type="hidden" name="ero_code" />
		<input type="hidden" name="csc_number" />
		<input type="hidden" name="area_group" />
        <!-- category-section -->
        <section class="category-section category-page mr-0 pt-60 pb-90">
            <div class="auto-container">
                <div class="wow slideInLeft animated" data-wow-delay="00ms" data-wow-duration="2000ms">
                    <div class="container-fluid">
                    	<div class="rounded-box">
                    		<div class="row g-2">
                            	<div class="col-sm-12">
                            	
                                    <div class="row p-1 justify-content-center">
                                    	<div class="existing-services text-danger pt-2">
		                       				Are there any Service Connections Existing in the same premises with same Name and Address ?
		                       				<label class="radio-inline" for="scnoexty">
      											<input type="radio" name="scnoext" id="scnoexty" value="yes">Yes
    										</label>
    										<label class="radio-inline">
      											<input type="radio" name="scnoext" id="scnoextn" value="no">No
    										</label>
										    
				                      	</div>
                                	</div>
                                </div>
                           	</div>
                           	<div class="row g-2">
                            	<div class="col-sm-12">
                            	 	<div class="row d-flex justify-content-center p-1">
                                		<div class="form-group input-group col-sm-4"  id="d-adjscno">
                                        	<label class="has-float-label">
                                           		<input class="form-control" type="text" id="adj_uscno" name="adj_uscno" placeholder=" " onblur="checkUSCNO(1);"/>
                                           		<span>Adjacent Unique Service Number</span>
                                        	</label>
                                        	<span class="input-group-addon">
                                        		<button class="search-button" type="button" data-toggle="tooltip" data-placement="bottom" title="Search Unique SC.No." onclick="checkUSCNO(1);return true;">
                                        		<i class="fa fa-search"></i>
                                        		</button>
                                        	</span>
                                        </div>
                                        <div class="form-group input-group col-sm-4" id="d-secname">
                                   			<input class="form-control alert alert-primary custom-alert" type="text" id="adjsecname" name="adjsecname" value="Find your Section Name here" readonly/>
                                           	<!-- <button type="button" class="btn btn-primary custom-btn" id="adjsecname" name="adjsecname">Section Name</button> -->
                                        </div>
                                        <div class="form-group input-group col-sm-3"  id="d-noexistscnos">
                                        	<label class="has-float-label">
                                            	<input class="form-control" type="number" id="noscnos" name="noscnos" placeholder=" " maxlength="1"  data-toggle="tooltip" data-placement="left" />
                                               	<span>No.of Existing Service Connections</span>
                                          	</label>
                                        </div>	
                                        <div class="new_check_button" id="d-btn-check">
                                        	<button type="button" class="new_reg_btn">Check</button>
                                       	</div>
                                    </div>
                                </div>       	
                            </div>           	
                            <span id="addscnos"></span>
                        </div>	       
                        <div id="form-container">        
                        <div class="row g-2 wow slideInLeft animated" data-wow-delay="00ms" data-wow-duration="2000ms">
                            <div class="col-sm-6">
                                <div class="panel-heading eng">Applicant Details</div>
                                <div class="panel-heading tel">దరఖాస్తుదారు వివరాలు</div>
                                <div class="panel-heading hin">आवेदक का विवरण</div>
                                <div class="rounded-box">
                                    <div class="form-group row p-3">
                                        <div class="form-group input-group col-sm-4">
                                            <select class="floating-select" name="idproof_type" id="idproof_type" 
                                                onclick="this.setAttribute('value', this.value);" 
                                                onchange="this.setAttribute('value', this.value);changeIdNumLabel(1);"
                                                value="">
                                                <option value=""></option>
                                                <option value='AC'>Aadhar Card</option>
                                                <option value='DL'>Driving Licence</option>
                                                <option value='PC'>PAN Card </option>
                                                <option value='RC'>Ration Card </option>
                                                <option value='VC'>Voter Card</option>
                                                <option value='PP'>Passport</option>
                                            </select>
                                            <!-- <label class="floating-label">ఐడిని ఎంచుకోండి</label> -->
                                            <label class="floating-label eng">ID Proof</label>
                                            <label class="floating-label tel">ఐడిని ఎంచుకోండి</label>
                                            <label class="floating-label hin">आईडी प्रूफ चुनें</label>
                                        </div> 
                                        <div class="form-group input-group col-sm-8">
                                            <span class="input-group-addon"><i class="fa fa-address-card-o"></i></span>
                                            <label class="has-float-label">
                                                <input class="form-control" type="text" id="idproof_no" name="idproof_no" placeholder=" "/>
                                                <span id="lan_id_no"></span>
                                            </label>
                                            <!-- <span class="input-group-addon"><i class="fa fa-address-card-o"></i></span>
                                            <input class="floating-input" id="idproofno" name="idproofno" type="text" placeholder=" ">
                        	                <label class="floating-label eng">Aadhar ID Proof Number</label>
                                            <label class="floating-label tel">ఆధార్ సంఖ్యను నమోదు చేయండి</label>
                                            <label class="floating-label hin">आधार नंबर दर्ज करें</label> -->
                                        </div>
                                        <div class="form-group input-group col-sm-4">
                                            <select class="floating-select" id="gender" name="gender" 
                                                onclick="this.setAttribute('value', this.value);" 
                                                onchange="this.setAttribute('value', this.value);"
                                                value="">
                                                <option value=""></option>
                                                <option value="Mr">Mr.</option>
                                                <option value="Mrs">Mrs.</option>
                                                <option value="Miss">Miss.</option>
                                                <option value="Others">Others</option>
                                            </select>
                                            <label class="floating-label eng">Honorifics</label>
                                            <label class="floating-label tel">గౌరవప్రదములు</label>
                                            <label class="floating-label hin">माननीय</label>
                                        </div>       
                                        <div class="form-group input-group col-sm-8">
                                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                            <label class="has-float-label">
                                                <input class="form-control" type="text" id="applicant_name" name="applicant_name" placeholder=" "/>
                                                <span id="lan_app_name"></span>
                                            </label>
                                        </div> 
                                    </div>
                                </div> 
                            </div> 
                            <div class="col-sm-6">
                                <div class="panel-heading"></div>
                                <div class="rounded-box">
                                    <div class="form-group row p-3">      
                                        <div class="form-group input-group col-sm-8">
                                            <span class="input-group-addon"><i class="fa fa-users"></i></span>
                                            <label class="has-float-label">
                                                <input class="form-control" type="text" id="father_name" name="father_name" placeholder=" "/>
                                                <span id="lan_father_name"></span>
                                            </label>
                                        </div> 
                                        <div class="form-group input-group col-sm-4">
                                            <select class="floating-select" id="social_grp" name="social_grp" 
                                                onclick="this.setAttribute('value', this.value);" 
                                                onchange="this.setAttribute('value', this.value);"
                                                value="">
                                                <option value=""></option>
                                                <option value="SC">SC</option>
                                                <option value="ST">ST</option>
                                                <option value="OBC">OBC</option>
                                                <option value="GENERAL">General</option>
                                            </select>
                                            <label class="floating-label eng">Social Group</label>
                                            <label class="floating-label tel">సోషల్ గ్రూప్</label>
                                            <label class="floating-label hin">सोशल ग्रुप</label>
                                        </div>    
                                        <div class="form-group input-group col-sm-6">	
                                        	<label class="has-float-label">
                                                <input class="form-control" type="text" id="secname" name="secname" placeholder=" " />
                                                <span id="lan_sec_name"></span>
                                            </label>
                                        </div>  
                                        <div class="form-group input-group col-sm-6">
                                            <select class="floating-select" name="area_code" id="area_code"  
                                                onclick="this.setAttribute('value', this.value);" 
                                                onchange="this.setAttribute('value', this.value); getArea();"
                                                value="">
                                                <option value=""></option>
                                            </select>
                                            <label class="floating-label eng">Area/Village Name</label>
                                            <label class="floating-label tel">ఏరియా/గ్రామం పేరు</label>
                                            <label class="floating-label hin">एरिया/गाँव नाम</label>
                                        </div>   
                                    </div>
                                </div>
                            </div>
                        </div>  
                        
                        <div class="row g-2 wow slideInLeft animated" data-wow-delay="00ms" data-wow-duration="2000ms">
                            <div class="col-sm-6">
                                <div class="panel-heading eng">Address for Connection</div>
                                <div class="panel-heading tel">కనెక్షన్ కోసం చిరునామా</div>
                                <div class="panel-heading hin">कनेक्शन के लिए पता</div>

                                <div class="rounded-box">
                                    <div class="form-group row p-3">
                                        <div class="form-group input-group col-sm-6">
                                            <span class="input-group-addon"><i class="fa fa-home"></i></span>
                                            <label class="has-float-label">
                                                <input class="form-control" type="text" id="conn_address1" name="conn_address1" placeholder=" "/>
                                                <span id="lan_con_addr1"></span>
                                            </label>
                                        </div>    
                                        <div class="form-group input-group col-sm-6">
                                            <!-- <span class="input-group-addon"><i class='fas fa-landmark'></i></span> -->
                                            <label class="has-float-label">
                                                <input class="form-control" type="text" id="conn_address2" name="conn_address2" placeholder=" "/>
                                                <span id="lan_con_addr2"></span>
                                            </label>
                                        </div>    
                                        <div class="form-group input-group col-sm-6">
                                            <span class="input-group-addon"><i class="fa fa-map-marker"></i></span>
                                            <label class="has-float-label">
                                                <input class="form-control" type="text" id="conn_address3" name="conn_address3" placeholder=" "/>
                                                <span id="lan_con_addr3"></span>
                                            </label>
                                        </div>    
                                        <div class="form-group input-group col-sm-6">
                                            <!-- <span class="input-group-addon"><i class="fa fa-mobile" style="font-size:20px"></i></span> -->
                                            <label class="has-float-label">
                                                <input class="form-control" type="text" id="conn_address4" name="conn_address4" placeholder=" "/>
                                                <span id="lan_con_addr4"></span>
                                            </label>
                                        </div>
                                        <div class="form-group input-group col-sm-6">
                                            <span class="input-group-addon"><i class="fa fa-file-zip-o"></i></span>
                                            <label class="has-float-label">
                                                <input class="form-control" type="text" id="conn_pincode" name="conn_pincode" placeholder=" "/>
                                                <span id="lan_con_pin"></span>
                                            </label>
                                        </div>    
                                        <div class="form-group input-group col-sm-6">
                                            <span class="input-group-addon"><i class="fa fa-mobile" style="font-size:20px"></i></span>
                                            <label class="has-float-label">
                                                <input class="form-control" type="text" id="conn_phoneno" name="conn_phoneno" placeholder=" "/>
                                                <span id="lan_con_mobile"></span>
                                            </label>
                                        </div>      
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-6">
                            	<div class="panel-heading">
                            		<div class="eng pull-left">Address for Communication</div>
                            		<div class="tel pull-left">కమ్యూనికేషన్ కోసం చిరునామా</div>
                                	<div class="hin pull-left">संचार के लिए पता</div>
        							<div class="custom-control custom-switch pull-right">
        								<input type="checkbox" class="custom-control-input heading" id="same_addr" name="same_addr" onclick="sameAddress();">
							        	<label class="custom-control-label heading" for="same_addr">Same as Connection Address</label>
							    	</div>
        							
                                	
                                </div>	
                                    <div class="rounded-box">
                                        <div class="form-group row p-3">
                                            <div class="form-group input-group col-sm-6">
                                                <span class="input-group-addon"><i class="fa fa-home"></i></span>
                                                <label class="has-float-label">
                                                    <input class="form-control" type="text" id="comm_address1" name="comm_address1" placeholder=" "/>
                                                    <span id="lan_com_addr1"></span>
                                                </label>
                                            </div>    
                                            <div class="form-group input-group col-sm-6">
                                                <!-- <span class="input-group-addon"><i class='fas fa-landmark'></i></span> -->
                                                <label class="has-float-label">
                                                    <input class="form-control" type="text" id="comm_address2" name="comm_address2" placeholder=" "/>
                                                    <span id="lan_com_addr2"></span>
                                                </label>
                                            
                                            </div>    
                                                                              
                                            <div class="form-group input-group col-sm-6">
                                                <span class="input-group-addon"><i class="fa fa-map-marker"></i></span>
                                                <label class="has-float-label">
                                                    <input class="form-control" type="text" id="comm_address3" name="comm_address3" placeholder=" "/>
                                                    <span id="lan_com_addr3"></span>
                                                </label>
                                            </div>    
                                            <div class="form-group input-group col-sm-6">
                                                <!-- <span class="input-group-addon"><i class="fa fa-mobile" style="font-size:20px"></i></span> -->
                                                <label class="has-float-label">
                                                    <input class="form-control" type="text" id="comm_address4" name="comm_address4" placeholder=" "/>
                                                    <span id="lan_com_addr4"></span>
                                                </label>
                                            </div>
                                            <div class="form-group input-group col-sm-6">
                                                <span class="input-group-addon"><i class="fa fa-file-zip-o"></i></span>
                                                <label class="has-float-label">
                                                    <input class="form-control" type="text" id="comm_pincode" name="comm_pincode" placeholder=" "/>
                                                    <span id="lan_com_pin"></span>
                                                </label>
                                            </div>    
                                            <div class="form-group input-group col-sm-6">
                                                <span class="input-group-addon"><i class="fa fa-mobile" style="font-size:20px"></i></span>
                                                <label class="has-float-label">
                                                    <input class="form-control" type="text" id="comm_phoneno" name="comm_phoneno" placeholder=" "/>
                                                    <span id="lan_com_mobile"></span>
                                                </label>
                                            </div>      
                                        </div>
                                    </div>
                                </div>    
                            </div>
                         
                        	<div class="row g-2 wow slideInLeft animated" data-wow-delay="00ms" data-wow-duration="2000ms">
                            	<div class="col-sm-6">
	                                <div class="panel-heading eng">Connection Details</div>
	                                <div class="panel-heading tel">కనెక్షన్ వివరాలు</div>
	                                <div class="panel-heading hin">कनेक्शन का विवरण</div>
	                                <div class="rounded-box" style="min-height:22.3rem">
	                                    <div class="form-group row p-3">
	                                        <div class="form-group input-group col-sm-6">
	                                            <select class="floating-select" id="category" name="category" 
	                                                onclick="this.setAttribute('value', this.value);" 
	                                                onchange="this.setAttribute('value', this.value);getSubCategory(1);"
	                                                value="">
	                                                <option value=""></option>
	                                                <option value='1'>1-DOMESTIC</option>
	                                                <option value='2'>2-NON-DOMESTIC AND COMMERCIAL</option>
	                                                <option value='3'>3-INDUSTRIAL</option>
	                                                <option value='4'>4-COTTAGE INDUSTRIES AND DHOBIGHATS</option>
	                                                <option value='5'>5-AGRICULTURAL</option>
	                                                <option value='6'>6-LOCALBODIES-STREETLIGHT/PWS SCHEME</option>
	                                                <option value='7'>7-GENERAL PURPOSE</option>
	                                                <option value='8'>8-TEMPORARY SUPPLY</option>
	                                                <option value='9'>9-EV CHARGING STATIONS</option>
	                                            </select>
	                                            <label class="floating-label eng">Category</label>
	                                            <label class="floating-label tel">కేటగిరి</label>
	                                            <label class="floating-label hin">केटेगरी</label>
	                                        </div>
	                                        <div class="form-group input-group col-sm-6" id="subcat-div">
	                                            <select class="floating-select" id="sub_category" name="sub_category" 
	                                                onclick="this.setAttribute('value', this.value);" 
	                                                onchange="this.setAttribute('value', this.value);getCode1(1);"
	                                                value="">
	                                                <option value=""></option>
	                                            </select>
	                                            <label class="floating-label eng">Sub-Category</label>
	                                            <label class="floating-label tel">సబ్-కేటగిరి</label>
	                                            <label class="floating-label hin">सब-केटेगरी</label>
	                                        </div>        
	                                        <div class="form-group input-group col-sm-6">
	                                            <label class="has-float-label">
	                                                <input class="form-control" type="text" id="conn_load" name="conn_load" placeholder=" "/>
	                                                <span id="lan_con_load"></span>
	                                               
	                                            </label> 
	                                            <span class="input-group-addon"><button class="load-calc-button" type="submit" data-toggle="tooltip" data-placement="bottom" title="Load Calculator"><i class="fa fa-calculator"></i></button></span>
	                                        </div>
	                                        <div class="form-group input-group col-sm-6" id="serv-type-div">
	                                            <select class="floating-select" id="serv_type" name="serv_type" 
	                                                onclick="this.setAttribute('value', this.value);" 
	                                                onchange="this.setAttribute('value', this.value);getCode2(1);"
	                                                value="">
	                                                <option value=""></option>
	                                                <option value="G">Government</option>
	                                                <option value="NG">Non-Government</option>
	                                                <option value="D">Department (TSSPDCL)</option>
	                                            </select>
	                                            <label class="floating-label eng">Service Type</label>
	                                            <label class="floating-label tel">సర్విస్ టైపు</label>
	                                            <label class="floating-label hin">सर्विस टाइप</label>
	                                        </div>
	                                       	<div class="form-group input-group" id="gov-hod-div"  style="display:none">
		                                        <div class="form-group input-group col-sm-6">
		                                            <select class="floating-select" id="gov_code" name="gov_code" 
		                                                onclick="this.setAttribute('value', this.value);" 
		                                                onchange="this.setAttribute('value', this.value);getHodCode(1);"
		                                                value="">
		                                                <option value=""></option>
		                                            </select>
		                                            <label class="floating-label eng">Government Code</label>
		                                            <label class="floating-label tel">గవర్నమెంట్ కోడ్</label>
		                                            <label class="floating-label hin">गवर्नमेंट कोड</label>
		                                        </div>
		                                        <div class="form-group input-group col-sm-6">
		                                            <select class="floating-select" id="hod_code" name="hod_code" 
		                                                onclick="this.setAttribute('value', this.value);" 
		                                                onchange="this.setAttribute('value', this.value);"
		                                                value="">
		                                                <option value=""></option>
		                                            </select>
		                                            <label class="floating-label eng">HOD Code</label>
		                                            <label class="floating-label tel">హెచ్ఒడి కోడ్</label>
		                                            <label class="floating-label hin">हेचवोडी कोड</label>
		                                        </div> 
	                                       	</div>
	                                       	<div class="form-group input-group" id="temp-date-div" style="display:none">
		                                        <div class="form-group input-group col-sm-6">
		                                            <label class="has-float-label">  
		                                                <input id="temp_fmdt" name="temp_fmdt" class="date form-control" data-date-format="dd/mm/yyyy" type="date" placeholder=" " data-inputmask-alias="date" />
		                                                <span>Temp.Supply Starting Date</span>
		                                            </label>
		                                        </div>
		                                        <div class="form-group input-group col-sm-6">
		                                            <label class="has-float-label">
		                                                <input id="temp_todt" name="temp_todt" class="date form-control" data-date-format="dd/mm/yyyy" type="date" placeholder=" " data-inputmask-alias="date" />
		                                                <span>Temp.Supply Ending Date</span>
		                                            </label>
		                                        </div>
	                                        </div>
	                                        <div class="form-group input-group col-sm-6">
	                                            <select class="floating-select" id="phase" name="phase" 
	                                                onclick="this.setAttribute('value', this.value);" 
	                                                onchange="this.setAttribute('value', this.value);"
	                                                value="">
	                                                <option value=""></option>
	                                                <option value="1" >1</option>
	                                                <option value="3" >3</option>
	                                            </select>
	                                            <label class="floating-label eng">Phase</label>
	                                            <label class="floating-label tel">ఫేజ్</label>
	                                            <label class="floating-label hin">फेज</label>
	                                        </div>        
	                                        <div class="form-group input-group col-sm-6">
	                                            <select class="floating-select" id="location_type" name="location_type" 
	                                                onclick="this.setAttribute('value', this.value);" 
	                                                onchange="this.setAttribute('value', this.value);"
	                                                value="">
	                                                <option value=""></option>
	                                                <option value="1">Urban</option>
	                                                <option value="2">Rural</option>
	                                            </select>
	                                            <label class="floating-label eng">Location</label>
	                                            <label class="floating-label tel">లొకేషన్</label>
	                                            <label class="floating-label hin">लोकेशन</label>
	                                        </div>  
	                                        <div class="form-group input-group col-sm-6">
	                                            <select class="floating-select" id="sez" name="sez" 
	                                                onclick="this.setAttribute('value', this.value);" 
	                                                onchange="this.setAttribute('value', this.value);"
	                                                value="">
	                                                <option value=""></option>
	                                                <option value="Y" >Yes</option>
	                                                <option value="N" >No</option>
	                                            </select>
	                                            <label class="floating-label eng">Is it SE Zone (SEZ)?</label>
	                                            <label class="floating-label tel">అది SE జోన్ లో ఉందా?</label>
	                                            <label class="floating-label hin">क्या यह SE ज़ोन है?</label>
	                                        </div>  
	                                        <div class="form-group input-group col-sm-6">
	                                            <select class="floating-select" id="comnserv" name="comnserv" 
	                                                onclick="this.setAttribute('value', this.value);" 
	                                                onchange="this.setAttribute('value', this.value);"
	                                                value="">
	                                                <option value=""></option>
	                                                <option value="Y" >Yes</option>
	                                                <option value="N" >No</option>
	                                            </select>
	                                            <label class="floating-label eng">Is It Common Connection?</label>
	                                            <label class="floating-label tel">ఇది కామన్ కనెక్షనేనా?</label>
	                                            <label class="floating-label hin">क्या यह सामान्य कनेक्शन है?</label>
	                                        </div>  
	                                   </div>
                                	</div>
                            	</div>
	                            <div class="col-sm-6">
	                                <div class="panel-heading eng">Registration Details</div>
	                                <div class="panel-heading tel">రిజిస్ట్రేషన్ వివరాలు</div>
	                                <div class="panel-heading hin">रजिस्ट्रेशन का विवरण</div>
	                                    <div class="rounded-box" style="min-height:22.3rem">
	                                        <div class="form-group row p-3">
	                                        	<div class="form-group input-group" id="constatus-div"  style="display:none">
		                                            <div class="form-group input-group col-sm-6">
		                                                <select class="floating-select" id="cons_status" name="cons_status" 
		                                                    onclick="this.setAttribute('value', this.value);" 
		                                                    onchange="this.setAttribute('value', this.value);"
		                                                    value="">
		                                                    <option value=""></option>
		                                                    <option value="1">Individual</option>
		                                                    <option value="2">Registered PartnerShip</option>
		                                                    <option value="3">UnRegistered PartnerShip</option>
		                                                    <option value="4">Private Company</option>
		                                                    <option value="5">Limited Company</option>
		                                                    <option value="7">Any Other</option>
		                                                    <option value="8">Government Institutions</option>
		                                                </select>
		                                                <label class="floating-label eng">Consumer Status</label>
		                                                <label class="floating-label tel">కన్సుమర్ స్టేటస్</label>
		                                                <label class="floating-label hin">कंस्यूमर स्टेटस</label>
		                                            </div>  
		                                        </div>    
		                                        <div class="form-group input-group" id="gstn-y-n-div"  style="display:none">
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
		                                                <label class="floating-label tel">GSTIN అందుబాటులో ఉందా?</label>
		                                                <label class="floating-label hin">क्या GSTIN उपलब्ध है?</label>
		                                            </div>  
		                                        </div>  
		                                        <div class="form-group input-group" id="gstn-y-div" style="display:none">  
		                                            <div class="form-group input-group col-sm-3">
		                                                <label class="has-float-label">
		                                                    <input class="form-control" type="text" id="gstno" name="gstno" placeholder=" "
		                                                     onkeyup="this.value = this.value.toUpperCase();" oncopy="return false" onpaste="return false"/>
		                                                     <span id="lan_gst"></span>
		                                                </label>
		                                            </div>    
		                                            <div class="form-group input-group col-sm-3">
		                                                <label class="has-float-label">
		                                                    <input class="form-control" type="text" id="cnfmgstno" name="cnfmgstno" placeholder=" "
		                                                     onkeyup="this.value = this.value.toUpperCase();" oncopy="return false" onpaste="return false" />
		                                               		<span id="lan_con_gst"></span>
		                                                </label>
		                                            </div>   
	                                            </div>
	                                            <div class="form-group input-group" id="municipal-div" style="display:none">
		                                            <div class="form-group input-group col-sm-6">
		                                                <select class="floating-select" id="municipality" name="municipality" 
		                                                    onclick="this.setAttribute('value', this.value);" 
		                                                    onchange="this.setAttribute('value', this.value);"
		                                                    value="">
		                                                    <option value=""></option>
		                                                </select>
		                                                <label class="floating-label eng">Muncipality</label>
		                                                <label class="floating-label tel">మున్సిపాలిటీ</label>
		                                                <label class="floating-label hin">मुन्सिपलिटी</label>
		                                            </div> 
		                                        </div>
		                                        <div class="form-group input-group" id="eqpoints-div" style="display:none">    
		                                            <div class="form-group input-group col-sm-6">
		                                                <label class="has-float-label">
		                                                    <input class="form-control" type="text" id="eqpoints" name="eqpoints" placeholder=" "/>
		                                                    <span id="lan_eq_points"></span>
		                                                </label>
		                                            </div>   
		                                        </div>    
	                                            <div class="form-group input-group col-sm-6">
	                                                <select class="floating-select" id="extension" name="extension" 
	                                                    onclick="this.setAttribute('value', this.value);" 
	                                                    onchange="this.setAttribute('value', this.value);estimateReq();"
	                                                    value="">
	                                                    <option value=""></option>
	                                                    <option value="Y" >Yes</option>
	                                                    <option value="N" >No</option>
	                                                </select>
	                                                <label class="floating-label eng">Laying of Service Line Required?</label>
	                                                <label class="floating-label tel">సర్వీస్ లైన్ వేయడం అవసరమా?</label>
	                                                <label class="floating-label hin">सर्विस लाइन बिछाने की आवश्यकता है?</label>
	                                            </div> 
	                                            <div class="form-group input-group col-sm-6">
	                                                <select class="floating-select" id="regdoc" name="regdoc" 
	                                                    onclick="this.setAttribute('value', this.value);" 
	                                                    onchange="this.setAttribute('value', this.value); RegisterDoc(1);"
	                                                    value="">
	                                                    <option value=""></option>
	                                                    <option value="Y" >Yes</option>
	                                                    <option value="N" >No</option>
	                                                </select>
	                                                <label class="floating-label eng">Registration Documents of Premises?</label>
	                                                <label class="floating-label tel">ప్రాంగణాల రిజిస్ట్రేషన్ పత్రాలు ఉన్నాయా?</label>
	                                                <label class="floating-label hin">परिसर के पंजीकरण दस्तावेज़ हैं?</label>
	                                            </div> 
	                                            <div class="form-group input-group" id="reg-doc-div" style="display:none">
		                                            <div class="form-group input-group col-sm-6">
		                                                <select class="floating-select" id="regdoctype" name="regdoctype" 
		                                                    onclick="this.setAttribute('value', this.value);" 
		                                                    onchange="this.setAttribute('value', this.value);"
		                                                    value="">
		                                                    <option value=""></option>
		                                                    <option value='Registered Sale Deed'>Registered Sale Deed</option>
		                          	                        <option value='Registered Gift Deed'>Registered Gift Deed</option>
									                        <option value='Registered Lease Deed'>Registered Lease Deed</option>
		                                                </select>
		                                                <label class="floating-label eng">Registration Document Type</label>
		                                                <label class="floating-label tel">నమోదు పత్రం రకం</label>
		                                                <label class="floating-label hin">पंजीकरण दस्तावेज़ प्रकार</label>
		                                            </div> 
		                                            <div class="form-group input-group col-sm-6">
		                                                <label class="has-float-label">
		                                                    <input class="form-control" type="text" id="regdocno" name="regdocno" placeholder=" "/>
		                                                    <span id="lan_doc_no"></span>
		                                                </label>
		                                            </div> 
		                                            <div class="form-group input-group col-sm-6">
		                                                <label class="has-float-label">  
		                                                    <input id="regdocdate" name="regdocdate" class="date form-control" data-date-format="dd/mm/yyyy" type="date" placeholder=" " data-inputmask-alias="date" />
		                                                    <span id="lan_doc_dt"></span>
		                                                </label>
		                                            </div> 
		                                            <div class="form-group input-group col-sm-6">
		                                                <label class="has-float-label">
		                                                    <input class="form-control" type="text" id="regdocofc" name="regdocofc" placeholder=" "/>
		                                                    <span id="lan_reg_off"></span>
		                                                </label>
		                                            </div> 
		                                        </div>  
		                                        <div class="form-group input-group" id="nature_div" style="display:none">  
		                                            <div class="form-group input-group col-sm-6">
		                                                <select class="floating-select" id="supply_nature" name="supply_nature" 
		                                                    onclick="this.setAttribute('value', this.value);" 
		                                                    onchange="this.setAttribute('value', this.value);"
		                                                    value="">
		                                                    <option value=""></option>
		                                                </select>
		                                                <label class="floating-label eng">Nature of Premises</label>
		                                                <label class="floating-label tel">ఆవరణ యొక్క స్వభావం</label>
		                                                <label class="floating-label hin"> परिसर की प्रकृति</label>
		                                            </div> 
		                                        </div>    
	                                        </div>
	                                    </div>
	                                </div>    
                             	</div>
                        		<div class="row g-2 wow slideInLeft animated" data-wow-delay="00ms" data-wow-duration="2000ms">
		                            <div class="col-sm-6">
		                                <div class="panel-heading eng">Documents Upload</div>
		                                <div class="panel-heading tel">డాక్యుమెంట్స్ అప్‌లోడ్</div>
		                                <div class="panel-heading hin">डॉक्यूमेंट अपलोड करें</div>
		                                <div class="rounded-box">
                                    		<div class="form-group row p-3">
		                                        <div class="form-group input-group col-sm-12">
		                                            <label class="has-float-label">  
		                                                <input type="file" name="applicant_photo" id="applicant_photo" accept="image/gif, image/jpeg">
		                                                <span>Applicant Photo (JPG/JPEG)
		                                                    <span style="color:red">Max.Size 50KB</span>
		                                                </span>
		                                            </label>
		                                        </div>
		                                        <div class="form-group input-group col-sm-12">
		                                            <label class="has-float-label">  
		                                                <input type="file" name="id_proof" id="id_proof" accept="image/gif, image/jpeg">
		                                                <span>Aadhar Card (JPG/JPEG)
		                                                    <span style="color:red">Max.Size 100KB</span>
		                                                </span>
		                                            </label>
		                                        </div>
		                                        <div class="form-group input-group col-sm-12">
		                                            <label class="has-float-label">  
		                                                <input type="file" name="premises_proof" id="premises_proof" accept="image/gif, image/jpeg">
		                                                <span>Premises Photo (JPG/JPEG)
		                                                    <span style="color:red">Max.Size 100KB</span>
		                                                </span>
		                                            </label>
		                                        </div>
		                                        <div class="form-group input-group col-sm-12">
		                                            <label class="has-float-label">  
		                                                <input type="file" name="location_photo" id="location_photo" accept="image/gif, image/jpeg">
		                                                <span>Location Photo (JPG/JPEG)
		                                                    <span style="color:red">Max.Size 200KB</span>
		                                                </span>
		                                            </label>
		                                        </div>     
                                    		</div>
                                		</div>
                            		</div>
                            		<div class="col-sm-6">
		                                <div class="panel-heading-payment eng">Required Payment</div>
		                                <div class="panel-heading-payment tel">అవసరమైన చెల్లింపు</div>
		                                <div class="panel-heading-payment hin">आवश्यक भुगतान</div>
		                                <div class="rounded-box"  style="min-height:20rem">
                                        	<div class="container p-3">
		                                        <div class="row p-1">
		                                            <div class="col-sm-8">Application Fee</div>
		                                            <div class="col-sm-4">0.00</div>
		                                        </div>
		                                        <div class="row p-1">
		                                            <div class="col-sm-8">GST on Application Fee</div>
		                                            <div class="col-sm-4">0.00</div>
		                                        </div>
		                                        <div class="row p-1">
		                                            <div class="col-sm-8">Security Deposit</div>
		                                            <div class="col-sm-4">0.00</div>
		                                        </div>
		                                        <div class="row p-1">
		                                            <div class="col-sm-8">Development Charges</div>
		                                            <div class="col-sm-4">0.00</div>
		                                        </div>
		                                        <div class="row p-1">
		                                            <div class="col-sm-8">GST on Development Charges</div>
		                                            <div class="col-sm-4">0.00</div>
		                                        </div>
		                                        <div class="row p-1 bg-light font-weight-bold">
		                                            <div class="col-sm-8">Total</div>
		                                            <div class="col-sm-4">0.00</div>
		                                        </div>
                                    		</div>    
                                		</div>
                            		</div>
                            	</div>  
		                        <div class="new_reg_button-container">
		                            <div style="display: flex;">
		                                <div class="new_reg__button">
		                                    <!-- <button type="button" class="new_reg_btn">OTP పంపండి</button> -->
		                                    <button type="button" class="new_reg_btn eng">Proceed</button>
		                                    <button type="button" class="new_reg_btn tel">కొనసాగండి</button>
		                                    <button type="button" class="new_reg_btn hin">आगे बढ़ना</button>
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
	        <button class="scroll-top scroll-to-target" data-target="html">
	            <span class="fa fa-angle-up"></span>
	        </button>
    	</div>
    <a id="whatsappChats" href="https://api.whatsapp.com/send?phone=919515022697" target="_blank" rel="nofollow" class="pushItDown">
        <img loading="lazy" src="${pageContext.request.contextPath}/assets/images/icons/whatsapp-icon.png" width="66" height="66">
        <span class="whatsappChatsText">Chat with us on <strong>WhatsApp</strong></span>
    </a>
    <!-- jquery plugins -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/owl.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/wow.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/validation.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery.fancybox.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/appear.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/scrollbar.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/isotope.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jQuery.style.switcher.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-ui.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/jquery_1.6.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/script.js"></script>
    <script type="text/javascript">

    $(document).ready(function () {
    	$("#form-container").hide();
    	$("#d-adjscno").hide();
    	$("#d-secname").hide();
    	$("#d-noexistscnos").hide();
    	$("#d-btn-check").hide();
    	//$("#alert-noscnos").hide();
    	//$("#addscnos").hisw();
    	
        setTimeout(function() {
            $("#whatsappChats").show();
        }, 500);
    });
    </script>
    <script language="javascript">
    var screxflg="";
    $("#scnoexty,#scnoextn").click(function(){
		if ($("#scnoexty").prop("checked")) {
			$("#form-container").hide();
			$("#d-adjscno").hide();
			$("#d-secname").hide();
			$("#d-noexistscnos").show();
			$("#d-btn-check").show();
			screxflg="Y";
		}
		if($("#scnoextn").is(":checked")) {
			//$("#noscno").prop( "placeholder", "No of Required Service Connections");
			//$("#noscno").val("");
			//$("#addscnos").html("");
			
			$("#d-noexistscnos").hide();
			$("#d-btn-check").hide();
			$("#addscnos").hide();
			$("#d-adjscno").show();
			$("#d-secname").show();
			screxflg="";
		}
	});
    
    $("#same_addr").click(function(){
        if($(this).prop("checked") == true){
            $("#comm_address1").val($("#conn_address1").val());
            $("#comm_address2").val($("#conn_address2").val());
            $("#comm_address3").val($("#conn_address3").val());  
            $("#comm_address4").val($("#conn_address4").val()); 
            $("#comm_pincode").val($("#conn_pincode").val()); 
	    }else {
	    	$("#comm_address1").val('');
            $("#comm_address2").val('');
            $("#comm_address3").val('');  
            $("#comm_address4").val(''); 
            $("#comm_pincode").val('');   
	    }
  	});
    
    var srflg="";
   	$("#noscnos").blur(function(){
		//if ($("#scnoext1").prop("checked")) {
		addscnos="";	
		//alert(addscnos);	
		//$("#addscnos").html(addscnos);	
		//alert($("#noscnos").val());	
		if($("#noscnos").val()!=""){
			if($("#noscnos").val()<5){
				if(srflg==""){
					nsc=$("#noscnos").val();
				 	if(screxflg=="Y"){
				 		addscnos+="<div class='row g-2'><div class='col-sm-12'>";
				 		addscnos+="<div class='row d-flex justify-content-center p-1'>";
				 		addscnos+="<div class='form-group input-group col-sm-12'>";
				 		
				 		for(var i=1;i<=nsc;i++){
							addscnos+="<div class='col-md-3'>";
							addscnos+="<label class='has-float-label'>";
							addscnos+="<input type='text' name='ukscnos' id='ukscnos"+i+"' class='form-control' size='8' placeholder=' ' onBlur='chkUkscno(this.value,"+i+")'>";
							addscnos+="<span>"+i+".Unique Service No.</span>";
							addscnos+="</label>";
							addscnos+="<div class='input-group-append'>";
							addscnos+="<button class='search-button' type='button' onclick='checkUSCNO(1);return true;'>";
							addscnos+="<span id='wr"+i+"'><i class='fa fa-search'></i></span>";
							addscnos+="</button>";
							addscnos+="</div>";
							addscnos+="</div>";
							
						}
						addscnos+="</div></div></div></div>";
					}
				 	//alert(addscnos);
				 	
					//$("#addscnos").val(addscnos);
					$('#addscnos').html(addscnos);
					//$("#addscnos").show();
					$("#noscnos").prop('readonly', true);
					getDefaultLanguage();
					$("#form-container").show();
					
					srflg="Y";
				}
			} else {
				addscnos+="<div class='row g-2'><div class='col-sm-12'>";
				addscnos+="<div class='row d-flex justify-content-center p-1'>";
				addscnos+="<div class='form-group input-group col-md-11 alert alert-danger custom-alert' role='alert'>";
				addscnos+="<strong>Warning!&nbsp;</strong> There are more than 4 Existing Services. Please Register in Low Tention Multi Service (LTM)";
				addscnos+="</div></div></div></div>";	
				$("#addscnos").html(addscnos);
				//$("#form-container").hide();
				srflg="";
				//<span class='text-danger text-bold'>There are more than 4 Services Existing Please Register in Low Tention Multi Service (LTM)</span>
			}
		}
		//}
	});

    $('#en').click(function() {
       
        $('.tel').css('display', 'none');
        $('.hin').css('display', 'none');
        $('.eng').show();
        getDefaultLanguage();                                    
    });

    $('#te').click(function() {
        $('.eng').css('display', 'none');
        $('.hin').css('display', 'none');
        $('.tel').show();
        document.getElementById("lan_id_no").innerHTML="ID సంఖ్యను నమోదు చేయండి";
        document.getElementById("lan_app_name").innerHTML="ID ప్రూఫ్‌లో ఉన్నట్లుగా దరఖాస్తుదారుని పేరు";
        document.getElementById("lan_father_name").innerHTML="తండ్రి/భార్య/భర్త పేరు";
        document.getElementById("lan_sec_name").innerHTML="సెక్షన్ పేరు";
        document.getElementById("lan_con_addr1").innerHTML="డోర్ నెం./భవనం పేరు";
        document.getElementById("lan_con_addr2").innerHTML="వీధి/ల్యాండ్‌మార్క్";
        document.getElementById("lan_con_addr3").innerHTML="గ్రామం/నగరం";
        document.getElementById("lan_con_addr4").innerHTML=" మండలం/జిల్లా";
        document.getElementById("lan_con_pin").innerHTML="పిన్ కోడ్";
        document.getElementById("lan_con_mobile").innerHTML="మొబైల్ నెం.";
        document.getElementById("lan_com_addr1").innerHTML="డోర్ నెం./భవనం పేరు";
        document.getElementById("lan_com_addr2").innerHTML="వీధి/ల్యాండ్‌మార్క్";
        document.getElementById("lan_com_addr3").innerHTML="గ్రామం/నగరం";
        document.getElementById("lan_com_addr4").innerHTML=" మండలం/జిల్లా";
        document.getElementById("lan_com_pin").innerHTML="పిన్ కోడ్";
        document.getElementById("lan_com_mobile").innerHTML="మొబైల్ నెం.";
        document.getElementById("lan_con_load").innerHTML="కనెక్టెడ్ లోడ్ (వాట్స్ లో)";
        document.getElementById("lan_gst").innerHTML="GSTIN నం.";   
        document.getElementById("lan_con_gst").innerHTML="GSTIN నం. నిర్ధారించండి";  
        document.getElementById("lan_eq_points").innerHTML="EQ పాయింట్స్";
        document.getElementById("lan_doc_no").innerHTML="డాక్యుమెంట్ నెంబర్";
        document.getElementById("lan_doc_dt").innerHTML="డాక్యుమెంట్ తేదీ";
        document.getElementById("lan_reg_off").innerHTML="రిజిస్ట్రార్ ఆఫీసు";
    });

    $('#hi').click(function() {
        $('.eng').css('display', 'none');
        $('.tel').css('display', 'none');
        $('.hin').show();
        document.getElementById("lan_id_no").innerHTML="ID नंबर दर्ज करें";
        document.getElementById("lan_app_name").innerHTML="आईडी प्रूफ में आवेदक का नाम";
        document.getElementById("lan_father_name").innerHTML="पिता/पत्नी/पति का नाम";
        document.getElementById("lan_sec_name").innerHTML="सेक्शन नाम";
        document.getElementById("lan_con_addr1").innerHTML="दरवाज़ा नं./भवन का नाम";
        document.getElementById("lan_con_addr2").innerHTML=" स्ट्रीट/लैंडमार्क";
        document.getElementById("lan_con_addr3").innerHTML="गाँव/शहर";
        document.getElementById("lan_con_addr4").innerHTML=" मंडल/जिला";
        document.getElementById("lan_con_pin").innerHTML="पिनकोड";
        document.getElementById("lan_con_mobile").innerHTML="मोबाइल न।";
        document.getElementById("lan_com_addr1").innerHTML="दरवाज़ा नं./भवन का नाम";
        document.getElementById("lan_com_addr2").innerHTML=" स्ट्रीट/लैंडमार्क";
        document.getElementById("lan_com_addr3").innerHTML="गाँव/शहर";
        document.getElementById("lan_com_addr4").innerHTML=" मंडल/जिला";
        document.getElementById("lan_com_pin").innerHTML="पिनकोड";
        document.getElementById("lan_com_mobile").innerHTML="मोबाइल न।";
        document.getElementById("lan_con_load").innerHTML="कनेक्टेड लोड (वाट्स में)";
        document.getElementById("lan_gst").innerHTML="GSTIN नंबर";  
        document.getElementById("lan_con_gst").innerHTML="GSTIN नंबर की पुष्टि करें";    
        document.getElementById("lan_eq_points").innerHTML="EQ पॉइंट्स";      
        document.getElementById("lan_doc_no").innerHTML="डॉक्यूमेंट नंबर";
        document.getElementById("lan_doc_dt").innerHTML="डॉक्यूमेंट तारीख";
        document.getElementById("lan_reg_off").innerHTML="रजिस्ट्रार ऑफिस";
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
