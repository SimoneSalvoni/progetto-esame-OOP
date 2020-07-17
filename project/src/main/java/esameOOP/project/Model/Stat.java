package esameOOP.project.Model;

import java.util.Vector;

/**
 * Rappresenta la classe contenente tutte le statistiche.
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 * @see StatLenght
 * @see StatPolitics
 * @see StatTime
 */
public class Stat {
	private StatLenght StatLenght;
	private StatPolitics StatPolitics;
	private StatTime StatTime;

	/**
	 * Questo costruttore genera un oggetto di tipo Stat, a patto che il feed non
	 * sia vuoto.
	 * 
	 * @param feed Vettore contenente i post su cui calcolare le statistiche
	 */
	public Stat(Vector<Post> feed) {
		if (feed.size() == 0)
			return;
		this.StatLenght = new StatLenght(feed);
		this.StatPolitics = new StatPolitics(feed);
		this.StatTime = new StatTime(feed);
	}

	public StatLenght getStatLenght() {
		return StatLenght;
	}

	public void setStatLenght(StatLenght statLenght) {
		this.StatLenght = statLenght;
	}

	public StatPolitics getStatPolitics() {
		return StatPolitics;
	}

	public void setStatPolitics(StatPolitics statPolitics) {
		this.StatPolitics = statPolitics;
	}

	public StatTime getStatTime() {
		return StatTime;
	}

	public void setStatTime(StatTime statTime) {
		this.StatTime = statTime;
	}

}
