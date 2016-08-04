/**
 * 
 */
package vo;

/**
 * @author rodrigo
 *
 */
public enum TipoDamEnum {
	DEFINITIVO("dba_juceb.dam_definitivo","dba_juceb.lote_definitivo"),PARCIAL("dba_juceb.dam","dba_juceb.lote");
	
	private final String schemaDam;
	private final String schemaLote;
	
	TipoDamEnum(String schemaDam, String schemaLote){
		this.schemaDam = schemaDam;
		this.schemaLote = schemaLote;
	}

	/**
	 * @return the schemaDam
	 */
	public String getSchemaDam() {
		return schemaDam;
	}

	/**
	 * @return the schemaLote
	 */
	public String getSchemaLote() {
		return schemaLote;
	}
	
	
	


}
