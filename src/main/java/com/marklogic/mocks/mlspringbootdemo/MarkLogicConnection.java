package com.marklogic.mocks.mlspringbootdemo;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.query.QueryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Wrap the connection creation process and make it consistent. Either the connection is created or not (in which 
 * case the constructor will return an exception).
 * It is allowed to have many MarkLogic connections in one application server - these may correspond to different users.
 * However it is assumed that all connections are to one and the same backend server.
 * 
 * @author gnasri
 *
 */

@Component
public class MarkLogicConnection {
	
	private static final Logger logger = LoggerFactory.getLogger(MarkLogicConnection.class);
	@Value( "${marklogic.host:localhost}" )
	private  String host ;
	@Value( "${marklogic.port:8010}" )
	private  String port;
	@Value( "${marklogic.user:admin}" )
	private  String user;
	@Value( "${marklogic.password:admin}" )
	private  String password ;
	
	private DatabaseClient client = null;
	
	private  void setHost(String ht) {
		if (host == null) {
			host = ht;			
		} 
	}
	public  String getHost(){
		return host;
	}
	private  void setPort(String pt) {
		if (port == null) {
			port = pt;			
		} 
	}
	public  String getPort() {
		return port;
	}
	/*public MarkLogicConnection(){
		logger.info("initialising MarkLogic Connection..."+host);

	}*/

	/**
	 * 
	 * This constructor should be used to create a MarkLogicConnection on the fly
	 * e.g. when a writer/admin user logs in. 
	 * 
	 * 
	 * @param user username to use in the connection
	 * @param password  password for the above username
	 * 
	 * private to restrict access to class creation
	 */
	public MarkLogicConnection(@Value( "${marklogic.host:localhost}" ) String host,@Value( "${marklogic.port:8010}" ) String port,@Value( "${marklogic.user:admin}" )String user, @Value( "${marklogic.password:admin}" )String password) {
		createConnection(host,port,user, password);
	}
	/**
	 * This constructor will be used to create connections on the fly - not at startup.
	 * However if host, port and authtype properties have not been set (i.e. the constructor is called
	 * at startup - then it will read the default properties file to get that data).
	 * 
	 * @param port port to connect to
	 * @param authType authentication type to be used when connecting to the server
	 * @param user username to use in the connection
	 * @param password  password for the above username
	 * @return void
	 */
	public void createConnection(String host,String port, String user, String password) {

		logger.info("Creating Connection to MarkLogic db on " + host + ":" + port);
			

		int   pt = Integer.parseInt(port);

		try {
			// create the client
			client = DatabaseClientFactory.newClient(host, pt, new DatabaseClientFactory.DigestAuthContext(user,password));
		} catch (Exception e) {
			logger.error("Failed to create db connection " + e.toString() + e.getMessage() );
			throw new RuntimeException(e);
		}

	}
	/**
	 * 
	 * @param filename name of the file containing MarkLogic connection properties
	 * @return void
	 */

	/**
	 * accessor for DatabaseClient
	 * usage: conn.getClient().newXMLDocumentManager()
	 * @return
	 */
	public DatabaseClient getClient() {
		return client;
	}
	
	/*
	 * release the MarkLogic db connection
	 * 
	 */
	public void release() {
		logger.info("releasing ML connection");
		client.release();
	}


}
