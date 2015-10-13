/**
 * 
 */
package de.pvws.swtools.swDataStructure;

/**
 * 1 : size / Größe
 * 2 : color / Farbe
 * 
 * @author PV KT
 *
 */
public class SWConfiguratorGroup {
	private int iSwId;		// PK
	private String strDescription;
	private String strName;
	private int iPosition;
	
	/**
	 * 
	 */
	public SWConfiguratorGroup () {
		
	}

	/**
	 * 
	 * @param strVAttribute
	 */
	public SWConfiguratorGroup (String strVAttribute) {
		if (strVAttribute.equals("size")) {
			this.iSwId = 1;
			this.strName = "Größe";
			this.iPosition = 1;
		}
		if (strVAttribute.equals("color")) {
			this.iSwId = 2;
			this.strName = "Farbe";
			this.iPosition = 2;
		}
		
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
	 * @return strDescription
	 */
	public String getDescription() {
		return strDescription;
	}

	/**
	 * @param strDescription das zu setzende Objekt strDescription
	 */
	public void setDescription(String strDescription) {
		this.strDescription = strDescription;
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
	 * @return iPosition
	 */
	public int getPosition() {
		return iPosition;
	}

	/**
	 * @param iPosition das zu setzende Objekt iPosition
	 */
	public void setPosition(int iPosition) {
		this.iPosition = iPosition;
	}

}
