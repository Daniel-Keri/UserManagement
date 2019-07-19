package com.stages.stage1.security;

import com.stages.stage1.entity.ParentUser;
import com.stages.stage1.enums.AccessRight;
import com.stages.stage1.repository.parentUser.ParentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        Set<AccessRight> grantedAuthorities = new HashSet<>();

        switch (parentUser.getAccessRight()) {
            case ROLE_USER:
                grantedAuthorities.add(AccessRight.ROLE_USER);
                return new CustomUserImpl(parentUser, grantedAuthorities);
            case ROLE_ADMIN:
                grantedAuthorities.add(AccessRight.ROLE_ADMIN);
                return new CustomUserImpl(parentUser, grantedAuthorities);
            case ROLE_SUPER_ADMIN:
                grantedAuthorities.add(AccessRight.ROLE_SUPER_ADMIN);
                return new CustomUserImpl(parentUser, grantedAuthorities);
            default:
                throw new IllegalArgumentException();
        }
    }
}
