package by.bntu.fitr.core.bytecode;

import by.bntu.fitr.core.context.ApplicationContext;
import by.bntu.fitr.core.context.PropertyContext;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;


import java.util.List;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ByteBuddyExecutor implements ByteCodeExecutor {
    private final ApplicationContext applicationContext = ApplicationContext.getInstance();

    @Override
    public void redefineMethodForTimedMetric(final Map<Class, List<String>> classMethodNames) {
        for (Map.Entry<Class, List<String>> entrySet : classMethodNames.entrySet()) {
            for (String methodName : entrySet.getValue()) {
                ByteBuddyAgent.install();
                new ByteBuddy()
                        .redefine(entrySet.getKey())
                        .visit(Advice.to(TimedMetricAdvice.class).on(ElementMatchers.isMethod()).method(named(methodName)))
                        .make()
                        .load(
                                applicationContext.getClassLoader(),
                                ClassReloadingStrategy.fromInstalledAgent());
            }
        }
    }
}
