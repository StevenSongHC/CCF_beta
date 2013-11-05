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
    
    <title>操作省份数据</title>
    
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
				$("table").append("<tr><td class=\"id\">increment</td><td class=\"name\"><input type=\"text\"></td><td class=\"cnName\"><input type=\"text\"></td><td class=\"cnShortName\"><input type=\"text\"></td><td class=\"capital\"><input type=\"text\"></td><td class=\"cityAmount\"><input type=\"text\" value=\"0\"></td><td class=\"brightness\"><input type=\"text\" value=\"0\"></td></tr>");
				$("#add").toggleClass("invisible");
				$("#confirm,#cancel").toggleClass("invisible");
			});
			$("#confirm").click(function() {
				if ($("tr .name:last input").val().trim() !== "" && $("tr .cnName:last input").val().trim() !== "" && $("tr .cnShortName:last input").val().trim() !== "" && $("tr .capital:last input").val().trim() !== "" && $("tr .cityAmount:last input").val().trim() !== "" && $("tr .brightness:last input").val().trim() !== "") {
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
					a.find("td[class='cnShortName']").html("<input type=\"text\" value=\"" + a.find("td[class='cnShortName']").html() + "\">");
					a.find("td[class='capital']").html("<input type=\"text\" value=\"" + a.find("td[class='capital']").html() + "\">");
					a.find("td[class='cityAmount']").html("<input type=\"text\" value=\"" + a.find("td[class='cityAmount']").html() + "\">");
					a.find("td[class='brightness']").html("<input type=\"text\" value=\"" + a.find("td[class='brightness']").html() + "\">");
					
					a.find("td[class='id']").html("<span class=\"btn\" func=\"update\">update</span><span class=\"btn\" func=\"discard\">discard</span>");
					
					$("span[func='update']").click(function() {
						if ($("tr .name input").val().trim() !== "" && $("tr .cnName input").val().trim() !== "" && $("tr .cnShortName input").val().trim() !== "" && $("tr .capital input").val().trim() !== "" && $("tr .cityAmount input").val().trim() !== "" && $("tr .brightness input").val().trim() !== "") {
							// first of all, get the data by posting the prid
							$.ajax( {
								type: "POST",
								url: "updateAndReturnProvinceData",
								data: { prid: a.attr("order"), newName: $("td.name>input").val(), newCnName : $("td.cnName>input").val(), newCnShortName : $("td.cnShortName>input").val(), newCapital : $("td.capital>input").val(), newCityAmount : $("td.cityAmount>input").val(), newBrightness : $("td.brightness>input").val() },
								dataType: "json"
							}).done(function( json ) {
							// then refresh the whole line
								var data = eval("("+json+")");
								a.find("td[class='id']").html("<div class=\"item\"></div>"+data.id);
								a.find("td[class='name']").html(data.name);
								a.find("td[class='cnName']").html(data.cnName);
								a.find("td[class='cnShortName']").html(data.cnShortName);
								a.find("td[class='capital']").html(data.capital);
								a.find("td[class='cityAmount']").html(data.cityAmount);
								a.find("td[class='brightness']").html(data.brightness);
								
							}).fail(function() {
								alert("FAIL");
							}).error(function (XMLHttpRequest, textStatus, errorThrown) {
								$("#ajax").html(XMLHttpRequest.responseText);
							});
						}
						else
							alert("请输入完整的属性!");
					});
					// cancel
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
				url: "saveAndFetchLastProvince",
				data: { name: $("tr .name:last input").val(), cnName: $("tr .cnName:last input").val(), cnShortName: $("tr .cnShortName:last input").val(), capital: $("tr .capital:last input").val(), cityAmount: $("tr .cityAmount:last input").val(), brightness: $("tr .brightness:last input").val() },
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
				$("tr .cnShortName:last").html(data.cnShortName);
				$("tr .capital:last").html(data.capital);
				$("tr .cityAmount:last").html(data.cityAmount);
				$("tr .brightness:last").html(data.brightness);
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
    
    <h1>省份</h1>
    <div id="main" style="width:75%;">
    <table border="1">
    	<tr> 
			<td width="6%">PRID</td>
			<td width="20%">Name</td>
			<td width="20">CHN Name</td>
			<td width="7%">Short Name</td>
			<td width="25%">Capital</td>
			<td width="6%">City Amount</td>
			<td width="6%">Brightness</td>
		</tr>
	<s:iterator value="#request.list" id="province">
		<tr class="content" order=${province.id}>
			<td class="id"><div class="item"></div>${province.id}</td>
			<td class="name">${province.name}</td>
			<td class="cnName">${province.cnName}</td>
			<td class="cnShortName">${province.cnShortName}</td>
			<td class="capital">${province.capital}</td>
			<td class="cityAmount">${province.cityAmount}</td>
			<td class="brightness">${province.brightness}</td>
		</tr>
	</s:iterator>
    </table>
    <div class="btn btn-add" id="add"></div>
    <div class="btn invisible" id="confirm">√</div><div class="btn invisible" id="cancel">X</div></div>
  </body>
</html>
