package chileayuda.personfinder.utils.config;

import java.util.Properties;

/**
 * Created by teo on 05/06/15.
 */
public class PeoplefinderProperties {
    public Properties properties;

    public static final String DEFAULT_PROPERTIES_FILE_PATH = "peoplefinder.properties";
    public static final String ALTERNATE_PROPERTIES_FILE_PATH = "peoplefinder.properties";
    public String propertiesFilePath = DEFAULT_PROPERTIES_FILE_PATH;
    public static final String DEFAULT_PERSISTENT_STORE_DIR = "./persistent_store";
    public static final String DEFAULT_PERSISTENT_STORE_FILE_NAME = "peoplefinder.pjson";
    public static final String DEFAULT_PERSISTENT_STORE_PATH =
            DEFAULT_PERSISTENT_STORE_DIR + "/" + DEFAULT_PERSISTENT_STORE_FILE_NAME;
    public String persistent_store_dir = DEFAULT_PERSISTENT_STORE_DIR;
    public static final int DEFAULT_APP_SERVER_PORT = 8980;


}
