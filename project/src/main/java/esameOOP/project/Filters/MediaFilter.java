package esameOOP.project.Filters;

import esameOOP.project.Model.Post;

public class MediaFilter extends Filter {

	private String type;
	
	public MediaFilter(String field, String type) {
		super(field);
		this.type = type;
	}

	@Override
	public boolean checkFilter(Post post) {
		if (post.getType().equals(type)) return true;
		return false;
	}
	}
