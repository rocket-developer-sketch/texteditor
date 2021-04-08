let resetBtn = document.getElementById("resetBtn");
let content = document.getElementById("content");
let undoBtn = document.getElementById("undoBtn");
let redoBtn = document.getElementById("redoBtn");
let titleList = document.getElementById("titleList");

resetBtn.onclick = function() {
	let title = document.getElementById("title");
	let content = document.getElementById("content");
	title.value = " ";
	content.value = " ";
	title.focus;
}// reset 닫음

content.addEventListener('keydown', function(key) {
	if (key.keyCode == 13) {
		let row = content.value.split('\n').length;
		let maxRow = 20;
		var contentText = content.value;
		
		

		if (row < maxRow) {
			ajaxSaveOneLine(contentText);
		}
		else if (row > maxRow) {
			alert('20줄 까지만 가능합니다.');
			afterSlicedText = content.value.split('\n').slice(0, maxRow);
			content.value = afterSlicedText.join('\n');
		}
	}

})// 엔터이벤트 닫음

function ajaxSaveOneLine(contentText) {
	let lastText = getLastText();
	
	var json = {
		"content": lastText
	}
	$.ajax({
		type: "GET",
		url: "./textValue.ajax",
		data: json,
		contentType: "application/json",
		success: function() {
			console.log("sucess")
			// 현재 데이터 받을 생각 없음.06.30
		}
	});// ajax닫힘
}

function calRowLength(content) {
	let row = content.val().split('\n').length;
	let array = content.val().split('\n');
}

function getLastText(){
	let contentText = content.value;
	let textSplitByEnter = contentText.split("\n");
	let contentLength = textSplitByEnter.length;
	let lastText = textSplitByEnter[contentLength - 1];
	
	return lastText;
}

undoBtn.onclick = function() {
	let lastText = getLastText();

	let json = {
		"lastText": lastText
	}

	$.ajax({
		type: "POST",
		url: "/textEditor/undo.ajax",
		data: JSON.stringify(json),
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			if (data.result == "OK") {
				if (contentLength > 1) {
					content.value = content.value.replace("\n" + lastText, "");
				}
				else if (contentLength == 1) {
					content.value = "";
					//undoBtn.disabled = true;					
				}
			}
		}
	}); // ajax닫힘

}// undo 닫음

//redoBtn.onclick = function() {
//	let Param = "redo";
//	$.ajax({
//		type : "GET",
//		url : "./doAction.ajax?doAction=" + Param,
//		data : content,
//		dataType : "json",
//		success : function(data) {
//			console.log("redp한 줄",data.redoString);
//		}
//	}); // ajax닫힘
//}// redo 닫음