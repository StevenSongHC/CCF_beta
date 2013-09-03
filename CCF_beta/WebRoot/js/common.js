var XMLHttp;

function createXMLHttpRequest() {
	try {
		//Create XMLHttpRequest Object for new version IE
		XMLHttp = new ActiveXObject("Msxml2.XMLHTTP");
	}catch (e) {
		try {
			//Create XMLHttpRequestObject for old version IE
			XMLHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}catch (oc) {
			//XMLHttp Object Created Failed
			alert('Communicate Failed!');
			XMLHttp = null;
		}
	}
	//Create XMLHttp Object for Other Kind of Browsers
	if (!XMLHttp && typeof XMLHttp != "undefined") {
		try {
			XMLHttp = new XMLHttpRequest();
		}catch(error) {
			//XMLHttp Object Created Failed
			alert('Communicate Failed!');
			XMLHttp = null;
		}
	}
	return XMLHttp;
}