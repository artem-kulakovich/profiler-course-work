package by.bntu.fitr.profiler.core.facade;


import java.util.List;

public class PreRequestFacadeExecutorImpl implements PreRequestFacadeExecutor {

    @Override
    public void doOperation(List<PreRequestFacade> preRequestFacades) {
        for (PreRequestFacade preRequestFacade : preRequestFacades) {
            preRequestFacade.loadOnStartUp();
        }
    }
}
