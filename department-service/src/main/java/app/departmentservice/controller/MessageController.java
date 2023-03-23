package app.departmentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/api/v1/message")
public class MessageController {
	@Value("${spring.boot.message}")
	private String appName;

	@GetMapping
	public ResponseEntity<String> getMessage() {
		return ResponseEntity.ok("Hello from " + appName);
	}
}
