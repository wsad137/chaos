package chaos.core.commons;

import chaos.core.model.ConfigModel_;
import chaos.core.service.ConfigService_;
import chaos.utils.PropertiesConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.io.File;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by 王健 on 2016-12-28.
 * qq:1413221142
 */
public class ConfigUtils_ {

    private ApplicationContext context;

    private ConfigUtils_(ApplicationContext context) {
        this.context = context;
        configService = context.getBean(ConfigService_.class);
    }

    public ConfigService_ getConfigService() {
        return configService;
    }

    private ConfigService_ configService;

    private static List<PropertiesConfig> configs = new ArrayList<>();

    private static ConfigUtils_ configHelper;

    public static void init(ApplicationContext context) {
        Assert.notNull(context, "ApplicationContext 不能为空！");
        if (configHelper == null) configHelper = new ConfigUtils_(context);

        File temp = null;
        try {
            temp = new File(ConfigUtils_.class.getClassLoader().getResource("").toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Collection<File> files = FileUtils.listFiles(temp, FileFilterUtils.suffixFileFilter("properties"), null);
        configs.clear();
        for (File file : files) {
            PropertiesConfig propertiesUtils = PropertiesConfig.init(file.getName());
            configs.add(propertiesUtils);
        }


    }


    /**
     * 同步配置文件到数据库
     *
     * @return
     */
    public static boolean synchronizeDb() {

        Map<String, Object> configMaps = new TreeMap<>(Comparator.comparing(Function.identity()));

        for (PropertiesConfig config : configs) {
            Iterator<String> keys = config.getKeys();
            while (keys.hasNext()) {
                String key = keys.next();
                configMaps.put(key, config.getString(key));
            }
        }
        List<ConfigModel_> dbModels = getInstance().configService.selectAll();
        Map<String, ConfigModel_> dbMaps = dbModels.stream().collect(Collectors.toMap(ConfigModel_::getKeyMark, Function.identity()));

        for (Map.Entry<String, Object> entry : configMaps.entrySet()) {
//          db 中存有 该配置记录
            if (dbMaps.containsKey(entry.getKey())) {
//              已数据库数据为准
                if (!dbMaps.get(entry.getKey()).equals(entry.getValue())) setValue(entry.getKey(), entry.getValue());
            } else {
//                db 中没有记录 直接添加
                getInstance().configService.insert(new ConfigModel_(entry.getKey(), String.valueOf(entry.getValue())));
            }
        }

        for (ConfigModel_ model : getInstance().configService.selectAll()) {
            System.out.println(model.getKeyMark() + " = " + model.getValue());
        }
        return true;
    }


    private static ConfigUtils_ getInstance() {
        Assert.notNull(configHelper, "ConfigHelper 还未初始化！");
        return configHelper;

    }

    public static <T> T get(String key) {
        return getDef(key, "");
    }

    public static <T> T getDef(String key, String def) {
        if (findPropertie(key) == null) return (T) def;
        def = findPropertie(key).getString(key, def);
        return (T) def;
    }

    private static PropertiesConfig findPropertie(String key) {
        for (PropertiesConfig config : configs) {
            if (config.containsKey(key)) return config;
        }
        return null;
    }

    public static void setValue(String key, Object o) {
        if (findPropertie(key) == null) return;
        findPropertie(key).setProperty(key, o);

    }


    public static boolean deleteByMarkKey(String markKey) {
        return getInstance().configService.deleteByMarkKey(markKey);
    }

    public static ConfigModel_ selectByMarkKey(String markKey) {
        return getInstance().configService.selectByMarkKey(markKey);
    }

    public static boolean saveOrUpdate(ConfigModel_ configModel) {
        return getInstance().configService.saveOrUpdate(configModel);
    }

}
