package org.cragdatabase.data;

import org.cragdatabase.models.RouteSummary;

import java.util.List;

public interface RouteSummaryRepository {

    List<RouteSummary> findByRouteId(int routeId);

    RouteSummary findById(int routeSummaryId);

    RouteSummary add(RouteSummary routeSummary);

    boolean update(RouteSummary routeSummary);

    boolean deleteById(int routeSummaryId);
}
