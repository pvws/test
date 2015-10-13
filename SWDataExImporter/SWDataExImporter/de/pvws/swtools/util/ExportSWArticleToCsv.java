/**
 * 
 */
package de.pvws.swtools.util;

import de.pvws.swtools.swDataStructure.*;

import java.util.*;
import java.io.*;

/**
 * @author PV KT
 *
 */
public class ExportSWArticleToCsv {
	private String strPath;
	private String strFilename;
	private LinkedList<SWArticle> llSwa;
	private String strDate;
	
	private Boolean bOrderNumber;
	private Boolean bMainNumber;
	private Boolean bName;
	private Boolean bSupplier;
	private Boolean bTax;
	private Boolean bPrice;
	private Boolean bCategories;
	
	private Boolean bConfigOption;
	
	private Boolean bAttributes;
	
	/**
	 * 
	 */
	public ExportSWArticleToCsv () {
		this.setDateTime();
		this.setDataRange();
		this.strFilename = "swArticle_" + strDate + ".csv";
		this.strPath = "D://";
	}
	
	/**
	 * 
	 * @param swad
	 * @param path
	 * @param filename
	 */
	public ExportSWArticleToCsv (LinkedList<SWArticle> swa, String path, String filename) {
		this.setDataRange();
		this.llSwa = swa;
		this.strPath = path;
		this.strFilename = filename;
	}

	/**
	 * 
	 * @param swad LinkedList of ArticleData
	 * @param path Path without Filename
	 */
	public ExportSWArticleToCsv (LinkedList<SWArticle> swa, String path) {
		this.setDateTime();
		this.setDataRange();
		this.llSwa = swa;
		this.strPath = path;
		this.strFilename = "swArticle_" + strDate + ".csv";
	}
	
	private void setDataRange () {
		this.bOrderNumber = true;
		this.bMainNumber = true;
		this.bName = true;
		this.bSupplier = true;
		this.bTax = true;
		this.bPrice = true;
		this.bCategories = true;
		
		this.bConfigOption = true;
		
		this.bAttributes = true;
	}
	
	/**
	 * @return strPath
	 */
	public String getStrPath() {
		return strPath;
	}

	/**
	 * @param strPath das zu setzende Objekt strPath
	 */
	public void setStrPath(String strPath) {
		this.strPath = strPath;
	}

	/**
	 * @param llSwad das zu setzende Objekt llSwad
	 */
	public void setLlSwa(LinkedList<SWArticle> llSwa) {
		this.llSwa = llSwa;
	}

	/**
	 * 
	 * @param swad
	 * @param path
	 */
	public void doExport (LinkedList<SWArticle> swa, String path) {
		this.llSwa = swa;
		this.strPath = path;
		this.doExport();
	}

	/**
	 * 
	 * @param swad
	 */
	public void doExport (LinkedList<SWArticle> swa) {
		this.llSwa = swa;
		this.doExport();
	}

	/**
	 * 
	 * @param path
	 */
	public void doExport (String path) {
		this.strPath = path;
		this.doExport();
	}

	/**
	 * 
	 */
	public boolean doExport () {
		FileWriter fw;
		Iterator<SWArticle> iA;			// iterator over Article Data Set
		SWArticle swa;
		
		if (this.strFilename == null || this.strFilename.equals(""))
			this.strFilename = "swArticle_" + strDate + ".csv";
		
		try {
			System.out.println ("Schreiben von " + this.strFilename);
			// create File Object and so on
			File f = new File (this.strPath + "/" + this.strFilename);
			if (!f.exists())
				if (!f.createNewFile())
					return false;
			fw = new FileWriter (f); // (this.strPath + "/" + this.strFilename);

			// write content
			// write headline
			fw.write(this.getCsvHeadLine());
			fw.flush();
			
			// write data set
			iA = this.llSwa.iterator();
			while (iA.hasNext()) {
				swa = iA.next();
				// sort out objects with wrong Numbers
				if (swa.getMainDetail().getSWNumber().equals("ads_null"))
					continue;
				// sort out Objects with no Price Data
				if (swa.getMainDetail().getPrice(1).getPrice() > 99999.00)
					continue;
				fw.write(this.getCsvString(swa));
				fw.flush();
			} // while iA.hasNext()
			
			// finished
			// fw.flush();
			fw.close();

			System.out.println ("Schreiben von " + this.strFilename + " erfolgreich beendet");
		}
		catch (Exception e) {
			System.out.println ("Schreiben von " + this.strFilename + " fehlgeschlagen");
			System.out.println (e);
			return false;
		}

		return true;
	} // do export

