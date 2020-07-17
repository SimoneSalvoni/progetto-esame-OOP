package esameOOP.project.Filters;

import esameOOP.project.Model.Post;

/**
 * Rappresenta la classe astratta che contiene l'oggetto operator estende la
 * classe Filter.
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */

public abstract class DateFilter extends Filter {
	protected static String operator;

	public DateFilter(String field, String operator) {
		super(field);
		DateFilter.operator = operator;
	}

	
	public abstract boolean checkFilter(Post post);
}
