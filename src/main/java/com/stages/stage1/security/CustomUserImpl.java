package com.stages.stage1.security;

import com.stages.stage1.entity.ParentUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

@Data
public class CustomUserImpl extends User {

    private final UUID id;
    private final Instant creationDate;

    public CustomUserImpl(ParentUser parentUser, Collection<? extends GrantedAuthority> authorities) {
        super(parentUser.getEmail(), parentUser.getPassword(), authorities);

        this.id = parentUser.getId();
        this.creationDate = parentUser.getCreationDate();
    }
}
