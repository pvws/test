/**
 * 
 */
package de.pvws.swtools.swDataStructure;

import java.util.*;

/**
 * @author PV WS
 *
 */
public class SWArticle {
	private String strArticleNumber;			// extern
	private String strParentArticelNumber;		// extern / obsolet
	private String strSwArticleNumber;
	private String strSwParentArticleNumber;	// obsolet

	private String strName;
	private String strDescription;
	private String strDescriptionLong;
	
	private SWTax swtSwTax;

	private SWArticleDetail swadMainDetail;
	
	private int iSwSupplierId;
	private SWSupplier swsSupplier;
	
	private int iSwPriceGroupId;
	private int iSwFilterGroupId;
	
	private String strAdded;		// date/time
	private Boolean bActive;
	private int iPseudoSales;
	private Boolean bHighlight;
	private String strKeywords;
	private String strMetaTitle;
	private String strChanged;		// date/time
	private Boolean bPriceGroupActive;
	private Boolean bLastStock;
	private Boolean bCrossBundleLook;
	private Boolean bNotification;
	private String strTemplate;
	private int iMode;
	private String strAvailableFrom;					// date/time
	private String strAvailableTo;						// date/time
	private SWPropertyValue swpvPropertyValue;			// array
	private SWCustomerGroup swcgCustomerGroups;			// array
	private SWImage swiImages;							// array
	private SWConfiguratorSet swcsConfiguratorSet;
	private SWDownload swdDownlowds;					// array
	private SWCategory swcCategories;					// array
	private SWSimilar swsSimilar;						// array
	private SWRelated swrRelated;						// array
	private LinkedList<SWArticleDetail> llSwadVariants;	// array -> LinkedList
	
	
	/**
	 * 
	 */
	public SWArticle () {
		// init Main Reference
		this.strArticleNumber = "new";
		this.strParentArticelNumber = "none";
		this.strSwArticleNumber = "new";
		this.strSwParentArticleNumber = "none";
		
		// init Objects
		this.swtSwTax = new SWTax();
		this.swadMainDetail = new SWArticleDetail();
		this.swsSupplier = new SWSupplier();
		this.swcsConfiguratorSet = new SWConfiguratorSet();
		
		// init Array / LinkedList
		// TODO Array init
		this.llSwadVariants = new LinkedList<SWArticleDetail>();
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
		if (strArticleNumber != null && !strArticleNumber.equals("")) {
			if (this.strArticleNumber.equals("new")) {
				this.strArticleNumber = strArticleNumber;
			}
			this.setSwArticleNumber("ads_" + strArticleNumber);
		}
	}


	/**
	 * @return strParentArticelNumber
	 */
	public String getParentArticelNumber() {
		return strParentArticelNumber;
	}


	/**
	 * @param strParentArticelNumber das zu setzende Objekt strParentArticelNumber
	 */
	public void setParentArticelNumber(String strParentArticelNumber) {
		this.strParentArticelNumber = strParentArticelNumber;
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
		if (strSwArticleNumber != null && !strSwArticleNumber.equals("") && this.strSwArticleNumber.equals("new")) {
			this.strSwArticleNumber = strSwArticleNumber;
		}
	}


	/**
	 * @return strSwParentArticleNumber
	 */
	public String getSwParentArticleNumber() {
		return strSwParentArticleNumber;
	}


	/**
	 * @param strSwParentArticleNumber das zu setzende Objekt strSwParentArticleNumber
	 */
	public void setSwParentArticleNumber(String strSwParentArticleNumber) {
		this.strSwParentArticleNumber = strSwParentArticleNumber;
	}


	/**
	 * @return strName
	 */
	public String getName() {
		return strName;
	}


	/**
	 * @param strName das zu setzende Objekt strName
	 */
	public void setName(String strName) {
		this.strName = strName;
	}


	/**
	 * @return strDescription
	 */
	public String getDescription() {
		if (this.strDescription.equals("")) {
			return this.strName;
		}
		return strDescription;
	}


	/**
	 * @param strDescription das zu setzende Objekt strDescription
	 */
	public void setDescription(String strDescription) {
		this.strDescription = strDescription;
	}


	/**
	 * @return strDescriptionLong
	 */
	public String getDescriptionLong() {
		return strDescriptionLong;
	}


	/**
	 * @param strDescriptionLong das zu setzende Objekt strDescriptionLong
	 */
	public void setDescriptionLong(String strDescriptionLong) {
		this.strDescriptionLong = strDescriptionLong;
	}


	/**
	 * @return iSwTaxId
	 */
	public int getSwTaxId() {
		return this.swtSwTax.getSwTaxId();
	}


	/**
	 * @return swtSwTax
	 */
	public SWTax getSwTax() {
		return swtSwTax;
	}


	/**
	 * @param swtSwTax das zu setzende Objekt swtSwTax
	 */
	public void setSwTax(SWTax swtSwTax) {
		this.swtSwTax = swtSwTax;
	}


