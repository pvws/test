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
public class SwArticleToJson {
	private SWArticle swArticle;
	private String strJsonArticle;

	public SwArticleToJson (SWArticle swa) {
		this.swArticle = swa;
	}
	
	public static String buildJsonArticle (SWArticle swa) {
		SwArticleToJson ra = new SwArticleToJson (swa);
		
		return ra.doBuild(swa);
	}
	
	public String doBuild (SWArticle swa) {
		this.strJsonArticle = "";
		
		this.strJsonArticle = this.SwaToJson();
		
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
		JsonArray ja;
		JsonObjectBuilder job;
		JsonArrayBuilder jab;
		
		String strJson = "";
		
		try {
			jf = Json.createBuilderFactory(null);
			job = jf.createObjectBuilder();
			jab = jf.createArrayBuilder();

			job.add("name", this.swArticle.getName());
			//job.add("taxId", "1");
			job.add("tax", this.createTaxObject());
			job.add("mainDetail", this.createMainDetailObject());
			job.add("supplier", this.createSupplierObject());
						
			joSwad = job.build();
			
			jab.add(joSwad);
			ja = jab.build();

			strJson = joSwad.toString();
			System.out.println(strJson);
		}
		catch (Exception e){
			System.out.println (e);
		}
		
		return strJson;
	}

	/**
	 * Creates the Article Detail-Object as JSON
	 * 
	 * @return JSON-Object (mainDetail)
	 */
	private JsonObject createArticleDetailObject (SWArticleDetail swad) {
		JsonObjectBuilder job;
		JsonObject jb;
		SWTax swTax;
		
		job = Json.createObjectBuilder();
		
		// create ArticleDetail Object
		if (swad == null) {
			job.add("number", 1);
			job.add("supplierNumber", "1");
			job.add("additionalText", "");
			job.add("weight", "");
			job.add("width", "");
			job.add("length", "");
			job.add("height", "");
			job.add("ean", "");
			job.add("purchaseUnit", "");
			job.add("descriptionLong", "");
			job.add("referenceUnit", "");
			job.add("packUnit", "");
			job.add("shippingTime", "");
			//job.add("prices", "[\"price\":99.99]");
			//job.add("configuratorOptions", "[]");
			//job.add("attribute", "[]");
			//job.add("id", "");
			//job.add("articleId", "");
			//job.add("unitId", "");
			job.add("kind", "");
			job.add("inStock", "75");
			job.add("position", "");
			job.add("minPurchase", "1");
			job.add("purchaseSteps", "1");
			job.add("maxPurchase", "");
			job.add("releaseDate", "");
			job.add("active", "0");
			job.add("shippingFree", "");
		}
		else {
			job.add("number", swad.getSWNumber());
			job.add("supplierNumber", swad.getSupplierNumber());
			job.add("additionalText", ""); 
			if (swad.getAdditionalText() != null) swad.getAdditionalText(); else String.valueOf("");
			job.add("weight", swad.getWeight());
			job.add("width", swad.getWidth());
			job.add("length", swad.getLen());
			job.add("height", swad.getHeight());
			job.add("ean", swad.getEan());
			job.add("purchaseUnit", swad.getPurchaseUnit());
			job.add("descriptionLong", swad.getDescriptionLong());
			job.add("referenceUnit", swad.getReferenceUnit());
			job.add("packUnit", swad.getPackUnit());
			job.add("shippingTime", swad.getShippingTime());
			job.add("prices", this.createPriceArray(swad));
			//job.add("configuratorOptions", "[]");
			//job.add("attribute", "[]");
			//job.add("id", swad.getSwId());
			//job.add("articleId", swad.getSwArticleId());
			//job.add("unitId", swad.getSwUnitId());
			job.add("kind", swad.getKind());
			job.add("inStock", swad.getInStock());
			job.add("position", swad.getPosition());
			job.add("minPurchase", swad.getMinPurchase());
			job.add("purchaseSteps", swad.getPurchaseSteps());
			job.add("maxPurchase", swad.getMaxPurchase());
			job.add("releaseDate", swad.getReleaseDate());
			job.add("active", swad.getActive());
			job.add("shippingFree", swad.getShippingFree());
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
		SWArticleDetail swad;
		
		swad = this.swArticle.getMainDetail();
		
		// create Article Detail Object
		// return is already build!
		return this.createArticleDetailObject(swad);		
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
			//job.add("id", 1);
			job.add("tax", "19.0");
			job.add("name", "Standars");
		}
		else {
			//job.add("id", swTax.getSwTaxId());
			job.add("tax", swTax.getSwTax());
			job.add("name", swTax.getSwName());
		}
		
		jb = job.build();
		
		return jb;		
	} // createTaxObject

	/**
	 * Creates the Supplier-Object as JSON
	 * 
	 * @return JSON-Object (supplier)
	 */
	private JsonObject createSupplierObject () {
		JsonObjectBuilder job;
		JsonObject jb;
		SWSupplier swSupplier;
		
		job = Json.createObjectBuilder();
		
		// create Supplier Object
		swSupplier = this.swArticle.getSupplier();
		if (swSupplier == null) {
			//job.add("id", 1);
			job.add("name", "arvato");
			job.add("image", "");
			job.add("link", "");
			job.add("description", "");
			job.add("metaTitle", "");
			job.add("metaDescription", "");
			job.add("metaKeywords", "");
		}
		else {
			//job.add("id", swSupplier.getSwId());
			job.add("name", swSupplier.getName());
			job.add("image", swSupplier.getImage());
			job.add("link", swSupplier.getLink());
			job.add("description", swSupplier.getDescription());
			job.add("metaTitle", swSupplier.getMetaTitle());
			job.add("metaDescription", swSupplier.getMetaDescription());
			job.add("metaKeywords", swSupplier.getMetaKeywords());
		}
		
		jb = job.build();
		
		return jb;		
	} // createSupplierObject

	/**
	 * 
	 * @param swad
	 * @return
	 */
	private JsonArray createPriceArray (SWArticleDetail swad) {
		JsonObjectBuilder job;
		JsonObject jb;
		JsonArrayBuilder jab;
		JsonArray ja;
		Iterator<SWPrice> iSwPrice;
		SWPrice swPrice;
		
		jab = Json.createArrayBuilder();
		
		// create Price Array
		iSwPrice = swad.getPrices().iterator();
		if (iSwPrice.hasNext()) {
			swPrice = iSwPrice.next();
			job = Json.createObjectBuilder();
			job.add("from", swPrice.getFrom());
			job.add("price", swPrice.getPrice());
			jb = job.build();
			jab.add(jb);
		}
		
		ja = jab.build();
		
		return ja;		
	} // createSupplierObject

}
