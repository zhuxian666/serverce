//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//    public class configCORS {
//        @Bean
//        public WebMvcConfigurer corsConfigurer() {
//            return new WebMvcConfigurer() {
//                @Override
//                public void addCorsMappings(CorsRegistry registry) {
//                    registry.addMapping("/api/**")
//                            .allowedOrigins("*")
//                            .allowCredentials(true)
//                            .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
//                            .allowedHeaders("*")
//                            .maxAge(3600);
//                }
//            };
//        }
//    }
