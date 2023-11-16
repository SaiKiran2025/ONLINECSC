<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="stag" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0,  shrink-to-fit=no, maximum-scale=1.0, user-scalable=0">
<title>TSSPDCL-Online CSC Services</title>
<!-- Fav Icon -->
<link rel="icon" href="${pageContext.request.contextPath}/assets/images/favicon.ico" type="image/x-icon">

<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<!-- Stylesheets -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600;700&amp;display=swap" rel="stylesheet">

<link href="${pageContext.request.contextPath}/assets/css/animate.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/responsive.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/services/icofont.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/services/services.css" rel="stylesheet">

</head>

<body>
<form name="service">
	<input type="hidden" name="mobileno" value="${mobileno}" />
	<!-- Start Preloader Area -->
	<!-- <div class="techSoft-preloader">
		<svg id="triangle" viewBox="-3 -4 39 39">
		  <polygon fill="#EFEFEF" stroke="#5764ec" stroke-width="1" points="16,0 32,32 0,32"></polygon>
		</svg>
		<div class="loader-text">TSSPDCL</div>
	</div> -->
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
		</header>
        
        <!--Page Title-->
        <section class="page-title-two bg-color-1 centred">
            <div class="auto-container">
                
                <div class="header-box-title">
                    <h1><i class="icofont-handshake-deal"></i>Online CSC Services</h1>
                   
                </div>
            </div>
        </section>
        <!--End Page Title-->
		<!-- Start Services Section -->
		<section class="category-section category-page pt-90 pb-90">
			<div class="container wow slideInLeft animated" data-wow-delay="1000ms" data-wow-duration="1000ms">
				<!-- <div class="container-fluid"> -->
					<div class="rounded-box" style="padding:2rem 5rem;margin-top:3rem;">
						<h4 style="margin-bottom:20px">Please Select Desired Service</h4>
						<div class="row">
							<div class="col-md-6 col-lg-4">
								<div class="single-services-box-2">
									<div class="services-icon-2 icon">
										<button id="newreg" onclick="pageRedirect(this.id);" style="background:transparent"><i class="icofont-home"></i></button>
										
									</div>
									<h3>New Service Registration</h3>
									<!--<p>IT Asset Management (ITAM) enables organizations..</p>
									<div class="services-btn">
										<a href="single-services.html" class="services-btn-one">Read More </a>
									</div>-->
								</div>
							</div>
							<div class="col-md-6 col-lg-4">
								<div class="single-services-box-2">
									<div class="services-icon-2 icon" >
									<button id="newLTMreg" onclick="pageRedirect(this.id);" style="background:transparent"><i class="icofont-building-alt"></i></button>
									</div>
									<h3>Apartment Connection (LTM)</h3>
									<!--<p>IT Asset Management (ITAM) enables organizations..</p>
									<div class="services-btn">
										<a href="single-services.html" class="services-btn-one">Read More </a>
									</div>-->
								</div>
							</div>
							<div class="col-md-6 col-lg-4">
								<div class="single-services-box-2">
									<div class="services-icon-2 icon">
										<i class="icofont-layout"></i>
									</div>
									<h3>Layout</h3>
										<!--<p>IT Asset Management (ITAM) enables organizations..</p>
										<div class="services-btn">
											<a href="single-services.html" class="services-btn-one">Read More </a>
										</div>-->
								</div>
							</div>
						</div>
						<div class="row">	
							<div class="col-md-6 col-lg-4">
								<div class="single-services-box-2">
									<div class="services-icon-2 icon" id="compreg">
										<i class="icofont-architecture-alt" aria-hidden="true"></i>
									</div>
									<h3>Complaints Registration</h3>
									<!--<p>IT Asset Management (ITAM) enables organizations..</p>
									<div class="services-btn">
										<a href="single-services.html" class="services-btn-one">Read More </a>
									</div>-->
								</div>
							</div>
						
							<div class="col-md-6 col-lg-4">
								<div class="single-services-box-2">
									<div class="services-icon-2 icon">
										<i class="icofont-light-bulb"></i>
									</div>
									<h3>Additional Load</h3>
									<!--<p>IT Asset Management (ITAM) enables organizations..</p>
									<div class="services-btn">
										<a href="single-services.html" class="services-btn-one">Read More</a>
									</div>-->
								</div>
							</div>
							<div class="col-md-6 col-lg-4">
								<div class="single-services-box-2">
									<div class="services-icon-2 icon" id="burntmeter">
										<i class="icofont-forest-fire"></i>
									</div>
									<h3>Payment for Burnt Meter</h3>
									<!--<p>IT Asset Management (ITAM) enables organizations..</p>
									<div class="services-btn">
										<a href="single-services.html" class="services-btn-one">Read More</a>
									</div>-->
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 col-lg-4">
								<div class="single-services-box-2">
									<div class="services-icon-2 icon">
										<button id="addpayment" onclick="pageRedirect(this.id);" style="background-color:transparent">
										<i class="icofont-rupee-plus"></i></button>
									</div>
									<h3>Additional Payment</h3>
									<!--<p>IT Asset Management (ITAM) enables organizations..</p>
									<div class="services-btn">
										<a href="single-services.html" class="services-btn-one">Read More</a>
									</div>-->
								</div>
							</div>
						</div>
					</div>
				<!-- </div> -->
			</div>
				
		</section>
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
		<!-- End Services Section -->
  		<!-- jQuery Min JS -->
	
		<script src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
		
		<script src="${pageContext.request.contextPath}/script/services/jquery.magnific-popup.min.js"></script>
		<script src="${pageContext.request.contextPath}/script/services/isotope.pkgd.min.js"></script>
		<script src="${pageContext.request.contextPath}/script/services/main.js"></script>
		<script type="text/javascript">
	    function pageRedirect(url) {
			//alert(url);
			//window.location.href = "${pageContext.request.contextPath}/"+url;
	    	document.service.action="${pageContext.request.contextPath}/"+url;	
			document.service.method="POST";
			document.service.submit();
	    }      
		</script> 
</form>	
</body>

</html>