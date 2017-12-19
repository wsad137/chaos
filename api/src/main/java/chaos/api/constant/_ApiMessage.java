package chaos.api.constant;

/**
 * ©chaos
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-08-05
 */
public interface _ApiMessage {


    interface _upFile {

        String isEmpty = "200_没有要上传的文件！";
        String error = "201_上传失败！";
    }

    interface _sms {

        String error_send = "100_发送失败！";
        String empty_type = "101_类型不能为空！";
        String empty_sCode = "102_短信验证码不能为空！";
        String error_verfy = "103_短信验证码错误！";
    }

    interface _captcha {

        String error = "301_验证码错误！";
        String empty_iCode = "303_图片验证码不能为空！";
        String empty_sCode = "304_短信验证码不能为空！";
        String empty_eCode = "305_邮箱验证码不能为空！";
    }

    interface _service {


        String error_insert = "501_保存失败！";
        String error_insert_or_update = "502_保存或者更新失败！";
        String error_update = "503_更新失败！";
        String error_delete = "504_删除失败！";
    }

    interface _controller {

        String empty_param = "801_参数不能为空";
    }

    interface _ucUser {
        String empty_id = "901_用户id不能为空！";
        String empty = "1_用户信息不能为空！";
        String empty_phone = "2_手机号码不能为空！";
        String empty_email = "3_邮箱不能为空！";
        String empty_phone_or_email = "4_手机和邮箱必填一个！";
        String empty_username = "5_用户名不能为空！";
        String empty_password = "6_密码不能为空！";
        String empty_code = "7_验证码不能为空！";
        String isExist_phone = "8_手机号码已注册！";
        String isExist_uName = "9_用户名已注册！";
        String isExist_email = "10_邮箱已注册！";
        String notExist_username = "12_用户不存在或密码错误！";
        String notExist_usernameAll = "13_用户不存在！";
        String empty_gradeName = "14_会员等级名称不能为空！";
        String empty_discountRate = "15_初始折扣率不能为空！";
        String empty_role = "16_管理员角色不能为空!";
        String error_code = "11_验证码错误！";
        String error_password = "17_原密码有误，请重新输入!";
        String error_login_password = "18_密码错误次！";
        String error_login_password_three = "19_密码错误 {0} 次！!";
        String empty_pk = "20_唯一主键不能为空！";
        String empty_field = "21_属性字段不能为空！";
        String empty_newValue = "22_新的内容不能为空！";
        String empty_oldValue = "23_旧内容不能为空！";
        String error_admin = "24_添加管理员失败！";
        String error_admin_edit = "25_编辑管理员失败！";
        String empty_wx_OpenId = "26_openId不能为空！";
        String error_bindWx = "27_绑定失败！";
        String error_login_wxOpenId = "28_登陆失败！当前用户不存在！";
        String error_login_not_user = "29_未登陆，或者登陆已经过期！";
        String empty_idCard = "30_身份证信息，不能为空！";
        String passErrorNumber = "密码错误次数";
    }

    interface _ucRole {
        String empty_id = "1001_角色id不能为空！";
        String empty_purs = "1002_权限列表不能为空！";
    }

    interface _ucSession {
        String empty_user = "201_未登录，或者登陆已过期！";
//        String empty_purs ="1102_权限列表不能为空！";
    }

    interface _ucAuthority {
        String error = "1201_权限不足！";
    }


    interface _weefs {
        String error_del = "1301_删除失败！";
    }

    interface _wechat4j {
        String empty_appid = "1401_appid不能为空！";
        String empty_appsecret = "1402_appsecret不能为空！";
    }


}
