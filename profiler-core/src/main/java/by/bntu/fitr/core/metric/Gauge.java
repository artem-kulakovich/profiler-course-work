package by.bntu.fitr.core.metric;

import java.time.LocalDateTime;
import java.util.Map;

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
