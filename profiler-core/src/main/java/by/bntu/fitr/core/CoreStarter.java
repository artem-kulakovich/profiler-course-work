package by.bntu.fitr.core;

import by.bntu.fitr.core.bytecode.ByteCodeExecutor;
import by.bntu.fitr.core.facade.*;
import by.bntu.fitr.core.service.ClassCollectorService;
import by.bntu.fitr.core.service.PropertyService;

import java.util.Arrays;
import java.util.List;

public class CoreStarter {
    private ClassLoader classLoader;
    private PropertyService propertyService;
    private ClassCollectorService classCollectorService;
    private ByteCodeExecutor byteCodeExecutor;

    public CoreStarter(final ClassLoader classLoader,
                       final PropertyService propertyService,
                       final ClassCollectorService classCollectorService,
                       final ByteCodeExecutor byteCodeExecutor) {
        this.classLoader = classLoader;
        this.propertyService = propertyService;
        this.classCollectorService = classCollectorService;
        this.byteCodeExecutor = byteCodeExecutor;
    }

    public void startUp() {
        List<PreRequestFacade> preRequestFacades = Arrays.asList(
                new InitializeContextsFacade(propertyService, classLoader),
                new CollectClassWithMetricAnnotationFacade(classCollectorService),
                new CollectTimedMetricFacade(classCollectorService, byteCodeExecutor),
                new MetricRegistryFacade()
        );

        PreRequestFacadeExecutor preRequestFacadeExecutor = new PreRequestFacadeExecutorImpl();
        preRequestFacadeExecutor.doOperation(preRequestFacades);

    }
}
