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
	String[] enterLineText = new String[] {};
	int inputStringLength = 0;

	String[] tempUndoText = new String[] {};
	int cnt = 1;

	
	public String[] saveText(String title, String content) {
		
		titleArray = new String[count+1];
		for (int i = 0; i < titleArray.length; i++) {
			TextEditorDTOArray[titleArray.length-1] = new TextEditorDTO(title, content);
			titleArray[i] = TextEditorDTOArray[i].getTitle();
		}
		
		count++;
	
		return titleArray;
	
	}
	
	public void enterLine(String intermediateStorage) {
		/*
		inputStringLength = intermediateStorage.split("\n").length;
		enterLineText = new String[inputStringLength];
		enterLineText = intermediateStorage.split("\n");
		if (enterLineText[inputStringLength - 1].equals("")) {
			return;
		} else {
			interStorage.push(enterLineText[inputStringLength - 1]);
		}
		*/
		
		if (intermediateStorage.equals("")) {
			return;
		} else {
			interStorage.push(intermediateStorage);
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
		String textSave = "";

		try {
			if(lastText.equals("")) {
				return "";
			} else {
				interStorage.push(lastText);
				textSave = "OK";
			}

		} catch (EmptyStackException e) {
			
				textSave = "NO";
				
				return textSave;

		}

		return textSave;
	};
	
	public String redo() {
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		String redoString = null;
		int tempUndoTextLength = tempUndoText.length;
		
		redoString = interStorage.push(tempUndoText[tempUndoTextLength - 1]);
		
		tempUndoText[tempUndoText.length - 1] = redoString;


		
		
//		for (int i = cnt; i <= tempUndoTextLength - 1; i++) {
//			System.out.println("?쒖븞?吏");
//			tempUndoText[i - 1] = tempUndoText[i];
//			System.out.println("吏?쒌꽩?? "+tempUndoText[i - 1]);
//			System.out.println("吏?쒌꽩?담뀋?밤꽩?뱁썑: "+tempUndoText[i]);
//		}
//		
//		tempUndoTextLength--;
//		System.out.println("類닿퀬?쒗썑: "+tempUndoTextLength);
//		tempUndoText[tempUndoTextLength] = null;
//		
//		tempUndoText[tempUndoTextLength - 1] = redoString;

		jsonObject.addProperty("redoString", tempUndoText[tempUndoText.length - 1]);
		String json = gson.toJson(jsonObject);
		
		return json;
	}


	
}
