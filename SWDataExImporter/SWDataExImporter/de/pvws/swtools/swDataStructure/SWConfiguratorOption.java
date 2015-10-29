/**
 * 
 */
package de.pvws.swtools.swDataStructure;

/**
 * @author PV KT
 *
 */
public class SWConfiguratorOption {
	private int swId;		// PK
	private int swGroupId;	// PK / Configurator Group
	private String name;
	private int position;
	private SWConfiguratorGroup swcgConfigGroup;
	private String strCode;

	/**
	 * 
	 * @param ConfigGroupId
	 */
	public SWConfiguratorOption (int ConfigGroupId) {
		this.swGroupId = ConfigGroupId;
	}

	/**
	 * 
	 * @param swcg
	 */
	public SWConfiguratorOption (SWConfiguratorGroup swcg) {
		this.swcgConfigGroup = swcg;
	}

	/**
	 * 
	 * @param ConfigGroupId
	 * @param value
	 */
	public SWConfiguratorOption (int ConfigGroupId, String value) {
		this.swGroupId = ConfigGroupId;
		this.name = value;
	}

	/**
	 * 
	 * @param ConfigGroupId
	 * @param value
	 * @param sortNumber
	 */
	public SWConfiguratorOption (int ConfigGroupId, String value, int sortNumber) {
		this.swGroupId = ConfigGroupId;
		this.name = value;
		this.position = sortNumber;
	}

	
	/**
	 * @return swId
	 */
	public int getSwId() {
		return swId;
	}

	/**
	 * @param swId das zu setzende Objekt swId
	 */
	public void setSwId(int swId) {
		this.swId = swId;
	}

	/**
	 * @return swGroupId
	 */
	public int getSwGroupId() {
		return this.swcgConfigGroup.getSwId();
	}
	
	/**
	 * 
	 * @return
	 */
	public SWConfiguratorGroup getSwConfiguratorGroup () {
		return this.swcgConfigGroup;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name das zu setzende Objekt name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position das zu setzende Objekt position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * 
	 * @return Code of the Option if set, empty String else
	 */
	public String getCode () {
		if (this.strCode != null)
			return this.strCode;
		else
			return "";
	}
	
	/**
	 * Persist a code regarding to the Option-Value, e.g. Color Code
	 * 
	 * @param code
	 */
	public void setCode (String code) {
		this.strCode = code;
		
		if (this.position <= 0) {
			try {
				this.position = Integer.valueOf(code);
			}
			catch (Exception e) {
				this.position = 0;
			}
		}
	}
}
