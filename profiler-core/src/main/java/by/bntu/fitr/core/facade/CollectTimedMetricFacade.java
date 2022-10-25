package by.bntu.fitr.core.facade;

import by.bntu.fitr.core.bytecode.ByteCodeExecutor;
import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.context.ApplicationContext;
import by.bntu.fitr.core.context.PropertyContext;
import by.bntu.fitr.core.service.ClassCollectorService;

import java.util.List;
import java.util.Map;

public class CollectTimedMetricFacade implements PreRequestFacade {
    private final ApplicationContext applicationContext = ApplicationContext.getInstance();
    private final ClassCollectorService classCollectorService;
    private final ByteCodeExecutor byteCodeExecutor;

    public CollectTimedMetricFacade(final ClassCollectorService classCollectorService,
                                    final ByteCodeExecutor byteCodeExecutor) {
        this.classCollectorService = classCollectorService;
        this.byteCodeExecutor = byteCodeExecutor;
    }

    @Override
    public void loadOnStartUp() {
        Map<Class, List<String>> classMethods = classCollectorService.getMethodsMarkedWithAnnotations(
                CommonConstant.TIMED_ANNOTATION_NAME,
                applicationContext.getClassesWithMetricAnnotation());
        byteCodeExecutor.redefineMethodForTimedMetric(classMethods);
    }
}
