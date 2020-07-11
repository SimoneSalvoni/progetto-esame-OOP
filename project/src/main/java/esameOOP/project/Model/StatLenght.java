package esameOOP.project.Model;

import java.util.Vector;

import esameOOP.project.Util.*;

public class StatLenght {
	private int minChar;
	private int maxChar;
	private String avgChar;
	private String stdDev;
	
	public StatLenght(Vector<Post> vector) {
		this.minChar = Calculate.calcMin(vector);
		this.maxChar = Calculate.calcMax(vector);
		double avg = Calculate.calcAvg(vector);
		String avgChar = new String();
		avgChar = avg+"%";
		this.avgChar = avgChar;
		double std = Calculate.calcStdDev(vector);
		String StdDev = new String();   // String StdDev = Double.toString(stddev);
		StdDev = std+"%"; 
		this.stdDev = StdDev;
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
	public String getAvgChar() {
		return avgChar;
	}
	public void setAvgChar(String avgChar) {
		this.avgChar = avgChar;
	}
	public String getStdDev() {
		return stdDev;
	}
	public void setStdDev(String stdDev) {
		this.stdDev = stdDev;
	}

	@Override
	public String toString() {
		return "StatLenght [minChar=" + minChar + ", maxChar=" + maxChar
				+ ", avgChar=" + avgChar + ", stdDev=" + stdDev + "]";
	}
}