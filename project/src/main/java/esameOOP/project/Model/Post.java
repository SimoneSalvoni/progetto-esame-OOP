package esameOOP.project.Model;

import java.util.Calendar;
import esameOOP.project.Util.Operations;

public class Post {
	private String id;
	private String message;
	private Calendar created_time;
	private int numChar;
	private String link;
	private String description;
	private Politic politic;
	private static String[] keyWords1 = { "politic", "govern", "president", "minist", "sindac" };
	private static String[] keyWords2 = { "Italia", "comune", "regione", "sindaco" };
	private static String[] keyWords3 = { "Europ", "Germania", "tedesc", "Franci", "Bruxelles", "Spagna" };
	private static String[] keyWords4 = { "USA", "America", "Cina", "Russia", "Asia", "Australia" };

	public Post() {
		super();
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

	public String getDescription() {
		return description;
	}

	public Politic getPolitic() {
		return politic;
	}

	public static String[] getKeyWords() {
		String[] keyWords = new String[22];
		int i;
		for (i = 0; i < 5; i++)
			keyWords[i] = keyWords1[i];
		for (i = 5; i < 9; i++)
			keyWords[i] = keyWords2[i - 5];
		for (i = 9; i < 16; i++)
			keyWords[i] = keyWords3[i - 12];
		for (i = 16; i < 22; i++)
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

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPolitic(Politic politic) {
		this.politic = politic;
	}

	public enum Politic {
		NON_POLITIC, POLITIC, NATIONAL, EU, EXTRA_EU, INTERNATIONAL
	}
/**
	private void politicControl() {
		this.politic = null;
		for (String s : keyWords1) {
			if (this.message.contains(s.toLowerCase(Locale.ITALIAN)) || this.message.contains(s)) {
				this.politic = Politic.POLITIC;
				break;
			}
		}
		if (this.politic == null) {
			this.politic=Politic.NON_POLITIC;
			return;
		}

		for (String s : keyWords2) {
			if (this.message.contains(s.toLowerCase(Locale.ITALIAN)) || this.message.contains(s)) {
				this.politic = Politic.NATIONAL;
				break;
			}
		}
		for (String s : keyWords3) {
			if (this.message.contains(s.toLowerCase(Locale.ITALIAN)) || this.message.contains(s)) {
				this.politic = Politic.EU;
				break;
			}
		}
		for (String s : keyWords4) {
			if (this.message.contains(s.toLowerCase(Locale.ITALIAN)) || this.message.contains(s)) {
				if (this.politic == Politic.POLITIC)
					this.politic = Politic.EXTRA_EU;
				else
					this.politic = Politic.INTERNATIONAL;
				break;
			}
		}
	}
**/
	public void politicControl() {
		this.politic=null;
		if(Operations.checkKeywords(this.message,keyWords1)) this.politic=Politic.POLITIC;
		else {
			this.politic=Politic.NON_POLITIC;
			return;
		}
		if(Operations.checkKeywords(this.message,keyWords2)) this.politic=Politic.NATIONAL;
		if(Operations.checkKeywords(this.message,keyWords3)) this.politic=Politic.EU;
		if(Operations.checkKeywords(this.message,keyWords4)) {
			if (this.politic==Politic.POLITIC) this.politic=Politic.EXTRA_EU;
			else this.politic=Politic.INTERNATIONAL;
		}
	}
	public String getJSON() {
		String json="{\"id\":\""+id+"\",\"message\":\"" + message + "\",\"created_time\":\"" + created_time.getTime().toString()+
				"\",\"description\":\"" + description + "\",\"number of characters\":\"" + numChar + 
				"\",\"politic categorization\":\""+politic+"\"}";
		return json;
	}
	@Override
	public String toString() {
		String time=created_time.getTime().toString();
		return "id post: "+ id +"\nmessage\": " + message + "\"\nposted the " + time + 
				"\nattached link: " + link + "\npreview of the link: \""+ description + "\"\nnumber of characters: " + numChar + "\npolitic categorization: "
				+ politic+'\n';
	}

	public static void getnumChar(int i) {
		// TODO Auto-generated method stub
		
	}

}