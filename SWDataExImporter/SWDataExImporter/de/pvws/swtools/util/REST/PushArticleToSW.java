/**
 * 
 */
package de.pvws.swtools.util.REST;

import de.pvws.swtools.swDataStructure.*;

import java.util.*;

import javax.ws.rs.core.*;
import javax.ws.rs.client.*;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.json.*;

/**
 * 
 * @author PV KT
 *
 */
public class PushArticleToSW {
	
	public PushArticleToSW () {
		
	}
	
	public static void doPush () {
		Client client;
		HttpAuthenticationFeature httpAuthFeat;
		WebTarget wtHost;
		Invocation.Builder invB;
		Response resp;
		
		client = ClientBuilder.newClient();
//		client.property("user", "api");
//		client.property("password","password");

		httpAuthFeat = HttpAuthenticationFeature.digest("api", "s5CPwkoPRlubpHGtpBuoVNRixoJwzb4rIUcTltOv");
		client.register(httpAuthFeat);

		wtHost = client.target("http://shopware-dev/api/article");
		invB =  wtHost.request();
		invB.accept(MediaType.APPLICATION_JSON_TYPE);
		invB.acceptEncoding("charset=utf-8");
		// Content-Type: application/json; charset=utf-8
		
		resp = wtHost.request().get();
	}

}
