function getNotification(){
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
};