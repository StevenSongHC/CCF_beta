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
    
    <title><s:property value="%{club.c_name}"/> homepage</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/user-exploring.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">

  </head>
  
  <body>
  
  	<s:include value="/user-box.jsp" />
    
    <img src="<s:property value="%{club.c_pic}"/>" />
	current link:<s:property value="%{club.c_homepage}"/>
	<br><br>
	<hr>
	<center><h1><font color="red">This is <s:property value="%{club.c_name}"/>'s Home,Welcome</font></h1></center>
	Our members:
	<ul>
		<li><s:property value="%{club.c_members}"/>
		[<s:a href="join?cid=%{club.cid}"><span class="join-club">join us</span></s:a>]
	</ul>
	Our leader is <s:property value="%{club.c_code_current_leader}"/>	
	<center>
	<hr>
	<s:property value="%{club.c_activities}"/>
	<textarea><s:property value="%{club.c_intro}"/></textarea>
	</center>
	<font color="red">ActivityPoint:<s:property value="%{club.c_xp}"/></font>
	<hr>
	<s:property value="%{club.c_province}"/> -》 <s:property value="%{club.c_city}"/>
	<hr><center>Only For Leader</center>
	<center>invite someone
	<s:form action="invite">
	<s:hidden name="cid" value="%{club.cid}"/>
	<s:textfield name="account"/>
	<s:submit value="invite"/>
	</s:form>
	</center>
	<hr>

	<s:textfield name="club.c_code_current_leader" value="%{club.c_code_current_leader}" />
	<s:textfield name="club.c_code_edit_authority_members" value="%{club.c_code_edit_authority_members}" />
	
  </body>
</html>
