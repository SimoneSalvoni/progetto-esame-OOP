package esameOOP.project.Model;

import java.util.Calendar;
import esameOOP.project.Util.Operations;

public class Post {
	private String id;
	private String message;
	private Calendar created_time;
	private int numChar;
	private String link;
	private String type;
	private Politic politic;
	private static String[] keyWords1 = { "Politic", "Govern", "President", "Minist" };
	private static String[] keyWords2 = { "Italia", "Comune", "Regione", "Sindaco" };
	private static String[] keyWords3 = { "Europ", "Germania", "tedesc", "Franci", "Bruxelles", "Spagna" };
	private static String[] keyWords4 = { " USA", "America", "Cina", "Russia", "Asia", "Australia" };
	//Lo spazio prima di USA è importante, altrimenti prenderebbe qualunque parola con all'interno usa, 
	//anche in minuscolo

	public Post() {
		super();
	}
	
//questo costruttore serve solo per testing
	public Post(String id, String message, Calendar created_time, String link, String type) {
		super();
		this.id = id;
		this.message = message;
		this.created_time = created_time;
		this.link = link;
		this.type = type;
		this.numChar=this.message.length();
		politicControl();
	}

	public String getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Calendar getCreated_time() {
		return created_time;
	}

	public int getNumChar() {
		return numChar;
	}

	public String getLink() {
		return link;
	}

	public String getType() {
		return type;
	}

	public Politic getPolitic() {
		return politic;
	}

	public static String[] getKeyWords() {
		String[] keyWords = new String[21];
		int i;
		for (i = 0; i < 4; i++)
			keyWords[i] = keyWords1[i];
		for (i = 4; i < 8; i++)
			keyWords[i] = keyWords2[i - 5];
		for (i = 8; i < 15; i++)
			keyWords[i] = keyWords3[i - 12];
		for (i = 15; i < 21; i++)
			keyWords[i] = keyWords4[i - 16];
		return keyWords;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCreated_time(Calendar createdTime) {
		this.created_time = createdTime;
	}

	public void setNumChar(int numChar) {
		this.numChar = numChar;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setType(String description) {
		this.type = description;
	}

	public void setPolitic(Politic politic) {
		this.politic = politic;
	}

	public enum Politic {
		NON_POLITIC, POLITIC, NATIONAL, EU, EXTRA_EU, INTERNATIONAL
	}

	public void politicControl() {
		this.politic = null;
		if (Operations.checkKeywords(this.message, keyWords1))
			this.politic = Politic.POLITIC;
		else {
			this.politic = Politic.NON_POLITIC;
		}
		if (Operations.checkKeywords(this.message, keyWords2))
			this.politic = Politic.NATIONAL;
		if (Operations.checkKeywords(this.message, keyWords3))
			this.politic = Politic.EU;
		if (Operations.checkKeywords(this.message, keyWords4)) {
			if (this.politic == Politic.EU || this.politic==Politic.POLITIC ||this.politic==Politic.NATIONAL)
				this.politic = Politic.INTERNATIONAL;
			else
				this.politic = Politic.EXTRA_EU;
		}
	}

	/**
	 * public String getJSON() { String json="{\"id\":\""+id+"\",\"message\":\"" +
	 * message + "\",\"created_time\":\"" + created_time.getTime().toString()+
	 * "\",\"description\":\"" + description + "\",\"number of characters\":\"" +
	 * numChar + "\",\"politic categorization\":\""+politic+"\"}"; return json; }
	 */
	@Override
	public String toString() {
		String time = created_time.getTime().toString();
		return "id post: " + id + "\nmessage\": " + message + "\"\nposted the " + time + "\nattached link: " + link
				+ "\ntype of the post: \"" + type + "\"\nnumber of characters: " + numChar
				+ "\npolitic categorization: " + politic + '\n';
	}

}