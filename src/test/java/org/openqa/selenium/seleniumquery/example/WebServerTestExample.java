package org.openqa.selenium.seleniumquery.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebServerTestExample {

	private Server server;

//	@Before
	public void startServer() throws Exception {
		server = new Server(8050);
		server.setStopAtShutdown(true);
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/app");
		webAppContext.setResourceBase("src/test/resources");
		webAppContext.setClassLoader(getClass().getClassLoader());
		// server.addBean(webAppContext);
		server.setHandler(webAppContext);
		server.start();
	}

//	@Test
	public void shouldBePreAuthenticated() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet("http://localhost:8050/app/BasicPage.html");
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
		String line = null;
		while ((line = rd.readLine()) != null) {
			System.out.println("LINE: "+line);
		}
		// DO YOUR ASSERTIONS
	}

//	@After
	public void shutdownServer() throws Exception {
		server.stop();
	}

}