package com.goeuro.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.IntStream;

public class Route {
    private final int id;
    private final LinkedHashSet<Integer> stationIds;

    public Route(int id, Collection<Integer> stationIds) {
        this.id = id;
        this.stationIds = new LinkedHashSet<>(stationIds);
    }

    public int getId() {
        return id;
    }

    public Collection<Integer> getStations() {
        return Collections.unmodifiableCollection(stationIds);
    }

    public boolean hasStations(Collection<Integer> stationIds) {
        return this.stationIds.containsAll(stationIds);
    }

    public boolean hasStations(int... stationIds) {
        return IntStream.of(stationIds).allMatch(this.stationIds::contains);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id == route.id &&
                Objects.equals(stationIds, route.stationIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stationIds);
    }
}
