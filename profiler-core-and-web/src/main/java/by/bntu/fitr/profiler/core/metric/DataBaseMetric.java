package by.bntu.fitr.profiler.core.metric;

public class DataBaseMetric extends AbstractMetric {
    private static Long idGenerator = 1l;
    private Long id;
    private String connectionName;
    private Long executionTimeStart;
    private Long executionTimeEnd;
    private Long executionTime;

    public DataBaseMetric() {
        id = idGenerator++;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public Long getExecutionTimeStart() {
        return executionTimeStart;
    }

    public void setExecutionTimeStart(Long executionTimeStart) {
        this.executionTimeStart = executionTimeStart;
    }

    public Long getExecutionTimeEnd() {
        return executionTimeEnd;
    }

    public void setExecutionTimeEnd(Long executionTimeEnd) {
        this.executionTimeEnd = executionTimeEnd;
    }

    public Long getExecutionTime() {
        return executionTimeEnd - executionTimeStart;
    }


    @Override
    public String toString() {
        return "DataBaseMetric{" +
                "connectionName='" + connectionName + '\'' +
                ", startedDate=" + getStartedDate() +
                ", executionTimeStart=" + executionTimeStart +
                ", executionTimeEnd=" + executionTimeEnd +
                ", executionTime=" + (executionTimeEnd - executionTimeStart) +
                '}';
    }
}
