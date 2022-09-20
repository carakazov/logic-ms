package notes.project.logic.utils;

import lombok.experimental.UtilityClass;
import notes.project.logic.config.ApplicationProperties;

@UtilityClass
public class ApplicationPropertiesUtils {
    public static ApplicationProperties applicationProperties() {
        return new ApplicationProperties();
    }
}