	/**
	 * 
	 */
	private void setDateTime () {
		int iAdd = 0;
		GregorianCalendar date = new GregorianCalendar(TimeZone.getDefault(), Locale.getDefault(Locale.Category.FORMAT));// (new GregorianCalendar(GregorianValendar).getTime()).toString();
		if (date.get(Calendar.AM_PM) == Calendar.PM)
			iAdd = 12;
		strDate = Integer.toString(date.get(Calendar.YEAR));
		if (date.get(Calendar.MONTH) + 1 < 10)
			strDate += "0" + Integer.toString(date.get(Calendar.MONTH) + 1);
		else
			strDate += Integer.toString(date.get(Calendar.MONTH) + 1);
		if (date.get(Calendar.DAY_OF_MONTH) < 10)
			strDate += "0" + Integer.toString(date.get(Calendar.DAY_OF_MONTH));
		else
			strDate += Integer.toString(date.get(Calendar.DAY_OF_MONTH));
		if (date.get(Calendar.HOUR) + iAdd < 10)
			strDate += "0" + Integer.toString(date.get(Calendar.HOUR) + iAdd);
		else
			strDate += Integer.toString(date.get(Calendar.HOUR) + iAdd);
		if (date.get(Calendar.MINUTE) < 10)
			strDate += "0" + Integer.toString(date.get(Calendar.MINUTE));
		else
			strDate += Integer.toString(date.get(Calendar.MINUTE));
		if (date.get(Calendar.SECOND) < 10)
			strDate += "0" + Integer.toString(date.get(Calendar.SECOND));
		else
			strDate += Integer.toString(date.get(Calendar.SECOND));
	} // setDateTime
	
	/**
	 * 
	 * @param swa
	 * @return
	 */
	private String getCsvString (SWArticle swa) {
		String strCsv;
		Iterator<SWArticleDetail> iAD;	// iterator over ArticleDetail Data Set
		SWArticleDetail swad;

		strCsv = "";
		
		if (swa != null) {
			// Data Set ( Line of Main Detail
			swad = swa.getMainDetail();
			if (swad != null) {
				strCsv += this.getCsvSwad(swad, swa, true);
				strCsv += "\r\n";
			}

			// Data Sets / Lines of Variants
			iAD = swa.getVariants().iterator();
			while (iAD.hasNext()) {
				swad = iAD.next();
				if (swad.getPrice(1).getPrice() > 99999.0)
					continue;
				strCsv += this.getCsvSwad(swad, swa, false);
				strCsv += "\r\n";
			} // while iAD.hasNext
		}
		
		return strCsv;
	} // getCsvString
	
