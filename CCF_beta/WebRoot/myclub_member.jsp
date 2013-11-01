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
    
    <title>My JSP 'myclub_member.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		body {
			margin: 0;
			padding: 0;
		}
		.family-portrait {
			width: 100%;
			min-width: 1250px;
		}
		.wrapper {
			min-width: 1250px;
		}
		.content {
			overflow: hidden;
		}
		.content .primary {
			float: left;
			width: 75%;
			min-width: 1000px;
			background-color: yellow;
		}
		.content .secondary {
			float: right;
			width: 16%;
			min-width: 200px;
			height: 200px;
			background-color: green;
		}
		.content .primary .staff {
			float: right;
			width: 170px;
			background-color: gray;
		}
		.content .primary .activity {
			float: right;
			width: 830px;
			height: 200px;
			padding: 0;
			background-color: orange;
		}
		.content .secondary .stuff {
			width: 200px;
		}
	</style>

  </head>
  <body>
  	<div class="family-portrait">
  		<img src="${clubinfo.pic}" width="100%"></img>
  	</div>
  	<div class="wrapper">
	   	<div class="content">
	    	<div class="primary">
	    		<div class="activity">
	    			${clubinfo.activity}
	    		</div>
	    		<div class="staff">
		    		<dl>
					<dt id="publisher_list">Publisher(s)</dt>
						<s:iterator value="%{#request.clubinfo.publisher}">
							<dd><div class="default"><span class="username"><s:property value="username"/></span><span class="link"><a href="profile?account=${useraccount}">H</a><a href="personelChange?cid=${clubinfo.cid}&account=${useraccount}&oldJob=publisher">C</a><a href="deleteStaff?cid=${clubinfo.cid}&account=${useraccount}">D</a></span></div></dd>
						</s:iterator>
					<dt id="member_list">Member(s)</dt>
					<dt id="leader_list">Leader</dt>
						<s:iterator value="%{#request.clubinfo.leader}">
							<dd><div class="default"><span class="username"><s:property value="username"/></span><span class="link"><a href="profile?account=${useraccount}">H</a><a href="personelChange?cid=${clubinfo.cid}&account=${useraccount}">C</a><a href="deleteStaff?cid=${clubinfo.cid}&account=${useraccount}">D</a></span></div></dd>
						</s:iterator>
					</dl>
				</div>
	    	</div>
	    	<div class="secondary">
	    		<div class="stuff">
	    			<div class="file">
	    			<s:if test="%{#request.clubinfo.file==''}">empty file</s:if><s:else>${clubinfo.file}</s:else>
					</div>
	    		</div>
	    	</div>
	   	</div>
	</div>
  </body>
</html>
