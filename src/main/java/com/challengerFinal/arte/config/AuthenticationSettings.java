package com.challengerFinal.arte.config;

import com.challengerFinal.arte.model.Client;
import com.challengerFinal.arte.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class AuthenticationSettings  extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    ClientRepository clientService;

    public void init(AuthenticationManagerBuilder authenticationUser) throws Exception {

        authenticationUser.userDetailsService(inputName ->{


            Client client = clientService.findByEmail(inputName);

            if (client != null ) {
                if (client.getEmail().contains("@admin")) {
                    return new User(client.getEmail(),client.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                }else {
                    return new User(client.getEmail(),client.getPassword(),
                            AuthorityUtils.commaSeparatedStringToAuthorityList("CLIENT,ARTIST"));
                }
            }else{

                throw new UsernameNotFoundException("User " + inputName + " not found");

            }
        });
    }
    @Bean
    public PasswordEncoder/*codificación de contraseñas.*/ passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //Crea un codificador de contraseña de delegación con asignaciones predeterminadas.
    }
}
