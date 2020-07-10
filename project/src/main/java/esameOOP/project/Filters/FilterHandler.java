package esameOOP.project.Filters;

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
			throw new FilterNotFoundException("No filter was found");
		if (!body.startsWith("["))
			createFilter(body);
		else {
			String[] s = body.split(",");
			createFilter(s[0].substring(1));
			createFilter(s[2].substring(0, s[2].length() - 1));
			if (s[1].contentEquals("AND"))
				this.logical = Logical.AND;
			else if (s[1].contentEquals("OR"))
				this.logical = Logical.OR;
			else
				throw new InvalidFilterException("Only AND/OR logical operations allowed");
		}

	}

	public Vector<Post> filterFeed(Vector<Post> feed) {
		Vector<Post> filteredFeed = feed;
		if (this.logical == null) {
			for (Post p : filteredFeed)
				if (!filter1.checkFilter(p))
					filteredFeed.remove(p);
		} else if (this.logical == Logical.AND) {
			for (Post p : filteredFeed)
				if (!filter1.checkFilter(p) || !filter2.checkFilter(p))
					filteredFeed.remove(p);
		} else
			for (Post p : filteredFeed)
				if (!filter1.checkFilter(p) && !filter2.checkFilter(p))
					filteredFeed.remove(p);
		return filteredFeed;
	}

	private void createFilter(String singleRequest) throws InvalidFilterException {
		int index = singleRequest.indexOf("\"", 2) - 1;
		int index2;
		String field = singleRequest.substring(2, index);
		switch (field) {
		case "type": {
			index2 = singleRequest.indexOf("}", index) - 1;
			String type = singleRequest.substring(index + 4, index2);
			filter1 = InstanceFilter.createMediaFilter(field, type);
			break;
		}
		case "category": {
			index2 = singleRequest.indexOf("}", index) - 1;
			String category = singleRequest.substring(index + 4, index2);
			filter1 = InstanceFilter.createPoliticFilter(field, category);
			break;
		}
		case "created_time": {
			index2 = singleRequest.indexOf(":", index) - 2;
			String operation = singleRequest.substring(index + 5, index2);
			index = index2 + 4;
			index2 = singleRequest.indexOf("}", index) - 2;
			String date = singleRequest.substring(index, index2);
			filter1 = InstanceFilter.createDateFilter(field, operation, date);
			break;
		}
		default:
			throw new InvalidFilterException("Invalid field");
		}
	}

	enum Logical {
		AND, OR
	}
}
