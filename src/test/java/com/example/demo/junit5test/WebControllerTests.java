package com.example.demo.junit5test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebControllerTests {

	@Autowired
	private WebTestClient webTestClient; // available with Spring WebFlux

	@LocalServerPort
	private Integer port;


	@Test
	void testPing() {
		webTestClient
				.get()
				.uri("/ping")
				.exchange()
				.expectStatus()
				.is2xxSuccessful();
	}

}
