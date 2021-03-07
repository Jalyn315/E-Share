package com.cjw.eshare.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author cj.w
 * @date 2020/12/30 22:21
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin implements Serializable, UserDetails {
    private Integer id;
    private String admin_user;
    private String admin_pass;
    private String admin_email;
    private Date login_at;
    private String login_ip;
    private Date create_at;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return admin_pass;
    }


    @Override
    public String getUsername() {
        return admin_user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
