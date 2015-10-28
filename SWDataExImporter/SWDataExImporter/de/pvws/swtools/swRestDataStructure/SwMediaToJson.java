/**
 * 
 */
package de.pvws.swtools.swRestDataStructure;

import de.pvws.swtools.swDataStructure.*;

import java.io.FileOutputStream;
import java.io.InputStream;
//import java.io.OutputStream;
import java.net.Proxy;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.InetAddress;
import java.util.*;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * @author pvws
 *
 */
public class SwMediaToJson {
	private SWArticle swArticle;
	private String strJsonMediaImport;

	public SwMediaToJson () {
	}

	public SwMediaToJson (SWArticle swa) {
		this.swArticle = swa;
	}
	
	public static String buildJsonMediaImport (SWArticle swa) {
		SwMediaToJson ra = new SwMediaToJson (swa);
		
		return ra.doBuild();
	}
	
	public static String buildJsonMediaImport (SWArticle swa, String path) {
		SwMediaToJson ra = new SwMediaToJson (swa);
		
		return ra.doBuild(path);
	}

	public String doBuild () {
		this.strJsonMediaImport = "";
		
		this.strJsonMediaImport = this.SwaToJson();
		
		return this.strJsonMediaImport;
	}
	
	public String doBuild (String path) {
		this.strJsonMediaImport = "";
		
		this.strJsonMediaImport = this.SwaToJson(path);
		
		return this.strJsonMediaImport;
	}

	/**
	 * Manages the JSON-Creation for Media Import to the given SWArticle
	 * 
	 * @return JSON Object (Complete Media Object)
	 */
	private String SwaToJson (String path) {
		JsonBuilderFactory jf;
		JsonObject joMediaImport;
		JsonObjectBuilder job;
		
		String strJson = "";
		
		try {
			jf = Json.createBuilderFactory(null);
			job = jf.createObjectBuilder();

			// Workaround as long as Scene7 is not reachable
//			this.pullImageFromScene7(this.getPathString(path));

			job = jf.createObjectBuilder();
			job.add("album", -1);

			// Standard-Doing
			//job.add("file", this.getPathString(path));
			// Workaround as long as Scene7 is not reachable
			String s = "file:////var/www/html/test" + this.getNameString(path) + ".jpg";
			job.add("file", s);

			job.add("name", this.getNameString(path).substring(1));
			job.add("description", this.getDescriptionString(path));
			job.add("type", "IMAGE");
			job.add("extension", "jpg");

			joMediaImport = job.build();

			strJson = joMediaImport.toString();
			System.out.println("SwMediaToJson-SwaToJson String: " + strJson);
		}
		catch (Exception e){
			System.out.println ("Error in SwMediaToJson-SwaToJson: " + e);
		}
		
		return strJson;
	}

	/**
	 * Manages the JSON-Creation for Media Import to the given SWArticle
	 * 
	 * @return JSON Object (Complete Media Array)
	 */
	private String SwaToJson () {
		String strJson = "[";
		String path = "";
		Iterator<String> iDwPath;
		
		iDwPath = this.swArticle.getDwImageList().iterator();
		while (iDwPath.hasNext()) {
			path = iDwPath.next();
			strJson += this.SwaToJson(path);
		}
		
		strJson += "]";
		return strJson;
	}

	/**
	 * Creates the Path-Object as JSON
	 * 
	 * @return JSON-Object (path)
	 */
	private String getPathString (String path) {
		String p = "";
		
		if (this.swArticle != null) {
			p = this.swArticle.getDwImagePath();
			if (p.length() > 1)
				p = p.substring(0, p.length()-1);
			p += path;
		}
		else 
			p = path;
		
		
		return p;
	} // create Path-Object as JSON

	/**
	 * Creates the Description-Object as JSON
	 * 
	 * @return JSON-Object (Description)
	 */
	private String getDescriptionString (String path) {
		String desc;

		desc = "Bild zu " + this.getNameString(path).substring(1);
		
		return desc;
	} // createDescriptionObject()
	
	private String getNameString (String path){
		String name = "";
		String part;
		int i;
		
		part = path;
		
		i = part.indexOf('?');
		if (i > 1)
			part = path.substring(0, i);
		
		while ((i = part.lastIndexOf('/')) > 1) {
			part = part.substring(i+1);
		}
		
		name = part;
		return name;
		
	}
	
	/**
	 * Pulls the image given in path from Scene7 and save it to D:/test with the given name and .jpg extension.
	 * 
	 * @param path
	 */
	public String pullImageFromScene7 (String path) {
		URL url;
		URLConnection uConn;
		Socket ss;
		Proxy proxy;
		InetAddress ia;
		
		InputStream is;
		FileOutputStream fos;
		
		String p;
//		String p = "http://netrada2.scene7.com/is/image/netrada2/ntw_sp68100_01_1?$zoomLarge$";
		String d = "D:/test/";
		String n;
		
		int l;
		byte b[] = new byte[0xFFF];
		
		p = path;
		n = this.getNameString(p);
		d += n + ".jpg";
		
		
		try {
			url = new URL (p);
			ia = InetAddress.getByName("proxy-ga");
			ss = new Socket(ia , 8080);
			proxy = new Proxy(Proxy.Type.HTTP, ss.getRemoteSocketAddress());
			uConn = url.openConnection(proxy);
			

			is = uConn.getInputStream();
			fos = new FileOutputStream(d);


			while ( (l=is.read(b)) != -1) {
				fos.write(b, 0, l);
			}

			if (is != null)
				is.close();
			if (fos != null)
				fos.close();
			if (ss != null)
				ss.close();
		}
		catch (Exception e) {
			System.out.println ("Error Pull of Image " + n + ".jpg: " + e.getMessage());
			return "Error Pull of Image " + n + ".jpg: " + e.getMessage();
		}
		
		System.out.println ("Pull of Image " + n + ".jpg - OK");
		return "Pull of Image " + n + ".jpg - OK";
	}

}
