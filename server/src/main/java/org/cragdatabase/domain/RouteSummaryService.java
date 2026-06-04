package org.cragdatabase.domain;

import org.cragdatabase.data.RouteSummaryRepository;
import org.cragdatabase.domain.results.Result;
import org.cragdatabase.domain.results.ResultType;
import org.cragdatabase.models.RouteSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteSummaryService {

    @Autowired
    private final RouteSummaryRepository routeSummaryRepository;

    public RouteSummaryService(RouteSummaryRepository routeSummaryRepository) {
        this.routeSummaryRepository = routeSummaryRepository;
    }

    public Result<RouteSummary> findByRouteId(int routeId) {
        Result<RouteSummary> result = new Result<>();

        List<RouteSummary> summaries = routeSummaryRepository.findByRouteId(routeId);

        result.setpayload(combineSummaries(summaries));

        return result;
    }

    public Result<RouteSummary> add(RouteSummary summary) {
        Result<RouteSummary> result = validateSummary(summary);

        if (result.isSuccess()) {
            result.setpayload(routeSummaryRepository.add(summary));
        }

        return result;
    }

    public Result<RouteSummary> update(RouteSummary summary) {
        Result<RouteSummary> result = validateSummary(summary);

        if (!result.isSuccess()) {
            return result;
        }

        if (!routeSummaryRepository.update(summary)) {
            result.addErrorMessage("failed to find Route summary by that id", ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int routeId) {
        return routeSummaryRepository.deleteById(routeId);
    }

    private Result<RouteSummary> validateSummary(RouteSummary summary) {
        Result<RouteSummary> result = new Result<>();

        if (summary.getUserId() <= 0 || summary.getRouteId() <= 0) {
            result.addErrorMessage("Route summary must have userId and RouteId >= 1", ResultType.INVALID);
        }

        if (summary.getDifficultyRating() < 0) {
            result.addErrorMessage("Difficulty can not be negative", ResultType.INVALID);
        }

        if (summary.getQualityRating() < 0) {
            result.addErrorMessage("Difficulty can not be negative", ResultType.INVALID);
        }

        if (summary.getDangerRating() < 0) {
            result.addErrorMessage("Difficulty can not be negative", ResultType.INVALID);
        }

        return result;
    }


    private RouteSummary combineSummaries(List<RouteSummary> summaries) {
        int difficultyTotal = 0;
        int qualityTotal = 0;
        int dangerTotal = 0;

        for (RouteSummary summary: summaries) {
            difficultyTotal += summary.getDifficultyRating();
            qualityTotal += summary.getQualityRating();
            dangerTotal = Integer.max(dangerTotal, summary.getDangerRating());
        }

        RouteSummary combinedSummary = summaries.get(0);
        combinedSummary.setDifficultyRating(difficultyTotal / summaries.size());
        combinedSummary.setQualityRating(qualityTotal / summaries.size());
        combinedSummary.setDangerRating(dangerTotal);

        return combinedSummary;
    }
}
