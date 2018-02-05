package chaos.core.constant;


import chaos.utils.PropertiesConfig;

/**
 * property 文件简写
 * ©chaos
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-08-05
 */
public interface P_ {
    /**
     * 环境配置
     */
    PropertiesConfig env_ = PropertiesConfig.init("env.properties");
    /**
     * 数据库配置
     */
    PropertiesConfig mysql_ = PropertiesConfig.init("mysql.properties");
    /**
     * 文件配置
     */
    PropertiesConfig file_ = PropertiesConfig.init("file.properties");
    /**
     * 邮箱服务配置
     */
    PropertiesConfig email_ = PropertiesConfig.init("email.properties");
    /**
     * 短信配置
     */
    PropertiesConfig sms_ = PropertiesConfig.init("sms.properties");
    /**
     * 验证码配置
     */
    PropertiesConfig kaptcha_ = PropertiesConfig.init("kaptcha.properties");
}
