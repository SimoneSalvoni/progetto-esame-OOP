package esameOOP.project.Controller;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import esameOOP.project.Exceptions.EmptyFeedException;
import esameOOP.project.Exceptions.FailedConnectionException;
import esameOOP.project.Exceptions.TokenNotFoundException;
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
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */

@RestController
public class ControllerClass {

	private Feed feed;
	private Stat stats = null;

	/**
	 * Questo metodo costruisce il feed completo e calcola le statitiche su di esso.
	 */

	@Autowired
	private void create() throws TokenNotFoundException, FailedConnectionException {
		feed = new Feed();
		stats = new Stat(feed.getFeed());
	}

	/**
	 * Risponde alla richiesta GET /Metadata restituendo i metadati
	 * 
	 * @return array di Metadata
	 */

	@GetMapping("/Metadata")
	public Metadata[] getMetadata() {

		return feed.getMetadata();

	}

	/**
	 * Risponde alla richiesta GET /Data restituendo tutti i post
	 * 
	 * @return Vector di oggetti Post
	 */
	@GetMapping("/Data")
	public Vector<Post> Feed() {
		return feed.getFeed();
	}

	/**
	 * Risponde alla richiesta POST /Data. Filtra i post secondo quanto presente nel
	 * body della richiesta
	 * 
	 * @param body String contenente i filtri da applicare
	 * @return Vector di oggetti Post filtrati
	 */

	@PostMapping("/Data")
	public Vector<Post> Feed(@RequestBody String body) {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		return filteredfeed;
	}

	/**
	 * Risponde alla richiesta GET /Stats restituendo le statistiche su tutti i post
	 * 
	 * @return oggetto di tipo Stat
	 * @throws EmptyFeedException Se il feed non ha post
	 */
	@GetMapping("/Stats")
	public Stat getStat() throws EmptyFeedException {
		if (feed.getFeed().size() == 0)
			throw new EmptyFeedException("The feed has no posts");
		return stats;
	}

	/**
	 * Risponde alla richiesta POST /Stats. Filtra i post secondo quanto richiesto
	 * nel body della richiesta e ricalcola le statistiche su questi post.
	 * 
	 * @param body String contentente i filtri da applicare
	 * @return oggetto di tipo Stat
	 * @throws EmptyFeedException Se l'applicazione del filtro restituisce un
	 *                            vettore vuoto
	 */

	@PostMapping("/Stats")
	public Stat getStat(@RequestBody String body) throws EmptyFeedException {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		if (filteredfeed.size() == 0)
			throw new EmptyFeedException("The feed has no posts");
		Stat filteredstats = new Stat(filteredfeed);
		return filteredstats;
	}

	/**
	 * Risponde alla richiesta GET /Stats/Politic restituendo le statistiche sulla
	 * categorizzazione politica
	 * 
	 * @return oggetto di tipo Statpolitics
	 * @throws EmptyFeedException Se il feed non ha post
	 */

	@GetMapping("/Stats/Politic")
	public StatPolitics getPoliticStat() throws EmptyFeedException {
		if (feed.getFeed().size() == 0)
			throw new EmptyFeedException("The feed has no posts");
		return stats.getStatPolitics();
	}

	/**
	 * Risponde alla richiesta POST /Stats/Politic. Filtra i post secondo quanto
	 * richiesto nel body della richiesta e ricalcola le statistiche politiche su
	 * questi post.
	 * 
	 * @param body contentente i filtri da applicare
	 * @return oggetti di tipo StatPolitics
	 * @throws EmptyFeedException Se l'applicazione del filtro restituisce un
	 *                            vettore vuoto
	 */
	@PostMapping("/Stats/Politic")
	public StatPolitics getPoliticStat(@RequestBody String body) throws EmptyFeedException {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		if (filteredfeed.size() == 0)
			throw new EmptyFeedException("The feed has no posts");
		StatPolitics filteredstatpolitic = new StatPolitics(filteredfeed);
		return filteredstatpolitic;
	}

	/**
	 * Risponde alla richiesta GET /Stats/Lenght. Restituendo le statistiche sulla
	 * lunghezza dei post
	 * 
	 * @return oggetto di tipo StatLenght
	 * @throws EmptyFeedException Se il feed non ha post
	 */

	@GetMapping("/Stats/Lenght")
	public StatLenght getLenghtStat() throws EmptyFeedException {
		if (feed.getFeed().size() == 0)
			throw new EmptyFeedException("The feed has no posts");
		return stats.getStatLenght();
	}

	/**
	 * Risponde alla richiesta POST /Stats/Lenght. iltra i post secondo quanto
	 * richiesto nel body della richiesta e ricalcola le statistiche sulla lunghezza
	 * su questi post.
	 * 
	 * @param body contenente i filtri da applicare.
	 * @return oggetto di tipo StatLenght
	 * @throws EmptyFeedException Se l'applicazione del filtro restituisce un
	 *                            vettore vuoto
	 */

	@PostMapping("/Stats/Lenght")
	public StatLenght getLenghtStat(@RequestBody String body) throws EmptyFeedException {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		if (filteredfeed.size() == 0)
			throw new EmptyFeedException("The feed has no posts");
		StatLenght filteredstatlenght = new StatLenght(filteredfeed);
		return filteredstatlenght;
	}

	/**
	 * Risponde alla richiesta GET /Stats/Time restituendo le statistiche sulla data
	 * di creazione dei post
	 * 
	 * @return oggetto di tipo StatTime
	 * @throws EmptyFeedException Se il feed non ha post
	 */

	@GetMapping("/Stats/Time")
	public StatTime getTimeStat() throws EmptyFeedException {
		if (feed.getFeed().size() == 0)
			throw new EmptyFeedException("The feed has no posts");
		return stats.getStatTime();
	}

	/**
	 * Risponde alla richiesta POST /Stat/Time. iltra i post secondo quanto
	 * richiesto nel body della richiesta e ricalcola le statistiche sulla data di
	 * creazione su questi post.
	 * 
	 * @param body contenente i filtri da applicare
	 * @return oggetto di tipo StatTime
	 * @throws EmptyFeedException Se l'applicazione del filtro restituisce un
	 *                            vettore vuoto
	 */

	@PostMapping("/Stats/Time")
	public StatTime getTimeStat(@RequestBody String body) throws EmptyFeedException {
		FilterHandler filter = new FilterHandler(body);
		Vector<Post> filteredfeed = filter.filterFeed(feed.getFeed());
		if (filteredfeed.size() == 0)
			throw new EmptyFeedException("The feed has no posts");
		StatTime filteredstattime = new StatTime(filteredfeed);
		return filteredstattime;
	}

}