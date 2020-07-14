package esameOOP.project.Filters;

import esameOOP.project.Model.Post;

/**
 * La classe controlla se il tipo di post passato corrisponde al filtro
 * richiesto. Estende la classe Filter
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */
public class MediaFilter extends Filter {

	private String type;

	public MediaFilter(String field, String type) {
		super(field);
		this.type = type;
	}

	/**
	 * Controlla se il tipo politico di post soddisfa il filtro.
	 * 
	 * @param post contenente il tipo da esaminare
	 * @return booleano <i>true</i> se il tipo esaminato soddisfa il filtro,
	 *         altrimenti <i>false</i>
	 */

	@Override
	public boolean checkFilter(Post post) {
		if (post.getType().equals(type))
			return true;
		return false;
	}
}
