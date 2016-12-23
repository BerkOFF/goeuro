package com.goeuro.service;

import com.goeuro.model.Route;
import com.goeuro.model.Station;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RoutePlanner {
    private final Map<Integer, Station> stations = new HashMap<>();
    private final Map<Integer, Route> routes = new HashMap<>();

    public void registerRoute(int routeId, Collection<Integer> stationIds) {
        routes.put(routeId, new Route(routeId, stationIds));
        stationIds.forEach(stationId ->
                stations.computeIfAbsent(stationId, Station::new).addRoute(routeId));
    }

    public boolean hasDirectConnection(int originStationId, int destinationStationId) {
        if (!stations.containsKey(originStationId) || !stations.containsKey(destinationStationId)) {
            throw new IllegalArgumentException("Invalid station ids were provided");
        }
        return stations.get(originStationId).getRouteIds()
                .parallelStream().anyMatch(routeId -> routes.get(routeId).hasStations(destinationStationId));
    }
}
