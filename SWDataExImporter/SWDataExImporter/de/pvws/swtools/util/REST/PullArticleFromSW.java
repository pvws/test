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
	private Client client;
	private HttpAuthenticationFeature httpAuthFeat;
	private WebTarget wtHost;
	private Invocation.Builder invB;
	private Response resp;
	
	private String strRespStatus;
	private String strRespStatusInfo;
	private String strRespEntity;

	public PullArticleFromSW () {
		
	}

	public PullArticleFromSW (LinkedList<SWArticle> llSwa) {
		
	}
	
	public static String doPull () {
//		Client client;
//		HttpAuthenticationFeature httpAuthFeat;
//		WebTarget wtHost;
//		Invocation.Builder invB;
//		Response resp;
//		
//		String strRespStatus;
//		String strRespStatusInfo;
//		String strRespEntity;
		
		PullArticleFromSW pull = new PullArticleFromSW();
		
		return pull.doExec();
		
//		client = ClientBuilder.newClient();
//		client.property("user", "api");
//		client.property("password","s5CPwkoPRlubpHGtpBuoVNRixoJwzb4rIUcTltOv");
		
/*		httpAuthFeat = HttpAuthenticationFeature.digest("api", "s5CPwkoPRlubpHGtpBuoVNRixoJwzb4rIUcTltOv");
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
		
		return strRespEntity;
*/	} // doPull()

	private String doExec () {

		this.client = ClientBuilder.newClient();
		
		this.httpAuthFeat = HttpAuthenticationFeature.digest("api", "s5CPwkoPRlubpHGtpBuoVNRixoJwzb4rIUcTltOv");
		this.client.register(httpAuthFeat);
		
		this.wtHost = client.target("http://shopware-dev/api/articles/1");
		this.invB =  wtHost.request();
		this.invB.accept("application/json");
		this.invB.acceptEncoding("charset=utf-8");
		
		// Post --> create
		// Put --> update
		this.resp = wtHost.request().get();

		this.strRespStatus = String.valueOf(this.resp.getStatus());
		this.strRespStatusInfo = this.resp.getStatusInfo().toString();
		this.strRespEntity = this.resp.readEntity(String.class);
		
		
		return this.strRespEntity;
	}
}
