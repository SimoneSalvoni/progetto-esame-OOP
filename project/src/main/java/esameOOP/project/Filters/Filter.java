package esameOOP.project.Filters;

import esameOOP.project.Model.Post;

/**
 * Rappresenta la superclasse contenente l'attributo field.
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */

public abstract class Filter {
	protected String field;

	public Filter(String field) {
		this.field = field;
	}

	/**
	 * Verifica se il post può essere filtrato o meno a seconda del filtro inserito.
	 * 
	 * @param post Post contente il post da filtrare
	 * @return boolean true se il post soddisfa il filtro, altrimenti false
	 */

	public abstract boolean checkFilter(Post post);
}
