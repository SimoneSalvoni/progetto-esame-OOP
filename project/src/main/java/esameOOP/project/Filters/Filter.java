package esameOOP.project.Filters;

import esameOOP.project.Model.Post;

/**
 * Rappresenta la superclasse contenente l'oggetto field.
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */

public abstract class Filter {
	protected String field;

	public Filter(String field) {
		this.field=field;
	}
	
	/**
	 * Verifica se il post può essere filtrato o meno a seconda del filtro inserito.
	 * @param post contente il post da filtrare
	 * @return booleano true se il post soddisfa il filtro,
	 * altrimenti false
	 */
	
	public abstract boolean checkFilter(Post post);
}
