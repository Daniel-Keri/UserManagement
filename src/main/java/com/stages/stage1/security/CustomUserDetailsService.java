package com.stages.stage1.security;

import com.stages.stage1.entity.ParentUser;
import com.stages.stage1.entity.WebsiteUser;
import com.stages.stage1.enums.AccessRight;
import com.stages.stage1.repository.parentUser.ParentUserRepository;
import com.stages.stage1.repository.websiteUser.WebsiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ParentUserRepository parentUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ParentUser parentUser = parentUserRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException(username));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        switch (parentUser.getAccessRight()) {
            case ROLE_USER:
                grantedAuthorities.add(new SimpleGrantedAuthority(AccessRight.ROLE_USER.toString()));
                return new CustomUserImpl(parentUser, grantedAuthorities);
            case ROLE_ADMIN:
                grantedAuthorities.add(new SimpleGrantedAuthority(AccessRight.ROLE_ADMIN.toString()));
                return new CustomUserImpl(parentUser, grantedAuthorities);
            case ROLE_SUPER_ADMIN:
                grantedAuthorities.add(new SimpleGrantedAuthority(AccessRight.ROLE_SUPER_ADMIN.toString()));
                return new CustomUserImpl(parentUser, grantedAuthorities);
            default:
                throw new IllegalArgumentException();
        }
    }
}
