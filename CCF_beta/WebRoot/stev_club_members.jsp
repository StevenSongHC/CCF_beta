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
    
    <title>My JSP 'stev_club_leader_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>This is Stev JSP page.</h1>
    
    <table border="1" width="90%" align="center">
	
	<tr> 
		<td>CID</td>
		<td>College</td>
		<td>Name</td>
		<td>Leader</td>
		<td>Publishers</td>
		<td>Members</td>
		<td>change</td>
	</tr>
	<s:iterator value="#request.list" id="club">
	<tr>
		<td>
			<s:property value="#club.cid" />
		</td>
		<td>
			<s:property value="#club.c_college" />
		</td>
		<td>
			<s:property value="#club.c_name" />
		</td>
		<td>
			<s:property value="#club.c_code_current_leader" />
		</td>
		<td>
			<s:property value="#club.c_code_edit_authority_members" />
		</td>
		<td>
			<s:property value="#club.c_members" />
		</td>
		<td><s:form action="changeClubCrew">
			<s:hidden name="club.cid" value="%{#club.cid}"/>
			<s:textfield name="club.c_code_current_leader" value="%{club.c_code_current_leader}" label="Leader" />
			<s:textfield name="club.c_members" value="%{club.c_members}" label="Members" />
			<s:textfield name="club.c_code_edit_authority_members" value="%{club.c_code_edit_authority_members}" label="Publishers" />
			<s:submit value="save personnel changes" />
    	</s:form>
	</tr>
	</s:iterator>
	
	</table>
    
  </body>
</html>
