package fr.codecake.ecom.shared.authentication.infrastructure.primary;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize ->
      authorize
        .requestMatchers(HttpMethod.GET, "api/categories").permitAll()
        .requestMatchers(HttpMethod.GET, "api/products-shop/**").permitAll()
        .requestMatchers(HttpMethod.GET, "api/orders/get-card-details").permitAll()
        .requestMatchers(HttpMethod.GET, "api/oders/webhook").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/**").authenticated())
      .csrf(AbstractHttpConfigurer::disable)
      .oauth2ResourceServer(
        oauth -> oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(new KindeJwtAuthenticationConverter())));
    return http.build();
  }

}
