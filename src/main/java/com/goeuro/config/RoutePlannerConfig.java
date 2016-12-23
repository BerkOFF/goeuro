package com.goeuro.config;

import com.goeuro.data.RoutesReader;
import com.goeuro.service.RoutePlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class RoutePlannerConfig {

    @Value("${dataSource:}")
    String dataSource;

    @Bean
    @Autowired
    public RoutePlanner routePlanner(ApplicationArguments args) throws IOException {
        RoutePlanner routePlanner = new RoutePlanner();
        RoutesReader routesReader = routesReader(args);
        routesReader.loadRoutesData().forEach(routeData -> routePlanner.registerRoute(routeData[0],
                IntStream.of(routeData).skip(1).boxed().collect(Collectors.toList())));
        return routePlanner;
    }

    @Bean
    @Autowired
    public RoutesReader routesReader(ApplicationArguments args) {
        if (StringUtils.hasText(dataSource)) {
            return new RoutesReader(dataSource);
        }
        if (args.getSourceArgs().length == 0) {
            throw new IllegalStateException("No input file was specified");
        }
        return new RoutesReader(args.getSourceArgs()[0]);
    }
}
