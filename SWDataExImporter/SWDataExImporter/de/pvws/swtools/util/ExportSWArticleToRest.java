/**
 * 
 */
package de.pvws.swtools.util;

import de.pvws.swtools.swDataStructure.*;
import de.pvws.swtools.swRestDataStructure.SwArticleToJson;
import de.pvws.swtools.util.REST.*;

import java.io.StringReader;
import java.util.*;

import javax.json.*;

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
		
		this.strSWDataJsonOld = PullArticleFromSW.doPull();
	} // doPull()
	
	/**
	 * Pushes the Data from llSwaExec to the Shop
	 * xx - for Create
	 * xx - for Update
	 * xx - for Delete
	 */
	private void doPush() {
		Iterator<SWArticle> itSwa;
		SWArticle swa;
		
		String strJsonPush;
		String strJsonReturn;

		JsonStructure js;
		// Exit if something is wrong
		if (this.llSwaNew == null || this.llSwaExec == null) {
			this.bSuccess = false;
			return;
		}
		
		// build JSON and push to Shop
		itSwa = this.llSwaExec.iterator();
		while (itSwa.hasNext()) {
			swa = itSwa.next();
			if (swa.getName() == null || swa.getName().equals("") || swa.getMainDetail().getPrice(1).getPrice() >= 99999.00)
				continue;
			strJsonPush = SwArticleToJson.buildJsonArticle(swa);

			strJsonReturn = PushArticleToSW.doPush(strJsonPush);
			
			// TODO: Response auswerten
			System.out.println(strJsonReturn);
			js = Json.createReader(new StringReader(strJsonReturn)).read();
			
			
		} // while itSwa.hasNext()
		
		
	} // doPush()
	
	/**
	 * Calculates the llSwaExec from llSwaNew and llSwaOld regarding the strMode 
	 */
	private void doCompute() {

		// Exit if something is wrong
		if (this.llSwaNew == null || this.llSwaOld == null) {
			this.bSuccess = false;
			return;
		}
		
		this.llSwaExec = new LinkedList<SWArticle>();
		
		// Zusammenführen von New und Old
		this.llSwaExec = this.llSwaNew;
		
	} //doCompute()
	
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
