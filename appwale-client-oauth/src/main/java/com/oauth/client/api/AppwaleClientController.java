package com.oauth.client.api;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oauth.client.service.AppwaleService;

@RestController
public class AppwaleClientController {
	
	@Autowired
	private AppwaleService appwaleService;
	
	@Value("${appwale.auth.code_url}")
	private String codeUrl;
	
	@Value("${appwale.auth.response_type}")
	private String responseType;
	
	@Value("${appwale.auth.client_id}")
	private String clientId;
	
	@Value("${appwale.auth.redirect_uri}")
	private String redirectUri;
	
	private String authCode;
	private String token;
	

	@GetMapping("/authCode")
    public String showAuthCode(@RequestParam("code") String authCode, Model model) {
        this.authCode=authCode;
        model.addAttribute("authCode", authCode);
        return "AppwaleContentScreen";
    }
	
	@GetMapping("/token")
    public String showAccessToken(Model model) {
        model.addAttribute("token", token);
        return "AppwaleContentScreen";
    }
	
	@GetMapping("/getAuthCode")
    public String redirect() {
		System.out.println(String.format("redirect:[{}]?response_type=[{}]&client_id=[{}]&redirect_uri=[{}]",codeUrl,responseType,clientId,redirectUri));
        return String.format("redirect:%s?response_type=%s&client_id=%s&redirect_uri=%s",codeUrl,responseType,clientId,redirectUri);
    }
	
	@GetMapping("/getToken")
    public String getToken(Model model) throws IOException, JSONException {
        JSONObject jsonObject = appwaleService.getToken(this.authCode);
        this.token = (String) jsonObject.get("access_token");
        model.addAttribute("authCode", "Auth Code Expired");
        model.addAttribute("token", token);
        return "AppwaleContentScreen";
    }
}
