package com.example.QAapp.security.userinfo;

import java.util.Map;

public abstract class OAuth2UserInfo {
    private Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();
}
