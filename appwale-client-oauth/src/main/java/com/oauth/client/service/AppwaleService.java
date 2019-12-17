package com.oauth.client.service;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface AppwaleService {

	public JSONObject getToken(String authCode) throws IOException, JSONException;

}
