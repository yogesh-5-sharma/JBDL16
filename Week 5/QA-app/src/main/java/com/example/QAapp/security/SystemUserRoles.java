package com.example.QAapp.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.QAapp.security.SystemUserPermissions.*;

public enum SystemUserRoles {
    PROGRAMMER(Set.of(POSTING.name(), ANSWER.name())),
    ADMIN(Set.of(POSTING.name(), ANSWER.name(), BLOCK.name(), FLAG.name())),
    ASSISTANT(Set.of(FLAG.name()));

    Set<String> permissons;

    SystemUserRoles(Set<String> permissons) {
        this.permissons = permissons;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        for(String permission: this.permissons) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }
}
