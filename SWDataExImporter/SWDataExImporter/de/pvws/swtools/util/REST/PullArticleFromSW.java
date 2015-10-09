/**
 * 
 */
package de.pvws.swtools.util.REST;

import de.pvws.swtools.swDataStructure.*;

import java.util.*;

import javax.ws.rs.core.*;
import javax.ws.rs.client.*;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

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
		HttpAuthenticationFeature httpAuthFeat;
		WebTarget wtHost;
		Invocation.Builder invB;
		Response resp;
		
		client = ClientBuilder.newClient();
//		client.property("user", "api");
//		client.property("password","s5CPwkoPRlubpHGtpBuoVNRixoJwzb4rIUcTltOv");
		
		httpAuthFeat = HttpAuthenticationFeature.digest("api", "s5CPwkoPRlubpHGtpBuoVNRixoJwzb4rIUcTltOv");
		client.register(httpAuthFeat);
		
		wtHost = client.target("http://shopware-dev/api/articles/1");
		invB =  wtHost.request();
		invB.accept("application/json");
		invB.acceptEncoding("charset=utf-8");
		// Content-Type: application/json; charset=utf-8
		
		// Post --> create
		// Put --> update
		resp = wtHost.request().get();
		
		System.out.println(resp.getStatus());
		System.out.println(resp.getStatusInfo());
		System.out.println(resp.readEntity(String.class));
		
	}
}
