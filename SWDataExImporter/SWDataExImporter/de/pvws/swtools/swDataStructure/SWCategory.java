/**
 * 
 */
package de.pvws.swtools.swDataStructure;

/**
 * @author PV KT
 *
 */
public class SWCategory {
	private int iSwId;		// PK
	private String strName;
	private String iId;
	
	/**
	 * 
	 */
	public SWCategory () {
		
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
	
	/**
	 * @return iId
	 */
	public String getId() {
		return this.iId;
	}

	/**
	 * @param iId das zu setzende Objekt iId
	 */
	public void setId(String iId) {
		this.iId = iId;
	}
	

}
