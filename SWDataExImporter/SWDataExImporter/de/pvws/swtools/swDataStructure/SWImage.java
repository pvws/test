/**
 * 
 */
package de.pvws.swtools.swDataStructure;

/**
 * @author PV KT
 *
 */
public class SWImage {
	private int swId;					// PK
	private int swArticleId;			// PK
	private int swArticleDetailId;		// PK
	private String description;
	private String path;
	private int main;
	private int position;
	private int width;
	private int height;
	private String relation;
	private String extension;
	private int parentId;
	private int mediaId;

	/**
	 * 
	 */
	public SWImage () {
		
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
	 * @return swArticleId
	 */
	public int getSwArticleId() {
		return swArticleId;
	}

	/**
	 * @param swArticleId das zu setzende Objekt swArticleId
	 */
	public void setSwArticleId(int swArticleId) {
		this.swArticleId = swArticleId;
	}

	/**
	 * @return swArticleDetailId
	 */
	public int getSwArticleDetailId() {
		return swArticleDetailId;
	}

	/**
	 * @param swArticleDetailId das zu setzende Objekt swArticleDetailId
	 */
	public void setSwArticleDetailId(int swArticleDetailId) {
		this.swArticleDetailId = swArticleDetailId;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description das zu setzende Objekt description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path das zu setzende Objekt path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return main
	 */
	public int getMain() {
		return main;
	}

	/**
	 * @param main das zu setzende Objekt main
	 */
	public void setMain(int main) {
		this.main = main;
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
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width das zu setzende Objekt width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height das zu setzende Objekt height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return relation
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * @param relation das zu setzende Objekt relation
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}

	/**
	 * @return extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension das zu setzende Objekt extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId das zu setzende Objekt parentId
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return mediaId
	 */
	public int getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId das zu setzende Objekt mediaId
	 */
	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}
	
	
}