	/**
	 * @return swadMainDetail
	 */
	public SWArticleDetail getMainDetail() {
		return swadMainDetail;
	}


	/**
	 * @param swadMainDetail das zu setzende Objekt swadMainDetail
	 */
	public void setMainDetail(SWArticleDetail swadMainDetail) {
		if (swadMainDetail != null)
			this.swadMainDetail = swadMainDetail;
		this.swadMainDetail.setStylenumber(this.strArticleNumber); // orig Stylenumber
	}


	/**
	 * @return iSwSupplierId
	 */
	public int getSupplierId() {
		return this.swsSupplier.getSwId();
	}


	/**
	 * @return swsSupplier
	 */
	public SWSupplier getSupplier() {
		return swsSupplier;
	}


	/**
	 * @param swsSupplier das zu setzende Objekt swsSupplier
	 */
	public void setSupplier(SWSupplier swsSupplier) {
		this.swsSupplier = swsSupplier;
	}


	/**
	 * @return iSwPriceGroupId
	 */
	public int getSwPriceGroupId() {
		return iSwPriceGroupId;
	}


	/**
	 * @param iSwPriceGroupId das zu setzende Objekt iSwPriceGroupId
	 */
	public void setSwPriceGroupId(int iSwPriceGroupId) {
		this.iSwPriceGroupId = iSwPriceGroupId;
	}


	/**
	 * @return iSwFilterGroupId
	 */
	public int getSwFilterGroupId() {
		return iSwFilterGroupId;
	}


	/**
	 * @param iSwFilterGroupId das zu setzende Objekt iSwFilterGroupId
	 */
	public void setSwFilterGroupId(int iSwFilterGroupId) {
		this.iSwFilterGroupId = iSwFilterGroupId;
	}


	/**
	 * @return strAdded
	 */
	public String getAdded() {
		return strAdded;
	}


	/**
	 * @param strAdded das zu setzende Objekt strAdded
	 */
	public void setAdded(String strAdded) {
		this.strAdded = strAdded;
	}


	/**
	 * @return bActive
	 */
	public Boolean getActive() {
		return bActive;
	}


	/**
	 * @param bActive das zu setzende Objekt bActive
	 */
	public void setActive(Boolean bActive) {
		this.bActive = bActive;
	}


	/**
	 * @return iPseudoSales
	 */
	public int getPseudoSales() {
		return iPseudoSales;
	}


	/**
	 * @param iPseudoSales das zu setzende Objekt iPseudoSales
	 */
	public void setPseudoSales(int iPseudoSales) {
		this.iPseudoSales = iPseudoSales;
	}


	/**
	 * @return bHighlight
	 */
	public Boolean getHighlight() {
		return bHighlight;
	}


	/**
	 * @param bHighlight das zu setzende Objekt bHighlight
	 */
	public void setHighlight(Boolean bHighlight) {
		this.bHighlight = bHighlight;
	}


	/**
	 * @return strKeywords
	 */
	public String getKeywords() {
		return strKeywords;
	}


	/**
	 * @param strKeywords das zu setzende Objekt strKeywords
	 */
	public void setKeywords(String strKeywords) {
		this.strKeywords = strKeywords;
	}


	/**
	 * @return strMetaTitle
	 */
	public String getMetaTitle() {
		return strMetaTitle;
	}


	/**
	 * @param strMetaTitle das zu setzende Objekt strMetaTitle
	 */
	public void setMetaTitle(String strMetaTitle) {
		this.strMetaTitle = strMetaTitle;
	}


	/**
	 * @return strChanged
	 */
	public String getChanged() {
		return strChanged;
	}


	/**
	 * @param strChanged das zu setzende Objekt strChanged
	 */
	public void setChanged(String strChanged) {
		this.strChanged = strChanged;
	}


	/**
	 * @return bPriceGroupActive
	 */
	public Boolean getPriceGroupActive() {
		return bPriceGroupActive;
	}


	/**
	 * @param bPriceGroupActive das zu setzende Objekt bPriceGroupActive
	 */
	public void setPriceGroupActive(Boolean bPriceGroupActive) {
		this.bPriceGroupActive = bPriceGroupActive;
	}


	/**
	 * @return bLastStock
	 */
	public Boolean getLastStock() {
		return bLastStock;
	}


	/**
	 * @param bLastStock das zu setzende Objekt bLastStock
	 */
	public void setLastStock(Boolean bLastStock) {
		this.bLastStock = bLastStock;
	}


	/**
	 * @return bCrossBundleLook
	 */
	public Boolean getCrossBundleLook() {
		return bCrossBundleLook;
	}


	/**
	 * @param bCrossBundleLook das zu setzende Objekt bCrossBundleLook
	 */
	public void setCrossBundleLook(Boolean bCrossBundleLook) {
		this.bCrossBundleLook = bCrossBundleLook;
	}


