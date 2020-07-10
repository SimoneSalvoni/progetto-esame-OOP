package esameOOP.project.Filters;

import esameOOP.project.Model.Post;

public abstract class DateFilter extends Filter {
	protected static String operator;
	
	public DateFilter(String field, String operator) {
	super(field);
	DateFilter.operator = operator;	
	}	
	public abstract boolean checkFilter(Post post);
}
