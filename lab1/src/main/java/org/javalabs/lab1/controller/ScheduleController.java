package org.javalabs.lab1.controller;

import org.javalabs.lab1.cache.ScheduleCache;
import org.javalabs.lab1.entity.Schedule;
import org.javalabs.lab1.exceptionhandler.GlobalExceptionHandler;
import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.javalabs.lab1.service.RequestCounterService;
import org.javalabs.lab1.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ScheduleController {
	private final ScheduleService scheduleService;
	private static final String STATUS_CODE_OK = "success";
	private final ScheduleCache scheduleCache;
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


	public ScheduleController(ScheduleService service, ScheduleCache scheduleCache) {
		this.scheduleService = service;
		this.scheduleCache = scheduleCache;
	}

	@GetMapping("/schedule")
	public ApiResponse search(@RequestParam(value = "studentGroup") String query) {
		LOGGER.info("get endpoint /schedule was called");

		RequestCounterService.incrementRequestCount();
		RequestCounterService.printRequestCount();

		ApiResponse cachedResponse = scheduleCache.get(query);
		if (cachedResponse != null) {
			return cachedResponse;
		} else {
			ApiResponse response = scheduleService.searchPage(query);
			scheduleCache.put(query, response);
			return response;
		}
	}

	@PostMapping("/schedule")
	public ResponseEntity<String> createSchedule(@RequestBody Schedule scheduleEntity) {
		LOGGER.info("post endpoint /schedule was called");

		if (scheduleEntity == null ||
				scheduleService.getTeacherScheduleRepository().findByGroupName(scheduleEntity.getGroupName()) != null) {
			return ResponseEntity.badRequest().body("error");
		}

		try {
			scheduleService.createSchedule(scheduleEntity);

			return ResponseEntity.ok(STATUS_CODE_OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error: " + e.getMessage());
		}
	}

	@PutMapping("/schedule/{id}")
	public ResponseEntity<String> updateSchedule(@PathVariable("id") int id, @RequestBody Schedule scheduleEntity) {
		LOGGER.info("put endpoint /schedule/{id} was called");

		if (scheduleEntity == null) {
			return ResponseEntity.badRequest().body("error");
		}

		try {
			Optional<Schedule> existingEntityOptional = scheduleService.getTeacherScheduleRepository().findById(id);
			if (existingEntityOptional.isEmpty()) {
				return ResponseEntity.notFound().build();
			}

			Schedule existingEntity = existingEntityOptional.get();

			if (!existingEntity.getGroupName().equals(scheduleEntity.getGroupName()) &&
					scheduleService.getTeacherScheduleRepository().findByGroupName(scheduleEntity.getGroupName()) != null) {
				return ResponseEntity.badRequest().body("error: Group name already exists");
			}

			scheduleService.updateSchedule(id, scheduleEntity);
			return ResponseEntity.ok(STATUS_CODE_OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error " + e.getMessage());
		}
	}

	@DeleteMapping("/schedule/{id}")
	public ResponseEntity<String> deleteSchedule(@PathVariable("id") int id) {
		LOGGER.info("delete endpoint /schedule/{id} was called");

		try {
			scheduleService.deleteSchedule(id);

			return ResponseEntity.ok(STATUS_CODE_OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public String handleMissingParameter(MissingServletRequestParameterException ex) {
		return "Required parameter 'studentGroup' is missing";
	}
}