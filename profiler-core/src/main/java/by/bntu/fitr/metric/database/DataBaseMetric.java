package by.bntu.fitr.metric.database;

import java.time.LocalDateTime;

public class DataBaseMetric {
    private String connectionName;
    private LocalDateTime startedDate;
    private Long executionTimeStart;
    private Long executionTimeEnd;
    private Long executionTime;

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public LocalDateTime getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(LocalDateTime startedDate) {
        this.startedDate = startedDate;
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
                ", startedDate=" + startedDate +
                ", executionTimeStart=" + executionTimeStart +
                ", executionTimeEnd=" + executionTimeEnd +
                ", executionTime=" + (executionTimeEnd - executionTimeStart) +
                '}';
    }
}
