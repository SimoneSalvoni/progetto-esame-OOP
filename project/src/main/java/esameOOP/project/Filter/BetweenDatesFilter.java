package esameOOP.project.Filter;

import java.util.Calendar;

import esameOOP.project.Model.Post;

public class BetweenDatesFilter extends DateFilter {
	private Calendar date1;
	private Calendar date2;
	
	public BetweenDatesFilter(String field, String operator,Calendar date1, Calendar date2) {
		super(field, operator);
		this.date1 = date1;
		this.date2 = date2;
	}

	@Override
	public boolean CheckFilter(Post post) {
		if(post.getCreated_time().compareTo(date1) > 0 &&  post.getCreated_time().compareTo(date2) < 0) return true;
		else return false;
	}
}
