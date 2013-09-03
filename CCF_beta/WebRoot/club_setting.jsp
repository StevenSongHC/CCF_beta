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
    
    <title>My JSP 'club_setting.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/update_club_page.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">

  </head>
  
  <body>
    
	<div id="user">
    <s:if test="#session.user_session!=null"><s:a href="homepage?account=%{#session.user_session.u_account}">${user_session.u_name}</s:a> | <s:a href="logout">log out</s:a></s:if>
    <s:else><s:a href="autologin">log in</s:a></s:else>
    </div>
    
	<s:form  action="updateClubProfile">
	
		<div id="portrait">
    	<img src="<s:property value="%{club.c_pic}"/>" />
    	<s:textfield name="club.c_pic" value="%{club.c_pic}"></s:textfield>
    	<%-- <s:file name="club.u_pic" accept="text/html,text/plain" label="Portrait" value="images/author_default.png"/> --%>
    	<input type="button" value="Change Club's Portrait(simulation)">
   		</div>
	
		<s:textfield name="club.c_name" label="Club Name" value="%{club.c_name}"></s:textfield>
		<s:textarea name="club.c_intro" label="Introduction" value="%{club.c_intro}"></s:textarea>
  		<s:textfield name="club.c_field" label="Tag(s)" value="%{club.c_field}" />

  		<s:hidden name="club.cid" value="%{club.cid}" />

		<s:submit value="Update"></s:submit>
	</s:form>

  </body>
</html>
