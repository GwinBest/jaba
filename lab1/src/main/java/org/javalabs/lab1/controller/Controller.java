package org.javalabs.lab1.controller;

import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.javalabs.lab1.logic.Logic;
import org.springframework.boot.SpringApplication;
import org.javalabs.lab1.cachemanager.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
@RestController
public class Controller {
	public static void run(String[] args) {
		SpringApplication.run(Controller.class, args);
	}
	@GetMapping("/schedule")
	public ApiResponse search(@RequestParam(value = "studentGroup") String query) {
		CacheManager manager = new CacheManager();
		Logic logic = new Logic();

		return  manager.getCachedSchedule(query, logic);

	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handleMissingParameter(MissingServletRequestParameterException ex) {
		return "Required parameter 'studentGroup' is missing";
	}
}