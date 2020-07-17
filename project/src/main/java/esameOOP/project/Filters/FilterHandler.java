package esameOOP.project.Filters;

import java.util.Iterator;
import java.util.Vector;

import esameOOP.project.Exceptions.FilterNotFoundException;
import esameOOP.project.Exceptions.InvalidFilterException;
import esameOOP.project.Model.Post;

/**
 * Questa è la classe che ha il compito di controllare il body della richiesta,
 * gestire la creazione dei filtri adatti a tale richiesta, e gestire il
 * filtraggio effettivo dei post secondo i filtri creati
 * 
 * @author Simone Salvoni
 * @author Daniele staffolani
 */
public class FilterHandler {

	private Filter filter1;
	private Logical logical = null;
	private Filter filter2 = null;

	/**
	 * Costruttore: si controlla se sono stati immessi uno o due filtri e la logica
	 * da applicare nel caso i filtri siano due
	 * 
	 * @param body String contenente il body della richiesta
	 * @throws FilterNotFoundException se il body è nullo
	 * @throws InvalidFilterException  se il body ha errori di scrittura
	 */
	public FilterHandler(String body) throws FilterNotFoundException, InvalidFilterException {
		if (body == null)
			throw new FilterNotFoundException("Body was empty"); // Da Postman la richiesta con body nullo
		// è bloccata anche prima che si entri nei metodi del controller, questo è qui
		// solo per sicurezza
		String[] s = body.split(",");
		if (s.length == 1)
			filter1 = createFilter(body);
		else if (s.length == 3) {
			filter1 = createFilter(s[0]);
			filter2 = createFilter(s[2]);
			if (s[1].contentEquals("AND"))
				this.logical = Logical.AND;
			else if (s[1].contentEquals("OR"))
				this.logical = Logical.OR;
			else
				throw new InvalidFilterException("Only AND/OR logical operations allowed");
		} else
			throw new InvalidFilterException("Request body was wrongly written");
	}

	/**
	 * Questo metodo filtra il Vettore di Post in ingresso secondo i filtri richiesti
	 * dal Client
	 * 
	 * @param feed L'originale Vettore di Post che contiene tutti i post
	 * @return Un Vector di Post con i post filtrati
	 */
	public Vector<Post> filterFeed(Vector<Post> feed) {
		@SuppressWarnings("unchecked") // Il compilatore non apprezza il casting, ma dovrebbe funzionare
		Vector<Post> filteredFeed = (Vector<Post>) feed.clone();
		Post p;
		Iterator<Post> i = filteredFeed.iterator();
		if (this.logical == null) {
			while (i.hasNext()) {
				p = i.next();
				if (!filter1.checkFilter(p))
					i.remove();
			}
		} else if (this.logical == Logical.AND) {
			while (i.hasNext()) {
				p = i.next();
				if (!filter1.checkFilter(p) || !filter2.checkFilter(p))
					i.remove();
			}
		} else {
			while (i.hasNext()) {
				p = i.next();
				if (!filter1.checkFilter(p) && !filter2.checkFilter(p))
					i.remove();
			}
		}
		return filteredFeed;
	}

	/**
	 * Questo metodo costruise un filtro adatto data una certa richiesta di
	 * filtraggio
	 * 
	 * @param singleRequest String con rappresenta una singola richiesta di
	 *                      filtraggio
	 * @return Un oggetto di una classe che implementa la classe astratta Filter
	 * @throws InvalidFilterException Se la richiesta è scritta male o se il campo
	 *                                nella richiesta non è accettato
	 * @see Filter
	 */
	private Filter createFilter(String singleRequest) throws InvalidFilterException {
		try {
			int index = singleRequest.indexOf("\"", 2);
			int index2;
			String field = singleRequest.substring(2, index);
			Filter filter;
			switch (field) {
			case "type": {
				index2 = singleRequest.indexOf("\"", index + 3);
				String type = singleRequest.substring(index + 3, index2);
				filter = InstanceFilter.createMediaFilter(field, type);
				break;
			}
			case "category": {
				index2 = singleRequest.indexOf("\"", index + 3);
				String category = singleRequest.substring(index + 3, index2);
				filter = InstanceFilter.createPoliticFilter(field, category);
				break;
			}
			case "created_time": {
				index2 = singleRequest.indexOf(":", index + 2) - 1;
				index += 4;
				String operation = singleRequest.substring(index, index2);
				index = index2 + 3;
				index2 = singleRequest.indexOf("}", index) - 1;
				String date = singleRequest.substring(index, index2);
				filter = InstanceFilter.createDateFilter(field, operation, date);
				break;
			}
			default:
				throw new InvalidFilterException("Invalid field");
			}
			return filter;
		} catch (StringIndexOutOfBoundsException e) {
			throw new InvalidFilterException("Request body was wrongly written");
		}
	}

	/**
	 * Questa enumerazione rappresenta la tipologia di logica da applicare nel caso
	 * i filtri siano due
	 *
	 */
	enum Logical {
		AND, OR
	}
}
