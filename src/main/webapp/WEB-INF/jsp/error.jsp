<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="stag" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TSSPDCL - Error page</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="${pageContext.request.contextPath}/css/errorstyle.css" rel="stylesheet">
</head>
<body>

	<div class="container">
	    <div class="row">
	        <div class="col-md-12">
	            <div class="error-template">
	            	<img src="${pageContext.request.contextPath}/assets/images/logo.png" />
	                <h1>
	                    Oops!</h1>
	                <h2>
	                    404 Not Found</h2>
	                <div class="error-details">
	                    Sorry, an error has occured, Requested page not found!
	                </div>
	                <div class="error-actions">
	                <c:if test = "${type == 'NR'}">
	                    <a href="${pageContext.request.contextPath}" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
	                        Take Me Home </a>
	                 </c:if>
	                  <c:if test = "${type == 'LTM'}">
	                    <a href="${pageContext.request.contextPath}/ltm" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
	                        Take Me Home </a>
	                 </c:if>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>

</body>
</html>