package esameOOP.project.Model;

/**
 * Questa classe contiene il modello per i Metadata
 * 
 * @author Simone Salvoni
 * @author Daniele Staffolani
 *
 */
public class Metadata {
	private String alias;
	private String sourceField;
	private String type;

	/**
	 * Costruttore
	 * 
	 * @param alias       String che contiene l'alias del campo, ossia il suo nome
	 * @param sourceField String che contiene la descrizione del campo
	 * @param type        String che contiene il tipo di dato contenuto nel campo
	 */
	public Metadata(String alias, String sourceField, String type) {
		super();
		this.alias = alias;
		this.sourceField = sourceField;
		this.type = type;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSourceField() {
		return sourceField;
	}

	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
