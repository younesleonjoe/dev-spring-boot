package com.leonspringdemo.mvc.controller;

import com.leonspringdemo.mvc.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

	/*
		Pre-processes every String form data
		Remove leading and trailing whitespace
		If a String only has whitespace...trim it to null
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/")
	public String showForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-form";
	}

	@PostMapping("/processCustomerForm")
	public String processCustomerForm(
			@Valid @ModelAttribute("customer") Customer customer,
			BindingResult bindingResult
	) {

		/*
			To check the error codes available in the bidingResult to add them inside messages.properties
		 */
		System.out.println("\n\n\n\n");
		System.out.println("Binding results: " + bindingResult.toString());
		System.out.println("\n\n\n\n");
		
		
		if (bindingResult.hasErrors()) {
			return "customer-form";
		}
		else {
			return "customer-confirmation";
		}
	}
}
