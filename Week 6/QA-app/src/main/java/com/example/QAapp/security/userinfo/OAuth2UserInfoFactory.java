package com.example.QAapp.security.userinfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String authProvider, Map<String, Object> attributes) {
        if(authProvider.equalsIgnoreCase("google")) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if(authProvider.equalsIgnoreCase("github")) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new IllegalStateException("No Auth Provider exists : " + authProvider);
        }
    }
}
