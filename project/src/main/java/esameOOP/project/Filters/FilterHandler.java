package esameOOP.project.Filters;

import java.util.Iterator;
import java.util.Vector;

import esameOOP.project.Exceptions.FilterNotFoundException;
import esameOOP.project.Exceptions.InvalidFilterException;
import esameOOP.project.Model.Post;

public class FilterHandler {

	private Filter filter1;
	private Logical logical = null;
	private Filter filter2 = null;

	public FilterHandler(String body) throws FilterNotFoundException, InvalidFilterException {
		if (body == null)
			throw new FilterNotFoundException("Body was empty"); // tDa Postman la richiesta con body nullo
		// è bloccata anche prima che entri nei metodi del controller, questo è qui solo
		// per sicurezza
		if (!body.startsWith("["))
			filter1 = createFilter(body);
		else {
			String[] s = body.split(",");
			filter1 = createFilter(s[0].substring(1));
			filter2 = createFilter(s[2].substring(0, s[2].length() - 1));
			if (s[1].contentEquals("AND"))
				this.logical = Logical.AND;
			else if (s[1].contentEquals("OR"))
				this.logical = Logical.OR;
			else
				throw new InvalidFilterException("Only AND/OR logical operations allowed");
		}

	}

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

	enum Logical {
		AND, OR
	}
}
