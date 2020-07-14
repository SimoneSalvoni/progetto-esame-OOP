package esameOOP.project.Filters;

import esameOOP.project.Model.Post;
import esameOOP.project.Model.Post.Politic;

/**
 * La classe controlla se il tipo politico di post passato corrisponde al filtro
 * richiesto. Estende la classe Filter
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 */

public class PoliticFilter extends Filter {
	private Politic category;

	public PoliticFilter(String field, Politic category) {
		super(field);
		this.category = category;
	}

	/**
	 * Controlla se il tipo politico di post soddisfa il filtro.
	 * 
	 * @param post contenente il tipo politico da esaminare
	 * @return booleano <i>true</i> se il tipo politico esaminato soddisfa il
	 *         filtro, altrimenti <i>false</i>
	 */

	@Override
	public boolean checkFilter(Post post) {
		if (post.getPolitic() == category)
			return true;
		else
			return false;
	}

}
