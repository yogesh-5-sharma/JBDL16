package com.example.QAapp.security.userinfo;

import java.util.Map;

public class GithubOAuth2UserInfo extends OAuth2UserInfo {

    public GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return super.getAttributes().get("id").toString();
    }

    @Override
    public String getName() {
        return (String) super.getAttributes().get("name");
    }

    @Override
    public String getEmail() {
        return (String) super.getAttributes().get("email");
    }
}
