package com.example.demo.junit5test.conditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"new.config.use=false"})
public class TestRepoOld {

	@Autowired
	@Qualifier("my-repository")
	private MyRepository repo;

	@Test
	void testOldRepo() {
		String text = repo.hello();
		Assertions.assertEquals("Old->world", text);
	}

}
