package com.spr.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.spr.init.AppConstants;

@Controller
@RequestMapping(value = "/social/facebook")
public class FacebookController<FacebookApi> {

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public ModelAndView signin() throws FileNotFoundException {
		FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory(AppConstants.appId,
				AppConstants.appSecret);

		OAuth2Operations oAuth2Operations = facebookConnectionFactory.getOAuthOperations();
		OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
		oAuth2Parameters.setState("recivedfromfacebooktoken");
		oAuth2Parameters.setRedirectUri(AppConstants.redirectUri);
		oAuth2Parameters.setScope(AppConstants.facebookPermissions);
		String authorizeUrl = oAuth2Operations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);
		RedirectView redirectView = new RedirectView(authorizeUrl, true, true, true);
		return new ModelAndView(redirectView);

	}

	@RequestMapping("/callback")
	@SuppressWarnings("deprecation")

	public String authorize(@RequestParam String code, Model model)
			throws FileNotFoundException, UnsupportedEncodingException, MalformedURLException {
		DefaultFacebookClient facebookClient = new DefaultFacebookClient(AppConstants.accessToken);
		FileInputStream fileInputStream = new FileInputStream(new File(AppConstants.quote));
		FacebookType response = facebookClient.publish("/me/photos", FacebookType.class,
				BinaryAttachment.with(AppConstants.quoteName, fileInputStream),
				Parameter.with("message", AppConstants.message));
		model.addAttribute("result", response.getId());
		return "result";

	}
}