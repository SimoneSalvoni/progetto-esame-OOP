package esameOOP.project.Model;

import java.util.LinkedHashMap;
import java.util.Vector;
import esameOOP.project.Util.*;

/**
 * Rappresenta la classe contenente le statistiche temporali dei post
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */
public class StatTime {
	private int numPost;
	private LinkedHashMap<String, String> postPerHour; //in questo modo le fasce orarie sono ordinate
	
	public StatTime(Vector<Post> feed) {
		LinkedHashMap <String, String> h= new LinkedHashMap <String, String> ();
	    this.numPost = feed.size() ;
		double[] sup = Calculate.calcPostPerHour(feed);
		int k;
		for(int i=0;i<sup.length;i++) {
			k=i+1;
			h.put(i+"-"+k,sup[i]+"%");
		}
		this.postPerHour=h;
	}

	public int getNumPost() {
		return numPost;
	}

	public void setNumPost(int numPost) {
		this.numPost = numPost;
	}

	public LinkedHashMap<String, String> getPostPerHour() {
		return postPerHour;
	}

	public StatTime(int numPost, LinkedHashMap<String, String> postPerHour) {
		super();
		this.numPost = numPost;
		this.postPerHour = postPerHour;
	}

	public void setPostPerHour(LinkedHashMap<String, String> postPerHour) {
		this.postPerHour = postPerHour;
	}

	@Override
	public String toString() {
		return "StatTime [numPost=" + numPost + ", postPerHour=" + postPerHour.toString() + "]";
	}
}
	