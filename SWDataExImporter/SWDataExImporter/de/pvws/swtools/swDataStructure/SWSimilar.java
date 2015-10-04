/**
 * 
 */
package de.pvws.swtools.swDataStructure;

/**
 * @author PV KT
 *
 */
public class SWSimilar {
	private int iSwId;		// FK / SWArticle
	private String strName;
	
	/**
	 * 
	 */
	public SWSimilar () {
		
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
	
	

}
