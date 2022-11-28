package by.bntu.fitr.profiler.core.metric;

import java.time.LocalDateTime;

public class Gauge {
    private String info;
    private LocalDateTime startedDate;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LocalDateTime getStartedDate() {
        return startedDate;
    }

    @Override
    public String toString() {
        return "Gauge{" +
                "info='" + info + '\'' +
                ", startedDate=" + startedDate +
                '}';
    }
}
