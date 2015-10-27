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
public class PushDataToSW {
	public static final String ARTICLES = "articles";
	public static final String MEDIA = "media";
	
	public PushDataToSW () {
		
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

//		wtHost = client.target("http://shopware-dev/api/articles");
		wtHost = client.target("http://shopware-dev/api/media");
		invB =  wtHost.request();
		invB.accept(MediaType.APPLICATION_JSON_TYPE);
		invB.acceptEncoding("charset=utf-8");

		resp = wtHost.request().post(Entity.entity(strJson, MediaType.APPLICATION_JSON_TYPE));
		
		return PushDataToSW.createResponseString(resp);
	} // doPush()

	/**
	 * Pushes the JSON String 'as POST' to 'target' and returns the Response as JSON String.
	 * Target can be:
	 * - PushDataToSW.ARTICLES
	 * - PushDataToSW.MEDIA
	 * 
	 * Return contains:
	 * - "status":int, 
	 * - "statusInfo":string and 
	 * - "entity":string
	 *  
	 * @param strJson
	 * @return
	 */
	public static String doPush (String strJson, String target) {
		Client client;
		HttpAuthenticationFeature httpAuthFeat;
		WebTarget wtHost;
		Invocation.Builder invB;
		Response resp;
		
		client = ClientBuilder.newClient();

		httpAuthFeat = HttpAuthenticationFeature.digest("api", "s5CPwkoPRlubpHGtpBuoVNRixoJwzb4rIUcTltOv");
		client.register(httpAuthFeat);

		switch (target) {
		case PushDataToSW.ARTICLES:
			wtHost = client.target("http://shopware-dev/api/articles");
			break;
		case PushDataToSW.MEDIA:
			wtHost = client.target("http://shopware-dev/api/media");
			break;
		default:
			return "{\"Error\":\"Wrong Push-Target in PushDataToSW.java\"}";
		}
//		wtHost = client.target("http://shopware-dev/api/articles");
//		wtHost = client.target("http://shopware-dev/api/media");
		invB =  wtHost.request();
		invB.accept(MediaType.APPLICATION_JSON_TYPE);
		invB.acceptEncoding("charset=utf-8");

		resp = wtHost.request().post(Entity.entity(strJson, MediaType.APPLICATION_JSON_TYPE));
		
		return PushDataToSW.createResponseString(resp);
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
