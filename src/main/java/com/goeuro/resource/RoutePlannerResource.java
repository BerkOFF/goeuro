package com.goeuro.resource;

import com.goeuro.response.DirectRouteResponse;
import com.goeuro.service.RoutePlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class RoutePlannerResource {
    @Autowired
    private RoutePlanner routePlanner;

    @RequestMapping(path = "direct")
    public DirectRouteResponse checkDirectRoute(@RequestParam int dep_sid, @RequestParam int arr_sid) {
        return new DirectRouteResponse(dep_sid, arr_sid, routePlanner.hasDirectConnection(dep_sid, arr_sid));
    }
}
