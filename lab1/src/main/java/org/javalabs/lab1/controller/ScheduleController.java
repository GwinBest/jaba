package org.javalabs.lab1.controller;

import org.javalabs.lab1.entity.ScheduleEntity;
import org.javalabs.lab1.model.apiresponse.ApiResponse;
import org.javalabs.lab1.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MissingServletRequestParameterException;

@RestController
public class ScheduleController {
	private final ScheduleService service;
	private static final String STATUS_CODE_OK = "success";


	public ScheduleController(ScheduleService service) {
		this.service = service;
	}

	@GetMapping("/schedule")
	public ApiResponse search(@RequestParam(value = "studentGroup") String query) {
		return service.searchPage(query);
	}

	@PostMapping("/schedule")
	public ResponseEntity<String> createSchedule(@RequestBody ScheduleEntity scheduleEntity) {
		if (scheduleEntity == null ||
				service.getTeacherScheduleRepository().findByGroupName(scheduleEntity.getGroupName()) != null) {
			return ResponseEntity.badRequest().body("error");
		}

		try {
			service.createSchedule(scheduleEntity);

			return ResponseEntity.ok(STATUS_CODE_OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error: " + e.getMessage());
		}
	}

	@PutMapping("/schedule/{id}")
	public ResponseEntity<String> updateSchedule(@PathVariable("id") int id, @RequestBody ScheduleEntity scheduleEntity) {
		if (scheduleEntity == null ||
				service.getTeacherScheduleRepository().findByGroupName(scheduleEntity.getGroupName()) != null) {
			return ResponseEntity.badRequest().body("error");
		}

		try {
			service.updateSchedule(id, scheduleEntity);

			return ResponseEntity.ok(STATUS_CODE_OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error " + e.getMessage());
		}
	}

	@DeleteMapping("/schedule/{id}")
	public ResponseEntity<String> deleteSchedule(@PathVariable("id") int id) {
		try {
			service.deleteSchedule(id);

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