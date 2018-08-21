package chaos.api;

import chaos.api.annoatation.Api;
import chaos.api.annoatation.ApiGroup;
import chaos.api.context.ApiInit_;
import chaos.api.model.ApiConfig;
import chaos.api.model.ApiGroupModel;
import chaos.api.model.ApiModel_;
import chaos.api.model.ApiFieldModel;
import chaos.utils.ComparatorUtils;
import chaos.utils.ZipUtil;
import chaos.utils.constant.UtilsConstant;
import chaos.utils.json.JsonUtils;
import chaos.utils.object.ObjectUtils;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * © app
 * qq:1413221142
 * 作者：王健(chaos)
 * 时间：2016-02-03
 */
public class ApiUtils_ {

    private static final Logger log = Logger.getLogger(ApiUtils_.class);

    private ApiUtils_() {
    }

    private ApiUtils_(ApplicationContext context) {
        this.context = context;
    }

    private static ApiUtils_ apiUtils_;

//    private static ApiUtils_ getInstance(ApplicationContext context) {
//        if (apiUtils_ == null) apiUtils_ = new ApiUtils_(context);
//        return apiUtils_;
//    }

    private static ApiConfig apiConfig;

    public static ApiConfig getConfig() {
        if (apiConfig == null) apiConfig = new ApiConfig();
        return apiConfig;
    }

//    private ApiConfig _config = new ApiConfig();

    /**
     *
     */
    public static void refresh() {
        apiUtils_.processor();
    }

//    public void refresh(ApplicationContext applicationContext) {
//        init(applicationContext);
//    }

    //    private static Object getJdkDynamicProxyTargetObject(Object proxy) {
//        Field h = null;
//        Object target = null;
//        try {
//            h = proxy.getClass().getSuperclass().getDeclaredField("h");
//            h.setAccessible(true);
//            AopProxy aopProxy = (AopProxy) h.get(proxy);
//            Field advised = aopProxy.getClass().getDeclaredField("advised");
//            advised.setAccessible(true);
//            target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return target;
//    }
//
//    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
//        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
//        h.setAccessible(true);
//        Object dynamicAdvisedInterceptor = h.get(proxy);
//
//        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
//        advised.setAccessible(true);
//
//        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
//
//        return target;
//    }
    private ApplicationContext context;

    /**
     * 生成html帮助文档
     *
     * @param context
     */
    public static void init(ApplicationContext context) {

        if (apiUtils_ == null) apiUtils_ = new ApiUtils_(context);
        refresh();
    }


