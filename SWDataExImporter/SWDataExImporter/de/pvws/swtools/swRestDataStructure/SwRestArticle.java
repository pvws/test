/**
 * 
 */
package de.pvws.swtools.swRestDataStructure;

import de.pvws.swtools.swDataStructure.*;

import java.util.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 * 
 * @author PV KT
 *
 */
public class SwRestArticle {
	private SWArticle swArticle;
	private String strJsonArticle;

	public SwRestArticle (SWArticle swa) {
		this.swArticle = swa;
	}
	
	public static String buildJsonArticle (SWArticle swa) {
		SwRestArticle ra = new SwRestArticle (swa);
		
		return ra.doBuild(swa);
	}
	
	public String doBuild (SWArticle swa) {
		this.strJsonArticle = "";
		
		this.SwaToJson();
		
		return this.strJsonArticle;
	}
	
	/**
	 * Manages the JSON-Creation to the given SWArticle
	 * 
	 * @return JSON Object (Complete SWArticle)
	 */
	private String SwaToJson () {
		JsonBuilderFactory jf;
		JsonObject joSwad;
		JsonObjectBuilder job;
		JsonArrayBuilder jab;
		
		String strJson = "";
		
		try {
			jf = Json.createBuilderFactory(null);
			job = jf.createObjectBuilder();
			jab = jf.createArrayBuilder();

			job.add("name", this.swArticle.getName());
			job.add("tax", this.createTaxObject());
			job.add("mainDetail", this.createMainDetailObject());
			
			
			joSwad = job.build();

			strJson = joSwad.toString();
		}
		catch (Exception e){
			System.out.println (e);
		}
		
		System.out.println(strJson);
		return strJson;
	}

	/**
	 * Creates the Article Detail-Object as JSON
	 * 
	 * @return JSON-Object (mainDetail)
	 */
	private JsonObject createArticleDetailObject () {
		JsonObjectBuilder job;
		JsonObject jb;
		SWTax swTax;
		
		job = Json.createObjectBuilder();
		
		// create Tax Object
		swTax = this.swArticle.getSwTax();
		if (swTax == null) {
			job.add("id", 1);
			job.add("tax", "19.0");
			job.add("name", "Standars");
		}
		else {
			job.add("id", swTax.getSwTaxId());
			job.add("tax", swTax.getSwTax());
			job.add("name", swTax.getSwName());
		}
		
		jb = job.build();
		
		return jb;		
	} // create ArticleDetail-Object as JSON

	/**
	 * Creates the mainDetail-Object as JSON
	 * 
	 * @return JSON-Object (mainDetail)
	 */
	private JsonObject createMainDetailObject () {
		// create Article Detail Object
		// return is already build!
		return this.createArticleDetailObject();		
	} // createMainDetailObject()

	/**
	 * Creates the Tax-Object as JSON
	 * 
	 * @return JSON-Object (tax)
	 */
	private JsonObject createTaxObject () {
		JsonObjectBuilder job;
		JsonObject jb;
		SWTax swTax;
		
		job = Json.createObjectBuilder();
		
		// create Tax Object
		swTax = this.swArticle.getSwTax();
		if (swTax == null) {
			job.add("id", 1);
			job.add("tax", "19.0");
			job.add("name", "Standars");
		}
		else {
			job.add("id", swTax.getSwTaxId());
			job.add("tax", swTax.getSwTax());
			job.add("name", swTax.getSwName());
		}
		
		jb = job.build();
		
		return jb;		
	} // createTaxObject

}
