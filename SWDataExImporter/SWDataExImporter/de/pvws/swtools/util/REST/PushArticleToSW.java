/**
 * 
 */
package de.pvws.swtools.util.REST;

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
	
	/**
	 * Pushes the JSON String 'as POST' to 'the articles' and returns the Response as JSON String.
	 * Return contains:
	 * - "status":int, 
	 * - "statusInfo":string and 
	 * - "entity":string
	 *  
	 * @param strJson
	 * @return
	 */
	public static String doPush (String strJson) {
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
		
		return PushArticleToSW.createResponseString(resp);
	} // doPush()

	/**
	 * Creates an JSON String from the Response, containing 
	 * - "status":int, 
	 * - "statusInfo":string and 
	 * - "entity":string
	 * 
	 * @param resp
	 * @return A JSON String
	 */
	private static String createResponseString (Response resp) {
		JsonObjectBuilder job;
		JsonObject jb;
		
		String strJson = "";
		
		if (resp != null) {
			job = Json.createObjectBuilder();
			job.add("status", resp.getStatusInfo().getStatusCode());
			job.add("statusInfo", resp.getStatusInfo().getReasonPhrase());
			job.add("entity", resp.readEntity(String.class));
			jb = job.build();
			strJson = jb.toString();
		}
		
		return strJson;
	} // createResponseString
}
