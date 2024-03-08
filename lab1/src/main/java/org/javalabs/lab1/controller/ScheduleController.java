package org.javalabs.lab1.controller;

import org.javalabs.lab1.cache.ScheduleCache;
import org.javalabs.lab1.entity.Schedule;
import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.javalabs.lab1.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.Optional;

@RestController
public class ScheduleController {
	private final ScheduleService scheduleService;
	private static final String STATUS_CODE_OK = "success";
	private final ScheduleCache scheduleCache;

	public ScheduleController(ScheduleService service, ScheduleCache scheduleCache) {
		this.scheduleService = service;
		this.scheduleCache = scheduleCache;
	}

	@GetMapping("/schedule")
	public ApiResponse search(@RequestParam(value = "studentGroup") String query) {
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