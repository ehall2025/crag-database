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
            RouteSummary added = routeSummaryRepository.add(summary);
            if (added != null) {
                result.setpayload(added);
            } else {
                result.addErrorMessage("failed to add route summary", ResultType.NOT_FOUND);
            }
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
        if (routeId <= 0) {
            return false;
        }

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
            result.addErrorMessage("Quality can not be negative", ResultType.INVALID);
        }

        if (summary.getDangerRating() < 0) {
            result.addErrorMessage("Danger Rating can not be negative", ResultType.INVALID);
        }

        return result;
    }


    private RouteSummary combineSummaries(List<RouteSummary> summaries) {
        int difficultyTotal = 0;
        int qualityTotal = 0;
        int dangerTotal = 0;
        int numDifficultySuggestions = summaries.size();
        int numQualitySuggestions = summaries.size();

        for (RouteSummary summary: summaries) {
            if (summary.getDifficultyRating() == 0) {
                numDifficultySuggestions--;
            } else {
                difficultyTotal += summary.getDifficultyRating();
            }

            if (summary.getQualityRating() == 0) {
                numQualitySuggestions--;
            } else {
                qualityTotal += summary.getQualityRating();
            }

            dangerTotal = Integer.max(dangerTotal, summary.getDangerRating());
        }

        RouteSummary combinedSummary = new RouteSummary();
        combinedSummary.setRouteId(summaries.get(0).getRouteId());
        combinedSummary.setUserId(summaries.get(0).getUserId());
        combinedSummary.setDifficultyRating(difficultyTotal / numDifficultySuggestions);
        combinedSummary.setQualityRating(qualityTotal / numQualitySuggestions);
        combinedSummary.setDangerRating(dangerTotal);

        return combinedSummary;
    }
}
