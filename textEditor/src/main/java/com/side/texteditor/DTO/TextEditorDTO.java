package com.side.texteditor.DTO;

public class TextEditorDTO {
	String title;
	String content;

	public TextEditorDTO(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public TextEditorDTO() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
