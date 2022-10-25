package by.bntu.fitr.core.context;

import java.util.List;

public class ApplicationContext {
    private static ApplicationContext instance;
    private List<Class> classesWithMetricAnnotation;
    private ClassLoader classLoader;

    private ApplicationContext() {

    }

    public static synchronized ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public List<Class> getClassesWithMetricAnnotation() {
        return classesWithMetricAnnotation;
    }

    public void setClassesWithMetricAnnotation(List<Class> classesWithMetricAnnotation) {
        this.classesWithMetricAnnotation = classesWithMetricAnnotation;
    }
}
