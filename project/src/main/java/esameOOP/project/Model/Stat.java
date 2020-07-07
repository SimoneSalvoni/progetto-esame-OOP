package esameOOP.project.Model;

import java.util.Vector;

public class Stat {
	private StatLenght StatLenght;
	private StatPolitics StatPolitics;
	private StatTime StatTime;
	
	public Stat(Vector<Post> feed) {
		this.StatLenght = new StatLenght(feed);
		this.StatPolitics = new StatPolitics(feed);
		this.StatTime = new StatTime(feed);
	}
	
	public StatLenght getStatLenght() {
		return StatLenght;
	}
	public void setStatLenght(StatLenght statLenght) {
		StatLenght = statLenght;
	}
	
	public StatPolitics getStatPolitics() {
		return StatPolitics;
	}
	public void setStatPolitics(StatPolitics statPolitics) {
		StatPolitics = statPolitics;
	}

	public StatTime getStatTime() {
		return StatTime;
	}
	public void setStatTime(StatTime statTime) {
		StatTime = statTime;
	}


}

