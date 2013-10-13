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
    
    <title>myclub::leader page</title>
    
	
	<link rel="stylesheet" type="text/css" href="css/club_leader_style.css">
	<script type="text/javascript"	src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			addUserNameItemHandler();
			$("#link").css("background-color", "red");	// fail!
			/* $(".link a").click(function(event) {
				event.preventDefault();
				alert("here");
			}); */
		});
		
		var isMemberListLoad = false;
		$(document).ready(function() {
			var isLoad = false;
			$("#member_list").click(function() {
				if (isMemberListLoad) {
					$("#member_list").nextUntil("dt").toggle("slow");
				}
				else{
					loadMemberList();
				}
			});
		});
		
		function loadMemberList() {
			$.ajax( {
				type: "POST",
				url: "loadUserName",
				data: { ids:"${clubinfo.member}" },
				dataType: "json"
			}).done(function(json) {
				var data = eval("("+json+")");
				$.each(data, function(index, member) {
					$("#leader_list").before("<dd><div class=\"default\"><span class=\"username\">" + member.username + "</span><span class=\"link\"><a href=\"profile?account=" + member.useraccount + "\">H</a><a id=\"personelChange\" href=\"personelChange?cid=${clubinfo.cid}&account=" + member.useraccount + "&oldJob=member\">C</a><a href=\"deleteStaff?cid=${clubinfo.cid}&account=" + member.useraccount + "\">D</a></span></div></dd>");
					$("#leader_list").before("<dd><div class=\"default\"><span class=\"more\"><input type=\"button\" value=\"P\" onclick=\"laodPersonPanel()\" /></span><span class=\"username\"><a href=\"\profile?account=" + member.useraccount + "\">" + member.username + "</a></span></div>");
				});
				$("#member_list").nextUntil("dt").css("display", "none");
				$("#member_list").nextUntil("dt").toggle("slow");
				isMemberListLoad = true;
			}).fail(function() {
				alert("FAIL");
			}).error(function (XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.responseText);
			});
		};
		
		function addUserNameItemHandler() {
			$("dd").hover(
				function() {
					$(this).css({ "background-color" : "#A8F9FA" , "cursor" : "default" });
				},
				function() {
					$(this).css("background-color", "#FFF");
				}
			);
			$("#member_list").css("cursor", "pointer");
			$("#publisher_list,#leader_list").css("cursor", "default");
		};
		
		function laodPersonPanel() {
			alert("hello");
		};
	</script>	

  </head>
  
  <body>
    <h4>${clubinfo.province}(province) - ${clubinfo.city}(city) - ${clubinfo.college}(college)</h4>
    <h1><a href="${clubinfo.url}">${clubinfo.name}</a></h1>
    <div id="top_area">
	    <div id="staff_panel">
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
		<div id="activity_panel">
			${clubinfo.activity}
		</div>
		<div id="stuff_panel">
			<div id="file_panel">
				<s:if test="%{#request.clubinfo.file==''}">empty file</s:if>
				<s:else>${clubinfo.file}</s:else>
				<hr>
				<span class="add_file"><img src="images/scroll_up.png"></span>
			</div>
			<div id="tag_panel">${clubinfo.tag}</div>
		</div>
	</div>
	<hr>
	<div id="middle_area">
		<div id="image_panel">
			<img alt="image of club" src="${clubinfo.pic}">
			<div class="upload_portrait"></div>
		</div>
		<div id="intro_panel">${clubinfo.intro}</div>
	</div>
  </body>
</html>
