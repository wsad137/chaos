package chaos.core.constant;

/**
 * ©chaos
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-06-04
 */
public interface CommonKey {


    interface _session {
        /**
         * 短信验证码
         */
        String sCode = "sCode";
        /**
         * 图片验证码
         */
        String iCode = "iCode";
        /**
         * 其他验证码
         */
        String qCode = "qCode";
        /**
         * 邮箱验证码
         */
        String eCode = "eCode";

        /**
         * 错误次数
         */
        String errorNumber = "s_errorNumber";
        String user = "s_user";
        /**
         * 发送次数
         */
        String sendNumber = "s_sendNumber";
    }


    interface _cookie {
        String user = "c_user";
    }

    interface _propertie {

        interface wechat4j {

            String appid = "wechat.appid";
            String appsecret = "wechat.appsecret";
            String token = "wechat.token";
            String encodingaeskey = "wechat.encodingaeskey";
            String mchid = "wechat.mchid";
            String accessToken = "wechat.accessToken";
            String jsApiAccessToken = "wechat.JsApiAccessToken";
        }

        interface file {

            String upload = "file.upload";
            String upload_ads = "file.upload.ads";
            String upload_temp = "file.upload.temp";
            String upload_temp_ads = "file.upload.temp.ads";
            String upload_server = "file.upload.server";
        }

        interface sms {
            String send = "sms.send";
            String url = "sms.url";
        }

        interface email {
            String send = "mail.send";
            String host = "mail.host";
            String port = "mail.port";
            String username = "mail.username";
            String password = "mail.password";
            String smtp_auth = "mail.smtp.auth";
            String smtp_timeout = "mail.smtp.timeout";
            String default_from = "mail.default.from";
        }

        interface kaptcha {
            String border = "kaptcha.border";
        }


        interface weedfs {
            String volume_server = "weedfs.volume.server";
            String volume_port = "weedfs.volume.port";
            String master_server = "weedfs.master.server";
            String master_port = "weedfs.master.port";
            String master_http_rootPath = "weedfs.http.rootPath";
        }

        interface project {
            String ucAuthority_mode = "project.ucAuthority.mode";

        }
    }

    interface _cache {

//        interface Region {
//            /**
//             *
//             */
//            String parentId = "parentId";
//        }

        String region = "region";

    }
}
