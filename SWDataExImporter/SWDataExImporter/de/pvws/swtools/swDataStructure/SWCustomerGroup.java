/**
 * 
 */
package de.pvws.swtools.swDataStructure;

/**
 * @author PV KT
 *
 */
public class SWCustomerGroup {
	private int swId;		// PK
	private String key;
	private String name;
	private Boolean tax;
	private Boolean taxInput;
	private Boolean mode;
	private Double discount;
	private Double minimumOrder;
	private Double minimumOrderPurchase;
	private Double basePrice;
	private Double percent;
	
	/**
	 * 
	 */
	public SWCustomerGroup () {
		
	}

	/**
	 * @return swId
	 */
	public int getSwId() {
		return swId;
	}

	/**
	 * @param swId das zu setzende Objekt swId
	 */
	public void setSwId(int swId) {
		this.swId = swId;
	}

	/**
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key das zu setzende Objekt key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name das zu setzende Objekt name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return tax
	 */
	public Boolean getTax() {
		return tax;
	}

	/**
	 * @param tax das zu setzende Objekt tax
	 */
	public void setTax(Boolean tax) {
		this.tax = tax;
	}

	/**
	 * @return taxInput
	 */
	public Boolean getTaxInput() {
		return taxInput;
	}

	/**
	 * @param taxInput das zu setzende Objekt taxInput
	 */
	public void setTaxInput(Boolean taxInput) {
		this.taxInput = taxInput;
	}

	/**
	 * @return mode
	 */
	public Boolean getMode() {
		return mode;
	}

	/**
	 * @param mode das zu setzende Objekt mode
	 */
	public void setMode(Boolean mode) {
		this.mode = mode;
	}

	/**
	 * @return discount
	 */
	public Double getDiscount() {
		return discount;
	}

	/**
	 * @param discount das zu setzende Objekt discount
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**
	 * @return minimumOrder
	 */
	public Double getMinimumOrder() {
		return minimumOrder;
	}

	/**
	 * @param minimumOrder das zu setzende Objekt minimumOrder
	 */
	public void setMinimumOrder(Double minimumOrder) {
		this.minimumOrder = minimumOrder;
	}

	/**
	 * @return minimumOrderPurchase
	 */
	public Double getMinimumOrderPurchase() {
		return minimumOrderPurchase;
	}

	/**
	 * @param minimumOrderPurchase das zu setzende Objekt minimumOrderPurchase
	 */
	public void setMinimumOrderPurchase(Double minimumOrderPurchase) {
		this.minimumOrderPurchase = minimumOrderPurchase;
	}

	/**
	 * @return basePrice
	 */
	public Double getBasePrice() {
		return basePrice;
	}

	/**
	 * @param basePrice das zu setzende Objekt basePrice
	 */
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	/**
	 * @return percent
	 */
	public Double getPercent() {
		return percent;
	}

	/**
	 * @param percent das zu setzende Objekt percent
	 */
	public void setPercent(Double percent) {
		this.percent = percent;
	}

}
