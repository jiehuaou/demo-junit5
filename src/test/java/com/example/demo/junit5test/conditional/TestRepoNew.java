package com.example.demo.junit5test.conditional;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TestRepoNew {

	@Autowired
	MyConfig myConfig;

	@Autowired
	@Qualifier("my-repository")
	private MyRepository repo;

	@Test
	void testNewRepo() {
		log.info("choose profile: {}", myConfig.profile);
		String text = repo.hello();
		Assertions.assertEquals("New->world", text);
	}


}
