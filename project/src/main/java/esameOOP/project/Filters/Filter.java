package esameOOP.project.Filters;

import esameOOP.project.Model.Post;

public abstract class Filter {
	protected String field;

	public Filter(String field) {
		this.field=field;
	}
	public abstract boolean checkFilter(Post post);
}
