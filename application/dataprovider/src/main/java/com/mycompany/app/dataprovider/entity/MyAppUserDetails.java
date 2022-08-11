package com.mycompany.app.dataprovider.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MyAppUserDetails implements UserDetails {
    @NonNull
    private String userName;

    @NonNull
    private String password;

    @NonNull
    private boolean active;

    @NonNull
    private List<GrantedAuthority> authorities;

    public MyAppUserDetails(MyAppUserEntity myAppUserEntity) {
        this.userName = myAppUserEntity.getUserName();
        this.password = myAppUserEntity.getPassword();
        this.active = myAppUserEntity.isActive();
        this.authorities = Arrays.stream(myAppUserEntity.getRoles().split(","))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

    public static MyAppUserDetails from(MyAppUserEntity myAppUserEntity) {
        MyAppUserDetails myAppUserDetails = new MyAppUserDetails(
                myAppUserEntity.getUserName(), myAppUserEntity.getPassword(),
                myAppUserEntity.isActive(),
                Arrays.stream(myAppUserEntity.getRoles().split(","))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

        return myAppUserDetails;
    }
}
