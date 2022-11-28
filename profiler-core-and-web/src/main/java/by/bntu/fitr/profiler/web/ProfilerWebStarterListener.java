package by.bntu.fitr.profiler.web;

import by.bntu.fitr.profiler.core.bytecode.ByteBuddyExecutor;
import by.bntu.fitr.profiler.core.bytecode.ByteCodeExecutor;
import by.bntu.fitr.profiler.core.facade.PreRequestFacade;
import by.bntu.fitr.profiler.core.facade.PreRequestFacadeExecutor;
import by.bntu.fitr.profiler.core.facade.PreRequestFacadeExecutorImpl;
import by.bntu.fitr.profiler.core.facade.ProfilerStartUpFacade;
import by.bntu.fitr.profiler.core.service.ClassCollectorService;
import by.bntu.fitr.profiler.core.service.ProfilerCoreService;
import by.bntu.fitr.profiler.core.service.PropertyService;
import by.bntu.fitr.profiler.core.service.impl.ClassCollectorServiceImpl;
import by.bntu.fitr.profiler.core.service.impl.ProfilerCoreServiceImpl;
import by.bntu.fitr.profiler.core.service.impl.PropertyServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;

@WebListener
public class ProfilerWebStarterListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PreRequestFacadeExecutor preRequestFacadeExecutor = new PreRequestFacadeExecutorImpl();
        ByteCodeExecutor byteCodeExecutor = new ByteBuddyExecutor();
        PropertyService propertyService = new PropertyServiceImpl();
        ClassCollectorService classCollectorService = new ClassCollectorServiceImpl();
        ProfilerCoreService profilerCoreService = new ProfilerCoreServiceImpl(propertyService, classCollectorService, byteCodeExecutor);
        PreRequestFacade profilerStartUp = new ProfilerStartUpFacade(profilerCoreService, servletContextEvent.getServletContext().getClassLoader());
        preRequestFacadeExecutor.doOperation(Arrays.asList(profilerStartUp));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
