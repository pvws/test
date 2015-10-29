/**
 * 
 */
package de.pvws.swtools.util.csv;

import de.pvws.swtools.swDataStructure.*;

import java.util.*;
import java.io.*;

/**
 * @author PV KT
 *
 */
public class ExportSWMediaToCsv {
	private String strPath;
	private String strFilename;
	private LinkedList<SWArticle> llSwa;
	private String strDate;
	
	private Boolean bOrderNumber;
	private Boolean bImage;
	private Boolean bMain;
	private Boolean bDescription;
	private Boolean bPosition;
	private Boolean bWidth;
	private Boolean bHeight;
	private Boolean bRelations;
	
	/**
	 * 
	 */
	public ExportSWMediaToCsv () {
		this.setDateTime();
		this.setDataRange();
		this.strFilename = "swMedia_" + strDate + ".csv";
		this.strPath = "D:/";
	}
	
	/**
	 * 
	 * @param swad
	 * @param path
	 * @param filename
	 */
	public ExportSWMediaToCsv (LinkedList<SWArticle> swa, String path, String filename) {
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
	public ExportSWMediaToCsv (LinkedList<SWArticle> swa, String path) {
		this.setDateTime();
		this.setDataRange();
		this.llSwa = swa;
		this.strPath = path;
		this.strFilename = "swMedia_" + strDate + ".csv";
	}
	
	private void setDataRange () {
		this.bOrderNumber = true;
		this.bImage = true;
		this.bMain = true;
		this.bDescription = true;
		this.bPosition = true;
		this.bWidth = true;
		this.bHeight = true;
		this.bRelations = true;
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
			//strLine += "001_Bao";
			strLine += swad.getSWNumber();
		// Main Detail gets no main number but empty field
		if (this.bImage)
			strLine += "http://shopware-dev/test/ntw_sp68100_01_1.jpg";
			//strLine += ";";
		// Variant gets main number => SW Article Number of Main Detail
		if (this.bMain)
			strLine += "1";
			//strLine += ";" + swa.getMainDetail().getSWNumber();
		if (this.bDescription)
			strLine += "Test-Upload";
			//strLine += ";" + swa.getName();
		if (this.bPosition)
			strLine += "1";
			//strLine += ";" + swa.getSupplier().getName();
		if (this.bWidth)
			strLine += "";
			//strLine += ";" + swa.getSwTax().getSwTax();
		if (this.bHeight)
			strLine += "";
			//strLine += ";" + String.valueOf(swad.getPrice(1).getPrice());
		if (this.bRelations) {
			strLine += "";
			//strLine += this.createCategoryString(swa, swad);
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
		if (this.bImage)
			strHL += ";" + "image";
		if (this.bMain)
			strHL += ";" + "main";
		if (this.bDescription)
			strHL += ";" + "description";
		if (this.bPosition)
			strHL += ";" + "position";
		if (this.bWidth)
			strHL += ";" + "width";
		if (this.bHeight)
			strHL += ";" + "height";
		
		if (this.bRelations) {
			strHL += ";" + "relations";
		}
		
		strHL += "\r\n";
		return strHL;
	} // getCstHeadLine
	
	private String createCategoryString (SWArticle swa, SWArticleDetail swad) {
		SWCategory swcCat;
		Iterator<SWCategory> iSwcCat;
		String strField = ";";
		String str = "";
		
		// Categories from Master
		iSwcCat = swa.getCategories().iterator();
		while (iSwcCat.hasNext()) {
			swcCat = iSwcCat.next();
			str += swcCat.getId();
			str += "|";
		}

		// Categories from Article
		iSwcCat = swad.getCategories().iterator();
		while (iSwcCat.hasNext()) {
			swcCat = iSwcCat.next();
			str += swcCat.getId();
			str += "|";
		}
		if (str.length() > 2)
			strField += str.substring(0, str.length()-1);

		return strField;
	} // createCategoryString()

	private String createConfigFields(SWArticleDetail swad) {
		String strFields = "";
		
		strFields += ";" + "1"; // configuratorsetID, must be set, but without function
		strFields += ";" + ""; // configuratortype, not used yet
		// strLine += ";" + "configuratorOption"; // configuratorOption, separated by pipe
		Iterator<SWConfiguratorOption> i;
		SWConfiguratorOption swco;
		String str = ";";
		i = swad.getConfiguratorOptions().iterator();
		while (i.hasNext()) {
			swco = i.next();
			str += swco.getSwConfiguratorGroup().getName();
			str += ":";
			str += swco.getName();
			str += "|";
		}
		if (str.length() > 3)
			strFields += str.substring(0, str.length()-1);
		else
			strFields += str;

		return strFields;
	} // createConfigFields()

	private String createArticleAttributesString(SWArticleDetail swad) {
		String strField = "";
		
		strField += ";" + swad.getAttribute().getAttr1();
		strField += ";" + swad.getAttribute().getAttr2();
		strField += ";" + swad.getAttribute().getAttr3();
		strField += ";" + swad.getAttribute().getAttr4();
		strField += ";" + swad.getAttribute().getAttr5();
		strField += ";" + swad.getAttribute().getAttr6();
		strField += ";" + swad.getAttribute().getAttr7();
		strField += ";" + swad.getAttribute().getAttr8();
		strField += ";" + swad.getAttribute().getAttr9();
		strField += ";" + swad.getAttribute().getAttr10();
		strField += ";" + swad.getAttribute().getAttr11();
		strField += ";" + swad.getAttribute().getAttr12();
		strField += ";" + swad.getAttribute().getAttr13();
		strField += ";" + swad.getAttribute().getAttr14();
		strField += ";" + swad.getAttribute().getAttr15();
		strField += ";" + swad.getAttribute().getAttr16();
		strField += ";" + swad.getAttribute().getAttr17();
		strField += ";" + swad.getAttribute().getAttr18();
		strField += ";" + swad.getAttribute().getAttr19();
		strField += ";" + swad.getAttribute().getAttr20();

		return strField;
	}
} // class ExportSWArticleToCsv
