package com.delinac.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class GatewayApplication {

    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        String host = environment.getProperty("server.host") + ":" + environment.getProperty("server.port");
        String teams = "http://" + environment.getProperty("gateway.teams");
        String footballers = "http://" + environment.getProperty("gateway.footballers");
        System.out.printf("HOST: %s, TEAMS: %s, FOOTBALLERS: %s\n", host, teams, footballers);

        return builder.routes()
                .route("teams", r -> r.host(host)
                        .and()
                        .path("/api/teams/{name}", "/api/teams")
                        .uri(teams))
                .route("footballers", r -> r
                        .host(host)
                        .and()
                        .path("/api/footballers", "/api/footballers/**", "/api/teams/footballers", "/api/teams/{name}/footballers", "/api/teams/{name}/footballers/**")
                        .uri(footballers))
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
