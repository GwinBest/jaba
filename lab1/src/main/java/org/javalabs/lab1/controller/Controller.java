package org.javalabs.lab1.controller;

import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.javalabs.lab1.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
@RestController
public class Controller {
	private final MyService service;
	public Controller(MyService service) {
		this.service = service;
	}
	@GetMapping("/schedule")
	public ApiResponse search(@RequestParam(value = "studentGroup") String query) {
		return  service.searchPage(query);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handleMissingParameter(MissingServletRequestParameterException ex) {
		return "Required parameter 'studentGroup' is missing";
	}
}