	/**
	 * Returns the SWAD as CSV - Line (Data Set) without closing Linefeed.
	 * 
	 * @param swad
	 * @return
	 */
	private String getCsvSwad (SWArticleDetail swad, SWArticle swa, Boolean isMainDetail) {
		String strLine = "";
		
		if (this.bOrderNumber)
			strLine += swad.getSWNumber();
		// Main Detail gets no main number but empty field
		if (this.bMainNumber && isMainDetail)
			strLine += ";";
		// Variant gets main number => SW Article Number of Main Detail
		if (this.bMainNumber && !isMainDetail)
			strLine += ";" + swa.getMainDetail().getSWNumber();
		if (this.bName)
			strLine += ";" + swa.getName();
		if (this.bSupplier)
			strLine += ";" + swa.getSupplier().getName();
		if (this.bTax)
			strLine += ";" + swa.getSwTax().getSwTax();
		if (this.bPrice)
			strLine += ";" + String.valueOf(swad.getPrice(1).getPrice());
		if (this.bCategories) {
			SWCategory swcCat;
			Iterator<SWCategory> iSwcCat;
			String str = ";";
			
			iSwcCat = swa.getCategories().iterator();
			while (iSwcCat.hasNext()) {
				swcCat = iSwcCat.next();
				str += swcCat.getId();
				str += "|";
			}
			if (str.length() > 2)
				strLine += str.substring(0, str.length()-1);
			else
				strLine += str;
		} // if categories

		if (this.bConfigOption) {
			strLine += ";" + "1"; // configuratorsetID, must be set, but without function
			strLine += ";" + ""; // configuratortype, not used yet
			// strLine += ";" + "configuratorOption"; // configuratorOption, separated by pipe
			Iterator<SWConfiguratorOption> i;
			SWConfiguratorOption swco;
			String str = ";";
			i = swad.getConfiguratorOptions().iterator();
			while (i.hasNext()) {
				swco = i.next();
				str += swco.getSwGroupId();
				str += ":";
				str += swco.getName();
				str += "|";
			}
			if (str.length() > 3)
				strLine += str.substring(0, str.length()-2);
			else
				strLine += str;
		} // if configOptions
		
		if (this.bAttributes) {
			strLine += ";" + swad.getAttribute().getAttr1();
			strLine += ";" + swad.getAttribute().getAttr2();
			strLine += ";" + swad.getAttribute().getAttr3();
			strLine += ";" + swad.getAttribute().getAttr4();
			strLine += ";" + swad.getAttribute().getAttr5();
			strLine += ";" + swad.getAttribute().getAttr6();
			strLine += ";" + swad.getAttribute().getAttr7();
			strLine += ";" + swad.getAttribute().getAttr8();
			strLine += ";" + swad.getAttribute().getAttr9();
			strLine += ";" + swad.getAttribute().getAttr10();
			strLine += ";" + swad.getAttribute().getAttr11();
			strLine += ";" + swad.getAttribute().getAttr12();
			strLine += ";" + swad.getAttribute().getAttr13();
			strLine += ";" + swad.getAttribute().getAttr14();
			strLine += ";" + swad.getAttribute().getAttr15();
			strLine += ";" + swad.getAttribute().getAttr16();
			strLine += ";" + swad.getAttribute().getAttr17();
			strLine += ";" + swad.getAttribute().getAttr18();
			strLine += ";" + swad.getAttribute().getAttr19();
			strLine += ";" + swad.getAttribute().getAttr20();
		}
		
		
		// for testing
		LinkedList<String> ll;
		Iterator<String> i;
		if (swa.isDwImagesSet()) {
			ll = swa.getDwImageList();
			i = ll.iterator();
			strLine += ";";
			while (i.hasNext()) {
				strLine += i.next();
				strLine += "|";
			}
			strLine = strLine.substring(0, strLine.length()-2);
		}
		else {
			strLine += ";";
		}
		
		return strLine;
	}

	/**
	 * Returns the Headline as CSV with closing Linefeed.
	 * 
	 * @return
	 */
	private String getCsvHeadLine () {
		String strHL;
		
		strHL = "";
		if (this.bOrderNumber)
			strHL = "ordernumber";
		if (this.bMainNumber)
			strHL += ";" + "mainnumber";
		if (this.bName)
			strHL += ";" + "name";
		if (this.bSupplier)
			strHL += ";" + "supplier";
		if (this.bTax)
			strHL += ";" + "tax";
		if (this.bPrice)
			strHL += ";" + "price";
		if (this.bCategories)
			strHL += ";" + "categories";
		
		if (this.bConfigOption) {
			strHL += ";" + "configuratorsetID";
			strHL += ";" + "configuratortype";
			strHL += ";" + "configuratorOption";
		}
		
		if (this.bAttributes) {
			strHL += ";" + "attr_attr1";
			strHL += ";" + "attr_attr2";
			strHL += ";" + "attr_attr3";
			strHL += ";" + "attr_attr4";
			strHL += ";" + "attr_attr5";
			strHL += ";" + "attr_attr6";
			strHL += ";" + "attr_attr7";
			strHL += ";" + "attr_attr8";
			strHL += ";" + "attr_attr9";
			strHL += ";" + "attr_attr10";
			strHL += ";" + "attr_attr11";
			strHL += ";" + "attr_attr12";
			strHL += ";" + "attr_attr13";
			strHL += ";" + "attr_attr14";
			strHL += ";" + "attr_attr15";
			strHL += ";" + "attr_attr16";
			strHL += ";" + "attr_attr17";
			strHL += ";" + "attr_attr18";
			strHL += ";" + "attr_attr19";
			strHL += ";" + "attr_attr20";
		}
		
		// for testing
		strHL += ";imageList";
		
		strHL += "\r\n";
		return strHL;
	} // getCstHeadLine
} // class ExportSWArticleToCsv
