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
    
    <title>"→_→${clubinfo.name}</title> 
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/custom-drop-down-list.css">
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
	.staff {
		background-color: yellow;
		float: left;
		width: 23%;
		min-width: 170px;
		padding: 10px 10px 10px 10px;
	}
	.activity {
		background-color: green;
		width: 850px;
		float: left;
		padding: 10px;
	}
	.stuff {
		background-color: orange;
		float: right;
		width: 14%;
		padding: 15px;
		min-width: 242px;
	}
	.dd ul li {
		overflow: hidden;
	}
	</style>
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	
	<script type="text/javascript">
	var isStaffListLoaded = false;
	function DropDown(el) {
		this.dd = el;
		this.initEvents();
	}
	DropDown.prototype = {
		initEvents : function() {
			var obj = this;
			obj.dd.on('click', function(event){
				if (!isStaffListLoaded) {
					loadStaffList();
				}
				$(this).toggleClass('active');
				event.stopPropagation();
			});	
		}
	};
	$(function() {
		var dd = new DropDown( $('#dd') );
		$(document).click(function() {
			// all dropdowns
			//$('.wrapper-dropdown-2').removeClass('active');
		});
	});
	function loadStaffList() {
		$.ajax( {
			type: "POST",
			url: "loadStaffList",
			data: { leader : "${clubinfo.leader}" , publisher : "${clubinfo.publisher}", member : "${clubinfo.member}" },
			dataType: "json"
		}).done(function(json) {
			var data = eval("("+json+")");
			var labelClass = "";	// from bottstrap
			$.each(data, function(index, staff) {
				switch (staff.title) {
					case "Leader":
						labelClass = "label-primary";
						break;
					case "Publisher":
						labelClass = "label-success";
						break;
					case "Member":
						labelClass = "label-info";
						break;
					default :
						labelClass = "label-default";
						break;
				}
				$("div #dd>ul").append("<li><a href=\"profile?account=" + staff.useraccount +"\"><img alt=\"avatar\" src=\"" + staff.userpic + "\" width=\"25px\" height=\"25px\">" + staff.username + "<span class=\"label " + labelClass + "\">" + staff.title + "</span></a></li>");
			});
			isStaffListLoaded = true;
		}).fail(function() {
			alert("FAIL");
		}).error(function (XMLHttpRequest, textStatus, errorThrown) {
			alert(XMLHttpRequest.responseText);
		});
	};
	</script>
  </head>
  
  <body>
  	<div class="family-portrait">
  		<img src="${clubinfo.pic}" width="100%"></img>
  	</div>
  	<div class="wrapper">
		<div class="staff">
			<div id="dd" class="wrapper-dropdown-2" tabindex="1">STAFF
				<ul class="dropdown">
					<%-- <s:iterator value="%{#request.clubinfo.leader}">
					<li><a href="profile?account=${useraccount}"><img alt="avatar" src="${userpic}" width="25px" height="25px"><s:property value="username"/><span class="label label-primary">Leader</span></a></li>
					</s:iterator>
					<s:iterator value="%{#request.clubinfo.publisher}">
					<li><a href="profile?account=${useraccount}"><img alt="avatar" src="${userpic}" width="25px" height="25px"><s:property value="username"/><span class="label label-success">Publisher</span></a></li>
					</s:iterator>
					<s:iterator value="%{#request.clubinfo.member}">
					<li><a href="profile?account=${useraccount}"><img alt="avatar" src="${userpic}" width="25px" height="25px"><s:property value="username"/><span class="label label-info">Member</span></a></li>
					</s:iterator> --%>
				</ul>
			</div>
		</div>
		<div class="activity">
			a,got it!
		</div>
		<div class="stuff">
			a
		</div>
  	</div>
  </body>
</html>
