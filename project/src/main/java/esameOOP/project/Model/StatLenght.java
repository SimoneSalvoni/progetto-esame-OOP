package esameOOP.project.Model;

import java.util.Vector;

import esameOOP.project.Util.*;

/**
 * Rappresenta la classe contenente le statistiche sulla lunghezza dei post
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */

public class StatLenght {
	private int minChar;
	private int maxChar;
	private double avgChar;
	private double stdDev;
	
	public StatLenght(Vector<Post> vector) {
		this.minChar = Calculate.calcMin(vector);
		this.maxChar = Calculate.calcMax(vector);
		this.avgChar = Calculate.calcAvg(vector);
		this.stdDev = Calculate.calcStdDev(vector);
	}
	public int getMinChar() {
		return minChar;
	}
	public void setMinChar(int minChar) {
		this.minChar = minChar;
	}
	public int getMaxChar() {
		return maxChar;
	}
	public void setMaxChar(int maxChar) {
		this.maxChar = maxChar;
	}
	public double getAvgChar() {
		return avgChar;
	}
	public void setAvgChar(double avgChar) {
		this.avgChar = avgChar;
	}
	public double getStdDev() {
		return stdDev;
	}
	public void setStdDev(double stdDev) {
		this.stdDev = stdDev;
	}

	@Override
	public String toString() {
		return "StatLenght [minChar=" + minChar + ", maxChar=" + maxChar
				+ ", avgChar=" + avgChar + ", stdDev=" + stdDev + "]";
	}
}