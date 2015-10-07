/**
 * 
 */
package de.pvws.swtools.util;

import de.pvws.swtools.swDataStructure.*;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;
//import java.io.*;

/**
 * @author PV KT
 *
 */
public class ImportDWArticleCat extends DefaultHandler {
	private LinkedList<SWArticle> llSWArticle;
	private LinkedList<SWArticleDetail> llSWArticleDetail;
	private SWArticle swa;
	private SWArticleDetail swad;
	private String strEValue;
	private Attributes aAttribute;
	private Boolean bIsMaster;
	private Boolean bIsDESet;
	private SWConfiguratorGroup swcgConficGroup;
	private SWConfiguratorOption swcoConfigOption;

	
	/**
	 * 
	 */
	public ImportDWArticleCat () {
		this.llSWArticle = new LinkedList<SWArticle>();
		this.llSWArticleDetail = new LinkedList<SWArticleDetail>();
	}

	/**
	 * 
	 */
	public ImportDWArticleCat (LinkedList<SWArticle> llSWArticle) {
		this.llSWArticle = llSWArticle;
		this.llSWArticle.clear();
		this.llSWArticleDetail = new LinkedList<SWArticleDetail>();
	}
	
	/**
	 * 
	 * @param path
	 */
	public void doImport (String path) {
		
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setNamespaceAware(true);
			SAXParser sp = spf.newSAXParser();
			
			XMLReader xr = sp.getXMLReader();
			xr.setContentHandler(this);
			xr.parse(path);
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	public void startDocument () throws SAXException {
		System.out.println("Beginn des XML-Dokumentes");

	}
	
	@Override
	public void endDocument () throws SAXException {
		System.out.println("Ende des XML-Dokumentes");
		System.out.println ("Sortieren der Datens�tze");
		this.sortArticleData();
		System.out.println("Sortieren beendet");
	}
	
	@Override
	public void startElement (String namespaceURI,
								String localeName,
								String qName,
								Attributes atts) throws SAXException {
		this.aAttribute = atts;
		
		// Logging
/*		System.out.println("Beginn des Elementes " + localeName);
		if (localeName == "catalog")
			System.out.println("\tNamespace : " + namespaceURI);
		for (int i = 0; i < atts.getLength(); i++) {
			System.out.println ("\tAttribute " + atts.getQName(i) + " : " + atts.getValue(i));
		}
*/		
		// Element-Attribute �bernehmen
		switch (localeName) {
		case "product":
			swa = new SWArticle();
			swad = new SWArticleDetail();
			this.bIsMaster = true;
			for (int i = 0; i < atts.getLength(); i++) {
				switch (atts.getQName(i)) {
				case "product-id":
					swa.setArticleNumber(atts.getValue(i));
					swad.setNumber(atts.getValue(i));
					break;
				} // switch (attribute)
			} // for all attributes
			break;
		case "ean":
			break;
		case "min-order-quantity":
			break;
		case "step-quantity":
			break;
		case "display-name":
			break;
		case "long-description":
			break;
		case "online-flag":
			break;
		case "tax-class-id":
			break;
		case "page-title":
			break;
		case "page-keywords":
			break;
		case "custom-attribute":
			break;
		case "variation-attribute":
			for (int i = 0; i < atts.getLength(); i++) {
				switch (atts.getQName(i)) {
				case "attribute-id":
					this.swcgConficGroup = new SWConfiguratorGroup(atts.getValue(i));
					break;
				} // switch (attribute)
			} // for all attributes
			break;
		case "variation-attribute-value":
			for (int i = 0; i < atts.getLength(); i++) {
				switch (atts.getQName(i)) {
				case "value":
//					this.swcoConfigOption = new SWConfiguratorOption(this.swcgConficGroup.getiSwId(), atts.getValue(i).substring(3), Integer.parseInt(atts.getValue(i).substring(0, 2)));
					this.swcoConfigOption = new SWConfiguratorOption(this.swcgConficGroup.getiSwId());
					this.bIsDESet = false;
					break;
				} // switch (attribute)
			} // for all attributes
			break;
		case "display-value":
			break;
		} // switch (localeName)
		
	}
	
	@Override
	public void endElement (String namespaceURI,
							String localeName,
							String qName) throws SAXException {
		String strAName;
		String strAValue;
		
//		System.out.println("Ende des Elementes " + localeName);

		// Element-Werte �bernehmen
		switch (localeName) {
			case "product":
				if (this.bIsMaster) { // Master -> swad into swa & swa into LinkedList
					swa.setMainDetail(swad);
					this.llSWArticle.add(swa);
				}
				else { // Variant -> persist swad; connect with master after import completion
					this.llSWArticleDetail.add(swad);
				}
				break;
			case "ean":
				if (this.strEValue != null && !this.strEValue.equals("")) {
					this.swad.setEan(this.strEValue);
				}
				break;
			case "min-order-quantity":
				if (this.strEValue != null && !this.strEValue.equals("")) {
					this.swad.setMinPurchase(Integer.parseInt(this.strEValue));
				}
				break;
			case "step-quantity":
				if (this.strEValue != null && !this.strEValue.equals("")) {
					this.swad.setPurchaseSteps(Integer.parseInt(this.strEValue));
				}
				break;
			case "display-name":
				for (int i = 0; i < this.aAttribute.getLength(); i++) {
					strAName = this.aAttribute.getQName(i);
					switch (strAName) {
					case "xml:lang":
					case "lang":
						strAValue = this.aAttribute.getValue(i);
						if (strAValue.equals("de") && !this.strEValue.equals("")) {
							this.swa.setName(this.strEValue);
						}
						break;
					} // switch (attribute)
				} // for all attributes
				break;
			case "long-description":
				for (int i = 0; i < this.aAttribute.getLength(); i++) {
					strAName = this.aAttribute.getQName(i);
					switch (strAName) {
					case "xml:lang":
					case "lang":
						strAValue = this.aAttribute.getValue(i);
						if (strAValue.equals("de")) {
							this.swad.setDescriptionLong(this.strEValue);
						}
						break;
					} // switch (attribute)
				} // for all attributes
				break;
			case "online-flag":
				if (this.strEValue.equals("true")) {
					this.swa.setActive(true);
					this.swad.setActive(true);
				} 
				else {
					this.swa.setActive(false);
					this.swad.setActive(false);
				}
				break;
			case "tax-class-id":
				if (this.strEValue != null && !this.strEValue.equals("")) {
					this.swa.setSwTax(new SWTax (this.strEValue));
					this.swad.setTaxClass(this.strEValue);
				}
				break;
			case "page-title":
				if (this.strEValue != null && !this.strEValue.equals("")) {
					this.swa.setMetaTitle(this.strEValue);
				}
				break;
			case "page-keywords":
				if (this.strEValue != null && !this.strEValue.equals("")) {
					this.swa.setKeywords(this.strEValue);
				}
				break;
			case "custom-attribute":
				for (int i = 0; i < this.aAttribute.getLength(); i++) {
					strAName = this.aAttribute.getQName(i);
					switch (strAName) {
					case "attribute-id":
						strAValue = this.aAttribute.getValue(i);
						switch (strAValue) {
						case "styleNumber": // Master of this Variant
							this.bIsMaster = false;
							if (this.strEValue != null && !this.strEValue.equals("")) {
								this.swad.setStylenumber(this.strEValue);
							}
							break;
						case "color":
							this.swad.getAttribute().setAttr1(strAValue + "|" + strEValue);
							break;
						case "colorCode":
							this.swad.getAttribute().setAttr2(strAValue + "|" + strEValue);
							break;
						case "searchColorId":
							this.swad.getAttribute().setAttr3(strAValue + "|" + strEValue);
							break;
						case "size":
							this.swad.getAttribute().setAttr4(strAValue + "|" + strEValue);
							break;
						} // switch (attribute-Value)
						break;
					} // switch (attribute)
				} // for all Element-Attributes
				break; // custom-attribute
			case "variation-attribute":
				break;
			case "variation-attribute-value":
				this.swad.addConfiguratorOptions(swcoConfigOption);
				break;
			case "display-value":
				for (int i = 0; i < this.aAttribute.getLength(); i++) {
					strAName = this.aAttribute.getQName(i);
					switch (strAName) {
					case "xml:lang":
					case "lang":
						strAValue = this.aAttribute.getValue(i);
						if (strAValue.equals("de")) {
							this.swcoConfigOption.setName(this.strEValue);
						}
						if (strAValue.equals("x-default") && !this.bIsDESet) { // just set Value if "de"-value isn't set yet
							this.swcoConfigOption.setName(this.strEValue);
						}
						break;
					} // switch (attribute)
				} // for all attributes
				break;

		} // switch (localeName)
	}
	
	@Override
	public void characters (char[] ch,
							int s,
							int l) throws SAXException {
		String v = String.valueOf(ch).substring(s, s+l).trim();
		this.strEValue = v;
		if (v.length() > 0) {
//			System.out.println ("	Wert :" + v);
		}
	} // characters
	
	/**
	 * Sorts the ArticleDetail DataSets into the regarding Article DataSets
	 */
	private void sortArticleData () {
		Iterator<SWArticleDetail> i;
		Iterator<SWArticle> ii;
		String parent;
		SWArticleDetail swad;
		SWArticle swa;
		Hashtable<String,SWArticle> htSWA;

		// Aufbau Hashtable <Stylenumber, swa>
		htSWA = new Hashtable<String,SWArticle>();
		ii = this.llSWArticle.iterator();
		while (ii.hasNext()) {
			swa = ii.next();
			htSWA.put(swa.getArticleNumber(), swa);
		}

		i = this.llSWArticleDetail.iterator();
		while (i.hasNext()) {
			swad = i.next();
			parent = swad.getStylenumber();
			
			(htSWA.get(parent)).addVariant(swad);

			// Extrem inperformant!!! mit Hashtable arbeiten !!!
//			ii = this.llSWArticle.iterator();
//			while (ii.hasNext()) {
//				swa = ii.next();
//				if (swa.getArticleNumber().equals(parent)) {
//					swa.addVariant(swad);
//				}
//			}
			
			
		}
	}
}