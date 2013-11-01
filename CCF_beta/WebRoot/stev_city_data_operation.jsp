<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>操作城市数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<style type="text/css">
		#main {
			text-align: center;
		}
		#header {
			font-size: 20pt;
			font-family: Microsoft-YaHei;
		}
		#header div {
			display: inline-block;
			background-color: yellow;
		}
		.tb-id {
			width: 6%;
		}
		.tb-name {
			width: 12%;
		}
		.tb-cn-name {
			width: 12%;
		}
		.tb-college-amount {
			width: 8%;
		}
		.tb-prid {
			width: 6%;
		}
	</style>

  </head>
  
  <body>
    <h1>城市</h1>
    <div id="main">
    <div id="header">
    	<div class="tb-id">id</div> <div class="tb-name">name</div> <div class="tb-cn-name">cnName</div> <div class="tb-college-amount">collegeAmount</div> <div class="tb-prid">prid</div>
    </div>
    <s:iterator value="#request.list" id="city">
    	$(city.name)
    </s:iterator>
    </div>
  </body>
</html>
