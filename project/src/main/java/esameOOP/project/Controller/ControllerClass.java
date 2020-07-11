package esameOOP.project.Controller;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import esameOOP.project.Exceptions.FailedConnectionException;
import esameOOP.project.Exceptions.FilterNotFoundException;
import esameOOP.project.Exceptions.InternalServerException;
import esameOOP.project.Filters.FilterHandler;
import esameOOP.project.Model.Feed;
import esameOOP.project.Model.Metadata;
import esameOOP.project.Model.Post;
import esameOOP.project.Model.Stat;
import esameOOP.project.Model.StatLenght;
import esameOOP.project.Model.StatPolitics;
import esameOOP.project.Model.StatTime;

@RestController
public class ControllerClass {

	private Feed feed;
	private Stat stats;

	@Autowired
	private void create() throws InternalServerException, FailedConnectionException {
		feed = new Feed();
		stats = new Stat(feed.getFeed());
	}

	@GetMapping("/Metadata")
	public Metadata[] getMetadata() {
		return feed.getMetadata();

	}

	@GetMapping("/Data")
	public Vector<Post> Feed() {
		return feed.getFeed();
	}

	@PostMapping("/Data")
	public Vector<Post> Feed(@RequestBody String body) {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		return filteredfeed;
	}

	@GetMapping("/Stats")
	public Stat getStat() {
		return stats;
	}

	@PostMapping("/Stats")
	public Stat getStat(@RequestBody String body) {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		Stat filteredstats = new Stat(filteredfeed);
		return filteredstats;
	}

	@GetMapping("/Stat/Politic")
	public StatPolitics getPoliticStat() {
		return stats.getStatPolitics();
	}

	@PostMapping("/Stat/Politic")
	public StatPolitics getPoliticStat(@RequestBody String body) {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		StatPolitics filteredstatpolitic = new StatPolitics(filteredfeed);
		return filteredstatpolitic;
	}

	@GetMapping("/Stat/Lenght")
	public StatLenght getLenghtStat() {
		return stats.getStatLenght();
	}

	@PostMapping("/Stat/Lenght")
	public StatLenght getLenghtStat(@RequestBody String body) {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		StatLenght filteredstatlenght = new StatLenght(filteredfeed);
		return filteredstatlenght;
	}

	@GetMapping("/Stat/Time")
	public StatTime getTimeStat() {
		return stats.getStatTime();
	}

	@PostMapping("/Stat/Time")
	public StatTime getTimeStat(@RequestBody String body) {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		StatTime filteredstattime = new StatTime(filteredfeed);
		return filteredstattime;
	}

}