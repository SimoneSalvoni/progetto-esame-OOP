package esameOOP.project.Model;

import java.util.Vector;

import esameOOP.project.Util.*;

public class StatPolitics {
	private double numPoliticPost;
	private double numNationalPost;
	private double numEUPost;
	private double numExtraEUPost;
	private double numInternationalPost;

	public StatPolitics(Vector<Post> feed) {
		double[] cont = new double[6];
		cont = Calculate.countPolitic(feed);
		this.numPoliticPost = cont[1];
		this.numNationalPost = cont[2];
		this.numEUPost = cont[3];
		this.numExtraEUPost = cont[4];
		this.numInternationalPost = cont[5];
	}

	public double getNumPoliticPost() {
		return numPoliticPost;
	}

	public void setNumPoliticPost(double numPoliticPost) {
		this.numPoliticPost = numPoliticPost;
	}

	public double getNumNationalPost() {
		return numNationalPost;
	}

	public void setNumNationalPost(double numNationalPost) {
		this.numNationalPost = numNationalPost;
	}

	public double getNumEUPost() {
		return numEUPost;
	}

	public void setNumEUPost(double numEUPost) {
		this.numEUPost = numEUPost;
	}

	public double getNumExtraEUPost() {
		return numExtraEUPost;
	}

	public void setNumExtraEUPost(double numExtraEUPost) {
		this.numExtraEUPost = numExtraEUPost;
	}

	public double getNumInternationalPost() {
		return numInternationalPost;
	}

	public void setNumInternationalPost(double numInternationalPost) {
		this.numInternationalPost = numInternationalPost;
	}

	@Override
	public String toString() {
		return "StatPolitics [numPoliticPost=" + numPoliticPost + ", numNationalPost=" + numNationalPost
				+ ", numEUPost=" + numEUPost + ", numExtraEUPost=" + numExtraEUPost + ", numInternationalPost="
				+ numInternationalPost + "]";
	}

}
