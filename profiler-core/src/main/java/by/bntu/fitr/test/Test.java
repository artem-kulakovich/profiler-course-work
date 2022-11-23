package by.bntu.fitr.test;

import by.bntu.fitr.core.annotations.Metric;
import by.bntu.fitr.core.annotations.Timed;

@Metric
public class Test {

    @Timed
    public Object test() {
        return 1;
    }
}
