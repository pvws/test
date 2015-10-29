/**
 * 
 */
package de.pvws.swtools.swDataStructure;

import java.util.*;

/**
 * Represents the Shopware Article Data Structure.
 * 
 * @author PV WS
 */
public class SWArticle {
	private String strArticleNumber;			// extern
//	private String strParentArticelNumber;		// extern / obsolet
	private String strSwArticleNumber;
//	private String strSwParentArticleNumber;	// obsolet

	private String strName;
	private String strDescription;
	private String strDescriptionLong;
	
	private SWTax swtSwTax;

	private SWArticleDetail swadMainDetail;
	private Boolean bIsMainDetailSet;
	
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
	private String strDwImagePath;
	private LinkedList<String> llDwImages;
	private LinkedList<String> llDwImagePaths;
	private Boolean bDwImagesSet;
	private LinkedList<SWImage> llSwiImages;			// array
	private SWConfiguratorSet swcsConfiguratorSet;
	private SWDownload swdDownlowds;					// array
	private LinkedList<SWCategory> llSwcCategories;		// array
	private SWSimilar swsSimilar;						// array
	private SWRelated swrRelated;						// array
	private LinkedList<SWArticleDetail> llSwadVariants;	// array -> LinkedList
	private Boolean bHasVariants;
	
	
	/**
	 * 
	 */
	public SWArticle () {
		// init Main Reference
		this.strArticleNumber = "new";
//		this.strParentArticelNumber = "none";
		this.strSwArticleNumber = "new";
//		this.strSwParentArticleNumber = "none";
		
		// init Objects
		this.swtSwTax = new SWTax();
		this.swadMainDetail = new SWArticleDetail();
		this.bIsMainDetailSet = false;
		this.swsSupplier = new SWSupplier();
		this.swcsConfiguratorSet = new SWConfiguratorSet();
		
		// init Array / LinkedList
		// TODO Array init
		this.llSwadVariants = new LinkedList<SWArticleDetail>();
		this.bHasVariants = false;
		
		this.llSwcCategories = new LinkedList<SWCategory>();
		
		this.llDwImages = new LinkedList<String>();
		this.llDwImagePaths = new LinkedList<String>();
		this.strDwImagePath = ""; // "http://netrada2.scene7.com/is/image/netrada2/";
		this.bDwImagesSet = false;
		
		this.llSwiImages = new LinkedList<SWImage>();
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
/*	public String getParentArticelNumber() {
		return strParentArticelNumber;
	}
*/
	/**
	 * @param strParentArticelNumber das zu setzende Objekt strParentArticelNumber
	 */
/*	public void setParentArticelNumber(String strParentArticelNumber) {
		this.strParentArticelNumber = strParentArticelNumber;
	}
*/
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
/*	public String getSwParentArticleNumber() {
		return strSwParentArticleNumber;
	}
*/

	/**
	 * @param strSwParentArticleNumber das zu setzende Objekt strSwParentArticleNumber
	 */
/*	public void setSwParentArticleNumber(String strSwParentArticleNumber) {
		this.strSwParentArticleNumber = strSwParentArticleNumber;
	}
*/

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
		if (this.strDescription == null || this.strDescription.equals("")) {
			return this.strName;
		}
		return this.strDescription;
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
		if (this.strDescriptionLong == null) 
			return "";
		else
			return this.strDescriptionLong;
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
		Iterator<SWConfiguratorOption> iSco;
		if (swadMainDetail != null) {
			this.swadMainDetail = swadMainDetail;
			this.swadMainDetail.setStylenumber(this.strArticleNumber); // orig Stylenumber
			this.setIsMainDetailSet(true);

			iSco = swadMainDetail.getConfiguratorOptions().iterator();
			while (iSco.hasNext()) {
				this.swcsConfiguratorSet.addCgGroup(iSco.next().getSwConfiguratorGroup());
			}
		}
	} // setMainDetail


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
	public LinkedList<SWImage> getImages() {
		return this.llSwiImages;
	}


