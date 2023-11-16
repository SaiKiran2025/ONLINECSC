<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,java.sql.*"%>
<%
	String msg = (String)request.getAttribute("msg");
	String encmsg = (String)request.getAttribute("paymentchecksum");
	StringBuilder outputHtml = new StringBuilder();
//	out.println("----msg---"+msg);
//	out.println("----encmsg---"+encmsg);
	outputHtml.append("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
	outputHtml.append("<html>");
	outputHtml.append("<head>");
	outputHtml.append("<title>Merchant Check Out Page</title>");
	outputHtml.append("</head>");
	outputHtml.append("<body>");
	outputHtml.append("<center><h1>Please do not refresh this page...</h1></center>");
	outputHtml.append("<form method='post' action='https://ts.meeseva.telangana.gov.in/meesevaredirectsrvs/MeesevaPaymentGatewayFont.htm' name='f1'>");
	outputHtml.append("<input type='hidden' name='msg' id='msg' value='" +msg+"'>");	
	outputHtml.append("<input type='hidden' name='encmsg' id='encmsg'  value='" +encmsg+"'>");		
	outputHtml.append("<script type='text/javascript'>");
	outputHtml.append("document.f1.submit();");
	outputHtml.append("</script>");
	outputHtml.append("</form>");
	outputHtml.append("</body>");
	outputHtml.append("</html>");
	out.println(outputHtml);
%>