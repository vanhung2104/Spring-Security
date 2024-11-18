package ute.hung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ute.hung.entity.UserInfo;
import ute.hung.repository.UserInfoRepository;

import java.util.Optional;

public class UserInfoService implements UserDetailsService {
    @Autowired
    UserInfoRepository repository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.repository = userInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        return userInfo.map(UserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
    }

}
