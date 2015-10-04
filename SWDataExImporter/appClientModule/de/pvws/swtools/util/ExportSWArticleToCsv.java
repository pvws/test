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
				fw.write(this.getCsvString(iA.next()));
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
		iAD = swa.getVariants().iterator();
		
		while (iAD.hasNext()) {
			swad = iAD.next();
			if (this.bOrderNumber)
				strCsv += swad.getSWNumber();
			if (this.bMainNumber)
				strCsv += ";" + swa.getSwArticleNumber();
			if (this.bName)
				strCsv += ";" + swa.getName();
			if (this.bSupplier)
				strCsv += ";" + swa.getSupplier().getName();
			if (this.bTax)
				strCsv += ";" + swa.getSwTax().getSwTax();
			if (this.bPrice)
				strCsv += ";" + String.valueOf(swad.getPrice(1).getPrice());
			
			strCsv += "\r\n";
		} // while iAD.hasNext
		
		return strCsv;
	} // getCsvString
	
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
		
		strHL += "\r\n";
		return strHL;
	} // getCstHeadLine
} // class ExportSWArticleToCsv
