package esameOOP.project.Filters;

import java.util.Calendar;

import esameOOP.project.Model.Post;

public class AfterDateFilter extends DateFilter {
	private Calendar date;
	
	public AfterDateFilter(String field, String operator, Calendar date) {
		super(field, operator);
		this.date=date;
	}

	@Override
	public boolean checkFilter(Post post) {
		if (post.getCreated_time().compareTo(date) > 0) return true;  
		else return false;
	}

}
