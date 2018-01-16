package chaos.utils;

import chaos.utils.constant.UtilsConstant;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;


/**
 * 配置文件properties自动加载类
 *
 * @author lyh
 * @version 2012-6-5
 * @see PropertiesConfig
 */
public class PropertiesConfig extends PropertiesConfiguration {
    /**
     * 日志
     */
    private static final Logger log = Logger.getLogger(PropertiesConfig.class);

    /**
     * Singleton
     */
    private static PropertiesConfig config;


//    /**
//     * Configuration
//     */
//    private PropertiesConfiguration propConfig;

    /**
     * 自动保存
     */
    private static boolean autoSave = true;

//    public PropertiesUtil(String propertiesFile) {
//
//    }

    /**
     * properties文件路径
     *
     * @param propertiesFile
     * @return
     * @see
     */
    public static PropertiesConfig init(String propertiesFile) {
        config = new PropertiesConfig(propertiesFile);
//        }
        return config;
    }


    /**
     * properties文件路径
     *
     * @return
     * @see
     */
    public static PropertiesConfig getInstance(String name) {
        if (config == null) {
            config = new PropertiesConfig(name);
        }
        return config;
    }

    private void PropertiesUtil() {
    }

    protected PropertiesConfig(String propertiesFile) {
        setEncoding(UtilsConstant._coding.UTF8);
        setBasePath(PropertiesConfig.class.getClassLoader().getResource("").getPath());
        //自动保存
        //setPath(PropertiesUtil.class.getClassLoader().getResource("").getPath());
        setAutoSave(autoSave);
        //自动重新加载
        setReloadingStrategy(new FileChangedReloadingStrategy());
        setFileName(propertiesFile);

    }


    /**
     * 构造器私有化
     */
    private PropertiesConfig() {

    }

//    /**
//     * 初始化
//     *
//     * @param propertiesFile
//     * @see
//     */
//    private void init(String propertiesFile) {
//
//        setEncoding("utf8");
//        setBasePath(PropertiesUtil.class.getClassLoader().getResource("").getPath());
////        propConfig.set
////        propConfig.setLogger();
//        //自动保存
////        propConfig.setPath(PropertiesUtil.class.getClassLoader().getResource("").getPath());
//        setAutoSave(autoSave);
//        //自动重新加载
//        setReloadingStrategy(new FileChangedReloadingStrategy());
//        setFileName(propertiesFile);
//
////        propConfig = new PropertiesConfiguration();
////        propConfig.setEncoding("utf8");
////        propConfig.setBasePath(PropertiesUtil.class.getClassLoader().getResource("").getPath());
//////        propConfig.set
//////        propConfig.setLogger();
////        //自动保存
//////        propConfig.setPath(PropertiesUtil.class.getClassLoader().getResource("").getPath());
////        propConfig.setAutoSave(autoSave);
////        //自动重新加载
////        propConfig.setReloadingStrategy(new FileChangedReloadingStrategy());
////        propConfig.setFileName(propertiesFile);
////        propConfig.addConfigurationListener(new ConfigurationListener() {
////            @Override
////            public void configurationChanged(ConfigurationEvent event) {
////
////            }
////        });
//
////        try {
//////            propConfig.load();
////        } catch (ConfigurationException e) {
////            e.printStackTrace();
////        }
////        return propConfig;
//    }

    /**
     * Test
     *
     * @param args
     * @see
     */

    public static void main(String[] args) {
//        System.out.println(CommonProperties._wechat4j.getString(CommonKey._propertie.wechat4j.appid));
//        CommonProperties._wechat4j.setProperty(CommonKey._propertie.wechat4j.appid,"appid");
//        System.out.println(CommonProperties._wechat4j.getString(CommonKey._propertie.wechat4j.appid));

//        UtilsConstant.File.tempDir.toString();
//        PropertiesUtil.getInstance("_config.properties").setProperty("aaaa", "1234");
//        log.info(PropertiesUtil.getInstance("_config.properties").getValueFromPropFile("pageSize"));
    }

}
