package com.programming.techie.api_gateway.routes;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {

    @Bean
    public RouteLocator productServiceRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                // product Service route
                .route("product-service", r -> r
                        .path("/api/product")
                        .filters(c-> c
                                .circuitBreaker(config -> config
                                        .setName("productServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/product-service"))) //filter end
                        .uri("http://localhost:8080")  //final service uri
                )

                //product service swagger route
                .route("product-service-swagger", r->r.path("/aggregate/product-service/v3/api-docs")
                        .filters(filter->filter.rewritePath("/aggregate/product-service/v3/api-docs","/v3/api-docs"))
                        .uri("http://localhost:8080"))


                //order service route
                .route("order-service",r->r.path("/api/order")
                        .uri("http://localhost:8081"))

                //order service swagger route
                .route("order-service-swagger",r->r.path("/aggregate/order-service/v3/api-docs")
                        .filters(filter->filter.rewritePath("/aggregate/order-service/v3/api-docs","/api-docs"))
                        .uri("http://localhost:8081")
                )

                //inventory service route
                .route("inventory-service",r->r.path("/api/inventory")
                        .uri("http://localhost:8082"))

                //inventory service swagger route
                .route("inventory-service-swagger", r->r.path("/aggregate/inventory-service/v3/api-docs")
                        .filters(filter->filter.rewritePath("/aggregate/inventory-service/v3/api-docs","/api-docs"))
                        .uri("http://localhost:8082"))
                .build();
    }
}
