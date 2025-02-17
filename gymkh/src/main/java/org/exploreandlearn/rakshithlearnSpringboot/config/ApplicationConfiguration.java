package org.exploreandlearn.rakshithlearnSpringboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.exploreandlearn.auth"})
public class ApplicationConfiguration {
}
