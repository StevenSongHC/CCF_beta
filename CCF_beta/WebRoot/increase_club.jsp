<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'increase_club.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/index.css">

  </head>
  
  <body>
    
    <s:include value="/user-box.jsp" />
    
	<h1>Increase Club's Data</h1>
	
	<center>
		<s:form action="addClub">
		
		<s:textfield name="club.c_province" label="Province(code)"></s:textfield>
  		<s:textfield name="club.c_city" label="City(code)"></s:textfield>
  		<s:textfield name="club.c_college" label="College(code)"></s:textfield>
  		<s:textfield name="club.c_name" label="Club's Name"></s:textfield>
  		<s:textfield name="club.c_pic" label="Portrait" value="images/portrait_club.jpg" />
  		<s:textfield name="club.c_homepage" label="Club's Index Page" value="demo.html" />
  		<s:textfield name="club.c_members" label="Current Members" value="0" />
  		<s:textfield name="club.c_intro" label="Introduction" value="This is a test" />
  		<s:textfield name="club.c_activities" label="Activities' code" value="@" />
  		<s:textfield name="club.c_shared_files" label="Shared Files" value="" />
  		<s:textfield name="club.c_xp" label="Active Point" value="0" />
  		<s:textfield name="club.c_field" label="Tags(code)" value="0" />
  		<s:textfield name="club.c_code_current_leader" label="Leader" value="0123456" />
  		
  		<s:submit value="Increase Club"></s:submit>
  	</s:form>
	</center>

  </body>
</html>
