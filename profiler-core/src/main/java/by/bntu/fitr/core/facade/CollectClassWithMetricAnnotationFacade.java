package by.bntu.fitr.core.facade;

import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.constant.ExceptionConstant;
import by.bntu.fitr.core.context.ApplicationContext;
import by.bntu.fitr.core.context.PropertyContext;
import by.bntu.fitr.core.exception.NotValidPackageForScanException;
import by.bntu.fitr.core.service.ClassCollectorService;
import by.bntu.fitr.core.util.FileUtil;

import java.net.URL;
import java.util.List;

public class CollectClassWithMetricAnnotationFacade implements PreRequestFacade {
    private final PropertyContext propertyContext = PropertyContext.getInstance();
    private final ApplicationContext applicationContext = ApplicationContext.getInstance();
    private final ClassCollectorService classCollectorService;

    public CollectClassWithMetricAnnotationFacade(final ClassCollectorService classCollectorService) {
        this.classCollectorService = classCollectorService;

    }

    @Override
    public void loadOnStartUp() {
        URL rootPath = applicationContext.getClassLoader().getResource(FileUtil.changeSeparator(
                propertyContext.getPackageForScan(),
                CommonConstant.DOT_SEPARATOR,
                CommonConstant.SLASH_SEPARATOR)
        );

        if (rootPath == null) {
            throw new NotValidPackageForScanException(ExceptionConstant.NOT_VALID_PACKAGE_FOR_SCAN_ATTR);
        }

        List<String> classesName = classCollectorService.getClassesNameWithPackages(rootPath.getPath());
        List<Class> classes = classCollectorService.getClassesMarkedWithAnnotation(CommonConstant.METRIC_ANNOTATION_NAME,
                classesName);
        applicationContext.setClassesWithMetricAnnotation(classes);
    }
}
