package com.side.texteditor.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.side.texteditor.DTO.TextEditorDTO;
import com.side.texteditor.service.TextEditorService;


@RequestMapping("/textEditor")
@Controller
public class TextEditorController {

	@Autowired
	TextEditorService textEditorService;
	
	String [] titleArray = null;
	
	
	@GetMapping("/open")
	public String toTextEditor(Model model) {
		
		model.addAttribute("textList", titleArray);
		
		return "/textEditor/textEditor";
	}
	
	@PostMapping("/saveContent")
	public String saveContent(HttpServletRequest request, Model model, 
			@RequestParam("title") String title, @RequestParam("content") String content) {
		
		titleArray = textEditorService.saveText(title, content);
		
		return "redirect:./open";
		
	}
	
	@GetMapping("/showContent")
	public String showText(HttpSession session, @RequestParam("index") Integer index, Model model) {

		TextEditorDTO textEditorDTO = textEditorService.showText(index);

		if (index != null) {
			model.addAttribute("textEditorDTO", textEditorDTO);
			model.addAttribute("textList", titleArray);
			
		} else {
			
			return "redirect: ./../";
		}
		
		return "textEditor/textEditor";

		
	}
	
	@GetMapping("/textValue.ajax")
	public @ResponseBody void intermediateStorage(@RequestParam("content") String intermediateStorage) {
		textEditorService.enterLine(intermediateStorage);
	}

	@PostMapping("/undo.ajax")
	public @ResponseBody Map<String, Object> undo(@RequestBody Map<String, Object> param) {

		String lastText = param.get("lastText").toString();
		Map<String, Object> result = new HashMap<String, Object>();
		
		String textSave = textEditorService.undo(lastText);
		
		result.put("result", textSave);

		return result;
	}

}

