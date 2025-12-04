package com.victor.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

    private transient com.victor.entity.User usuario;

    public JwtUserDetails(com.victor.entity.User usuario) {
        super(usuario.getUsername(), usuario.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }

    public String getId() {
        return this.usuario.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        JwtUserDetails that = (JwtUserDetails) obj;
        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

}
