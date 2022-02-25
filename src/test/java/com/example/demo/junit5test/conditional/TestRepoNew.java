package com.example.demo.junit5test.conditional;

import com.example.demo.junit5test.TestAppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith({ SpringExtension.class })
@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource(properties = {"new.config.use=true"})
public class TestRepoNew {

	@Autowired
	MyConfig myConfig;

	@Autowired
	@Qualifier("my-repository")
	private MyRepository repo;

	@Test
	void testNewRepo() {
//		log.info("choose profile: {}", myConfig.profile);
		String text = repo.hello();
		Assertions.assertEquals("New->world", text);
	}


}
