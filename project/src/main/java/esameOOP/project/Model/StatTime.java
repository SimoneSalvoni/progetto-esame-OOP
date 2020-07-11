package esameOOP.project.Model;

import java.util.Arrays;
import java.util.Vector;
import esameOOP.project.Util.*;

public class StatTime {
	private int numPost;
	private HashMap<String, String> postPerHour;
	
	public StatTime(Vector<Post> feed) {
		this.numPost = feed.size() ;
		double[] sup = Calculate.calcPostPerHour(feed);
		int k=1;
		for(int i=0;i<24;i++) this.postPerHour.put(i+"-"+k,sup[i]+"%");
		
	}

	public int getNumPost() {
		return numPost;
	}

	public void setNumPost(int numPost) {
		this.numPost = numPost;
	}

	public HashMap<String, String> getPostPerHour() {
		return postPerHour;
	}

	public StatTime(int numPost, HashMap<String, String> postPerHour) {
		super();
		this.numPost = numPost;
		this.postPerHour = postPerHour;
	}

	public void setPostPerHour(HashMap<String, String> postPerHour) {
		this.postPerHour = postPerHour;
	}

	@Override
	public String toString() {
		return "StatTime [numPost=" + numPost + ", postPerHour=" + Arrays.toString(postPerHour) + "]";
	}
}
	