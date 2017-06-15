package com.spr.init;

public interface AppConstants {

	
	
	String appId="312973512486501";
    String appSecret="ba4ec159ad5bffc9367d5638a5425734";
    String accessToken="";   // access token to be generated from graph api explorer
    String redirectUri="http://localhost:8080/dpr-data/social/facebook/callback";
    String quote="";    // image with quote location on your device
    String quoteName="";    // image name
    String message="Hi";    // message to be posted can be changed here
    String facebookPermissions="publish_actions";
}
