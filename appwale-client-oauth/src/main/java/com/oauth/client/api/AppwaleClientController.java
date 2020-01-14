package com.oauth.client.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView showAuthCode(@RequestParam("code") String authCode, ModelAndView model) {
        this.authCode=authCode;
        model.addObject("authCode", authCode);
        model.setViewName("AppwaleContentScreen");
        return model;
    }
	
	@GetMapping("/token")
    public String showAccessToken(Model model) {
        model.addAttribute("token", token);
        return "AppwaleContentScreen";
    }
	
	@GetMapping("/getAuthCode")
    public ModelAndView redirect() throws ClientProtocolException, IOException {
		String url = String.format("%s?response_type=%s&client_id=%s&redirect_uri=%s", codeUrl, responseType,
				clientId, redirectUri);
		System.out.println(url);
		return new ModelAndView("redirect:"+url);
	}
	
	@GetMapping("/getToken")
    public ModelAndView getToken(ModelAndView model) throws IOException, JSONException {
        JSONObject jsonObject = appwaleService.getToken(this.authCode);
        this.token = (String) jsonObject.get("access_token");
        model.addObject("authCode", "Auth Code Expired");
        model.addObject("token", token);
        model.setViewName("AppwaleContentScreen");
       return model;
	}
}
