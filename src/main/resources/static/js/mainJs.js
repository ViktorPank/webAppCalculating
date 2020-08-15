
window.onload = pageLoadedHandler; 

function pageLoadedHandler(e) {
	document.getElementById('loadBtn').addEventListener('click', loadResult, false);
	document.getElementById('saveResult').addEventListener('click', saveResult, false);
}

function saveResult(evt){
	evt.preventDefault();	
	var enterText = document.getElementById('enterText').value;	
	var chooseExample = document.getElementById('chooseExample').value;
	var sReq = new XMLHttpRequest();
	
	sReq.onreadystatechange = function() {
		if (this.status == 200 && this.readyState == 4) {
			var hyperlink = document.createElement('a');
			hyperlink.setAttribute('href','/downloadFile/temp.bin');
			hyperlink.setAttribute('download','download');
			onload=hyperlink.click();			
		}
	};
	
	sReq.open("POST","/createDownloadFileResult");
	sReq.setRequestHeader("Content-Type", "application/json");
	var rawData = "#" + chooseExample + "#" + "\n" + enterText;
	sReq.send(JSON.stringify({rawData:rawData}));	     
	
}

function loadResult(evt){
	evt.preventDefault();	
	
	var form = document.forms.namedItem('fileinfo');
	var oOutput = document.getElementById('resultText');
	var enterText = document.getElementById('enterText');
	var chooseExample = document.getElementById('chooseExample');
	var oData = new FormData(form);
	var oReq = new XMLHttpRequest();
	
   
	oReq.onreadystatechange = function() {
		if (this.status == 200 && this.readyState == 4) {
			var resultText = document.getElementById('resultText');
			var calculate = JSON.parse(this.responseText);
			resultText.value = calculate.resultData;
			enterText.value = calculate.data;
			chooseExample.value = calculate.typeExample;
		}
	};
	
	oReq.open("POST", "/upload", true);
	oReq.send(oData);	
	
}

function clearScreen(){
	var enterText = document.getElementById('enterText');
	var resultText = document.getElementById('resultText');
	enterText.value = '';
	resultText.value = '';
}

function calculate(){
	var enterText = document.getElementById('enterText').value;

	var chooseExample = document.getElementById('chooseExample').value;
	if (enterText != "" && chooseExample != ""){
		var xhttp = new XMLHttpRequest();   // new HttpRequest instance
		xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var resultText = document.getElementById('resultText');
				var data = JSON.parse(this.responseText);
				resultText.value = data.resultData;
			}
		}
        xhttp.open("POST", "/calculate");
        xhttp.setRequestHeader("Content-Type", "application/json");
        var rawData = "#" + chooseExample + "#" + "\n" + enterText;
        xhttp.send(JSON.stringify({rawData:rawData}));
	}
}