    public void processor() {
        try {
            log.info("开始生成api页面html");
            String[] con = context.getBeanNamesForAnnotation(ApiGroup.class);
            if (ObjectUtils.isEmpty(con)) return;
            List<ApiGroupModel> groupModels = Lists.newArrayList();

            //遍历所有控制器
            for (Object c : con) {
                String s = String.valueOf(c);
                Object o = context.getBean(s);
                Class cla = o.getClass();
                if (AopUtils.isAopProxy(o)) {
                    cla = AopUtils.getTargetClass(o);
                    if (AopUtils.isJdkDynamicProxy(o)) cla = cla.getSuperclass();
                    while (cla.getName().contains("$$")) cla = cla.getSuperclass();
                }
                ApiGroupModel groupModel = new ApiGroupModel();
                String clsMapping = "";
                /*
                 *取菜单标题
                 */
                if (cla.isAnnotationPresent(ApiGroup.class)) {
                    ApiGroup apiGroup = (ApiGroup) cla.getAnnotation(ApiGroup.class);
                    if (ObjectUtils.isEmpty(groupModel.getName())) {
                        if (!StringUtils.isEmpty(apiGroup.value())) groupModel.setName(apiGroup.value());
                        if (!StringUtils.isEmpty(apiGroup.name())) groupModel.setName(apiGroup.name());
                    }
                }
                if (ObjectUtils.isEmpty(groupModel.getName())) {
                    groupModel.setName(s);
                }

                /*
                 * 取url
                 */
                if (cla.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = (RequestMapping) cla.getAnnotation(RequestMapping.class);
                    clsMapping = requestMapping.value()[0];
                }

                /*
                 * 遍历方法
                 */
                List<ApiModel_> apis = Lists.newArrayList();
                for (Method method : cla.getMethods()) {
                    Api api = method.getAnnotation(Api.class);
                    if (ObjectUtils.isEmpty(api)) continue;


                    getReturnFields(method);

                    ApiModel_ apiModel = new ApiModel_();
                    apiModel.setName(api.value());
                    if (StringUtils.isEmpty(apiModel.getName())) apiModel.setName(api.name());
                    apiModel.setNameGroup(groupModel.getName());
                    apiModel.setDesc(api.desc());
//                    apiModel.setFieldStr(api.fieldStr());
                    apiModel.set_fields(Arrays.asList(api.fs()));
                    apiModel.setBeans(api.beans());
//                    apiModel.set_res(Arrays.asList(api.res()));
                    apiModel.setDict_(api.dict());
                    apiModel.setDicts_(Arrays.asList(api.dicts()));
                    apiModel.setExclude(api.excFields());
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

                    String contextPath = "";
                    if (ApiInit_.appContext != null) {/*设置项目 contextPath*/
                        ServletContext bean = ApiInit_.appContext.getBean(ServletContext.class);
                        contextPath = bean.getContextPath();
                    }

                    if (ObjectUtils.isEmpty(requestMapping)) {
                        apiModel.setUrl(contextPath + (clsMapping + "/" + method.getName()));
//                        ApiInit_.appContext.getBean()
                    } else {
                        apiModel.setUrl(contextPath + (clsMapping + requestMapping.value()[0]));
                    }

                    if (ObjectUtils.isEmpty(apiModel.getName())) {
                        apiModel.setName(method.getName());
                    }
                    apiModel.setMethod(method);
                    apis.add(apiModel.processor(groupModel));
                }

                ComparatorUtils.sort(apis, "name", true);
                groupModel.setApis(apis);
                groupModels.add(groupModel);
            }


            /*
             *生成html
             */
            String realPath = context.getResource(getConfig().getName()).getFile().getPath();
            String webPath = realPath;
            if (!StringUtils.isEmpty(getConfig().getRootPath())) {
                webPath = getConfig().getRootPath() + getConfig().getName();
            }
            log.info("api文件路径：" + webPath);
            FileUtils.forceMkdir(new File(webPath));
            File customFile = new File(webPath, "chaosApi.zip");
            if (customFile.exists()) {
                ZipUtil.unZip(customFile.getPath(), webPath);
            } else {/*获取不到 jar中获取*/
                InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("chaosApi.zip");
                FileUtils.copyToFile(resourceAsStream, new File(realPath, "temp.zip"));
                ZipUtil.unZip(realPath + File.separator + "temp.zip", webPath);
                FileUtils.deleteQuietly(new File(realPath + File.separator + "temp.zip"));
            }
            /*重命名*/
            String tojSON = JsonUtils.tojSON(groupModels);
            IOUtils.write(tojSON, new FileOutputStream(webPath + File.separator + "data.json"), UtilsConstant._coding.UTF8);
        } catch (Exception e) {
            log.warn("加载模板异常！", e);
        }
        log.info("生成api-html完成");
    }

    /**
     * 获取返回值说明
     *
     * @param method
     * @return
     */
    private List<ApiFieldModel> getReturnFields(Method method) {
        List<ApiFieldModel> paramModelList = new ArrayList<>();
        Type type = method.getGenericReturnType();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] types = parameterizedType.getActualTypeArguments();
            for (Type t : types) {
                // TODO: 2017/3/27 类型原因
                if (!(t instanceof Class)) continue;
                Class c = (Class) t;
                paramModelList.addAll(Arrays.stream(c.getDeclaredFields()).map(ApiFieldModel::new).collect(Collectors.toList()));
            }
        }
        return paramModelList;
    }


    public static void main(String[] args) {
        System.out.println(RandomStringUtils.randomAlphanumeric(10));
        System.out.println(RandomStringUtils.randomNumeric(10));
    }

}