	/**
	 * @return bNotification
	 */
	public Boolean getNotification() {
		return bNotification;
	}


	/**
	 * @param bNotification das zu setzende Objekt bNotification
	 */
	public void setNotification(Boolean bNotification) {
		this.bNotification = bNotification;
	}


	/**
	 * @return strTemplate
	 */
	public String getTemplate() {
		return strTemplate;
	}


	/**
	 * @param strTemplate das zu setzende Objekt strTemplate
	 */
	public void setTemplate(String strTemplate) {
		this.strTemplate = strTemplate;
	}


	/**
	 * @return iMode
	 */
	public int getMode() {
		return iMode;
	}


	/**
	 * @param iMode das zu setzende Objekt iMode
	 */
	public void setMode(int iMode) {
		this.iMode = iMode;
	}


	/**
	 * @return strAvailableFrom
	 */
	public String getAvailableFrom() {
		return strAvailableFrom;
	}


	/**
	 * @param strAvailableFrom das zu setzende Objekt strAvailableFrom
	 */
	public void setAvailableFrom(String strAvailableFrom) {
		this.strAvailableFrom = strAvailableFrom;
	}


	/**
	 * @return strAvailableTo
	 */
	public String getAvailableTo() {
		return strAvailableTo;
	}


	/**
	 * @param strAvailableTo das zu setzende Objekt strAvailableTo
	 */
	public void setAvailableTo(String strAvailableTo) {
		this.strAvailableTo = strAvailableTo;
	}


	/**
	 * @return swpvPropertyValue
	 */
	public SWPropertyValue getPropertyValue() {
		return swpvPropertyValue;
	}


	/**
	 * @param swpvPropertyValue das zu setzende Objekt swpvPropertyValue
	 */
	public void setPropertyValue(SWPropertyValue swpvPropertyValue) {
		this.swpvPropertyValue = swpvPropertyValue;
	}


	/**
	 * @return swcgCustomerGroups
	 */
	public SWCustomerGroup getCustomerGroups() {
		return swcgCustomerGroups;
	}


	/**
	 * @param swcgCustomerGroups das zu setzende Objekt swcgCustomerGroups
	 */
	public void setCustomerGroups(SWCustomerGroup swcgCustomerGroups) {
		this.swcgCustomerGroups = swcgCustomerGroups;
	}


	/**
	 * @return swiImages
	 */
	public SWImage getImages() {
		return swiImages;
	}


	/**
	 * @param swiImages das zu setzende Objekt swiImages
	 */
	public void setImages(SWImage swiImages) {
		this.swiImages = swiImages;
	}


	/**
	 * @return swcsConfiguratorSet
	 */
	public SWConfiguratorSet getConfiguratorSet() {
		return swcsConfiguratorSet;
	}


	/**
	 * @param swcsConfiguratorSet das zu setzende Objekt swcsConfiguratorSet
	 */
	public void setConfiguratorSet(SWConfiguratorSet swcsConfiguratorSet) {
		this.swcsConfiguratorSet = swcsConfiguratorSet;
	}


	/**
	 * @return swdDownlowds
	 */
	public SWDownload getDownlowds() {
		return swdDownlowds;
	}


	/**
	 * @param swdDownlowds das zu setzende Objekt swdDownlowds
	 */
	public void setDownlowds(SWDownload swdDownlowds) {
		this.swdDownlowds = swdDownlowds;
	}


	/**
	 * @return swcCategories
	 */
	public SWCategory getCategories() {
		return swcCategories;
	}


	/**
	 * @param swcCategories das zu setzende Objekt swcCategories
	 */
	public void setCategories(SWCategory swcCategories) {
		this.swcCategories = swcCategories;
	}


	/**
	 * @return swsSimilar
	 */
	public SWSimilar getSimilar() {
		return swsSimilar;
	}


	/**
	 * @param swsSimilar das zu setzende Objekt swsSimilar
	 */
	public void setSimilar(SWSimilar swsSimilar) {
		this.swsSimilar = swsSimilar;
	}


	/**
	 * @return swrRelated
	 */
	public SWRelated getRelated() {
		return swrRelated;
	}


	/**
	 * @param swrRelated das zu setzende Objekt swrRelated
	 */
	public void setRelated(SWRelated swrRelated) {
		this.swrRelated = swrRelated;
	}


	/**
	 * @return swadVariants
	 */
	public LinkedList<SWArticleDetail> getVariants() {
		return this.llSwadVariants;
	}


	/**
	 * @param swadVariants das zu setzende Objekt swadVariants
	 */
	public void addVariant(SWArticleDetail swadVariant) {
		if (swadVariant.getSupplierNumber() == null || swadVariant.getSupplierNumber().equals(""))
			swadVariant.setSupplierNumber(String.valueOf(this.iSwSupplierId));
		this.llSwadVariants.add(swadVariant);
	}

	
	
}