let resetBtn = document.getElementById("resetBtn");
let content = document.getElementById("content");
let undoBtn = document.getElementById("undoBtn");
let redoBtn = document.getElementById("redoBtn");
let titleList = document.getElementById("titleList");

resetBtn.onclick = function() {
	let title = document.getElementById("title");
	title.value = " ";
	content.value = " ";
	title.focus;
}// reset 닫음

content.addEventListener('keydown', function(key) {
	if (key.keyCode == 13) {
		let row = content.value.split('\n').length;
		let maxRow = 20;

		if (row < maxRow) {
			ajaxSaveOneLine();
		}
		else if (row > maxRow) {
			alert('20줄 까지만 가능합니다.');
			afterSlicedText = content.value.split('\n').slice(0, maxRow);
			content.value = afterSlicedText.join('\n');
		}
	}

})// 엔터이벤트 닫음

function ajaxSaveOneLine() {
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
			console.log("ENTER SAVE sucess");
		}
	});// ajax닫힘
}

function calRowLength(content) {
	let row = content.val().split('\n').length;
	let array = content.val().split('\n');
}

function getLastText() {
	let contentText = document.getElementById("content").value;
	let textSplitByEnter = contentText.split("\n");
	let contentLength = textSplitByEnter.length;
	let lastText = "";
	if (contentLength > 1) { // 첫 번째 줄이 아닌 경우
		lastText = textSplitByEnter[contentLength - 1] == "" ? textSplitByEnter[contentLength - 2] : textSplitByEnter[contentLength - 1]; // 공백이라면, 공백 앞 줄이 lastText
	} else if (contentLength <= 1) { // 첫 번째 줄 인 경우
		lastText = textSplitByEnter[contentLength - 1];
	}

	return lastText;
}

undoBtn.onclick = function() {
	
	if (document.getElementById("content").value == "") {
		alert("빈 칸입니다.");
		return false;
	}
	
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
			var text = document.getElementById("content");
			if (data.result == lastText) {
				/* 
					에러:
						마지막 줄에 동일한 문자열 패턴이 있다면 동일한 문자열 패턴이 모두 사라짐
				*/
				var index = text.value.indexOf("\n"+lastText);
				text.value = text.value.substring(0, index)			
			}
		}
	}); // ajax닫힘

}// undo 닫음

redoBtn.onclick = function() {
	$.ajax({
		type : "GET",
		url : "/textEditor/redo.ajax",
		dataType : "json",
		success : function(data) {
			console.log(data);
			var text = document.getElementById("content");
			text.value = text.value + "\n" + data.result;
		}
	}); // ajax닫힘
}// redo 닫음