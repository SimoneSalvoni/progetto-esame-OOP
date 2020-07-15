package esameOOP.project.Model;

import java.text.DecimalFormat;
import java.util.Vector;

import esameOOP.project.Util.*;

/**
 * Rappresenta la classe contenente le statistiche politiche dei post
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */
public class StatPolitics {
	// sono valori percentuali, posti come stringhe
	private String numPoliticPost;
	private String numNationalPost;
	private String numEUPost;
	private String numExtraEUPost;
	private String numInternationalPost;

	/**
	 * Questo costruttore genera un oggetto di tipo StatPoltics, a patto che il feed
	 * non sia vuoto.
	 * 
	 * @param vector Vettore contenente i post su cui calcolare le statistiche
	 */
	public StatPolitics(Vector<Post> vector) {
		if (vector.size() == 0)
			return;
		DecimalFormat df = new DecimalFormat("#.##");
		double[] cont = new double[6];
		cont = Calculate.countPolitic(vector);
		this.numPoliticPost = df.format(cont[1]) + "%";
		this.numNationalPost = df.format(cont[2]) + "%";
		this.numEUPost = df.format(cont[3]) + "%";
		this.numExtraEUPost = df.format(cont[4]) + "%";
		this.numInternationalPost = df.format(cont[5]) + "%";
	}

	public String getNumPoliticPost() {
		return numPoliticPost;
	}

	public void setNumPoliticPost(String numPoliticPost) {
		this.numPoliticPost = numPoliticPost;
	}

	public String getNumNationalPost() {
		return numNationalPost;
	}

	public void setNumNationalPost(String numNationalPost) {
		this.numNationalPost = numNationalPost;
	}

	public String getNumEUPost() {
		return numEUPost;
	}

	public void setNumEUPost(String numEUPost) {
		this.numEUPost = numEUPost;
	}

	public String getNumExtraEUPost() {
		return numExtraEUPost;
	}

	public void setNumExtraEUPost(String numExtraEUPost) {
		this.numExtraEUPost = numExtraEUPost;
	}

	public String getNumInternationalPost() {
		return numInternationalPost;
	}

	public void setNumInternationalPost(String numInternationalPost) {
		this.numInternationalPost = numInternationalPost;
	}

	@Override
	public String toString() {
		return "StatPolitics [numPoliticPost=" + numPoliticPost + ", numNationalPost=" + numNationalPost
				+ ", numEUPost=" + numEUPost + ", numExtraEUPost=" + numExtraEUPost + ", numInternationalPost="
				+ numInternationalPost + "]";
	}

}
