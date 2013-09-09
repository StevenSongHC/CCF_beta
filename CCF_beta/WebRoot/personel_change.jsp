<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>where to go</title>
  
  </head>
  
  <body>
  <hr>
  
  	<s:if test="${oldJob=='member'}">
	    <a href="personelChange?cid=${cid}&account=${account}&oldJob=${oldJob}&newJob=publisher">PUBLISHER</a>
	    <br>
	    <a href="personelChange?cid=${cid}&account=${account}&oldJob=${oldJob}&newJob=leader">LEADER</a>
	    <br>
	    <a href="<%=basePath%>homepage/myclub_${cid}">cancel</a>
    </s:if>
    
    <s:if test="${oldJob=='publisher'}">
    	<a href="personelChange?cid=${cid}&account=${account}&oldJob=${oldJob}&newJob=member">MEMBER</a>
	    <br>
	    <a href="personelChange?cid=${cid}&account=${account}&oldJob=${oldJob}&newJob=leader">LEADER</a>
	    <br>
	    <a href="<%=basePath%>homepage/myclub_${cid}">cancel</a>
    </s:if>
    
  <hr>
  </body>
</html>
