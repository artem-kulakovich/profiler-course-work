package by.bntu.fitr.core.facade;

import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.context.PropertyContext;
import by.bntu.fitr.core.service.ProfilerCoreService;

import java.util.List;

public class ProfilerStartUpFacade implements PreRequestFacade {
    private ProfilerCoreService profilerCoreService;
    private final PropertyContext propertyContext = PropertyContext.getInstance();

    private ClassLoader classLoader;

    public ProfilerStartUpFacade(final ProfilerCoreService profilerCoreService, final ClassLoader classLoader) {
        this.profilerCoreService = profilerCoreService;
        this.classLoader = classLoader;
    }

    @Override
    public void loadOnStartUp() {
        profilerCoreService.initializeContext(classLoader);
        profilerCoreService.redefineClasses(classLoader, propertyContext.getPackageForScan(),
                CommonConstant.TIMED_ANNOTATION_NAME);
        profilerCoreService.registerMetric(CommonConstant.TIMED_METRIC_REGISTRY);
        profilerCoreService.registerMetric(CommonConstant.GAUGE_REGISTRY);
    }
}
