package esameOOP.project.Filter;
import esameOOP.project.Model.Post;

public abstract class Filter {

		protected String field;
		
		public Filter(String field) {
			this.field = field;
		}
		public abstract boolean CheckFilter(Post post);
	}

