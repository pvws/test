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
	
	public static void doPush (String strJson) {
		Client client;
		HttpAuthenticationFeature httpAuthFeat;
		WebTarget wtHost;
		Invocation.Builder invB;
		Response resp;
		
		client = ClientBuilder.newClient();

		httpAuthFeat = HttpAuthenticationFeature.digest("api", "s5CPwkoPRlubpHGtpBuoVNRixoJwzb4rIUcTltOv");
		client.register(httpAuthFeat);

		wtHost = client.target("http://shopware-dev/api/articles");
		invB =  wtHost.request();
		invB.accept(MediaType.APPLICATION_JSON_TYPE);
		invB.acceptEncoding("charset=utf-8");

		resp = wtHost.request().post(Entity.entity(strJson, MediaType.APPLICATION_JSON_TYPE));
		
		System.out.println("POST-Status:     " + resp.getStatus());
		System.out.println("POST-StatusInfo: " + resp.getStatusInfo());
		System.out.println("POST-Response:   " + resp.readEntity(String.class));
	}

}
