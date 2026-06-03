package org.cragdatabase.data;

import org.cragdatabase.models.RouteSummary;
import java.util.List;

public interface RouteSummaryRepository {
    List<RouteSummary> findByRouteId(int routeId);

    RouteSummary add(RouteSummary summary);

    boolean update(RouteSummary summary);

    boolean deleteById(int routeId);
}
