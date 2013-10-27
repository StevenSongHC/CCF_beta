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
    
    <title>College Club Field</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<script type="text/javascript"	src="js/common.js"></script>
	<script type="text/javascript"	src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#ajaxTest").click(function() {
				/* $("#ajax").load("login-box.html"); */
				/* checkNotification(); */
				loadSearchBox();
			});
			$("#login").click(function() {
				/* $("#ajax").load("login-box.html"); */
				openLoginBox();
			});
		});
		$(window).load(function() {
		});
		
		function openLoginBox() {
			$.ajax( {
					type: "POST",
					url: "login-box.html",
					data: { from: "ajaxTest", to: "server" },
					dataType: "html"
				}).done(function(box) {
					$("#ajax").append(box);
				});
		}
		
		function checkNotification() {
			alert("check notification");
			$.ajax( {
				type: "POST",
				url: "ajaxNotification",
				dataType: "json"
			}).done(function(data) {
				$("#notification").html(data);
			}).fail(function() {
				alert("FAIL");
			}).error(function (XMLHttpRequest, textStatus, errorThrown) {
				$("#ajax").html(XMLHttpRequest.responseText);
			});
		}
		
		function loadSearchBox() {
			$("#ajax").load("search-box.html");
		}
	</script>
	
  </head>
  
  <body>
  	
  	<s:a href="freemarker">Freemarker Test</s:a> <br>
    <s:a href="freemarker-userlist">static user list html</s:a>
    --<a href="userlist.html">link to it</a>
    
    <div id="user-box"><s:include value="/user-box.jsp" /></div>
    <h4>homepage.action = profile.action</h4>
    <h1>Start explorer№${user.uid}</h1>
    <center><div id="ajax"><button id="ajaxTest">Ajax</button></div></center>
    <s:if test="#cookie.user_cookie!=null">already login</s:if>
    
    <!-- Notification model -->
    <hr>
    
    <%-- <s:include value="/notification-box.html" /> --%>
    
    <%-- <div id="notification">
	<s:iterator value="#request.notifications">
		<s:if test="key=='invitation'">
			<s:iterator value="value">
				<div class="invitation-item">
					<s:property value="sender"/> to <s:property value="club"/> - <s:property value="date"/>
						<s:a href="acceptInvitation?from=%{from}&to=%{to}&where=%{where}">accept</s:a>
							<s:a href="refuseInvitation?from=%{from}&to=%{to}&where=%{where}">refuse</s:a>
				</div>
			</s:iterator>
		</s:if>
		<s:if test="key=='invitation-reply'">
			<s:iterator value="value">
				<div class="invitation-reply-item">
					<s:property value="newbie"/> has <s:property value="%{status}"/>
					  your invitation to join <s:property value="club"/>
					- <s:property value="date"/>
						<s:a href="muteNotification?type=invitation-reply&sender=%{sender}&receiver=%{receiver}&cid=%{cid}&date=%{date}">got it</s:a>
				</div>
			</s:iterator>
		</s:if>
		<s:if test="key=='newbie-notice'">
			<s:iterator value="value">
				<div class="newbie-notice-item">
					a new member <s:property value="newbie"/> has just joined your club <s:property value="%{club}"/>
					- <s:property value="date"/>
						<s:a href="muteNotification?type=newbie-notice&sender=%{sender}&receiver=%{receiver}&cid=%{cid}">got it</s:a>
				</div>
			</s:iterator>
		</s:if>
		<s:if test="key=='join-club-apply'">
			<s:iterator value="value">
				<div class="join-club-apply-item">
					<s:property value="applicant"/> wants to join your club - <s:property value="%{club}"/> 
					- <s:property value="date"/>
						<s:a href="responseJoinClubApply?action=approve&sender=%{sender}&receiver=%{receiver}&cid=%{cid}"><span class="approve">approve</span></s:a>
						<s:a href="responseJoinClubApply?action=reject&sender=%{sender}&receiver=%{receiver}&cid=%{cid}"><span class="reject">negative</span></s:a>
				</div>
			</s:iterator>
		</s:if>
		<s:if test="key=='join-club-apply-reply'">
			<s:iterator value="value">
				<div class="join-club-apply-reply-item">
					<s:property value="leader"/> has <s:property value="status"/> your apply to join <s:property value="%{club}"/> 
					- <s:property value="date"/>
						<s:a href="muteNotification?type=join-club-reply&sender=%{sender}&receiver=%{receiver}&cid=%{cid}">I see</s:a>
				</div>
			</s:iterator>
		</s:if>
		
	</s:iterator>
	</div> --%>
	<hr>
	<!-- END -->
    
    <div id="navi_user">
    	<div class="title">Explorer User</div>
		    <div class="func">
		    <s:a href="stev_add_user.jsp">add user</s:a>
		    <hr>
		    <s:a href="listUser">Show all users</s:a>
		    </div>
    </div>
    <div id="navi_club">
    	<div class="title">Explorer Club</div>
		    <div class="func">
		    <s:a href="increase_club.jsp">add club</s:a>
		    <hr>
		    <s:a href="listClub">Show all clubs</s:a>
		    </div>
    </div>
    
    <hr><hr>
    Some Database Entities
    <s:a href="operateProvince">Province(省份)</s:a>——<s:a href="operateCity">City(城市)</s:a>

	<hr>
    <% 
    Cookie cookies[]=request.getCookies();  
    if(cookies != null) {
    	out.println(" cookie's amount is："+ cookies.length); 
     	out.println("<br>"+"<br>"); 
   		for(int i = 0;i<cookies.length;i++){ 
	      	out.println("getName="+cookies[i].getName()+"<br>");  
	      	out.println("getValue="+cookies[i].getValue()+"<br>");  
	      	out.println("getComment="+cookies[i].getComment()+"<br>");  
	      	out.println("getDomain="+cookies[i].getDomain()+"<br>"); 
	      	out.println("getPath="+cookies[i].getPath()+"<br>"); 
	      	out.println("getMaxAge="+cookies[i].getMaxAge()+"<br>"); 
	      	out.println("getVersion="+cookies[i].getVersion()+"<br>"); 
	      	out.println("getSecure="+cookies[i].getSecure()+"<br>"); 
	      	out.println("<br>"+"<br>"); 
    	}
    } 
    %> 
    
  </body>
</html>
