/**
 * 
 */
package de.pvws.swtools.swDataStructure;

import java.util.*;

/**
 * @author PV WS
 *
 */
public class SWArticleDetail {
	private String strStylenumber = "";		// Parent original product-id
	private String strNumber = "";			// original product-id
	private String strSWNumber = "";			// ordernumber
	private String strSupplierNumber = "";
	private String strAdditionalText = "";
	private String strWeight = "";
	private String strWidth = "";
	private String strLen = "";
	private String strHeight = "";
	private String strEan = "";
	private String strPurchaseUnit = "";
	private String strDescriptionLong = "";
	private String strReferenceUnit = "";
	private String strPackUnit = "";
	private String strShippingTime = "";
	private LinkedList<SWPrice> llSwpPrice;								// array
	private LinkedList<SWConfiguratorOption> llSwcoConfiguratorOptions;	// array
	private SWArticleAttribute swaaAttribute;
	private int iSwId = 0;					// PK
	private int iSwArticleId = 0;			// FK / swArticle / swParent
	private int iSwUnitId = 0;				// FK
	private int iKind = 0;
	private int iInStock = 99;
	private int iPosition = 1;
	private int iMinPurchase = 1;
	private int iPurchaseSteps = 1;
	private int iMaxPurchase = 99999;
	private String strReleaseDate = "";
	private Boolean bActive = false;
	private Boolean bShippingFree = false;
	private String taxClass = "";
	private LinkedList<SWCategory> llSwcCategories;	// isn't member of SW Data Structure
	
	private Boolean bPriceSet;
	
	/**
	 */
	public SWArticleDetail() {
		// init Main Info
		this.iInStock = 50;
		this.iMinPurchase = 1;
		this.iPurchaseSteps = 1;
				
		this.bActive = false;
		this.bShippingFree = false;
		
		//init Objects
		this.swaaAttribute = new SWArticleAttribute();
		
		// init Arrays
		// TODO Array init
		this.llSwpPrice = new LinkedList<SWPrice>();
		this.llSwpPrice.add(new SWPrice());
		this.bPriceSet = false;
		this.llSwcoConfiguratorOptions = new LinkedList<SWConfiguratorOption>();
		this.llSwcCategories = new LinkedList<SWCategory>();
	}

	/**
	 * Returns the original Style Number (Number of Master)
	 * 
	 * @return strStylenumber
	 */
	public String getStylenumber() {
		if (this.strStylenumber != null)
			return strStylenumber;
		else 
			return "";
	}

	/**
	 * Sets the original Style Number (Number of Master)
	 * 
	 * @param strStylenumber das zu setzende Objekt strStylenumber
	 */
	public void setStylenumber(String strStylenumber) {
		if (this.strStylenumber != null)
			this.strStylenumber = strStylenumber;
		else
			this.strStylenumber = "";
	}

	/**
	 * Returns the original Article Number.
	 * 
	 * @return number
	 */
	public String getNumber() {
		if (this.strNumber != null)
			return strNumber;
		else 
			return "";
	}

	/**
	 * Sets the original Article Number.
	 * 
	 * @param number das zu setzende Objekt number
	 */
	public void setNumber(String number) {
//		this.setSWNumber("ads_" + number);
		this.strNumber = number;
	}

	/**
	 * Returns the SW Article Number (Order Number).
	 * 
	 * @return
	 */
	public String getSWNumber() {
		if (this.strSWNumber == null || this.strSWNumber == "")
			return "ads_" + this.strNumber;
		else
			return strSWNumber;
	}
	
	/**
	 * Sets the SW Article Number (Order Number).
	 * @param number
	 */
	public void setSWNumber(String number) {
		this.strSWNumber = number;
	}
	
	/**
	 * @return supplierNumber
	 */
	public String getSupplierNumber() {
		return strSupplierNumber;
	}

	/**
	 * @param supplierNumber das zu setzende Objekt supplierNumber
	 */
	public void setSupplierNumber(String supplierNumber) {
		this.strSupplierNumber = supplierNumber;
	}

	/**
	 * @return additionalText
	 */
	public String getAdditionalText() {
		return strAdditionalText;
	}

	/**
	 * @param additionalText das zu setzende Objekt additionalText
	 */
	public void setAdditionalText(String additionalText) {
		this.strAdditionalText = additionalText;
	}

