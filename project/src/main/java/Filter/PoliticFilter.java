package Filter;

import esameOOP.project.Model.Post;
import esameOOP.project.Model.Post.Politic;

public class PoliticFilter extends Filter {
	private Politic category;

	
	public PoliticFilter(String field, Politic category) {
		super(field);
		this.category = category;
	}


	@Override
	public boolean CheckFilter(Post post) {
		if(post.getPolitic()==category) return true;
		else return false;
	}


}
