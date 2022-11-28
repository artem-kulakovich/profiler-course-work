package by.bntu.fitr.profiler.core.util;

import java.util.List;

public class CommonUtil {

    public static boolean isValueFromPropertiesCorrected(final String value, final List<String> whiteListValues) {
        for (String whiteListValue : whiteListValues) {
            if (whiteListValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static Object[] parseSqlParams(final String sql) {
        Object[] sqlParams = sql.split(":");
        return sqlParams;
    }
}