	/**
	 * @return weight
	 */
	public String getWeight() {
		return strWeight;
	}

	/**
	 * @param weight das zu setzende Objekt weight
	 */
	public void setWeight(String weight) {
		this.strWeight = weight;
	}

	/**
	 * @return width
	 */
	public String getWidth() {
		return strWidth;
	}

	/**
	 * @param width das zu setzende Objekt width
	 */
	public void setWidth(String width) {
		this.strWidth = width;
	}

	/**
	 * @return len
	 */
	public String getLen() {
		return strLen;
	}

	/**
	 * @param len das zu setzende Objekt len
	 */
	public void setLen(String len) {
		this.strLen = len;
	}

	/**
	 * @return height
	 */
	public String getHeight() {
		return strHeight;
	}

	/**
	 * @param height das zu setzende Objekt height
	 */
	public void setHeight(String height) {
		this.strHeight = height;
	}

	/**
	 * @return ean
	 */
	public String getEan() {
		return strEan;
	}

	/**
	 * @param ean das zu setzende Objekt ean
	 */
	public void setEan(String ean) {
		this.strEan = ean;
	}

	/**
	 * @return purchaseUnit
	 */
	public String getPurchaseUnit() {
		return strPurchaseUnit;
	}

	/**
	 * @param purchaseUnit das zu setzende Objekt purchaseUnit
	 */
	public void setPurchaseUnit(String purchaseUnit) {
		this.strPurchaseUnit = purchaseUnit;
	}

	/**
	 * @return descriptionLong
	 */
	public String getDescriptionLong() {
		return strDescriptionLong;
	}

	/**
	 * @param descriptionLong das zu setzende Objekt descriptionLong
	 */
	public void setDescriptionLong(String descriptionLong) {
		this.strDescriptionLong = descriptionLong;
	}

	/**
	 * @return referenceUnit
	 */
	public String getReferenceUnit() {
		return strReferenceUnit;
	}

	/**
	 * @param referenceUnit das zu setzende Objekt referenceUnit
	 */
	public void setReferenceUnit(String referenceUnit) {
		this.strReferenceUnit = referenceUnit;
	}

	/**
	 * @return packUnit
	 */
	public String getPackUnit() {
		return strPackUnit;
	}

	/**
	 * @param packUnit das zu setzende Objekt packUnit
	 */
	public void setPackUnit(String packUnit) {
		this.strPackUnit = packUnit;
	}

	/**
	 * @return shippingTime
	 */
	public String getShippingTime() {
		return strShippingTime;
	}

	/**
	 * @param shippingTime das zu setzende Objekt shippingTime
	 */
	public void setShippingTime(String shippingTime) {
		this.strShippingTime = shippingTime;
	}

	/**
	 * returns base price object (erster einer Mengenstaffel)
	 * 
	 * @return
	 */
	public SWPrice getPrice() {
		return this.llSwpPrice.getFirst();
	}

	/**
	 * returns price object regarding Menge
	 * 
	 * @param from
	 * @return
	 */
	public SWPrice getPrice(int from) {
		Iterator<SWPrice> iSwp;
		SWPrice swpA;
		SWPrice swp;
		int iFrom;
		Double dPrice;
		
		swpA = new SWPrice();
		iFrom = 0;
		dPrice = 0.00;
		
		iSwp = this.llSwpPrice.iterator();
		while (iSwp.hasNext()) {
			swp = iSwp.next();
			iFrom = Integer.valueOf(swp.getFrom());
			dPrice = swp.getPrice();
			if (iFrom <= from && Integer.valueOf(swpA.getFrom()) < iFrom) {
				swpA = swp;
			}
		}
		
		return swpA;
	}

	/**
	 * returns a list of all price objects (alle Staffelpreise)
	 * @return
	 */
	public LinkedList<SWPrice> getPrices() {
		return this.llSwpPrice;
	}

	/**
	 * @param price das zu setzende Objekt price
	 */
	public void addPrice(SWPrice price) {
//		if (this.llSwpPrice.getFirst().getPrice() >= 99999.00) {
		if (!this.bPriceSet) {
			this.llSwpPrice.clear();
		}
		this.llSwpPrice.add(price);
	}

