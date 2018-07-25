package chaos.api.controller;

import chaos.api.annoatation.Api;
import chaos.api.annoatation.ApiGroup;
import chaos.api.annoatation.ApiRes;
import chaos.api.annoatation.F;
import chaos.api.model.TestModel;
import chaos.utils.web.model.CaseRes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Properties;

@ApiGroup(name = "core-api控制器")
@Controller
@RequestMapping("/api")
public class ExamplesController_ extends BaseApiController {

    /**
     * 测试返回JSON数据
     */
    @Api(fs = {@F(name = "name", desc = "测试字段1"), @F("phone")}, name = "测试", beans = {TestModel.class}, res = {@ApiRes(beans = {TestModel.class})})
    @RequestMapping(value = "/test")
    @ResponseBody
    public CaseRes test() {

        System.out.println("-=-=-=-=-" + request.getRemoteAddr());
        System.out.println("Test................");
//        String a = null;
//        a.toString();
//        throw new IllegalArgumentException("唯一约束异常！");
        return CaseRes.New();
    }

    /**
     * 测试返回JSON数据
     */
//    @Api(value = "测试方法", params = {"value///aaaa//bbb"})
    @Api(fs = {@F("aaaa"), @F("aa")}, name = "测试", beans = {TestModel.class},
            res = {@ApiRes(desc = "aaa", beans = {TestModel.class})})
//    @ApiRes(desc = "接口数据返回说明desc", beans = {TestModel.class})
    @RequestMapping(value = "/test2")
    @ResponseBody
    public CaseRes test2() {

        System.out.println("-=-=-=-=-" + request.getRemoteAddr());
        System.out.println("Test................");
//        String a = null;
//        a.toString();
//        throw new IllegalArgumentException("唯一约束异常！");
        return CaseRes.New();
    }

    /**
     * 测试返回JSON数据
     */
//    @Api(value = "获取服务器信息")
    @Api(name = "获取服务器信息", beans = {TestModel.class})
    @RequestMapping(value = "/system")
    @ResponseBody
    public CaseRes<Map> getSystemInfo() {


        Properties p = System.getProperties();// 获取当前的系统属性

        p.list(System.out);// 将属性列表输出

        System.out.println("操作系统：" + p.getProperty("sun.desktop"));
        System.out.print("CPU个数:");// Runtime.getRuntime()获取当前运行时的实例
        System.out.println(Runtime.getRuntime().availableProcessors());// availableProcessors()获取当前电脑CPU数量
        System.out.print("虚拟机内存总量:");
        System.out.println(Runtime.getRuntime().totalMemory());// totalMemory()获取java虚拟机中的内存总量
        System.out.print("虚拟机空闲内存量:");
        System.out.println(Runtime.getRuntime().freeMemory());// freeMemory()获取java虚拟机中的空闲内存量
        System.out.print("虚拟机使用最大内存量:");
        System.out.println(Runtime.getRuntime().maxMemory());// maxMemory()获取java虚拟机试图使用的最大内存量

        System.out.println("-=-=-=-=-" + request.getRemoteAddr());
        System.out.println("Test................");
        String a = null;
        a.toString();
//        throw new IllegalArgumentException("唯一约束异常！");
        return CaseRes.data(p);
    }


}


