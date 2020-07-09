package esameOOP.project.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import com.fasterxml.jackson.databind.ObjectMapper;

import esameOOP.project.Exceptions.FailedConnectionException;
import esameOOP.project.Exceptions.InternalServerException;
import esameOOP.project.Exceptions.TokenNotFoundException;
import esameOOP.project.Util.Operations;

public class Feed {
	private Vector<Post> feed;
	private Metadata[] metadata;

	public Feed() throws InternalServerException, FailedConnectionException {
		metadata = new Metadata[7];
		feed = new Vector<Post>();
		metadata[0] = new Metadata("id", "Post id", "String");
		metadata[1] = new Metadata("message", "Content of the post", "String");
		metadata[2] = new Metadata("createdTime", "Time of the post", "Calendar"); // ci va il tipo di Java o come
																					// appare in JSON?
		metadata[3] = new Metadata("numChar", "Number of characters in the post", "Integer");
		metadata[4] = new Metadata("link", "Attached link", "String");
		metadata[5] = new Metadata("type", "Type of the post: status, link, photo or video", "String");
		metadata[6] = new Metadata("politic", "Politic categorization of the post", "POLITIC");// stesso dubbio di prima
		populateFeed();
		this.feed.remove(this.feed.lastElement());
		for (Post p : this.feed) {
			p.setNumChar(p.getMessage().length());
			p.politicControl();
		}
	}

	/**
	 * public Feed(Calendar date) throws InternalServerException,
	 * FailedConnectionException { metadata[0] = new Metadata("id", "Post id",
	 * "String"); metadata[1] = new Metadata("message", "Content of the post",
	 * "String"); metadata[2] = new Metadata("createdTime", "Time of the post",
	 * "Calendar"); // ci va il tipo di Java o come // appare in JSON? metadata[3] =
	 * new Metadata("numChar", "Number of characters in the post", "Integer");
	 * metadata[4] = new Metadata("link", "Attached link", "String"); metadata[5] =
	 * new Metadata("description", "Description of the link (if not present, the
	 * link represents an image)", "String"); metadata[6] = new Metadata("politic",
	 * "Politic categorization of the post", "POLITIC");// stesso dubbio di prima
	 * populateFeed(); for (Post p : this.feed) {
	 * p.setNumChar(p.getMessage().length()); p.politicControl(); }
	 * feedFromDate(date); }
	 */

	public Vector<Post> getFeed() {
		return feed;
	}

	public Metadata[] getMetadata() {
		return metadata;
	}

	public Post getSinglePost(String id) {
		for (Post p : feed)
			if (p.getId() == id)
				return p;
		return null;
	}

	private void populateFeed() throws FailedConnectionException, TokenNotFoundException {
		String request = "https://graph.facebook.com/106556701114666/feed?access_token=";
		request += getAccessToken();
		request += "&fields=id,message,link,type,created_time&=";
		this.feed = (Vector<Post>) requestAndParseJSON(request);
	}

	private String getAccessToken() throws TokenNotFoundException {
		String token = null;
		token = Operations.readFromFile("token.txt");
		return token;
	}

	/**
	 * public String getJSONArray() { String s = "{\"feed\":["; for (Post p :
	 * this.feed) { s += p.getJSON() + ','; } s += "]}"; return s; }
	 */
	private void feedFromDate(Calendar date) {
		for (int i = 0; i < feed.size(); i++)
			if ((feed.elementAt(i).getCreated_time().compareTo(date) < 0))
				feed.remove(i);
	}

	private List<Post> requestAndParseJSON(String request) throws FailedConnectionException {
		char s;
		String data = "[";
		List<Post> f = new Vector<Post>();
		URL url;
		try {
			url = new URL(request);
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			InputStream is = c.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			ObjectMapper map = new ObjectMapper();
			while ((s = (char) br.read()) != '[') {
			}
			while ((s = (char) br.read()) != ']') {
				data += s;
			}
			data += "]";
			br.close();
			f.addAll(Arrays.asList(map.readValue(data, Post[].class)));
		} catch (IOException e) {
			throw new FailedConnectionException("An error has occured while trying to connect to Facebook");
		}
		return f;
	}

	@Override
	public String toString() {
		String s = "";
		for (Post p : this.feed)
			s += p.toString() + '\n';
		return s;
	}

}
