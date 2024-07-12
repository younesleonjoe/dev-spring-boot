package com.leon.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	/*
	Spring by default will search the following directories top to bottom:
		/META-INF/resources
		/resources
		/static
		/public
	 */

	/*
	To add a 3rd library like bootstrap, we can download min.css and place it inside static/css directory
	and call it in thymeleaf using <link rel="stylesheet" th:href="@{/css/bootstrap.min.css}">
	 */

	@GetMapping("/hello")
	public String sayHello(Model model) {
		model.addAttribute("date", new java.util.Date());
		return "helloworld";
	}
}
