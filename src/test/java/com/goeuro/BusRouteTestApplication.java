package com.goeuro;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class BusRouteTestApplication {
    static {
        ClassLoader classLoader = BusRouteTestApplication.class.getClassLoader();
        File file = new File(classLoader.getResource("routes.txt").getFile());
        System.setProperty("dataSource", file.getAbsolutePath());
    }
}
