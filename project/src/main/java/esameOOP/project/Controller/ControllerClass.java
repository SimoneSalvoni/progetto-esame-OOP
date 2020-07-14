package esameOOP.project.Controller;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import esameOOP.project.Exceptions.FailedConnectionException;
import esameOOP.project.Exceptions.InternalServerException;
import esameOOP.project.Filters.FilterHandler;
import esameOOP.project.Model.Feed;
import esameOOP.project.Model.Metadata;
import esameOOP.project.Model.Post;
import esameOOP.project.Model.Stat;
import esameOOP.project.Model.StatLenght;
import esameOOP.project.Model.StatPolitics;
import esameOOP.project.Model.StatTime;

/** 
 * La classe gestisce le chiamate GET e POST del Client 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 * */

@RestController
public class ControllerClass {

	private Feed feed;
	private Stat stats;
	
	/** 
	 * Vengono definite le dipendenze tra bean. 
	 */
	
	@Autowired
	private void create() throws InternalServerException, FailedConnectionException {
		feed = new Feed();
		stats = new Stat(feed.getFeed());
	}
	
	/**
	 * Risponde alla richiesta GET /Metadata
	 * @return vettore di Metadata
	 */
	
	@GetMapping("/Metadata")
	public Metadata[] getMetadata() {
		
		return feed.getMetadata();

	}

	/**
	 * Risponde alla richiesta GET /Data
	 * @return Vector di oggetti Post
	 */
	@GetMapping("/Data")
	public Vector<Post> Feed() {
		return feed.getFeed();
	}
	
	/**
	 * Risponde alla richiesta POST /Data
	 * @param body contenente i filtri da applicare
	 * @return Vector di oggetti post filtrati
	 */
	
	@PostMapping("/Data")
	public Vector<Post> Feed(@RequestBody String body) {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		return filteredfeed;
	}

	/**
	 * Risponde alla richiesta GET /Stats
	 * @return oggetti di tipo Stat
	 */
	@GetMapping("/Stats")
	public Stat getStat() {
		return stats;
	}
	
	/**
	 * Risponde alla richiesta POST /Stats
	 * @param body contentente i filtri da applicare
	 * @return oggetti di tipo Stat filtrati
	 */

	@PostMapping("/Stats")
	public Stat getStat(@RequestBody String body) {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		Stat filteredstats = new Stat(filteredfeed);
		return filteredstats;
	}

	/**
	 * Risponde alla richiesta GET /Stats/Politic
	 * @return oggetti di tipo Statpolitics
	 */
	
	@GetMapping("/Stats/Politic")
	public StatPolitics getPoliticStat() {
		return stats.getStatPolitics();
	}

	/**
	 * Risponde alla richiesta POST /Stats/Politic
	 * @param body contentente i filtri da applicare
	 * @return oggetti di tipo StatPolitics filtrati
	 */
	@PostMapping("/Stats/Politic")
	public StatPolitics getPoliticStat(@RequestBody String body) {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		StatPolitics filteredstatpolitic = new StatPolitics(filteredfeed);
		return filteredstatpolitic;
	}
	
	/**
	 * Risponde alla richiesta GET /Stats/Lenght
	 * @return oggetti di tipo StatLenght
	 */

	@GetMapping("/Stats/Lenght")
	public StatLenght getLenghtStat() {
		return stats.getStatLenght();
	}
	
	/**
	 * Risponde alla richiesta POST /Stats/Lenght
	 * @param body contenente i filtri da applicare.
	 * @return oggetti di tipo StatLenght filtrati
	 */

	@PostMapping("/Stats/Lenght")
	public StatLenght getLenghtStat(@RequestBody String body) {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		StatLenght filteredstatlenght = new StatLenght(filteredfeed);
		return filteredstatlenght;
	}

	/**
	 * Risponde alla richiesta GET /Stats/Time
	 * @return oggetti di tipo StatTime
	 */
	
	@GetMapping("/Stats/Time")
	public StatTime getTimeStat() {
		return stats.getStatTime();
	}
	
	/**
	 * Risponde alla richiesta POST /Stat/Time
	 * @param body contenente i filtri da applicare
	 * @return oggetti di tipo StatTime filtrati
	 */

	@PostMapping("/Stats/Time")
	public StatTime getTimeStat(@RequestBody String body) {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		StatTime filteredstattime = new StatTime(filteredfeed);
		return filteredstattime;
	}

}