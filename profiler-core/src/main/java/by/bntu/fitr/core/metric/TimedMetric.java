package by.bntu.fitr.core.metric;

import java.time.LocalDateTime;
import java.util.Vector;


public class TimedMetric extends AbstractMetric {
    public static Long idGenerator = 1l;
    private Long id;
    private Long executionTime;
    private Long executionTimeStart;
    private Long executionTimeEnd;
    private LocalDateTime endedDate;

    public TimedMetric() {
        id = idGenerator++;
    }


    public Long getExecutionTime() {
        return executionTimeEnd - executionTimeStart;
    }

    public LocalDateTime getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(LocalDateTime endedDate) {
        this.endedDate = endedDate;
    }

    public void setExecutionTimeStart(Long executionTimeStart) {
        this.executionTimeStart = executionTimeStart;
    }

    public void setExecutionTimeEnd(Long executionTimeEnd) {
        this.executionTimeEnd = executionTimeEnd;
    }

    @Override
    public String toString() {
        return "TimedMetric{" +
                "id=" + id +
                ", methodName='" + getMethodName() + '\'' +
                ", executionTime=" + (executionTimeEnd - executionTimeStart) +
                ", executionTimeStart=" + executionTimeStart +
                ", executionTimeEnd=" + executionTimeEnd +
                ", startedDate=" + getStartedDate() +
                ", endedDate=" + endedDate +
                '}';
    }
}
