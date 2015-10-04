/**
 * 
 */
package de.pvws.swtools.swDataStructure;

/**
 * @author PV WS
 *
 */
public class SWTax {
	private String exTax;
	private String exTaxDescription;
	
	private int swTaxId;
	private String swTax;
	private String swName;

	/**
	 * Generates an empty SWTax-Object.
	 * 
	 */
	public SWTax() {
		this.swTaxId = 1;
		this.swTax = "19.00";
		this.swName = "Standard";
	}

	/**
	 * 
	 * @param bDefault
	 */
	public SWTax(Boolean bDefault) {
		if (bDefault) {
			this.swTaxId = 1;
			this.swTax = "19.00";
			this.swName = "Standard";
		}
		else {
			this.swTaxId = 4;
			this.swTax = "7.00";
			this.swName = "Reduziert";
		}
	}

	public SWTax(String strDefault) {
		if (strDefault.equals("default")) {
			this.swTaxId = 1;
			this.swTax = "19.00";
			this.swName = "Standard";
		}
		else {
			this.swTaxId = 4;
			this.swTax = "7.00";
			this.swName = "Reduziert";
		}
	}

	
	/**
	 * Generates a SWTax-Object just with external data.
	 * 
	 * @param exTax
	 * @param exTaxDescription
	 */
	public SWTax(String exTax, String exTaxDescription) {
		this.exTax = exTax;
		this.exTaxDescription = exTaxDescription;
	}

	/**
	 * Generates a SWTax-Object with all data.
	 * 
	 * @param exTax
	 * @param exTaxDescription
	 * @param swTaxId
	 * @param swTax
	 * @param swName
	 */
	public SWTax(String exTax, String exTaxDescription, int swTaxId, String swTax, String swName) {
		super();
		this.exTax = exTax;
		this.exTaxDescription = exTaxDescription;
		this.swTaxId = swTaxId;
		this.swTax = swTax;
		this.swName = swName;
	}

	/**
	 * @return exTax
	 */
	public String getExTax() {
		return exTax;
	}

	/**
	 * @param exTax das zu setzende Objekt exTax
	 */
	public void setExTax(String exTax) {
		this.exTax = exTax;
	}

	/**
	 * @return exTaxDescription
	 */
	public String getExTaxDescription() {
		return exTaxDescription;
	}

	/**
	 * @param exTaxDescription das zu setzende Objekt exTaxDescription
	 */
	public void setExTaxDescription(String exTaxDescription) {
		this.exTaxDescription = exTaxDescription;
	}

	/**
	 * @return swTaxId
	 */
	public int getSwTaxId() {
		return swTaxId;
	}

	/**
	 * @param swTaxId das zu setzende Objekt swTaxId
	 */
	public void setSwTaxId(int swTaxId) {
		this.swTaxId = swTaxId;
	}

	/**
	 * @return swTax
	 */
	public String getSwTax() {
		return swTax;
	}

	/**
	 * @param swTax das zu setzende Objekt swTax
	 */
	public void setSwTax(String swTax) {
		this.swTax = swTax;
	}

	/**
	 * @return swName
	 */
	public String getSwName() {
		return swName;
	}

	/**
	 * @param swName das zu setzende Objekt swName
	 */
	public void setSwName(String swName) {
		this.swName = swName;
	}
	

}
