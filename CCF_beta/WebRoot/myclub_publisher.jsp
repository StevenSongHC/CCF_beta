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
    
    <title>My JSP 'myclub_publisher.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
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
		.row1 {
			float: left;
			width: 20%;
		}
		row2 {
			float: right;
			width: 75%;
		}
		.staff {
			background-color: yellow;
			width: 170px;
		}
		.activity {
			background-color: green;
			width: 830px;
		}
		.stuff {
			background-color: orange;
			width: 200px;
		}
	</style>

  </head>
  
  <body>
  	<div class="family-portrait">
  		<img src="${clubinfo.pic}" width="100%"></img>
  	</div>
  	<div class="wrapper">
		<div class="row1">
			<div class="staff">
				a,some conflicts!
			</div>
			<div class="row2">
				<div class="activity">
					a,got it!
				</div>
				<div class="stuff">
					a
				</div>
			</div>
		</div>
  	</div>
  </body>
</html>
