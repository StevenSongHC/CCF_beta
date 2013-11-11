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
    
    <title>操作大学数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/layout-datatable.css">

	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#add").click(function() {
				$("table").append("<tr><td class=\"id\">increment</td><td class=\"name\"><input type=\"text\"></td><td class=\"cnName\"><input type=\"text\"></td><td class=\"fullName\"><input type=\"text\"></td><td class=\"summary\"><input type=\"text\"></td><td class=\"clubAmount\"><input type=\"text\" value=\"0\"></td><td class=\"prid\"><input type=\"text\" value=\"0\"></td><td class=\"ctid\"><input type=\"text\" value=\"0\"></td></tr>");
				$("#add").toggleClass("invisible");
				$("#confirm,#cancel").toggleClass("invisible");
			});
			$("#confirm").click(function() {
				if ($("tr .name:last input").val().trim() !== "" && $("tr .cnName:last input").val().trim() !== "" && $("tr .fullName:last input").val().trim() !== "" && $("tr .clubAmount:last input").val().trim() !== "" && $("tr .prid:last input").val().trim() !== "" && $("tr .ctid:last input").val().trim() !== "") {
					saveAndFetchLastData();
					$("tr :last").addClass("content");
				}
				else
					alert("请输入完整的属性!");
			});
			$("#cancel").click(function() {
				$("tr :last").remove();
				$("#add").toggleClass("invisible");
				$("#confirm,#cancel").toggleClass("invisible");
			});
			$("tr[class='content']").hover(function() {
				$(this).css("background-color", "rgb(241, 223, 176)");
				$(this).children().find(".item").html("<div class=\"btn btn-edit\"></div>");
				$(this).children().find(".btn-edit").click(function() {
					$(this).toggleClass("invisible");
					// make it editable
						
					var a = ($(this).parent().parent().parent());
					var b = a.html();	// storage a'html, for cancel function
					
					a.find("td[class='name']").html("<input type=\"text\" value=\"" + a.find("td[class='name']").html() + "\">");
					a.find("td[class='cnName']").html("<input type=\"text\" value=\"" + a.find("td[class='cnName']").html() + "\">");
					a.find("td[class='fullName']").html("<input type=\"text\" value=\"" + a.find("td[class='fullName']").html() + "\">");
					a.find("td[class='summary']").html("<input type=\"text\" value=\"" + a.find("td[class='summary']").html() + "\">");
					a.find("td[class='clubAmount']").html("<input type=\"text\" value=\"" + a.find("td[class='clubAmount']").html() + "\">");
					var oldPrid = a.find("td[class='prid']").html();
					var oldCtid = a.find("td[class='ctid']").html();
					a.find("td[class='prid']").html("<input type=\"text\" value=\"" + oldPrid + "\">");
					a.find("td[class='ctid']").html("<input type=\"text\" value=\"" + oldCtid + "\">");
					
					a.find("td[class='id']").html("<span class=\"btn\" func=\"update\">update</span><span class=\"btn\" func=\"discard\">discard</span>");
					
					$("span[func='update']").click(function() {
						if ($("tr .name input").val().trim() !== "" && $("tr .cnName input").val().trim() !== "" && $("tr .fullName input").val().trim() !== "" && $("tr .clubAmount input").val().trim() !== "" && $("tr .prid input").val().trim() !== "" && $("tr .ctid input").val().trim() !== "") {
							$.ajax( {
								type: "POST",
								url: "updateAndReturnCollegeData",
								data: { coid: a.attr("order"), newName: $("td.name>input").val(), newCnName : $("td.cnName>input").val(), newFullName : $("td.fullName>input").val(), newSummary : $("td.summary>input").val(), newClubAmount : $("td.clubAmount>input").val(), newPrid : $("td.prid>input").val(), newCtid : $("td.ctid>input").val(), oldPrid : oldPrid, oldCtid : oldCtid },
								dataType: "json"
							}).done(function( json ) {
								var data = eval("("+json+")");
								a.find("td[class='id']").html("<div class=\"item\"></div>"+data.id);
								a.find("td[class='name']").html(data.name);
								a.find("td[class='cnName']").html(data.cnName);
								a.find("td[class='fullName']").html(data.fullName);
								a.find("td[class='summary']").html(data.summary);
								a.find("td[class='clubAmount']").html(data.clubAmount);
								a.find("td[class='prid']").html(data.prid);
								a.find("td[class='ctid']").html(data.ctid);
								
							}).fail(function(XMLHttpRequest, textStatus, errorThrown) {
								$("body").append(XMLHttpRequest.responseText);
							}).error(function (XMLHttpRequest, textStatus, errorThrown) {
								$("#ajax").html(XMLHttpRequest.responseText);
							});
						}
						else
							alert("请输入完整的属性!");
					});
					$("span[func='discard']").click(function() {
						a.html(b);
					});
				});
			}, function() {
				$(this).css("background-color", "#FFF");
				$(this).children().find(".item").empty();
			});
		});
		function saveAndFetchLastData() {
			$.ajax( {
				type: "POST",
				url: "saveAndFetchLastCollege",
				data: { name: $("tr .name:last input").val(), cnName: $("tr .cnName:last input").val(), fullName: $("tr .fullName:last input").val(), summary: $("tr .summary:last input").val(), clubAmount: $("tr .clubAmount:last input").val(), ctid: $("tr .ctid:last input").val(), prid: $("tr .prid:last input").val() },
				dataType: "json"
			}).done(function( json ) {
				var data = eval("("+json+")");
				$("#add").toggleClass("invisible");
				$("#confirm,#cancel").toggleClass("invisible");
				$("tr:last .id").empty();
				$("tr:last input").remove();
				/* fill the last data into the last row */
				$("tr:last").attr("order" , data.id);
				$("tr .id:last").html("<div class=\"item\"></div>" + data.id);
				$("tr .name:last").html(data.name);
				$("tr .cnName:last").html(data.cnName);
				$("tr .fullName:last").html(data.fullName);
				$("tr .summary:last").html(data.summary);
				$("tr .clubAmount:last").html(data.clubAmount);
				$("tr .prid:last").html(data.prid);
				$("tr .ctid:last").html(data.ctid);
				$("tr .more:last").html("<div class=\"btn btn-edit\"></div>");
			}).fail(function() {
				alert("FAIL");
			}).error(function (XMLHttpRequest, textStatus, errorThrown) {
				$("#ajax").html(XMLHttpRequest.responseText);
			});
		}
	</script>
	
  </head>
  
  <body>
    <h1>college</h1>
    <div id="main" style="width:65%;">
    <table border="1">
    	<tr> 
			<td width="5%">COID</td>
			<td width="10%">Name</td>
			<td width="20">CHN Name</td>
			<td width="20%">Full Name</td>
			<td width="30%">Summary</td>
			<td width="5%">Club Amount</td>
			<td width="5%">CO PRID</td>
			<td width="5%">CO CTID</td>
		</tr>
	<s:iterator value="#request.list" id="college">
		<tr class="content" order=${college.id}>
			<td class="id"><div class="item"></div>${college.id}</td>
			<td class="name">${college.name}</td>
			<td class="cnName">${college.cnName}</td>
			<td class="fullName">${college.fullName}</td>
			<td class="summary">${college.summary}</td>
			<td class="clubAmount">${college.clubAmount}</td>
			<td class="prid">${college.prid}</td>
			<td class="ctid">${college.ctid}</td>
		</tr>
	</s:iterator>
    </table>
    <div class="btn btn-add" id="add"></div>
    <div class="btn invisible" id="confirm">√</div><div class="btn invisible" id="cancel">X</div></div>
  </body>
</html>
