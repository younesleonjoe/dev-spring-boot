package com.leon.springboot.thymeleafdemo.controller;

import com.leon.springboot.thymeleafdemo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloWorldController {

	// Show an initial html form
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}

	// Process the html form
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}

	@RequestMapping("/processFormVersionTwo")
	public String processFormVersionTwo(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		name = name.toUpperCase();
		String result = "Yo! " + name;
		model.addAttribute("message", result);
		return "helloworld";
	}

	@RequestMapping(path="/processFormVersionThree", method= RequestMethod.GET)
	public String processFormVersionThree(@RequestParam("name") String name, Model model) {
		name = name.toUpperCase();
		String result = "Yo! " + name;
		model.addAttribute("message", result);
		return "helloworld";
	}
}
