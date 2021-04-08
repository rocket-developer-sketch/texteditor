package com.side.texteditor.service;

import java.util.EmptyStackException;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.side.texteditor.DTO.TextEditorDTO;
import com.side.util.StackCollectionFunction;

@Service
public class TextEditorService {
	int count = 0;
	TextEditorDTO[] TextEditorDTOArray = new TextEditorDTO[20];
	String[] titleArray = new String[] {};

	StackCollectionFunction interStorage = new StackCollectionFunction(20);
	StackCollectionFunction dynamicStorage = new StackCollectionFunction(20);
	String[] enterLineText = new String[] {};
	int inputStringLength = 0;

	String[] tempUndoText = new String[] {};
	int cnt = 1;

	public void emptyStack() {
		if (interStorage.size() > 0) {
			for(int i = 0; i < interStorage.size(); i++) {
				interStorage.pop();
			}
		}
		if (dynamicStorage.size() > 0) {
			for(int i = 0; i < dynamicStorage.size(); i++) {
				dynamicStorage.pop();
			}
		}
	}
	
	public String[] saveText(String title, String content) {

		titleArray = new String[count + 1];
		for (int i = 0; i < titleArray.length; i++) {
			TextEditorDTOArray[titleArray.length - 1] = new TextEditorDTO(title, content);
			titleArray[i] = TextEditorDTOArray[i].getTitle();
		}

		count++;

		return titleArray;

	}

	public void enterLine(String intermediateStorage) {
		if (intermediateStorage.equals("")) {
			return;
		} else {
			if (interStorage.size() < 19) {
				interStorage.push(intermediateStorage);
			}
		}
	};

	public TextEditorDTO showText(Integer index) {

		TextEditorDTO textEditorDTO = null;

		if (index != null) {
			textEditorDTO = new TextEditorDTO(TextEditorDTOArray[index].getTitle(),
					TextEditorDTOArray[index].getContent());
		}

		return textEditorDTO;

	};

	public String undo(String lastText) {
		try {
			if (interStorage.peek().equals(lastText)) {
				interStorage.pop();
			}
		} catch (EmptyStackException e) {
			System.out.println("ENTER 로 저장 된 데이터 없음");
		}

		if (dynamicStorage.isEmpty() || dynamicStorage.peek().equals(lastText) == false) { // 가장 위에 있는 값이 방금 undo
																							// 한 값과 같은 지 검사
			dynamicStorage.push(lastText);
		}

		return lastText;
	};

	public String redo() {
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		// String redoString = null;
		// int tempUndoTextLength = tempUndoText.length;

		// redoString = dynamicStorage.push(tempUndoText[tempUndoTextLength - 1]);
		// tempUndoText[tempUndoText.length - 1] = redoString;
		// redoString = dynamicStorage.pop();

		// jsonObject.addProperty("redoString", tempUndoText[tempUndoText.length - 1]);
		interStorage.push(dynamicStorage.peek());
		jsonObject.addProperty("redoString", dynamicStorage.pop());
		String json = gson.toJson(jsonObject);

		return json;
	}

}
