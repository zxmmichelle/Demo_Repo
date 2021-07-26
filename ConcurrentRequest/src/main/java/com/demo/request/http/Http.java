package com.demo.request.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Http {
	static HttpClient httpClient;

	public static String post(String url, Object data) {
		if (httpClient == null)
			httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMillis(5000)).build();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
			System.out.println(requestBody);
			HttpRequest request = (HttpRequest) HttpRequest.newBuilder().uri(new URI(url))
					.header("Content-Type", "application/json").POST(BodyPublishers.ofString(requestBody)).build();
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
			return response.body();
		} catch (URISyntaxException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
}
