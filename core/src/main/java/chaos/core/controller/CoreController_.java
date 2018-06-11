package chaos.core.controller;

import chaos.api.annoatation.Api;
import chaos.api.annoatation.ApiGroup;
import chaos.core.commons.KaptchaUtils_;
import chaos.core.commons.RegionUtils_;
import chaos.core.model.RegionModel_;
import chaos.utils.object.ObjectUtils;
import chaos.utils.web.model.CaseRes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ApiGroup(value = "chaos-core控制器")
@Controller
@RequestMapping("/")
public class CoreController_ extends BaseCoreController {


    //    @Autowired
//    CaptchaService captchaService;
    @Api(value = "图片验证码:获取")
    @RequestMapping("kaptcha/getKaptchaImage")
    public ModelAndView getKaptchaImage() {

        KaptchaUtils_.getICodeImg(request_, response_);

        return null;
    }

//    @Api(value = "短信验证码:发送", fieldStr = {"phone/手机号", "iCode/图片验证码"})
//    @RequestMapping("sms/sendVerify")
//    public CaseRes sendSmsVerify(@RequestBody(required = false) Map<String, String> model) {
//
//        String code = model.getOrDefault("code", "");
//
//        if (!KaptchaUtils_.isVerify(code)) return CaseRes.getInstance().setMessage(CommonMessage._captcha.error);
//
//
//        String phone = model.getOrDefault("phone", "");
//
//        String random = RandomStringUtils.randomNumeric(4);
//
//        SmsHelper.getInstance().getSmsServer().sendSms(phone, random);
//
////        KaptchaHelper.getInstance().getCaptchaService().getICodeImg(request, response);
//
//        return CaseRes.getInstance();
//    }

    @Api(value = "图片验证码:取B64")
    @RequestMapping("kaptcha/getKaptchaImageB64")
    @ResponseBody
    public CaseRes getKaptchaImageB64() {
//        CaptchaService captchaService = KaptchaHelper.getInstance().getCaptchaService();
        String result = KaptchaUtils_.getICode();

//        String result = captchaService.getICode();
        Map<String, String> map = new HashMap<>();
        map.put("head", "data:image/png;base64");
        map.put("body", result);
        return CaseRes.data(map);
    }

    @Api(value = "图片验证码:验证", fieldStr = {"code"})
    @RequestMapping("kaptcha/kaptchaVerify")
    @ResponseBody
    public CaseRes kaptchaVerify() {
//        CaptchaService captchaService = KaptchaHelper.getInstance().getCaptchaService();
        String code = getStringParam("code");

        if (KaptchaUtils_.isVerify(code)) {
            return CaseRes.New();
        }
        return CaseRes.message("1_验证码错误！");
    }


//    @Autowired
//    EmailService mailSenderService;
//
//    @UcVerifyIgnore
//    @Api(value = "邮箱:获取验证码", params = {"email/接收者邮箱地址"})
//    @RequestMapping("email/getCode")
//    @ResponseBody
//    public CaseRes sendEmail(@RequestBody(required = false) Map<String, String> model) {
//
//
//        String email = model.getOrDefault("email", "");
//        Assert.hasText(email, CommonMessage._ucUser.empty_email);
//
//        String subject = "注册验证码";
//        String code = RandomStringUtils.randomNumeric(6);
//        String con = "您的注册验证码是 [ " + code + " ]!";
//        CommonRequestHelper.getInstance().getRequest().getSession().setAttribute(CommonKey._session.eCode, email + code);
//        mailSenderService.emailSendText(subject, con, email);
//
//
////        mailSenderService.emailSendText("标题", "内容", "1413221142@qq.controller");
//
//        return CaseRes.getInstance();
//    }


    @Api(name = "地区:列表", fieldStr = {"parentId/上级ID//0"})
    @RequestMapping("region/getRegion")
    @ResponseBody
    public CaseRes getRegion(@RequestBody(required = false) RegionModel_ model) {
        return CaseRes.data(RegionUtils_.getRegion(ObjectUtils.toInt(model.getParentId())));
    }


//    @Autowired
//    UpFileService upFileService;

//    @Api(value = "文件上传:单文件", params = {"file//file", "code/文件对应的标示//time"})
//    @RequestMapping("upLoad/file")
//    @ResponseBody
//    public CaseRes fileUpload(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(required = false) String code) {
//        UpFileService upFileService = UpFileHelper.getInstance().getUpFileService();
//
//        UpFileModel save = upFileService.save(file, code);
//        return CaseRes.data(save);
//    }
//
//    @Api(value = "文件上传:多文件", params = {"file//file", "file1//file", "file2//file", "codes/文件对应的标示"})
//    @RequestMapping("upLoad/muchFile")
//    @ResponseBody
//    public CaseRes muchFileUpload(@RequestParam(value = "file", required = false) MultipartFile[] files, @RequestParam(required = false) String code) {
//
//        if (org.springframework.util.ObjectUtils.isEmpty(files)) return CaseRes.getInstance().setMessage(CommonMessage._upFile.isEmpty);
//        UpFileService upFileService = UpFileHelper.getInstance().getUpFileService();
//
//        List<UpFileModel> save = upFileService.save(files);
//        return CaseRes.data(save);
//    }
//
//    @UcVerifyIgnore
//    @Api(value = "富文本:上传")
//    @RequestMapping("ueditor/upLoad")
//    @ResponseBody
//    public void ueditor() {
////        String rootPath = request.getServletContext().getRealPath("/");
//        String rootPath = CommonProperties._file.getString(CommonKey._propertie.file.upload_ads);
//        ActionEnter actionEnter = new ActionEnter(request, rootPath);
//        try {
//            response.getWriter().write(actionEnter.exec());
//            response.getWriter().close();
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//
//    }

}


