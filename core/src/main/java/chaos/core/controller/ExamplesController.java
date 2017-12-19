package chaos.core.controller;

import chaos.utils.web.model.CaseRes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class ExamplesController extends BaseCoreController {

    /**
     * 测试返回JSON数据
     */
    @RequestMapping(value = "test/test")
    @ResponseBody
    public CaseRes test() {

        System.out.println("-=-=-=-=-" + request.getRemoteAddr());
        System.out.println("Test................");
//        String a = null;
//        a.toString();
//        throw new IllegalArgumentException("唯一约束异常！");
        return CaseRes.New();
    }

}