	/**
	 * @param swiImages das zu setzende Objekt swiImages
	 */
	public void addImages(SWImage swiImages) {
		this.llSwiImages.add(swiImages);
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
	public LinkedList<SWCategory> getCategories() {
		return this.llSwcCategories;
	}


	/**
	 * @param swcCategories das zu setzende Objekt swcCategories
	 */
	public void addCategories(SWCategory swcCategories) {
		this.llSwcCategories.add(swcCategories);
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
	 * Returns a LinkedList of the Variants as SW ArticleDetail Objects.
	 * If there are no Variants, the LinkedList is empty.
	 * 
	 * @return LinkedList<SWArticleDetail>
	 */
	public LinkedList<SWArticleDetail> getVariants() {
		return this.llSwadVariants;
	}

	/**
	 * Returns a LinkedList with the SWArticleDetail Objects which fits to the ConfigGroup / ConfigOptionCode
	 * (eg Farbe / White)
	 * 
	 * @param configGroupName
	 * @param configOptioncode
	 * @return LinkedList with fitting SWArticleDetail Objects
	 */
	public LinkedList<SWArticleDetail> getVariants (String configGroupName, String configOptionCode) {
		LinkedList<SWArticleDetail> llSwad;
		Iterator<SWConfiguratorOption> iSwco;
		Iterator<SWArticleDetail> iSwad;
		SWConfiguratorOption swco;
		SWArticleDetail swad;
		
		llSwad = new LinkedList<SWArticleDetail>();
		
		// check MainDetail
		swad = this.getMainDetail();
		iSwco = swad.getConfiguratorOptions().iterator();
		while (iSwco.hasNext()) {
			swco = iSwco.next();
			if (swco.getSwConfiguratorGroup().getName().equals(configGroupName))
				if (swco.getCode().equals(configOptionCode)) {
					llSwad.add(swad);
					break;
				}
		}

		// check Variants
		if (this.hasVariants()) {
			iSwad = this.getVariants().iterator();
			while (iSwad.hasNext()) {
				swad = iSwad.next();
				iSwco = swad.getConfiguratorOptions().iterator();
				while (iSwco.hasNext()) {
					swco = iSwco.next();
					if (swco.getSwConfiguratorGroup().getName().equals(configGroupName))
						if (swco.getCode().equals(configOptionCode)) {
							llSwad.add(swad);
							break;
						}
				}
			}
		}

		return llSwad;
	} // getVariants (ConfigGroupName, ConfigOptionName)

	/**
	 * @param swadVariants das zu setzende Objekt swadVariants
	 */
	public void addVariant(SWArticleDetail swadVariant) {
		Iterator<SWConfiguratorOption> iSco;
		
		// set Supplier if needed
		if (swadVariant.getSupplierNumber() == null || swadVariant.getSupplierNumber().equals(""))
			swadVariant.setSupplierNumber(String.valueOf(this.iSwSupplierId));
		// if there is no MainDetail yet, set it
		if (!this.isMainDetailSet()) {
			this.setMainDetail(swadVariant);
		}
		else {
			this.llSwadVariants.add(swadVariant);

			iSco = swadMainDetail.getConfiguratorOptions().iterator();
			while (iSco.hasNext()) {
				this.swcsConfiguratorSet.addCgGroup(iSco.next().getSwConfiguratorGroup());
			}
			
			this.setHasVariants(true);
		}
	}

	/**
	 * Indicates, if there are Variants for this Article.
	 * 
	 * @return true, if there are Variants - false else
	 */
	public Boolean hasVariants() {
		return this.bHasVariants;
	} // hasVariants
	
	/**
	 * Set the indicator if the article has Variants
	 * 
	 * @param b
	 */
	private void setHasVariants (Boolean b) {
		this.bHasVariants = b;
	} // setHasVariants
	
	/**
	 * Returns true, if the MainDetail is already set
	 * 
	 * @return
	 */
	private Boolean isMainDetailSet () {
		return this.bIsMainDetailSet;
	}
	
	/**
	 * Sets the Indicator if the MainDetail already set.
	 * 
	 * @param b
	 */
	private void setIsMainDetailSet(Boolean b) {
		this.bIsMainDetailSet = b;
	} // steIsMainDetailSet
	
	/**
	 * Sets the path to the images.
	 * 
	 * @param path
	 */
	public void setDwImagePath(String path) {
		this.strDwImagePath = path;
	}
	
	/**
	 * Returns the PATH where the Images are.
	 * 
	 * @return
	 */
	public String getDwImagePath () {
		return this.strDwImagePath;
	}
	
	/**
	 * Sets the Image is Set Indicator.
	 * 
	 * @param b
	 */
	private void setDwImagesSet (Boolean b) {
		this.bDwImagesSet = b;
	}
	
	/**
	 * Returns true, if at least one Image was added.
	 * 
	 * @return
	 */
	public Boolean isDwImagesSet () {
		return this.bDwImagesSet;
	}
	
	/**
	 * Adds a Image-Name to the Image-List.
	 * 
	 * @param image
	 */
	public void addDwImage (String image) {
		String path;
		
		if (this.llDwImages == null)
			this.llDwImages = new LinkedList<String>();
		
		// add just Image
		this.llDwImages.add(image);
		this.bDwImagesSet = true;

		// add full URI
		path = this.getDwImagePath();
		if (path.length() > 2)
			if (path.charAt(path.length()-1) == '/')
				path = path.substring(0, path.length()-2);
		this.llDwImagePaths.add(path + image);
	}
	
	/**
	 * Returns a LinkedList with the Image-Names and eventually the scene7 postfixes.
	 * 
	 * @return
	 */
	public LinkedList<String> getDwImageList () {
		return this.llDwImages;
	}
	
	/**
	 * Returns a LinkedList with DW-Image-Paths where the Images can be downloaded
	 * 
	 * @return ImagePath - List as Strings
	 */
	public LinkedList<String> geDwImagePathList() {
		return this.llDwImagePaths;
	}
	
	/**
	 * Transforms DW Image Paths to SW Image Object
	 */
	public void computeDWImageToSw () {
		Iterator<String> itDwImage;
		SWImage swImage;
		String path;
		String uri;
		String name;
		String desc;
		String colorCode;
		int pos;
		
		if (this.bDwImagesSet) {
			itDwImage = this.getDwImageList().iterator();
			while (itDwImage.hasNext()) {
				swImage = new SWImage();
				path = itDwImage.next();
				uri = "/media/image" + this.getImagePathName(path) + ".jpg";
				name = this.getImagePathName(path);
				name = name.substring(name.lastIndexOf('/') + 1);
				desc = this.getImageDesc(path);
				pos = this.getImagePos(path);
				colorCode = this.getImageColorCode(path);
				
				swImage.setPath(uri);
				swImage.setName(name);
				swImage.setDescription(desc);
				swImage.setPosition(pos);
				swImage.setMain(1);
				swImage.setExtension("jpg");
				swImage.setColorCode(colorCode);
				
				this.addImages(swImage);
			}
		}
		
	}
	
	/**
	 * Extracts the Image Name out of a URI String
	 * 
	 * @param path
	 * @return Image Name
	 */
	private String getImagePathName (String path) {
		String name = "";
		
		if (path != null) {
			name = path.substring(0, path.indexOf('?'));
			name = name.substring(name.lastIndexOf('/'));
		}
		
		return name;
	}

	/**
	 * Transforms a Image Path URI to a standard Image Description.
	 * (Bild zu /ImageName/)
	 * 
	 * @param path
	 * @return Image Description
	 */
	private String getImageDesc (String path) {
		String desc;
		
		desc = "Bild zu ";
		desc += this.getImagePathName(path).substring(1);
		
		return desc;
	}

	/**
	 * Extracts the Image Position out of the Image Name (Path / URI)
	 * 
	 * @param path
	 * @return Image Position
	 */
	private int getImagePos (String path) {
		int p = 0;
		String pos;
		
		if (path != null) {
			pos = this.getImagePathName(path);
			pos = pos.substring(pos.lastIndexOf('_') + 1);
			p = Integer.parseInt(pos);
		}
		
		return p;
	}
	
	/**
	 * Extracts the ColorCode out of the Image Name (Path / URI)
	 * @param path
	 * @return
	 */
	private String getImageColorCode (String path) {
		String cc = "00";
		
		cc = this.getImagePathName(path);
		cc = cc.substring(0, cc.lastIndexOf('_'));
		cc = cc.substring(cc.lastIndexOf('_') + 1);
		
		return cc;
	}	
}
