package by.bntu.fitr.core;

import java.time.LocalDateTime;
import java.util.Vector;


public class TimedMetric {
    public static Long idGenerator = 1l;
    private Long id;
    private String methodName;
    private String className;
    private Long executionTime;
    private Long executionTimeStart;
    private Long executionTimeEnd;
    private LocalDateTime startedDate;
    private LocalDateTime endedDate;

    public TimedMetric() {
        id = idGenerator++;
    }


    public Long getExecutionTime() {
        return executionTimeEnd - executionTimeStart;
    }

    public LocalDateTime getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(LocalDateTime startedDate) {
        this.startedDate = startedDate;
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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "TimedMetric{" +
                "id=" + id +
                ", methodName='" + methodName + '\'' +
                ", className='" + className + '\'' +
                ", executionTime=" + (executionTimeEnd - executionTimeStart) +
                ", executionTimeStart=" + executionTimeStart +
                ", executionTimeEnd=" + executionTimeEnd +
                ", startedDate=" + startedDate +
                ", endedDate=" + endedDate +
                '}';
    }
}
