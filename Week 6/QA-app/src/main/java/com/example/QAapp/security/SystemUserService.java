package com.example.QAapp.security;

import com.example.QAapp.models.AuthProvider;
import com.example.QAapp.repository.SystemUserRepository;
import com.example.QAapp.security.userinfo.OAuth2UserInfo;
import com.example.QAapp.security.userinfo.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService extends DefaultOAuth2UserService implements UserDetailsService {

    @Autowired
    SystemUserRepository systemUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return systemUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username: %s not found", username)));
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
       OAuth2User user = super.loadUser(userRequest);

       // "google"
        // "github"
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(userRequest.getClientRegistration().getRegistrationId(),
                user.getAttributes());

        String username = userInfo.getId();

        if(systemUserRepository.findByUsername(username).isEmpty()) {
            SystemUser systemUser = SystemUser.builder()
                    .username(username)
                    .role(SystemUserRoles.Author)
                    .provider(AuthProvider.valueOf(userRequest.getClientRegistration().getRegistrationId()))
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(true)
                    .build();

            systemUserRepository.save(systemUser);
        }

       return user;
    }
}
