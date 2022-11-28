package by.bntu.fitr.profiler.core.exception.handler;


import by.bntu.fitr.profiler.core.exception.ProfilerCoreException;

public class ProfilerCoreExceptionHandler {
    private static ProfilerCoreExceptionHandler instance;

    private ProfilerCoreExceptionHandler() {

    }

    public synchronized static ProfilerCoreExceptionHandler getInstance() {
        if (instance == null) {
            instance = new ProfilerCoreExceptionHandler();
        }
        return instance;
    }


    public void handle(final boolean statement, final boolean expectedResult, final String msg) {
        if (statement == expectedResult) {
            throw new ProfilerCoreException(msg);
        }
    }

    public void handle(final String msg) {
        throw new ProfilerCoreException(msg);
    }
}
