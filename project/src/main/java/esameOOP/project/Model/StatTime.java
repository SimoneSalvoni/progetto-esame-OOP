package esameOOP.project.Model;

import java.util.LinkedHashMap;
import java.util.Vector;
import esameOOP.project.Util.*;

/**
 * Rappresenta la classe contenente le statistiche temporali dei post
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */
public class StatTime {
	private LinkedHashMap<String, String> postPerHour; // LinkedHashMap poiché
	// in questo modo le fasce orarie sono ordinate. Utilizziamo String sia per
	// rappresentare le fascie orarie
	// sia per indicare i valori percentuali

	/**
	 * Questo costruttore genera un oggetto di tipo StatTime. Trasforma le
	 * statistiche, inizialmente in formato numerico , in String, così da poter
	 * essere inserite in postPerHour in forma percentuale.
	 * 
	 * @param feed Vettore contenente i post su cui calcolare le statistiche
	 * @see Calculate#calcPostPerHour
	 */
	public StatTime(Vector<Post> feed) {
		LinkedHashMap<String, String> h = new LinkedHashMap<String, String>();
		double[] sup = Calculate.calcPostPerHour(feed);
		int k;
		for (int i = 0; i < sup.length; i++) {
			k = i + 1;
			h.put(i + "-" + k, sup[i] + "%");
		}
		this.postPerHour = h;
	}

	public LinkedHashMap<String, String> getPostPerHour() {
		return postPerHour;
	}

	public StatTime(int numPost, LinkedHashMap<String, String> postPerHour) {
		super();
		this.postPerHour = postPerHour;
	}

	public void setPostPerHour(LinkedHashMap<String, String> postPerHour) {
		this.postPerHour = postPerHour;
	}

	@Override
	public String toString() {
		return "StatTime [postPerHour=" + postPerHour + "]";
	}

}
