/**
 * 
 */
package de.pvws.swtools.util.REST;

import de.pvws.swtools.swDataStructure.*;

import java.util.*;

import javax.ws.rs.core.*;
import javax.ws.rs.client.*;

/**
 * 
 * @author PV KT
 *
 */
public class PullArticleFromSW {

	public PullArticleFromSW (LinkedList<SWArticle> llSwa) {
		
	}
	
	public static void doPull () {
		Client client;
		WebTarget wtHost;
		Invocation.Builder invB;
		Response resp;
		
		client = ClientBuilder.newClient();
		client.property("user", "api");
		client.property("password","password");
		
		wtHost = client.target("http://shopware-dev/api/article");
		invB =  wtHost.request();
		invB.accept("application/json");
		invB.acceptEncoding("charset=utf-8");
		// Content-Type: application/json; charset=utf-8
		
		// Post --> create
		// Put --> update
		resp = wtHost.request().get();
		
	}
}
