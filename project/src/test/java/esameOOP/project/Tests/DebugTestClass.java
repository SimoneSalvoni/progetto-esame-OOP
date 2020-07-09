package esameOOP.project.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import esameOOP.project.Model.Feed;
import esameOOP.project.Model.Stat;

@SpringBootTest
class DebugTestClass {

	private Feed feed;
	private Stat stat;

	@BeforeEach
	void setUp() throws Exception {
		feed = new Feed();
		stat = new Stat(feed.getFeed());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void contexLoads() {
	}

	@Test
	void feedStatNotNull() {
		assertAll(() -> assertNotNull(feed), () -> assertNotNull(stat));
	}

}
