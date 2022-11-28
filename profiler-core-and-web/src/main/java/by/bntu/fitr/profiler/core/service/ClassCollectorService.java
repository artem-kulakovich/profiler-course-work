package by.bntu.fitr.profiler.core.service;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

public interface ClassCollectorService {

    List<Class> getClassesMarkedWithAnnotation(final String annotationName, final List<String> classesName);

    List<String> getClassesNameWithPackages(final String rootPath, final String packageForScan);

    Map<Class, List<String>> getMethodsMarkedWithAnnotations(final String annotationName,
                                                             final List<Class> classes);

    boolean isCorrectAnnotated(final Annotation annotation, final String annotationName);
}
