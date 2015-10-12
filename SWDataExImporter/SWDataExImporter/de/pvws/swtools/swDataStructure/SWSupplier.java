/**
 * 
 */
package de.pvws.swtools.swDataStructure;

/**
 * @author PV KT
 *
 */
public class SWSupplier {
	private int iSwId;		// PK
	private String strName;
	private String strImage;
	private String strLink;
	private String strDescription;
	private String strMetaTitle;
	private String strMetaDescription;
	private String strMetaKeywords;
	
	/**
	 * 
	 */
	public SWSupplier () {
		this.iSwId = 1;
		this.strName = "arvato";
		this.strImage = "";
		this.strLink = "http://www.arvato.com";
		this.strDescription = "";
		this.strMetaTitle = "";
		this.strMetaDescription = "";
		this.strMetaKeywords = "";
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
	 * @return name
	 */
	public String getName() {
		return strName;
	}

	/**
	 * @param name das zu setzende Objekt name
	 */
	public void setName(String name) {
		this.strName = name;
	}

	/**
	 * @return image
	 */
	public String getImage() {
		return strImage;
	}

	/**
	 * @param image das zu setzende Objekt image
	 */
	public void setImage(String image) {
		this.strImage = image;
	}

	/**
	 * @return link
	 */
	public String getLink() {
		return strLink;
	}

	/**
	 * @param link das zu setzende Objekt link
	 */
	public void setLink(String link) {
		this.strLink = link;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return strDescription;
	}

	/**
	 * @param description das zu setzende Objekt description
	 */
	public void setDescription(String description) {
		this.strDescription = description;
	}

	/**
	 * @return metaTitle
	 */
	public String getMetaTitle() {
		return strMetaTitle;
	}

	/**
	 * @param metaTitle das zu setzende Objekt metaTitle
	 */
	public void setMetaTitle(String metaTitle) {
		this.strMetaTitle = metaTitle;
	}

	/**
	 * @return metaDescription
	 */
	public String getMetaDescription() {
		return strMetaDescription;
	}

	/**
	 * @param metaDescription das zu setzende Objekt metaDescription
	 */
	public void setMetaDescription(String metaDescription) {
		this.strMetaDescription = metaDescription;
	}

	/**
	 * @return metaKeywords
	 */
	public String getMetaKeywords() {
		return strMetaKeywords;
	}

	/**
	 * @param metaKeywords das zu setzende Objekt metaKeywords
	 */
	public void setMetaKeywords(String metaKeywords) {
		this.strMetaKeywords = metaKeywords;
	}

	
}
