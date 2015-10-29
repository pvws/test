/**
 * 
 */
package de.pvws.swtools.swDataStructure;

import java.util.Collection;
import java.util.Hashtable;

/**
 * 1 : standard
 * 
 * @author PV KT
 *
 */
public class SWConfiguratorSet {
	private int iSwId;		// PK
	private String strName;
	private Boolean bPublic;
	private int iType;
	private Hashtable<String, SWConfiguratorGroup> htSwcgGroup;		// array
	
	/**
	 * 
	 */
	public SWConfiguratorSet () {
		this.iSwId = 1;
		this.strName = "Fashion";
		this.bPublic = true;
		this.htSwcgGroup = new Hashtable<String, SWConfiguratorGroup>();
	}

	/**
	 * @return iSwId
	 */
	public int getiSwId() {
		return iSwId;
	}

	/**
	 * @param iSwId das zu setzende Objekt iSwId
	 */
	public void setiSwId(int iSwId) {
		this.iSwId = iSwId;
	}

	/**
	 * @return strName
	 */
	public String getStrName() {
		return strName;
	}

	/**
	 * @param strName das zu setzende Objekt strName
	 */
	public void setStrName(String strName) {
		this.strName = strName;
	}

	/**
	 * @return bPublic
	 */
	public Boolean getbPublic() {
		return bPublic;
	}

	/**
	 * @param bPublic das zu setzende Objekt bPublic
	 */
	public void setbPublic(Boolean bPublic) {
		this.bPublic = bPublic;
	}

	/**
	 * @return iType
	 */
	public int getiType() {
		return iType;
	}

	/**
	 * @param iType das zu setzende Objekt iType
	 */
	public void setiType(int iType) {
		this.iType = iType;
	}

	/**
	 * @return cgGroup
	 */
	public Collection<SWConfiguratorGroup> getCgGroups() {
		return this.htSwcgGroup.values();
	}

	/**
	 * @param cgGroup das zu setzende Objekt cgGroup
	 */
	public void addCgGroup(SWConfiguratorGroup cgGroup) {
			this.htSwcgGroup.put(cgGroup.getName(), cgGroup);
	}

}
