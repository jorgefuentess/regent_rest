package com.regent.security;

import com.regent.security.RESTAuthenticationEntryPoint;
 import com.regent.security.RESTAuthenticationFailureHandler;
 import com.regent.security.RESTAuthenticationSuccessHandler;
 import com.regent.util.Config;
 import java.util.Arrays;
 import javax.sql.DataSource;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.http.HttpStatus;
 import org.springframework.security.authentication.encoding.PasswordEncoder;
 import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
 import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
 import org.springframework.security.web.AuthenticationEntryPoint;
 import org.springframework.security.web.authentication.AuthenticationFailureHandler;
 import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
 import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
 import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
 import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
 import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
 import org.springframework.security.web.header.HeaderWriter;
 import org.springframework.security.web.header.writers.StaticHeadersWriter;
 import org.springframework.web.cors.CorsConfiguration;
 import org.springframework.web.cors.CorsConfigurationSource;
 import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
 import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
 
 @Configuration
 @EnableWebSecurity
 public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   @Autowired
   private DataSource dataSource;
   @Autowired
   private RESTAuthenticationEntryPoint authenticationEntryPoint;
   
   protected void configure(HttpSecurity http) throws Exception {
     ((HttpSecurity)((HttpSecurity)((FormLoginConfigurer)((FormLoginConfigurer)((HttpSecurity)((FormLoginConfigurer)((HttpSecurity)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)http.sessionManagement().invalidSessionUrl(String.valueOf(Config.IP) + "/registro")
       .and())
       .authorizeRequests()
       .antMatchers(new String[] { "/", "/login", "/getLogueado", "/ingresoIncorrecto", "/olvidoClave", "/getInfoEntidades", 
           "/getAllInstitucionHabilitanteActivas", "/getAllTipoEntidadActivos", "/getAllTipoEntidadTipoDescuento", 
           "/nuevaSolicitudEntidad", "/getAllTipoDescuentoVigente", "/generarPdfSolicitud", "/getPdfSolicitud", "/deletePdfSolicitud" })).permitAll()
       .anyRequest()).authenticated()
       .and())
       
       .exceptionHandling().authenticationEntryPoint((AuthenticationEntryPoint)this.authenticationEntryPoint)
       .and())
       
       .formLogin().successHandler((AuthenticationSuccessHandler)this.authenticationSuccessHandler))
       .and())
       
       .formLogin().failureHandler((AuthenticationFailureHandler)this.authenticationFailureHandler))
       .failureUrl("/login.html"))
       .and())
       .logout()
       .logoutUrl("/logout")
       .clearAuthentication(true)
       .invalidateHttpSession(true)
       .deleteCookies(new String[] { "JSESSIONID"
         }).logoutSuccessHandler((LogoutSuccessHandler)new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
       .and())
       .rememberMe()
       .alwaysRemember(true)
       .tokenRepository(persistentTokenRepository())
       .tokenValiditySeconds(5400);
     http.csrf().disable();
     http.cors();
     http.headers().addHeaderWriter((HeaderWriter)new StaticHeadersWriter("Access-Control-Allow-Origin", new String[] { Config.IP }));
     http.headers().addHeaderWriter((HeaderWriter)new StaticHeadersWriter("Access-Control-Allow-Methods", new String[] { "GET,POST,PUT,DELETE,OPTIONS" }));
     http.headers().addHeaderWriter((HeaderWriter)new StaticHeadersWriter("Access-Control-Allow-Headers", new String[] { "Content-Type,Authorization,Origin" }));
     http.headers().addHeaderWriter((HeaderWriter)new StaticHeadersWriter("Access-Control-Allow-Credentials", new String[] { "true" }));
     http.headers().addHeaderWriter((HeaderWriter)new StaticHeadersWriter("Access-Control-Expose-Headers", new String[] { "Authorization" }));
   } @Autowired
   private RESTAuthenticationSuccessHandler authenticationSuccessHandler; @Autowired
   private RESTAuthenticationFailureHandler authenticationFailureHandler; @Bean
   CorsConfigurationSource corsConfigurationSource() {
     CorsConfiguration configuration = new CorsConfiguration();
     configuration.setAllowedMethods(Arrays.asList(new String[] { "GET", "POST", "PUT", "DELETE", "OPTIONS" }));
     configuration.setAllowCredentials(Boolean.valueOf(true));
     configuration.setAllowedHeaders(Arrays.asList(new String[] { "*" }));
     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     source.registerCorsConfiguration("/**", configuration);
     return (CorsConfigurationSource)source;
   }
   
   @Bean
   PersistentTokenRepository persistentTokenRepository() {
     JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
     db.setDataSource(this.dataSource);
     return (PersistentTokenRepository)db;
   }
 
   
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.jdbcAuthentication()
       .dataSource(this.dataSource)
       .usersByUsernameQuery("SELECT lower(nombre_usuario),password,1 FROM usuarios WHERE nombre_usuario=to_char(?) AND estado='A' ")
       .authoritiesByUsernameQuery("SELECT lower(nombre_usuario), f.path FROM usuarios u INNER JOIN roles r on u.codigo_rol=r.codigo_rol INNER JOIN rol_funcion rf on r.CODIGO_ROL=rf.CODIGO_ROL INNER JOIN funciones f on rf.CODIGO_FUNCION=f.CODIGO_FUNCION WHERE nombre_usuario=to_char(?) and rf.estado='A' ")
       .passwordEncoder((PasswordEncoder)new Md5PasswordEncoder());
   }
 }