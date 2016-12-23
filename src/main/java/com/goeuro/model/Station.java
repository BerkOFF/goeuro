package com.goeuro.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Station {
    private final int id;
    private final Set<Integer> routeIds = new HashSet<>();

    public Station(int id) {
        this.id = id;
    }

    public void addRoute(int routeId) {
        routeIds.add(routeId);
    }

    public Set<Integer> getRouteIds() {
        return Collections.unmodifiableSet(routeIds);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return id == station.id &&
                Objects.equals(routeIds, station.routeIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeIds);
    }
}
