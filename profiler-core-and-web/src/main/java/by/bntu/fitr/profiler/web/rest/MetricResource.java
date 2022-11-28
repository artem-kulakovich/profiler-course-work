package by.bntu.fitr.profiler.web.rest;



import by.bntu.fitr.profiler.core.constant.CommonConstant;
import by.bntu.fitr.profiler.core.metric.DataBaseMetric;
import by.bntu.fitr.profiler.core.metric.TimedMetric;
import by.bntu.fitr.profiler.core.metric.registry.MetricRegistry;
import by.bntu.fitr.profiler.core.service.DataBaseMetricService;
import by.bntu.fitr.profiler.core.service.impl.DataBaseExecutionServiceImpl;
import by.bntu.fitr.profiler.core.service.impl.DataBaseMetricServiceImpl;
import by.bntu.fitr.profiler.core.util.CommonUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/metrics")
public class MetricResource {
    private final DataBaseMetricService dataBaseExecutionService = new DataBaseMetricServiceImpl(new DataBaseExecutionServiceImpl());

    @GET
    @Path("/timed")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TimedMetric> getTimedMetricStatistics() {
        return MetricRegistry.getInstance().getMetricRegistry(CommonConstant.TIMED_METRIC_REGISTRY).getMetrics();
    }

    @GET
    @Path("/database")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DataBaseMetric> getDataBaseMetricStatistics() {
        return MetricRegistry.getInstance().getMetricRegistry(CommonConstant.DATABASE_METRIC_REGISTRY).getMetrics();
    }

    @POST
    @Path("/collect/database-connection-time")
    public void collectDataBaseConnectionTimeMetric() {
        dataBaseExecutionService.getConnectionTimeExecution();
    }

    @POST
    @Path("/collect/database-sql")
    public void collectDataBaseSqlTimeMetric(@PathParam("sql") final String sql,
                                             @PathParam("sqlParams") final String sqlParams) {
        dataBaseExecutionService.getSqlTimeExecution(sql, CommonUtil.parseSqlParams(sql));
    }
}
