package com.oauth.client.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppwaleServiceImpl implements AppwaleService {

	@Value("${appwale.auth.client_id}")
	private String clientId;

	@Value("${appwale.auth.client_secret}")
	private String clientSecret;

	@Value("${appwale.auth.server.url}")
	private String authServerUrl;

	@Value("${appwale.auth.redirect_uri}")
	private String redirectUri;

	@Value("${appwale.auth.grant_type}")
	private String grantType;

	public JSONObject getToken(String authCode) throws IOException, JSONException {

		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(authServerUrl);

		httpPost.setHeader("Authorization", getCredential());

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("client_id", clientId));
		params.add(new BasicNameValuePair("redirect_uri", redirectUri));
		params.add(new BasicNameValuePair("code", authCode));
		params.add(new BasicNameValuePair("grant_type", grantType));

		httpPost.setEntity(new UrlEncodedFormEntity(params));

		CloseableHttpResponse response = client.execute(httpPost);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());

		JSONObject jsonObject = new JSONObject(result.toString());

		client.close();

		return jsonObject;
	}

	public String getCredential() {
		String userDetail = String.join(":", clientId, clientSecret);
		return "Basic " + Base64.getEncoder().encodeToString(userDetail.getBytes());
	}

}
