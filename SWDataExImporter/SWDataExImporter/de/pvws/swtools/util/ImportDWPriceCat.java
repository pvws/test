/**
 * 
 */
package de.pvws.swtools.util;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import de.pvws.swtools.swDataStructure.SWArticle;
import de.pvws.swtools.swDataStructure.SWArticleDetail;
import de.pvws.swtools.swDataStructure.SWPrice;

/**
 * 
 * @author mfehr
 *
 */
public class ImportDWPriceCat extends DefaultHandler {
	private LinkedList<SWPrice> llSWPrice;
	private LinkedList<SWArticle> llSWArticle;
	private String strEValue;
	private Attributes aAttribute;
	private Boolean bIsSale;
	private SWPrice swp;

	
	/**
	 * 
	 */
	public ImportDWPriceCat () {
		this.llSWPrice = new LinkedList<SWPrice>();
		this.bIsSale = false;
	}

	/**
	 * 
	 */
	public ImportDWPriceCat (LinkedList<SWArticle> llSWArticle) {
		this.llSWPrice = new LinkedList<SWPrice>();
		this.llSWArticle = llSWArticle;
		this.bIsSale = false;
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
		System.out.println("Beginn Zuweisung Preise zu Artikel");
		this.sortPriceData();
		System.out.println("Ende Zuweisung Preise zu Artikel");
	}
	
	@Override
	public void startElement (String namespaceURI,
								String localeName,
								String qName,
								Attributes atts) throws SAXException {
		String strAValue;
		this.aAttribute = atts;
		
		// Logging
/*		System.out.println("Beginn des Elementes " + localeName);
		if (localeName == "pricebooks")
			System.out.println("\tNamespace : " + namespaceURI);
		for (int i = 0; i < atts.getLength(); i++) {
			System.out.println ("\tAttribute " + atts.getQName(i) + " : " + atts.getValue(i));
		}
*/		
		// Element-Attribute übernehmen
		switch (localeName) {
		case "header":
			for (int i = 0; i < atts.getLength(); i++) {
				switch (atts.getQName(i)) {
				case "pricebook-id":
					strAValue = atts.getValue(i);
					switch (strAValue) {
					case "de-list-price":
						this.bIsSale = false;
						break;
					case "de-sale-prices":
						this.bIsSale = true;
						break;
					} // switch (AttributeValue)
					break;
				} // switch (attribute)
			} // for all attributes
			break;
		case "price-table":
			for (int i = 0; i < atts.getLength(); i++) {
				switch (atts.getQName(i)) {
				case "product-id":
					swp = new SWPrice();
					swp.setArticleNumber(atts.getValue(i));
					break;
				} // switch (attribute)
			} // for all attributes
			break;
		case "amount":
			for (int i = 0; i < this.aAttribute.getLength(); i++) {
				switch (this.aAttribute.getQName(i)) {
				case "quantity":
					if (this.swp != null)
						this.swp.setFrom(this.aAttribute.getValue(i));
					break;
				} // switch (attribute)
			} // for all attributes
			break;
		} // switch (localeName)
		
	}
	
	@Override
	public void endElement (String namespaceURI,
							String localeName,
							String qName) throws SAXException {
//		String strAName;
//		String strAValue;
		
//		System.out.println("Ende des Elementes " + localeName);

		// Element-Werte übernehmen
		switch (localeName) {
			case "header":
				break;
			case "price-table":
				this.llSWPrice.add(this.swp);
				break;
			case "amount":
				if (this.swp != null) {
					this.swp.setPrice(Double.valueOf(this.strEValue), this.bIsSale);
/*					if (this.bIsSale) { // Sale Price
						this.swp.setPseudoPrice(this.swp.getPrice()); // List Price to Pseudo Price
						this.swp.setPrice(Double.valueOf(this.strEValue));
					}
					else { // List Price
						if (this.swp.getPrice() < 0 || this.swp.getPrice() > 99999.00)
							this.swp.setPrice(Double.valueOf(this.strEValue));
						else
							this.swp.setPseudoPrice(Double.valueOf(this.strEValue));
					}
*/				}
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
	
	private void sortPriceData () {
		Hashtable<String, SWPrice> htPrice;
		Iterator<SWPrice> iPrice;
		Iterator<SWArticle> iSwa;
		Iterator<SWArticleDetail> iSwad;
		SWPrice swp;
		SWArticle swa;
		SWArticleDetail swad;
		String ean;
		
		System.out.println("Anzahl PriceData : " + this.llSWPrice.size());
		// Hashtable Price <ArticleId, swPrice>
		htPrice = new Hashtable<String, SWPrice>();
		iPrice = this.llSWPrice.iterator();
		while (iPrice.hasNext()) {
			swp = iPrice.next();
			htPrice.put(swp.getArticleNumber(), swp);
		} // while iPrice.hasNext() 
		
		// Iteration over SWA & SWAD & connect with swp
		iSwa = this.llSWArticle.iterator();
		while (iSwa.hasNext()) {
			swa = iSwa.next();
			// Price Data to Main Detail
			if ((ean = swa.getMainDetail().getEan()) == null)
				continue;
			swp = htPrice.get(ean);
			if (swp != null)
				swa.getMainDetail().addPrice(swp);
			// Now for Variants
			iSwad = swa.getVariants().iterator();
			while (iSwad.hasNext()) {
				swad = iSwad.next();
				if ((ean = swad.getEan()) == null)
					continue;
				swp = htPrice.get(ean);
				if (swp != null) {
					swad.addPrice(swp);
				}
			} // while iSwad.hasNext()
		} // while iSwa.hasNext()
	} // sortPriceData
}