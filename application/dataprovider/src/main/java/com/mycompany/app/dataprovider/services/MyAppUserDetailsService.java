package com.mycompany.app.dataprovider.services;

import com.mycompany.app.dataprovider.entity.MyAppUserEntity;
import com.mycompany.app.dataprovider.entity.MyAppUserDetails;
import com.mycompany.app.dataprovider.repository.MyAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyAppUserDetailsService implements UserDetailsService {
    @Autowired
    MyAppUserRepository myAppUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyAppUserEntity> myAppUser = myAppUserRepository.findByUserName(username);

        myAppUser.orElseThrow(() -> new UsernameNotFoundException(username +"- user name not found"));

        return myAppUser.map(MyAppUserDetails::new).get();
    }
}