	/**
	 * @return configuratorOptions
	 */
	public LinkedList<SWConfiguratorOption> getConfiguratorOptions() {
		return llSwcoConfiguratorOptions;
	}

	/**
	 * @param configuratorOptions das zu setzende Objekt configuratorOptions
	 */
	public void addConfiguratorOptions(SWConfiguratorOption configuratorOptions) {
		this.llSwcoConfiguratorOptions.add(configuratorOptions);
	}

	/**
	 * @return attribute
	 */
	public SWArticleAttribute getAttribute() {
		return swaaAttribute;
	}

	/**
	 * @param attribute das zu setzende Objekt attribute
	 */
	public void setAttribute(SWArticleAttribute attribute) {
		this.swaaAttribute = attribute;
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
	 * @return swUnitId
	 */
	public int getSwUnitId() {
		return iSwUnitId;
	}

	/**
	 * @param swUnitId das zu setzende Objekt swUnitId
	 */
	public void setSwUnitId(int swUnitId) {
		this.iSwUnitId = swUnitId;
	}

	/**
	 * @return kind
	 */
	public int getKind() {
		return iKind;
	}

	/**
	 * @param kind das zu setzende Objekt kind
	 */
	public void setKind(int kind) {
		this.iKind = kind;
	}

	/**
	 * @return inStock
	 */
	public int getInStock() {
		return iInStock;
	}

	/**
	 * @param inStock das zu setzende Objekt inStock
	 */
	public void setInStock(int inStock) {
		this.iInStock = inStock;
	}

	/**
	 * @return position
	 */
	public int getPosition() {
		return iPosition;
	}

	/**
	 * @param position das zu setzende Objekt position
	 */
	public void setPosition(int position) {
		this.iPosition = position;
	}

	/**
	 * @return minPurchase
	 */
	public int getMinPurchase() {
		return iMinPurchase;
	}

	/**
	 * @param minPurchase das zu setzende Objekt minPurchase
	 */
	public void setMinPurchase(int minPurchase) {
		if (minPurchase >= 0) {
			this.iMinPurchase = minPurchase;
		}
	}

	/**
	 * @return purchaseSteps
	 */
	public int getPurchaseSteps() {
		return iPurchaseSteps;
	}

	/**
	 * @param purchaseSteps das zu setzende Objekt purchaseSteps
	 */
	public void setPurchaseSteps(int purchaseSteps) {
		if (purchaseSteps >= 0) {
		 	this.iPurchaseSteps = purchaseSteps;
		}
	}

	/**
	 * @return maxPurchase
	 */
	public int getMaxPurchase() {
		return iMaxPurchase;
	}

	/**
	 * @param maxPurchase das zu setzende Objekt maxPurchase
	 */
	public void setMaxPurchase(int maxPurchase) {
		this.iMaxPurchase = maxPurchase;
	}

	/**
	 * @return releaseDate
	 */
	public String getReleaseDate() {
		return strReleaseDate;
	}

	/**
	 * @param releaseDate das zu setzende Objekt releaseDate
	 */
	public void setReleaseDate(String releaseDate) {
		this.strReleaseDate = releaseDate;
	}

	/**
	 * @return active
	 */
	public Boolean getActive() {
		return bActive;
	}

	/**
	 * @param active das zu setzende Objekt active
	 */
	public void setActive(Boolean active) {
		this.bActive = active;
	}

	/**
	 * @return shippingFree
	 */
	public Boolean getShippingFree() {
		return bShippingFree;
	}

	/**
	 * @param shippingFree das zu setzende Objekt shippingFree
	 */
	public void setShippingFree(Boolean shippingFree) {
		this.bShippingFree = shippingFree;
	}

	/**
	 * Tax Class: currently there are 2 states: "default" and not "default".
	 * 
	 * @return taxClass
	 */
	public String getTaxClass() {
		return taxClass;
	}

	/**
	 * Tax Class: currently there are 2 states: "default" and not "default".
	 * 
	 * @param taxClass das zu setzende Objekt taxClass
	 */
	public void setTaxClass(String taxClass) {
		this.taxClass = taxClass;
	}
	
	public void addCategories (SWCategory swCat) {
		this.llSwcCategories.add(swCat);
	}
	
	public LinkedList<SWCategory> getCategories () {
		return this.llSwcCategories;
	}

}
