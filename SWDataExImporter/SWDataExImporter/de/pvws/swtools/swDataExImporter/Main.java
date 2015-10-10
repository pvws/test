/**
 * 
 */
package de.pvws.swtools.swDataExImporter;

import de.pvws.util.*;

import de.pvws.swtools.swDataStructure.*;
import de.pvws.swtools.util.ExportSWArticleToCsv;
import de.pvws.swtools.util.ExportSWArticleToRest;
import de.pvws.swtools.util.ImportDWArticleCat;
import de.pvws.swtools.util.ImportDWPriceCat;
import de.pvws.swtools.util.REST.*;

import java.util.*;

/**
 * @author PV WS
 *
 */
public class Main {
	//private ImportDWArticleCat dwCat;
	//private LinkedList<SWArticleDetail> llSWArticleDetail;

	public static void main(String[] args) {
		ImportDWArticleCat dwACat;
		ImportDWPriceCat dwPCat;
		LinkedList<SWArticle> llSWArticle;
		ExportSWArticleToCsv exCsv = new ExportSWArticleToCsv();
		// TODO Auto-generated method stub
		
		llSWArticle = new LinkedList<SWArticle>();
		try {
			//PullArticleFromSW.doPull();
			
			// import Article MasterData
			dwACat = new ImportDWArticleCat (llSWArticle);
			System.out.println ("Beginn MasterCat einlesen : " + PvwsCalendar.getDateTime());
			//dwACat.doImport("C:/Users/mfehr/Documents/Projekte/Shopware/POC Shop/DEV/DW Daten/mastercat_2products.xml");
			dwACat.doImport("V:/Entwicklung/Eclipse/SWTools/DW_Daten/mastercat_1Style.xml");
			//dwACat.doImport("C:/Users/mfehr/Documents/Projekte/Shopware/POC Shop/DEV/DW Daten/mastercat_1style.xml");
			//dwACat.doImport("V:/Entwicklung/Eclipse/SWTools/DW_Daten/mastercat_150924.xml");
			System.out.println ("Ende MasterCat einlesen : " + PvwsCalendar.getDateTime());

			// import Price Data
			System.out.println ("Beginn PriceCat List einlesen : " + PvwsCalendar.getDateTime());
			dwPCat = new ImportDWPriceCat (llSWArticle);
			//dwPCat.doImport("C:/Users/mfehr/Documents/Projekte/Shopware/POC Shop/DEV/DW Daten/demoshop-de-listprice.xml");
			//dwPCat.doImport("V:/Entwicklung/Eclipse/SWTools/DW_Daten/demoshop-de-listprice.xml");
			dwPCat.doImport("V:/Entwicklung/Eclipse/SWTools/DW_Daten/demoshop-de-listprice.xml");
			System.out.println ("Ende PriceCat List einlesen : " + PvwsCalendar.getDateTime());
			System.out.println ("Beginn PriceCat Sale einlesen : " + PvwsCalendar.getDateTime());
			dwPCat = new ImportDWPriceCat (llSWArticle);
			//dwPCat.doImport("C:/Users/mfehr/Documents/Projekte/Shopware/POC Shop/DEV/DW Daten/demoshop-de-saleprice.xml");
			//dwPCat.doImport("V:/Entwicklung/Eclipse/SWTools/DW_Daten/demoshop-de-saleprice.xml");
			//dwPCat.doImport("V:/Entwicklung/Eclipse/SWTools/DW_Daten/demoshop-de-saleprice-test.xml");
			System.out.println ("Ende PriceCat Sale einlesen : " + PvwsCalendar.getDateTime());
			
			System.out.println("Beginn Artikel-CSV schreiben : " + PvwsCalendar.getDateTime());
//			exCsv.doExport(llSWArticle);
			System.out.println("Ende Artikel-CSV schreiben : " + PvwsCalendar.getDateTime());

			System.out.println("Beginn Artikel-JSON schreiben : " + PvwsCalendar.getDateTime());
			ExportSWArticleToRest.doExport(llSWArticle, ExportSWArticleToRest.MODE_MERGE);
			System.out.println("Ende Artikel-JSON schreiben : " + PvwsCalendar.getDateTime());
			
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

	
}