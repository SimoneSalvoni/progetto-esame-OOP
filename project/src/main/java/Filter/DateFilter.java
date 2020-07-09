package Filter;

import esameOOP.project.Model.Post;

public abstract class DateFilter extends Filter {
	protected static String operator;
	
	public DateFilter(String field, String operator) {
	super(field);
	DateFilter.operator = operator;	
	}	
	public abstract boolean CheckFilter(Post post);
}
