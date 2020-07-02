package esameOOP.project.Model;

import java.util.Calendar;
import java.util.Locale;

public class Post {
	private String id;
	private String message;
	private Calendar createdTime;
	private int numChar;
	private String link;
	private String description;
	private Politic politic;
	private static String[] keyWords1 = { "politic", "govern", "president", "minist", "sindac" };
	private static String[] keyWords2 = { "Italia", "comune", "regione", "sindaco" };
	private static String[] keyWords3 = { "Europ", "Germania", "tedesc", "Franci", "Bruxelles", "Spagna" };
	private static String[] keyWords4 = { "USA", "America", "Cina", "Russia", "Asia", "Australia" };

	public Post(String id, String message, String time, String link, String description) {
		super();
		this.id = id;
		this.message = message;
		this.link = link;
		this.description = description;
		this.numChar = message.length();
		politicControl();
		int y = Integer.parseInt(time.substring(0, 3));
		int mo = Integer.parseInt(time.substring(5, 6));
		int day = Integer.parseInt(time.substring(8, 9));
		int h = Integer.parseInt(time.substring(11, 12));
		int mi = Integer.parseInt(time.substring(14, 15));
		int s = Integer.parseInt(time.substring(17, 18));
		this.createdTime.set(y, mo, day, h, mi, s);
	}

	public String getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Calendar getCreatedTime() {
		return createdTime;
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

	public void setCreatedTime(Calendar createdTime) {
		this.createdTime = createdTime;
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
		NON_POLTIC, POLITIC, NATIONAL, EU, EXTRA_EU, INTERNATIONAL
	}

	private void politicControl() {
		this.politic = null;
		for (String s : keyWords1) {
			if (this.message.contains(s.toLowerCase(Locale.ITALIAN)) || this.message.contains(s)) {
				this.politic = Politic.POLITIC;
				break;
			}
		}
		if (this.politic == null) {
			this.politic=Politic.NON_POLTIC;
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

	@Override
	public String toString() {
		return "id post: "+ id +"\nmessage: " + message + "\nposted the: " + createdTime.toString() + 
				"\nattached link: " + link + "\nnumber of characters: " + numChar + "\npolitic categorization: "
				+ politic;
	}

}