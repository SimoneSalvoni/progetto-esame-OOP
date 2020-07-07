package esameOOP.project.Model;

import java.util.Arrays;
import java.util.Vector;
import esameOOP.project.Util.*;

public class StatTime {
	private int numPost;
	private double[] postPerHour;
	
	public StatTime(Vector<Post> feed) {
		this.numPost = feed.size() ;
		this.postPerHour = Calculate.calcPostPerHour(feed);
	}

	public int getNumPost() {
		return numPost;
	}

	public void setNumPost(int numPost) {
		this.numPost = numPost;
	}

	public double[] getPostPerHour() {
		return postPerHour;
	}

	public void setPostPerHour(double[] postPerHour) {
		this.postPerHour = postPerHour;
	}

	@Override
	public String toString() {
		return "StatTime [numPost=" + numPost + ", postPerHour=" + Arrays.toString(postPerHour) + "]";
	}
}
	