package chaos.core.constant;


import chaos.utils.PropertiesConfig;

/**
 * ©chaos
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-08-05
 */
public interface CommonProperties {

    PropertiesConfig _mysql = PropertiesConfig.init("mysql.properties");
    PropertiesConfig _file = PropertiesConfig.init("file.properties");
    PropertiesConfig _email = PropertiesConfig.init("email.properties");
    PropertiesConfig _sms = PropertiesConfig.init("sms.properties");
    PropertiesConfig _kaptcha = PropertiesConfig.init("kaptcha.properties");
    PropertiesConfig _im4java = PropertiesConfig.init("im4java.properties");
    PropertiesConfig _wechat4j = PropertiesConfig.init("wechat4j.properties");
    PropertiesConfig _weedfs = PropertiesConfig.init("weedfs.properties");
    PropertiesConfig _project = PropertiesConfig.init("project.properties");
}
