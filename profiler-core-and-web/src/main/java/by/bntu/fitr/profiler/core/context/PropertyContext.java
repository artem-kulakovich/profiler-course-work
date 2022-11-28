package by.bntu.fitr.profiler.core.context;

import java.util.Properties;

public class PropertyContext {
    private static PropertyContext instance;
    private Properties properties;
    private String packageForScan;
    private String dataBaseUser;
    private String dataBasePassword;
    private String dataBaseHost;
    private boolean isDataBaseMetricShouldBeCollected;

    private PropertyContext() {

    }

    public synchronized static PropertyContext getInstance() {
        if (instance == null) {
            instance = new PropertyContext();
        }
        return instance;
    }

    public static void setInstance(PropertyContext instance) {
        PropertyContext.instance = instance;
    }

    public void setPackageForScan(String packageForScan) {
        this.packageForScan = packageForScan;
    }

    public String getPackageForScan() {
        return packageForScan;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public boolean isDataBaseMetricShouldBeCollected() {
        return isDataBaseMetricShouldBeCollected;
    }

    public void setDataBaseMetricShouldBeCollected(boolean dataBaseMetricShouldBeCollected) {
        isDataBaseMetricShouldBeCollected = dataBaseMetricShouldBeCollected;
    }

    public String getDataBaseUser() {
        return dataBaseUser;
    }

    public void setDataBaseUser(String dataBaseUser) {
        this.dataBaseUser = dataBaseUser;
    }

    public String getDataBasePassword() {
        return dataBasePassword;
    }

    public void setDataBasePassword(String dataBasePassword) {
        this.dataBasePassword = dataBasePassword;
    }

    public String getDataBaseHost() {
        return dataBaseHost;
    }

    public void setDataBaseHost(String dataBaseHost) {
        this.dataBaseHost = dataBaseHost;
    }
}
