package esameOOP.project.Controller;

import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import esameOOP.project.Exceptions.FailedConnectionException;
import esameOOP.project.Exceptions.InternalServerException;
import esameOOP.project.Model.*;

@RestController
public class ControllerClass {

	@GetMapping("/Metadata")
	public Metadata[] getMetadata() throws InternalServerException, FailedConnectionException {
		Feed feed = new Feed();
		Metadata[] reply = feed.getMetadata();
		return reply;

	}

	@GetMapping("/Data")
	public Vector<Post> Feed() throws InternalServerException, FailedConnectionException {
		Feed feed = new Feed();
		return feed.getFeed();
	}

	/*
	 * @GetMapping("/FilteredData") public Stat getFilteredData(){ }
	 * 
	 */
	@GetMapping("/Stats")
	public Stat getStat() throws InternalServerException, FailedConnectionException {
		Feed feed = new Feed();
		Stat stats = new Stat(feed.getFeed());
		return stats;
	}

	/*
	 * @GetMapping("/FilteredStats") public Stat getFilteredStats() { }
	 * 
	 */
	@GetMapping("/Stats/Politic")
	public StatPolitics getPoliticStat() throws InternalServerException, FailedConnectionException {
		Feed feed = new Feed();
		StatPolitics statpolitics = new StatPolitics(feed.getFeed());
		return statpolitics;
	}

	/*
	 * @GetMapping("/Stat") public Stat getFilteredPoliticStat() { }
	 */

	@GetMapping("/Stats/Lenght")
	public StatLenght getLenghtStat() throws InternalServerException, FailedConnectionException {
		Feed feed = new Feed();
		StatLenght statlenght = new StatLenght(feed.getFeed());
		return statlenght;
	}

	@GetMapping("/Stats/Time")
	public StatTime getTimeStat() throws InternalServerException, FailedConnectionException {
		Feed feed = new Feed();
		StatTime stattime = new StatTime(feed.getFeed());
		return stattime;
	}
}
/*
 * @GetMapping("/Stat") public Stat getFilteredLenghtStat() { }
 * 
 * 
 * 
 * 
 * /* @GetMapping("/Stat") public Stat getFilteredTimeStat() { }
 * 
 * 
 * 
 * 
 */
