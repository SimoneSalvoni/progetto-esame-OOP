package esameOOP.project.MainApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import esameOOP.project.Model.Feed;

/**
 * @SpringBootApplication public class MainApplication {
 * 
 *                        public static void main(String[] args) {
 *                        SpringApplication.run(MainApplication.class, args); }
 * 
 *                        }
 */

public class MainApplication {
	public static void main(String[] args) {
		Feed feed = new Feed();
		System.out.println(feed);
	}
}