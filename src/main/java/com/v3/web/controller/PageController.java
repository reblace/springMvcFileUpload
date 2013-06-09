package com.v3.web.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loadHome(Model model){
		//build the model to return to the view for rendering
		model.addAttribute("time", Calendar.getInstance());

		return "upload";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		return "login";
	}

	@RequestMapping(value = "/error/404", method = RequestMethod.GET)
	public String handle404(){
		return "404";
	}

	@RequestMapping(value = "/error/403", method = RequestMethod.GET)
	public String handle403(){
		return "403";
	}

}
