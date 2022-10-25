package by.bntu.fitr.core;


import java.util.List;
import java.util.Stack;

public class GaugeRegistry {
    private List<Gauge> gauges;

    public synchronized void addMetric(Gauge gauge) {
        if (gauges == null) {
            gauges = new Stack<>();
        }
        gauges.add(gauge);
    }

    public List<Gauge> getGauges() {
        return gauges;
    }
}
