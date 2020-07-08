package esameOOP.project.Controller;

import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import esameOOP.project.Model.*;

@RestController
public class Controller {
	
	@GetMapping("/Metadata")
	public Metadata[] getMetadata() { 
		Feed feed = new Feed();
		return feed.getMetadata();
		
	}

	@GetMapping("/Data")
	public Vector<Post> Feed(){
		Feed feed = new Feed();
		return feed.getFeed();
	}
	
	/* @GetMapping("/FilteredData")
	public Stat getFilteredData(){
	}
	*/
	
	@GetMapping("/Stats")
	public Stat getStat() {
		Feed feed = new Feed();
		Stat stats = new Stat(feed.getFeed());
		return stats;
	}
	
	
	/* @GetMapping("/FilteredStats")
	public Stat getFilteredStats() {
	}
	*/
	
	@GetMapping("/Stat/Politic")
	public StatPolitics getPoliticStat() {
		Feed feed = new Feed();
		StatPolitics statpolitics = new StatPolitics(feed.getFeed());
		return statpolitics;
	}
	
	/* @GetMapping("/Stat")
	public Stat getFilteredPoliticStat() {
	}
	*/
	
	@GetMapping("/Stat/Lenght")
	public StatLenght getLenghtStat() {
		Feed feed = new Feed();
		StatLenght statlenght = new StatLenght(feed.getFeed());
		return statlenght;
	}
	
	/* @GetMapping("/Stat")
	public Stat getFilteredLenghtStat() {
	}
	*/
	
	@GetMapping("/Stat/Time")
	public StatTime getTimeStat() {
		Feed feed = new Feed();
		StatTime stattime = new StatTime(feed.getFeed());
		return stattime;
	}
	
	/* @GetMapping("/Stat")
	public Stat getFilteredTimeStat() {
	}
	
	*/
	

	



}

