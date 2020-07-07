package esameOOP.project.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Feed {
	private Vector<Post> feed;
	private Metadata[] metadata;

	public Feed() {
		metadata = new Metadata[7];
		feed = new Vector<Post>();
		metadata[0] = new Metadata("id", "Post id", "String");
		metadata[1] = new Metadata("message", "Content of the post", "String");
		metadata[2] = new Metadata("createdTime", "Time of the post", "Calendar"); // ci va il tipo di Java o come
																					// appare in JSON?
		metadata[3] = new Metadata("numChar", "Number of characters in the post", "Integer");
		metadata[4] = new Metadata("link", "Attached link", "String");
		metadata[5] = new Metadata("description",
				"Description of the link (if not present, the link represents an image)", "String");
		metadata[6] = new Metadata("politic", "Politic categorization of the post", "POLITIC");// stesso dubbio di prima
		populateFeed();
		this.feed.remove(this.feed.lastElement());
		for (Post p : this.feed) {
			p.setNumChar(p.getMessage().length());
			p.politicControl();
		}
	}

	public Feed(Calendar date) {
		metadata[0] = new Metadata("id", "Post id", "String");
		metadata[1] = new Metadata("message", "Content of the post", "String");
		metadata[2] = new Metadata("createdTime", "Time of the post", "Calendar"); // ci va il tipo di Java o come
																					// appare in JSON?
		metadata[3] = new Metadata("numChar", "Number of characters in the post", "Integer");
		metadata[4] = new Metadata("link", "Attached link", "String");
		metadata[5] = new Metadata("description",
				"Description of the link (if not present, the link represents an image)", "String");
		metadata[6] = new Metadata("politic", "Politic categorization of the post", "POLITIC");// stesso dubbio di prima
		populateFeed(date);
		for (Post p : this.feed) {
			p.setNumChar(p.getMessage().length());
			p.politicControl();
		}
	}

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

	private void populateFeed() {
		char s;
		String data = "[";
		String request = "https://graph.facebook.com/106556701114666/feed?access_token=";
		request += getAccessToken();
		request += "&fields=id,message,link,description,created_time&=";
		try {
			URL url = new URL(request);
			HttpURLConnection c = (HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			InputStream is = c.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			ObjectMapper map = new ObjectMapper();
			// SPOSTA IL PARSING IN UN METODO A SE' NEL PACKAGE UTIL (MAGARI CLASSE A SE'?
			// DIVIDIAMO?). ORA STA QUA PER
			// FARE TESTING E FAR FUNZIONARE IL TUTTO
			try {
				while ((s = (char) br.read()) != '[') {
				}
				while ((s = (char) br.read()) != ']') {
					data += s;
				}
				data += "]";
				List<Post> f = new Vector<Post>();
				f.addAll(Arrays.asList(map.readValue(data, Post[].class)));
				this.feed = (Vector<Post>) f;
			} finally {
				br.close();
			}
		} catch (MalformedURLException e) {
			// eccezione di connessione a FB?
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void populateFeed(Calendar date) {
		// TODO con i filtri
	}

	private String getAccessToken() {
		String token = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("token.txt"));
			token = reader.readLine();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// eccezione access token mancante
			e.printStackTrace();
		}
		return token;
	}

	public String getJSONArray() {
		String s = "{\"feed\":[";
		for (Post p : this.feed) {
			s += p.getJSON() + ',';
		}
		s += "]}";
		return s;
	}

	@Override
	public String toString() {
		String s = "";
		for (Post p : this.feed)
			s += p.toString() + '\n';
		return s;
	}

	private static void feedFromDate(Vector<Post> feed, Calendar date) {
		for (int i=0;i<feed.size(); i++) if((feed.elementAt(i).getCreated_time().compareTo(date) <0)) feed.remove(i);
	}
	
}
