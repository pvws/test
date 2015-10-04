/**
 * 
 */
package de.pvws.swtools.swDataStructure;

/**
 * @author PV KT
 *
 */
public class SWPropertyValue {
	private int swId;		// PK
	private Double valueNumeric;
	private int position;
	private int optionId;
	private String value;
	
	/**
	 * 
	 */
	public SWPropertyValue () {
		
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
	 * @return valueNumeric
	 */
	public Double getValueNumeric() {
		return valueNumeric;
	}

	/**
	 * @param valueNumeric das zu setzende Objekt valueNumeric
	 */
	public void setValueNumeric(Double valueNumeric) {
		this.valueNumeric = valueNumeric;
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
	 * @return optionId
	 */
	public int getOptionId() {
		return optionId;
	}

	/**
	 * @param optionId das zu setzende Objekt optionId
	 */
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	/**
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value das zu setzende Objekt value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}
