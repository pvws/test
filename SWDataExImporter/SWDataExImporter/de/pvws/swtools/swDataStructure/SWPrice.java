/**
 */
package de.pvws.swtools.swDataStructure;

/**
 * @author PV KT
 *
 */
public class SWPrice {
	private String strArticleNumber;
	private String strSwArticleNumber;
	private String strCustomerGroupKey;
	private String strCustomerGroup;
	private int iSwArticleDetailsId;	// PK
	private int	iSwArticleId;			// PK
	private int iSwId;					// PK
	private String strFrom;
	private String strTo;
	private Double dPrice;
	private Double dPseudoPrice;
	private Double dBasePrice;
	private Double dPercent;
	private Boolean bSet;
	private Boolean bSalePrice;

	/**
	 * 
	 */
	public SWPrice () {
		this.bSet = false;
		this.dPrice = 99999.99;
		this.strFrom = "0";
	}

	/**
	 * @return customerGroupKey
	 */
	public String getCustomerGroupKey() {
		return strCustomerGroupKey;
	}

	/**
	 * @param customerGroupKey das zu setzende Objekt customerGroupKey
	 */
	public void setCustomerGroupKey(String customerGroupKey) {
		this.strCustomerGroupKey = customerGroupKey;
	}

	/**
	 * @return customerGroup
	 */
	public String getCustomerGroup() {
		return strCustomerGroup;
	}

	/**
	 * @param customerGroup das zu setzende Objekt customerGroup
	 */
	public void setCustomerGroup(String customerGroup) {
		this.strCustomerGroup = customerGroup;
	}

	/**
	 * @return swArticleDetailsId
	 */
	public int getSwArticleDetailsId() {
		return iSwArticleDetailsId;
	}

	/**
	 * @param swArticleDetailsId das zu setzende Objekt swArticleDetailsId
	 */
	public void setSwArticleDetailsId(int swArticleDetailsId) {
		this.iSwArticleDetailsId = swArticleDetailsId;
	}

	/**
	 * @return swArticleId
	 */
	public int getSwArticleId() {
		return iSwArticleId;
	}

	/**
	 * @param swArticleId das zu setzende Objekt swArticleId
	 */
	public void setSwArticleId(int swArticleId) {
		this.iSwArticleId = swArticleId;
	}

	/**
	 * @return swId
	 */
	public int getSwId() {
		return iSwId;
	}

	/**
	 * @param swId das zu setzende Objekt swId
	 */
	public void setSwId(int swId) {
		this.iSwId = swId;
	}

	/**
	 * @return from
	 */
	public String getFrom() {
		return strFrom;
	}

	/**
	 * @param from das zu setzende Objekt from
	 */
	public void setFrom(String from) {
		this.strFrom = from;
	}

	/**
	 * @return to
	 */
	public String getTo() {
		return strTo;
	}

	/**
	 * @param to das zu setzende Objekt to
	 */
	public void setTo(String to) {
		this.strTo = to;
	}

	/**
	 * @return price
	 */
	public Double getPrice() {
		return dPrice;
	}

	/**
	 * @param price das zu setzende Objekt price
	 */
	public void setPrice(Double price) {
		this.dPrice = price;
		this.bSet = true;
	}

	public void setPrice(Double price, Boolean isSale) {
		if (isSale == true && price < this.dPrice) {
			this.dPseudoPrice = this.dPrice;
		}
		this.dPrice = price;
		this.bSet = true;
	}

	/**
	 * @return pseudoPrice
	 */
	public Double getPseudoPrice() {
		return dPseudoPrice;
	}

	/**
	 * @param pseudoPrice das zu setzende Objekt pseudoPrice
	 */
/*	public void setPseudoPrice(Double pseudoPrice) {
		this.dPseudoPrice = pseudoPrice;
	}
*/
	/**
	 * @return basePrice
	 */
	public Double getBasePrice() {
		return dBasePrice;
	}

	/**
	 * @param basePrice das zu setzende Objekt basePrice
	 */
	public void setBasePrice(Double basePrice) {
		this.dBasePrice = basePrice;
	}

	/**
	 * @return percent
	 */
	public Double getPercent() {
		return dPercent;
	}

	/**
	 * @param percent das zu setzende Objekt percent
	 */
	public void setPercent(Double percent) {
		this.dPercent = percent;
	}

	/**
	 * @return strArticleNumber
	 */
	public String getArticleNumber() {
		return strArticleNumber;
	}

	/**
	 * @param strArticleNumber das zu setzende Objekt strArticleNumber
	 */
	public void setArticleNumber(String strArticleNumber) {
		this.strArticleNumber = strArticleNumber;
		this.setSwArticleNumber("ads_" + strArticleNumber);
	}

	/**
	 * @return strSwArticleNumber
	 */
	public String getSwArticleNumber() {
		return strSwArticleNumber;
	}

	/**
	 * @param strSwArticleNumber das zu setzende Objekt strSwArticleNumber
	 */
	public void setSwArticleNumber(String strSwArticleNumber) {
		this.strSwArticleNumber = strSwArticleNumber;
	}
	
	
}
