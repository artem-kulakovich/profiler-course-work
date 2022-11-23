package by.bntu.fitr.core.service.impl;

import by.bntu.fitr.core.annotations.Metric;
import by.bntu.fitr.core.annotations.Timed;
import by.bntu.fitr.core.constant.CommonConstant;
import by.bntu.fitr.core.service.ClassCollectorService;
import by.bntu.fitr.core.util.FileUtil;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassCollectorServiceImpl implements ClassCollectorService {

    @Override
    public List<Class> getClassesMarkedWithAnnotation(final String annotationName, final List<String> classesName) {
        List<Class> classes = new ArrayList<>();
        try {
            for (String className : classesName) {
                Class clazz = Class.forName(className);
                for (Annotation annotation : clazz.getAnnotations()) {
                    if (isCorrectAnnotated(annotation, annotationName)) {
                        classes.add(clazz);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return classes;
    }

    @Override
    public List<String> getClassesNameWithPackages(final String rootPath, final String packageForScan) {
        List<String> classesName = new ArrayList<>();
        getClassesNameWithPackages(new File(rootPath), classesName, packageForScan);
        return classesName;
    }

    @Override
    public Map<Class, List<String>> getMethodsMarkedWithAnnotations(final String annotationName,
                                                                    final List<Class> classes) {
        Map<Class, List<String>> classMethods = new HashMap<>();
        for (Class clazz : classes) {
            for (Method method : clazz.getMethods()) {
                for (Annotation annotation : method.getAnnotations()) {
                    if (isCorrectAnnotated(annotation, annotationName)) {
                        if (classMethods.get(clazz) == null) {
                            List<String> methodNameList = new ArrayList<>();
                            methodNameList.add(method.getName());
                            classMethods.put(clazz, methodNameList);
                        } else {
                            List<String> methodNameList = classMethods.get(clazz);
                            methodNameList.add(method.getName());
                        }
                    }
                }
            }
        }
        return classMethods;
    }

    @Override
    public boolean isCorrectAnnotated(final Annotation annotation, final String annotationName) {
        switch (annotationName) {
            case CommonConstant.TIMED_ANNOTATION_NAME:
                return annotation instanceof Timed;
            case CommonConstant.METRIC_ANNOTATION_NAME:
                return annotation instanceof Metric;
            default:
                return false;
        }
    }


    private void getClassesNameWithPackages(final File rootFile, List<String> classesName, String currentDirectory) {
        if (rootFile.isDirectory()) {
            for (File file : rootFile.listFiles()) {
                if (file.isDirectory()) {
                    getClassesNameWithPackages(file, classesName, currentDirectory + "." + file.getName());
                } else {
                    String fileName = file.getName();
                    if (FileUtil.isFileBelongToJava(fileName)) {
                        classesName.add(currentDirectory + "." + FileUtil.getFileNameWithOutExtension(fileName));
                    }
                }
            }
        }
    }
}
