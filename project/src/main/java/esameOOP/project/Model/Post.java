package esameOOP.project.Model;

import java.util.Calendar;
import esameOOP.project.Util.Operations;

/**
 * Questa classe contiene il modello di un post
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */
public class Post {
	private String id;
	private String message;
	private Calendar created_time;
	private int numChar;
	private String link;
	private String type;
	private Politic politic;
	// Le parole chiave sono divise per gestire meglio l'analisi. Alcune parolo sono troncate per poter 
	// riconoscere anche parole che contengono la parola chiava (per esempio  "govern" vale per "governo"
	// o "governatore"
	private static String[] keyWords1 = { "Politic", "Govern", "President", "Minist" };
	private static String[] keyWords2 = { "Italia", "Comune", "Regione", "Sindaco" };
	private static String[] keyWords3 = { "Europ", "Germania", "tedesc", "Franci", "Bruxelles", "Spagna" };
	private static String[] keyWords4 = { " USA", "America", "Cina", "Russia", "Asia", "Australia" };
	// Lo spazio prima di USA è importante, altrimenti prenderebbe qualunque parola
	// con all'interno usa, anche in minuscolo

	/**
	 * Costruttore
	 */
	public Post() {
		super();
	}

//questo costruttore serve solo per testing
	/**
	 * Costruttore
	 * @param id String che contiene l'id del post
	 * @param message String che contiene il contenuto del post
	 * @param created_time Oggetto della classe Calendar che contiene la data di creazione del post
	 * @param link String che contiene un eventuale link presente nel post
	 * @param type String che contiene la tipologia di post
	 * @see java.util.Calendar
	 */
	public Post(String id, String message, Calendar created_time, String link, String type) {
		super();
		this.id = id;
		this.message = message;
		this.created_time = created_time;
		this.link = link;
		this.type = type;
		this.numChar = this.message.length();
		politicControl();
	}

	/**
	 * Metodo getter
	 * @return String con l'id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo getter
	 * @return String con il contenuto del post
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Metodo getter
	 * @return Calendar con la data di creazione
	 */
	public Calendar getCreated_time() {
		return created_time;
	}

	/**
	 * Metodo getter
	 * @return int con il numero di caratteri
	 */
	public int getNumChar() {
		return numChar;
	}

	/**
	 * Metodo getter
	 * @return String con il link contenuto nel post. <i>null</i> se non è presente nessun link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Metodo getter
	 * @return String con il tipo del post
	 */
	public String getType() {
		return type;
	}

	/**
	 * Metodo getter
	 * @return Oggetto del tipo enumerativo Politic che rappresenta la sua categorizzazione politica
	 */
	public Politic getPolitic() {
		return politic;
	}

	/**
	 * Metodo getter
	 * @return Array di String con tutte le parole chiave
	 */
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

	/**
	 * Metodo setter
	 * @param id String con l'id del post
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Metodo setter
	 * @param message String con il contenuto del post
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Metodo setter
	 * @param createdTime Calendar che rappresenta la data di creazione del post
	 */
	public void setCreated_time(Calendar createdTime) {
		this.created_time = createdTime;
	}

	/**
	 * Metodo setter
	 * @param numChar int che contiene il numero di caratteri
	 */
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

	/**
	 * Tipologie di categorizzazione politica ammesse
	 */
	public enum Politic {
		NON_POLITIC, POLITIC, NATIONAL, EU, EXTRA_EU, INTERNATIONAL
	}

	/**
	 * Questo metodo ha il compito di categorizzare politicamente il contenuto del post controllando la 
	 * presenza delle parole chiave. Sfrutta il metodo checkKeywords della classe Operations
	 * @see Operations.checkKeywords
	 */
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
			if (this.politic == Politic.EU || this.politic == Politic.POLITIC || this.politic == Politic.NATIONAL)
				this.politic = Politic.INTERNATIONAL;
			else
				this.politic = Politic.EXTRA_EU;
		}
	}

	@Override
	public String toString() {
		String time = created_time.getTime().toString();
		return "id post: " + id + "\nmessage\": " + message + "\"\nposted the " + time + "\nattached link: " + link
				+ "\ntype of the post: \"" + type + "\"\nnumber of characters: " + numChar
				+ "\npolitic categorization: " + politic + '\n';
	}

}