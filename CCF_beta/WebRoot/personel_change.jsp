<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
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
    <a href="personelChange?cid=${cid}&account=${account}&job=publisher">PUBLISHER</a>
    <br>
    <a href="personelChange?cid=${cid}&account=${account}&job=leader">LEADER</a>
    <br>
    <a href="<%=basePath%>homepage/myclub_${cid}">cancel</a>
  <hr>
  </body>
</html>
