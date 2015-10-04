/**
 * 
 */
package de.pvws.swtools.swDataStructure;

/**
 * @author PV KT
 *
 */
public class SWDownload {
	private int iSwId;				// PK
	private int iSwArticleId;		// FK / SWArticle
	private String strName;
	private String strFile;
	private int iSize;
	
	/**
	 * 
	 */
	public SWDownload () {
		
	}

	/**
	 * @return iSwId
	 */
	public int getSwId() {
		return iSwId;
	}

	/**
	 * @param iSwId das zu setzende Objekt iSwId
	 */
	public void setSwId(int iSwId) {
		this.iSwId = iSwId;
	}

	/**
	 * @return iSwArticleId
	 */
	public int getSwArticleId() {
		return iSwArticleId;
	}

	/**
	 * @param iSwArticleId das zu setzende Objekt iSwArticleId
	 */
	public void setSwArticleId(int iSwArticleId) {
		this.iSwArticleId = iSwArticleId;
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
	 * @return strFile
	 */
	public String getFile() {
		return strFile;
	}

	/**
	 * @param strFile das zu setzende Objekt strFile
	 */
	public void setFile(String strFile) {
		this.strFile = strFile;
	}

	/**
	 * @return iSize
	 */
	public int getSize() {
		return iSize;
	}

	/**
	 * @param iSize das zu setzende Objekt iSize
	 */
	public void setSize(int iSize) {
		this.iSize = iSize;
	}

	
}
