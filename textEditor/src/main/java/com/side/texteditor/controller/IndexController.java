package com.side.texteditor.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@SpringBootApplication
public class IndexController {
	//LoggerFactoryctory.getLogger(IndexController.class);

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "index";
	}

}
