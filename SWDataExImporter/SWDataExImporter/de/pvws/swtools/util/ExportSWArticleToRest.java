/**
 * 
 */
package de.pvws.swtools.util;

import de.pvws.swtools.swDataStructure.*;
import de.pvws.swtools.util.REST.*;

import java.util.*;

import javax.json.*;
import javax.json.spi.*;


/**
 * 
 * @author PV KT
 *
 */
public class ExportSWArticleToRest {
	public static final String MODE_REPLACE = "REPLACE";
	public static final String MODE_MERGE = "MERGE";
	public static final String MODE_DELETE = "DELETE";

	private LinkedList<SWArticle> llSwaNew;
	private LinkedList<SWArticle> llSwaOld;
	private LinkedList<SWArticle> llSwaExec;
	
	private String strSWDataJsonOld;
	
	private final String strMode;
	
	private Boolean bSuccess;
	

	/**
	 * 
	 */
	public ExportSWArticleToRest () {
		this.bSuccess = false;
		this.strMode = ExportSWArticleToRest.MODE_MERGE;
	}

	/**
	 * 
	 * @param llswa
	 */
	public ExportSWArticleToRest (LinkedList<SWArticle> llswa) {
		this.bSuccess = false;
		this.llSwaNew = llswa;
		this.strMode = ExportSWArticleToRest.MODE_MERGE;
	}
	
	/**
	 * 
	 * @param llswa
	 * @param mode
	 */
	public ExportSWArticleToRest (LinkedList<SWArticle> llswa, String mode) {
		this.bSuccess = false;
		this.llSwaNew = llswa;
		switch (mode) {
		case "DELETE":
		case "Delete":
		case "delete":
			this.strMode = ExportSWArticleToRest.MODE_DELETE;
			break;
		case "REPLACE":
		case "Replace":
		case "replace":
			this.strMode = ExportSWArticleToRest.MODE_REPLACE;	
			break;
		default:
			this.strMode = ExportSWArticleToRest.MODE_MERGE;
		}
	}

	/**
	 * 
	 * @param llswa Article List as SWArticle-Objects
	 * @param mode "REPLACE" / "MERGE" (default) / "DELETE"
	 * @return true if successful, false else 
	 */
	public static Boolean doExport (LinkedList<SWArticle> llswa, String mode) {
		ExportSWArticleToRest expR;
		
		if (llswa == null)
			return false;
		
		expR = new ExportSWArticleToRest(llswa, mode);
		
		expR.doPull();
		expR.doCompute();
		expR.doPush();
		
		return expR.getbSuccess();
	} // doExport
	
	/**
	 * Pulls the current Data from the Shop into the llSwaOld
	 */
	private void doPull() {
		if (this.llSwaNew == null) {
			this.bSuccess = false;
			return;
		}
		
		this.llSwaOld = new LinkedList<SWArticle>();
		
//		this.strSWDataJsonOld = PullArticleFromSW.doPull();
	} // doPull()
	
	/**
	 * Pushes the Data from llSwaExec to the Shop
	 * xx - for Create
	 * xx - for Update
	 * xx - for Delete
	 */
	private void doPush() {
		Iterator<SWArticle> itSwa;
		Iterator<SWArticleDetail> itSwad;
		SWArticle swa;
		SWArticleDetail swad;
		
		if (this.llSwaNew == null || this.llSwaExec == null) {
			this.bSuccess = false;
			return;
		}
		
		itSwa = this.llSwaExec.iterator();
		while (itSwa.hasNext()) {
			swa = itSwa.next();
			itSwad = swa.getVariants().iterator();
			while (itSwad.hasNext()) {
				swad = itSwad.next();
				this.SwadToJson(swad, swa);
			} // whileitSwas.hasNext()
		} // while itSwa.hasNext()
		
	} // doPush()
	
	/**
	 * Calculates the llSwaExec from llSwaNew and llSwaOld regarding the strMode 
	 */
	private void doCompute() {
		if (this.llSwaNew == null || this.llSwaOld == null) {
			this.bSuccess = false;
			return;
		}
		
		this.llSwaExec = new LinkedList<SWArticle>();
		
		// Zusammenführen von New und Old
		this.llSwaExec = this.llSwaNew;
		
	} //doCompute()

	/**
	 * 
	 * @param swad
	 * @return The SWAD translated to JSON as String
	 */
	private String SwadToJson (SWArticleDetail swad, SWArticle swa) {
		JsonWriter jw;
		JsonBuilderFactory jf;
		JsonObject jo;
		JsonArray ja;
		JsonArray jaSwa;
		
		String strJson = "";
		
		try {
			jf = Json.createBuilderFactory(null);
			//jaSwa = jf.createArrayBuilder().build();

			jo = Json.createObjectBuilder().add("name", "Name")
					.add("name", swa.getName())
					.build();
			
//			jaSwa = Json.createArrayBuilder().add(jo).build();
//			ja.add(jf.createObjectBuilder().add("name", swa.getName()).build());
			jaSwa = Json.createArrayBuilder().add(jo).build();

			strJson = jaSwa.toString();
		}
		catch (Exception e){
			System.out.println (e);
		}
		
		System.out.println(strJson);
		return strJson;
	}
	
	
	/**
	 * @return bSuccess
	 */
	private Boolean getbSuccess() {
		return bSuccess;
	}

	/**
	 * @param bSuccess das zu setzende Objekt bSuccess
	 */
	private void setbSuccess(Boolean bSuccess) {
		this.bSuccess = bSuccess;
	}
}
