/**
 * 
 */
package de.pvws.swtools.util.Json;

import de.pvws.swtools.swDataStructure.*;

import java.util.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
//import javax.json.JsonWriter;

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
			job.add("description", this.swArticle.getDescription());
			job.add("descriptionLong", this.swArticle.getDescriptionLong());
			job.add("images", this.createImagesArray());
			// have to exists only if there are Variants! 
			if (this.swArticle.hasVariants())
				job.add("configuratorSet", this.createConfiguratorSetObject());
			
			job.add("variants", this.createVariantsArray());
			
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
			// TODO: if no Object then no JSON Content
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
			job.add("configuratorOptions", this.createConfigOptionsArray(swad));
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
		while (iSwPrice.hasNext()) {
			swPrice = iSwPrice.next();
			job = Json.createObjectBuilder();
			job.add("from", swPrice.getFrom());
			job.add("price", swPrice.getPrice());
			jb = job.build();
			jab.add(jb);
		}
		
		ja = jab.build();
		
		return ja;		
	} // createPriceArray

	private JsonObject createConfiguratorSetObject (){
		JsonObjectBuilder job;
		JsonObject jb;
		
		job = Json.createObjectBuilder();

	// Name
		if (this.swArticle.getConfiguratorSet().getStrName() != null && this.swArticle.getConfiguratorSet().getStrName() != "")
			job.add("name", this.swArticle.getConfiguratorSet().getStrName());
		else
			job.add("name", "Fashion");
		// Groups-Array
		job.add("groups", this.createConfiguratorGroupsArray());
			
		jb = job.build();
		
		return jb;
	} // createConfiguratorSetObject

	private JsonArray createConfiguratorGroupsArray () {
		JsonObjectBuilder job;
		JsonObject jb;
		JsonArrayBuilder jab;
		JsonArray ja;
		Iterator<SWConfiguratorGroup> iSwcg;
		SWConfiguratorGroup swcg;
		
		jab = Json.createArrayBuilder();
		
		// create ConfiguratorGroup Array
		iSwcg = this.swArticle.getConfiguratorSet().getCgGroups().iterator();
		while (iSwcg.hasNext()) {
			swcg = iSwcg.next();
			job = Json.createObjectBuilder();
			job.add("id", swcg.getSwId());
			job.add("name", swcg.getName());
			job.add("options", this.createConfigOptionArrayByGroupName(swcg.getName()));
			jb = job.build();
			jab.add(jb);
		}
		
		ja = jab.build();
		
		return ja;		
	} // createConfiguratorGroupsArray

	private JsonArray createVariantsArray () {
		JsonArrayBuilder jab;
		JsonArray ja;
		Iterator<SWArticleDetail> iSwad;
		SWArticleDetail swad;
		
		jab = Json.createArrayBuilder();
		
		// create ConfiguratorGroup Array
		iSwad = this.swArticle.getVariants().iterator();
		while (iSwad.hasNext()) {
			swad = iSwad.next();
			jab.add(this.createArticleDetailObject(swad));
		}
		
		ja = jab.build();
		
		return ja;		
	} // createVariantsArray

	private JsonArray createConfigOptionsArray (SWArticleDetail swad) {
		JsonObjectBuilder job;
		JsonObject jb;
		JsonArrayBuilder jab;
		JsonArray ja;
		Iterator<SWConfiguratorOption> iSwco;
		SWConfiguratorOption swco;
		
		jab = Json.createArrayBuilder();
		
		// create ConfiguratorGroup Array
		iSwco = swad.getConfiguratorOptions().iterator();
		while (iSwco.hasNext()) {
			swco = iSwco.next();
			job = Json.createObjectBuilder();
			job.add("name", swco.getName());
			job.add("position", swco.getPosition());
			job.add("groupId", swco.getSwConfiguratorGroup().getSwId());

			jb = job.build();
			
			jab.add(jb);
		}
		
		ja = jab.build();
		
		return ja;		
	} // createConfigOptionsArray

	private JsonArray createConfigOptionArrayByGroupName (String name) {
		JsonObjectBuilder job;
		JsonObject jb;
		JsonArrayBuilder jab;
		JsonArray ja;
		Iterator<SWConfiguratorOption> iSwco;
		Iterator<SWArticleDetail> iSwad;
		SWConfiguratorOption swco;
		SWArticleDetail swad;
		LinkedList<String> llOName;
		
		llOName = new LinkedList<String>();
		jab = Json.createArrayBuilder();

		// create ConfigGroupArryByName for MainDetail Variant
		swad = this.swArticle.getMainDetail();
		iSwco = swad.getConfiguratorOptions().iterator();
		while (iSwco.hasNext()) {
			swco = iSwco.next();
			if (swco.getSwConfiguratorGroup().getName().equals(name)) {
				if (!llOName.contains(swco.getName())) { 
					job = Json.createObjectBuilder();
					job.add("name", swco.getName());
					job.add("position", swco.getPosition());
					jb = job.build();
					jab.add(jb);
					llOName.add(swco.getName());
				}
			}
		}
		// create ConfigGroupArryByName for Variants Variant
		iSwad = this.swArticle.getVariants().iterator();
		while (iSwad.hasNext()) {
			swad = iSwad.next();
			iSwco = swad.getConfiguratorOptions().iterator();
			while (iSwco.hasNext()) {
				swco = iSwco.next();
				if (swco.getSwConfiguratorGroup().getName().equals(name)) {
					if (!llOName.contains(swco.getName())) { 
						job = Json.createObjectBuilder();
						job.add("name", swco.getName());
						job.add("position", swco.getPosition());
						jb = job.build();
						jab.add(jb);
						llOName.add(swco.getName());
					}
				}
			}
		}
		
		ja = jab.build();
		
		return ja;
	} // createConfigOptionsArrayByGroupName

	private JsonArray createImagesArray () {
		JsonObjectBuilder job;
		JsonObject jb;
		JsonArrayBuilder jab;
		JsonArray ja;
		Iterator<SWImage> iSwi;
		SWImage swi;
		
		jab = Json.createArrayBuilder();
		
		// create ConfiguratorGroup Array
		iSwi = this.swArticle.getImages().iterator();
		while (iSwi.hasNext()) {
			swi = iSwi.next();
			job = Json.createObjectBuilder();
			job.add("mediaId", swi.getMediaId());
			job.add("path", swi.getPath());
			job.add("options", this.createImageOptionArray(swi));

			jb = job.build();
			
			jab.add(jb);
		}
		
		ja = jab.build();
		
		return ja;		
	} // createImagesArray

	private JsonArray createImageOptionArray (SWImage swi) {
		JsonObjectBuilder job;
		JsonObject jb;
		JsonArrayBuilder jab;
		JsonArray ja;
		Iterator<SWArticleDetail> iSwad;
		Iterator<SWConfiguratorOption> iSwco;
		SWArticleDetail swad;
		SWConfiguratorOption swco;
		LinkedList<String> llOName;
		
		llOName = new LinkedList<String>();
		jab = Json.createArrayBuilder();
		
		// create ImageOptions Array for MainDetail
		swad = this.swArticle.getMainDetail();
		iSwco = swad.getConfiguratorOptions().iterator();
		while (iSwco.hasNext()) {
			swco = iSwco.next();
			if (swco.getSwConfiguratorGroup().getName().equals("Farbe"))
				if (swco.getCode() == swi.getColorCode()) {
					if (!llOName.contains(swco.getName())) {
						job = Json.createObjectBuilder();
						job.add("name", swco.getName());
	
						jb = job.build();
						
						jab.add(jb);
						llOName.add(swco.getName());
					}
				}
		}
		
		// create ImageOptions Array for Variants
		iSwad = this.swArticle.getVariants("Farbe", swi.getColorCode()).iterator();
		while (iSwad.hasNext()) {
			swad = iSwad.next();
			iSwco = swad.getConfiguratorOptions().iterator();
			while (iSwco.hasNext()) {
				swco = iSwco.next();
				if (swco.getSwConfiguratorGroup().getName().equals("Farbe"))
					if (swco.getCode().equals(swi.getColorCode())) {
						if (!llOName.contains(swco.getName())) {
							job = Json.createObjectBuilder();
							job.add("name", swco.getName());
		
							jb = job.build();
							
							jab.add(jb);
							llOName.add(swco.getName());
						}
					}
			}
		}
		
		ja = jab.build();
		
		return ja;		
	} // createImageOptionsArray

}
