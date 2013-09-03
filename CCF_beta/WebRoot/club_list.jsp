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
    
    <title>My JSP 'club_list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/index.css">
	

  </head>
  
  <body>
    
    <s:include value="/user-box.jsp" />
    
	<center><h1><font color="red">Current Online Clubs List</font></h1></center>
	
	<table border="1" width="90%" align="center">
	
	<tr> 
		<td>CID</td>
		<td>Name</td>
		<td>Club's Portrait URL</td>
		<td>Index</td>
		<td>members</td>
		<td>intro</td>
		<td>activities</td>
		<td>Files</td>
		<td>XP</td>
		<td>Province</td>
		<td>City</td>
		<td>College</td>
		<td>Tags</td>
		<td>Leader</td>
		<td>Publishers</td>
		<td>view detail</td>
		<td>modify</td>
	</tr>
	<s:iterator value="#request.list" id="club">
	<tr>
		<td>
			<s:property value="#club.cid" />
		</td>
		<td>
			<s:property value="#club.c_name" />
		</td>
		<td>
			<s:property value="#club.c_pic" />
		</td>
		<td>
			<s:property value="#club.c_homepage" />
		</td>
		<td>
			<s:property value="#club.c_members" />
		</td>
		<td>
			<s:property value="#club.c_intro" />
		</td>
		<td>
			<s:property value="#club.c_activities" />
		</td>
		<td>
			<s:property value="#club.c_shared_files" />
		</td>
		<td>
			<s:property value="#club.c_xp" />
		</td>
		<td>
			<s:property value="#club.c_province" />
		</td>
		<td>
			<s:property value="#club.c_city" />
		</td>
		<td>
			<s:property value="#club.c_college" />
		</td>
		<td>
			<s:property value="#club.c_field" />
		</td>
		<td>
			<s:property value="#club.c_code_current_leader" />
			<s:a href="viewClubMembers">Club Members</s:a>
		</td>
		<td>
			<s:property value="#club.c_code_edit_authority_members" />
		</td>
		<td><s:a href="goClubIndex?club.cid=%{#club.cid}">go to home page</s:a></td>
		<td><s:a href="goClubProfile?club.cid=%{#club.cid}">update</s:a></td>
	</tr>
	</s:iterator>
	
	</table>
	
  </body>
</html>
