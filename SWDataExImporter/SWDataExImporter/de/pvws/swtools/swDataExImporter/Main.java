/**
 * 
 */
package de.pvws.swtools.swDataExImporter;

import de.pvws.util.*;

import de.pvws.swtools.swDataStructure.*;
import de.pvws.swtools.util.ExportSWArticleToCsv;
import de.pvws.swtools.util.ExportSWArticleToRest;
import de.pvws.swtools.util.ExportSWMediaToCsv;
import de.pvws.swtools.util.ExportSWMediaToRest;
import de.pvws.swtools.util.ImportDWArticleCat;
import de.pvws.swtools.util.ImportDWPriceCat;
//import de.pvws.swtools.util.REST.*;

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
		ExportSWArticleToCsv exArticlesCsv = new ExportSWArticleToCsv();
		ExportSWMediaToCsv exMediaCsv = new ExportSWMediaToCsv();
		// TODO Auto-generated method stub
		
		llSWArticle = new LinkedList<SWArticle>();
		try {
		// import Article MasterData
			// Daten-Quellen
			System.out.println ("Beginn MasterCat einlesen : " + PvwsCalendar.getDateTime());
			dwACat = new ImportDWArticleCat (llSWArticle);
			//dwACat.doImport("C:/Users/mfehr/Documents/Projekte/Shopware/POC Shop/DEV/DW Daten/mastercat_2products.xml");
			//dwACat.doImport("V:/Entwicklung/Eclipse/SWTools/DW_Daten/mastercat_1Style.xml");
			dwACat.doImport("C:/Users/mfehr/Documents/Projekte/Shopware/POC Shop/DEV/DW Daten/mastercat_1style.xml");
			//dwACat.doImport("C:/Users/mfehr/Documents/Projekte/Shopware/POC Shop/DEV/DW Daten/demo_mastercat_150924.xml");
			//dwACat.doImport("V:/Entwicklung/Eclipse/SWTools/DW_Daten/mastercat_150924.xml");
			System.out.println ("Ende MasterCat einlesen : " + PvwsCalendar.getDateTime());
			System.out.println();
			
		// import Price Data
			// Daten-Quellen
			System.out.println ("Beginn PriceCat List einlesen : " + PvwsCalendar.getDateTime());
			dwPCat = new ImportDWPriceCat (llSWArticle);
			dwPCat.doImport("C:/Users/mfehr/Documents/Projekte/Shopware/POC Shop/DEV/DW Daten/demoshop-de-listprice.xml");
			//dwPCat.doImport("V:/Entwicklung/Eclipse/SWTools/DW_Daten/demoshop-de-listprice.xml");
			System.out.println ("Ende PriceCat List einlesen : " + PvwsCalendar.getDateTime());
			System.out.println();

			System.out.println ("Beginn PriceCat Sale einlesen : " + PvwsCalendar.getDateTime());
			dwPCat = new ImportDWPriceCat (llSWArticle);
			//dwPCat.doImport("C:/Users/mfehr/Documents/Projekte/Shopware/POC Shop/DEV/DW Daten/demoshop-de-saleprice.xml");
			//dwPCat.doImport("V:/Entwicklung/Eclipse/SWTools/DW_Daten/demoshop-de-saleprice.xml");
			//dwPCat.doImport("V:/Entwicklung/Eclipse/SWTools/DW_Daten/demoshop-de-saleprice-test.xml");
			System.out.println ("Ende PriceCat Sale einlesen : " + PvwsCalendar.getDateTime());
			System.out.println();

		// import Category Assignments
			// Daten-Quellen
			System.out.println ("Beginn Category Assignments einlesen : " + PvwsCalendar.getDateTime());
			dwPCat = new ImportDWPriceCat (llSWArticle);
			//dwPCat.doImport("C:/Users/mfehr/Documents/Projekte/Shopware/POC Shop/DEV/DW Daten/demo_navcat_150527.xml");
			//dwPCat.doImport("V:/Entwicklung/Eclipse/SWTools/DW_Daten/demo_navcat_150527.xml");
			System.out.println ("Ende Category Assignments einlesen : " + PvwsCalendar.getDateTime());
			System.out.println();
			
		// Media-Daten in CSV schreiben
//			System.out.println("Beginn Media-CSV schreiben : " + PvwsCalendar.getDateTime());
//			exMediaCsv.doExport(llSWArticle);
//			System.out.println("Ende Media-CSV schreiben : " + PvwsCalendar.getDateTime());
//			System.out.println();

		// Artikel-Daten in CSV schreiben
			System.out.println("Beginn Artikel-CSV schreiben : " + PvwsCalendar.getDateTime());
			exArticlesCsv.doExport(llSWArticle);
			System.out.println("Ende Artikel-CSV schreiben : " + PvwsCalendar.getDateTime());
			System.out.println();

		// Media-Daten in SW importieren
//			System.out.println("Beginn Media-JSON schreiben : " + PvwsCalendar.getDateTime());
//			ExportSWMediaToRest.doExport(llSWArticle, ExportSWArticleToRest.MODE_MERGE);
//			System.out.println("Ende Media-JSON schreiben : " + PvwsCalendar.getDateTime());
//			System.out.println();

		// Artikel-Daten in SW importieren
//			System.out.println("Beginn Artikel-JSON schreiben : " + PvwsCalendar.getDateTime());
//			ExportSWArticleToRest.doExport(llSWArticle, ExportSWArticleToRest.MODE_MERGE);
//			System.out.println("Ende Artikel-JSON schreiben : " + PvwsCalendar.getDateTime());
//			System.out.println();
			
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