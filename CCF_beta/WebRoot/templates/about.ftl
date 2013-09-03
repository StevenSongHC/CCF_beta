<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>This is Fremaker Template</title>
		<meta http-equiv="keywords" content="enter,your,keywords,here" />
		<meta http-equiv="description" content="A short description of this page." />
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	</head>
	<body>
		<h1>Hey,this is ${user.u_name?default("Empty")}<#if user.u_college?exists>, from ${user.u_college}!</#if></h1>
		中文,россии
		<p>
		Well,my account is <a href="#">${user.u_account}</a>,you can follow me
		</p>
		<#if user.u_summary?exists><blockquote></blockquote></#if>
		<#if user.u_pic?exists><img src="${user.u_pic?default("jaja")}" /></#if>
	</body>
</html>