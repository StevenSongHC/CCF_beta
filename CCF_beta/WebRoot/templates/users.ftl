<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>This is Fremaker Template</title>
		<meta http-equiv="keywords" content="enter,your,keywords,here" />
		<meta http-equiv="description" content="A short description of this page." />
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		
		<link rel="stylesheet" type="text/css" href="css/index.css">
	</head>
	<body>
		中文,россии!
		<table border="1">
		<tr> 
			<td>UID</td>
			<td>User Name</td>
			<td>Account</td>
			<td>PWD</td>
			<td>College</td>
			<td>Code</td>
			<td>Portrait Link</td>
			<td>Summary</td>
			<td>User's Home Page</td>
			<td>Clubs</td>
			<td>Clubs Level</td>
			<td>XP</td>
		</tr>
		<#list userlist as user>
		
		<#assign url_list = user.u_name+"_club_url_list">
		<#assign name_list = user.u_name+"_club_name_list">
		<#--<#list user.u_name+"_club_job_list" as job_list>-->
		<tr>
			<td>${user.uid}</td>
			<td>${user.u_name}</td>
			<td>${user.u_account}</td>
			<td>${user.u_password}</td>
			<td>${user.u_college}</td>
			<td>${user.u_code}</td>
			<td>${user.u_pic}</td>
			<td>${user.u_summary}</td>
			<td>${user.u_homepage}</td>
			<td>${user.u_clubs}<#list url_list as url>
								<a href="${url}"></a>
								</#list>
								<#list name_list as name_name>
								${name_list}
								</#list>
								</td>
			<td>${user.u_clubs_level}</td>
			<td>${user.u_xp}</td>
		</tr>
		</#list>
		</table>
	</body>
</html>