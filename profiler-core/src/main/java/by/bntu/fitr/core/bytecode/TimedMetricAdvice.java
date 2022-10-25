package by.bntu.fitr.core.bytecode;


import by.bntu.fitr.core.MetricRegistry;
import by.bntu.fitr.core.TimedMetric;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;
import java.time.LocalDateTime;


public class TimedMetricAdvice {

    @Advice.OnMethodEnter(suppress = Throwable.class)
    public static void onMethodEnter(@Advice.This Object thisObject,
                                     @Advice.Origin Method method) {
        TimedMetric timedMetric = new TimedMetric();
        timedMetric.setMethodName(method.getName());
        timedMetric.setClassName(String.valueOf(thisObject.getClass()));
        timedMetric.setStartedDate(LocalDateTime.now());
        timedMetric.setExecutionTimeStart(System.currentTimeMillis());
        MetricRegistry.getInstance().getTimedMetricRegistry().addMetric(timedMetric);

    }

    @Advice.OnMethodExit(suppress = Throwable.class, onThrowable = Throwable.class)
    public static void onMethodExit() {
        TimedMetric timedMetric = MetricRegistry.getInstance().getTimedMetricRegistry().getTimedMetric();
        timedMetric.setEndedDate(LocalDateTime.now());
        timedMetric.setExecutionTimeEnd(System.currentTimeMillis());
    }
    /*
    is working
    @RuntimeType
    public static Object intercept(@This Object self) {

        return null;
    }

    /*
    @Advice.OnMethodEnter
    static long invokeBeforeEnterMethod(
            @Advice.Origin String method) {
        ServiceManager.getInstance().getMetricRegistry().addMetric(new TimedMetric().setStartedDate(System.currentTimeMillis()));
        return System.currentTimeMillis();
    }

    @Advice.OnMethodExit
    static void invokeAfterExitMethod(
            @Advice.Origin String method,
            @Advice.Enter long startTime
    ) {
        System.out.println("Method " + method + " took " + (System.currentTimeMillis() - startTime) + "ms");
    }

     */
}


