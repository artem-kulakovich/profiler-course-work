package by.bntu.fitr.core.service.impl;

import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.metric.TimedMetric;
import by.bntu.fitr.core.service.PopulateMetricsService;

import java.time.LocalDateTime;

public class PopulateMetricsServiceImpl implements PopulateMetricsService {

    @Override
    public TimedMetric getPopulatedTimedMetric(final String methodName, final Long executionTimeStart,
                                           final Long executionTimeEnd, final LocalDateTime endedDate) {
        TimedMetric timedMetric = new TimedMetric();
        timedMetric.setMethodName(methodName);
        timedMetric.setMetricType(CommonConstant.TIMED_ANNOTATION_NAME);
        timedMetric.setExecutionTimeEnd(executionTimeEnd);
        timedMetric.setExecutionTimeStart(executionTimeStart);
        timedMetric.setEndedDate(endedDate);
        return timedMetric;
    }

}
