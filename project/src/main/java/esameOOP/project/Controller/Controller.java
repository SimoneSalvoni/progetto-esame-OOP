package esameOOP.project.Controller;

import java.util.Vector;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esameOOP.project.Util.*;
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
		Stat stat = new Stat();
		return stat;
	}
	
	
	/* @GetMapping("/FilteredStats")
	public Stat getFilteredStats() {
	}
	*/
	
	@GetMapping("/PoliticStat")
	public StatPolitics getPoliticStat() {
		Stat stat = new Stat();
		return stat.getStatPolitics();
	}
	
	/* @GetMapping("/FilteredPoliticStat")
	public Stat getFilteredPoliticStat() {
	}
	*/
	
	@GetMapping("/LenghtStat")
	public StatLenght getLenghtStat() {
		Stat stat = new Stat();
		return stat.getStatLenght();
	}
	
	/* @GetMapping("/FilteredLenghtStat")
	public Stat getFilteredLenghtStat() {
	}
	*/
	
	@GetMapping("/TimeStat")
	public StatTime getTimeStat() {
		Stat stat = new Stat();
		return stat.getStatTime();
	}
	
	/* @GetMapping("/FilteredTimeStat")
	public Stat getFilteredTimeStat() {
	}
	
	*/
	

	



}

