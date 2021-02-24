package br.com.alps.vuttr.config.validacao.errors.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/tools/users").permitAll()
                .antMatchers(HttpMethod.GET, "/api/tools/users").authenticated()
                .antMatchers(HttpMethod.GET, "/api/tools").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/tools/*").authenticated()
                .antMatchers(HttpMethod.POST, "/api/tools").authenticated();
    }
}
