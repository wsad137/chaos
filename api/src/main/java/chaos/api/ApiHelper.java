package chaos.api;

import chaos.api.annoatation.Api;
import chaos.api.annoatation.ApiGroup;
import chaos.api.model.ApiConfig;
import chaos.api.model.ApiGroupModel;
import chaos.api.model._ApiModel;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * © app
 * qq:1413221142
 * 作者：王健(chaos)
 * 时间：2016-02-03
 */
public class ApiHelper {

    private static final Logger log = Logger.getLogger(ApiHelper.class);

    private ApiHelper() {
    }

    private static ApiHelper apiHelper = new ApiHelper();

    public static ApiHelper getInstance() {
        return apiHelper;
    }

    private static ApiConfig apiConfig;

    public ApiConfig getConfig() {

        if (apiConfig != null) {
            return apiConfig;
        } else {
            apiConfig = new ApiConfig();
        }
        return apiConfig;
    }

//    private ApiConfig config = new ApiConfig();

    public void refresh() {
        if (this.applicationContext != null) init(applicationContext);
    }

    public void refresh(ApplicationContext applicationContext) {
        init(applicationContext);
    }

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
    private ApplicationContext applicationContext;

    /**
     * 生成html帮助文档
     *
     * @param applicationContext
     */
    public void init(ApplicationContext applicationContext) {
        try {


            if (applicationContext == null) {
                log.error("applicationContext 为空无法初始化Api接口文档！");
                return;
            }
            this.applicationContext = applicationContext;

            log.info("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝开始生成api页面html＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
            String[] con = applicationContext.getBeanNamesForAnnotation(ApiGroup.class);
//        Object[] con = ArrayUtils.addAll(ArrayUtils.addAll(con, con2), con3);
            if (ObjectUtils.isEmpty((Object) con)) return;
            Map<String, List<_ApiModel>> listMap = new HashMap<>();
            List<ApiGroupModel> groupModels = Lists.newArrayList();

            //遍历所有控制器
            for (Object c : con) {
                String s = String.valueOf(c);
//                ArrayList list = new ArrayList();
                Object o = applicationContext.getBean(s);
                Class cla = o.getClass();


                if (AopUtils.isAopProxy(o)) {
                    cla = AopUtils.getTargetClass(o);
                    if (AopUtils.isJdkDynamicProxy(o)) cla = cla.getSuperclass();
                    while (cla.getName().contains("$$")) cla = cla.getSuperclass();
                }
//                ApiModel apiGroupModel = new ApiModel();
                ApiGroupModel groupModel = new ApiGroupModel();

                String clsMapping = "";
                /**
                 *取菜单标题
                 */
//            if (cla.isAnnotationPresent(ApiMenu.class)) {
//                ApiMenu apiMenu = (ApiMenu) cla.getAnnotation(ApiMenu.class);
//                conUrlModel.className = apiMenu.value();
//            }

                if (cla.isAnnotationPresent(ApiGroup.class)) {
                    ApiGroup apiGroup = (ApiGroup) cla.getAnnotation(ApiGroup.class);
                    if (ObjectUtils.isEmpty(groupModel.getName())) {
                        if (!StringUtils.isEmpty(apiGroup.value())) groupModel.setName(apiGroup.value());
                        if (!StringUtils.isEmpty(apiGroup.name())) groupModel.setName(apiGroup.name());
                    }
                }
//                if (cla.isAnnotationPresent(Api.class)) {
//                    Api api = (Api) cla.getAnnotation(Api.class);
//                    if (ObjectUtils.isEmpty(groupModel.getName())) {
//                        groupModel.setName(api.name());
//                    }
//                }

//            if (cla.isAnnotationPresent(ApiResponse.class)) {
//                ApiResponse apiResponse = (ApiResponse) cla.getAnnotation(ApiResponse.class);
//                if (ObjectUtils.isEmpty(conUrlModel.getClassName())) {
//                    conUrlModel.getClassName() = apiResponse.value();
//                }
//            }
                if (ObjectUtils.isEmpty(groupModel.getName())) {
                    groupModel.setName(s);
                }

                /**
                 * 取url
                 */
                if (cla.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = (RequestMapping) cla.getAnnotation(RequestMapping.class);
                    clsMapping = requestMapping.value()[0];
                }

                /**
                 * 遍历方法
                 */
                List<_ApiModel> apis = Lists.newArrayList();
                for (Method method : cla.getMethods()) {
                    Api api = method.getAnnotation(Api.class);
                    if (ObjectUtils.isEmpty(api)) continue;


                    getReturnFields(method);

                    _ApiModel apiModel = new _ApiModel();
                    apiModel.setName(api.name());
                    apiModel.setNameGroup(groupModel.getName());
                    apiModel.setDesc(api.desc());
                    apiModel.setFieldStr(api.fieldStr());
                    apiModel.set_fields(Arrays.asList(api.value()));
                    apiModel.setBeans(api.beans());
                    apiModel.set_res(Arrays.asList(api.res()));
                    apiModel.setExclude(api.excFields());
//                    apiModel.setReturnFields(getReturnFields(method));


//                if (method.isAnnotationPresent(ApiMenu.class)) {
//                    ApiMenu apiMenu = method.getAnnotation(ApiMenu.class);
//                    urlModel.requestDesc = apiMenu.requestDesc();
//                    urlModel.responseDesc = apiMenu.responseDesc();
//                }


                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
//                    System.out.println(applicationContext.getApplicationName());
//                    System.out.println(applicationContext.getDisplayName());
                    if (ObjectUtils.isEmpty(requestMapping)) {
                        apiModel.setUrl((clsMapping + "/" + method.getName()));
                    } else {
                        apiModel.setUrl((clsMapping + requestMapping.value()[0]));
                    }
                    if (ObjectUtils.isEmpty(apiModel.getName())) {
                        apiModel.setName(method.getName());
                    }
                    apiModel.setMethod(method);
//                Parameter[] parameters = method.getParameters();
//                for (Parameter parameter : parameters) {
////                        System.out.println(parameter.getType().getCanonicalName());
////                        System.out.println(parameter.getType());
//                    if (parameter.isNamePresent()) {
//                            System.out.println(parameter.getName());
//                    } else {
//                        System.out.println("无法获取参数名称，请使用JDK8 添加 -parameters 后编译");
//                    }
//                }
                    apis.add(apiModel.processor(groupModel));
                }


                ComparatorUtils.sort(apis, "name", true);
                groupModel.setApis(apis);
                groupModels.add(groupModel);
//                listMap.put(groupModel.getName(), list);
            }

//            ApiConfig config = new ApiConfig();

        /*
        生成html
         */

//            String realPath = ((WebApplicationContext) applicationContext).getServletContext().getRealPath("");
//            String defPath = ((WebApplicationContext) applicationContext).getServletContext().getRealPath("/api/");
            String realPath = ((WebApplicationContext) applicationContext).getServletContext().getRealPath("/");
            String webPath;
            if (StringUtils.isEmpty(getConfig().getRootPath())) {
//                webPath = ((WebApplicationContext) applicationContext).getServletContext().getRealPath(getConfig().getName());
                webPath = realPath + File.separator + getConfig().getName();
            } else {
                webPath = getConfig().getRootPath() + getConfig().getName();
            }

            FileUtils.forceMkdir(new File(webPath));

            URL webFile = this.getClass().getClassLoader().getResource(File.separator + "chaosApi.zip");
//            URL webFile = this.getClass().getClassLoader().getResource(File.separator + "chaosApi");

            if (webFile != null) {
//                FileUtils.copyDirectoryToDirectory(new File(webFile.getPath()), new File(realPath));
                ZipUtil.unZip(webFile.getPath(), webPath);
            } else {/*获取不到 jar中获取*/
//                ClassLoaderUtil.
//                InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("/chaosApi/");
                InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("chaosApi.zip");
//                InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(File.separator + "/chaosApi/" + File.separator);
                FileUtils.copyToFile(resourceAsStream, new File(realPath, "temp.zip"));
//                new FileImageInputStream(resourceAsStream,)
//                FileUtils.copyInputStreamToFile(resourceAsStream, new File(realPath, "temp.zip"));
                ZipUtil.unZip(realPath + File.separator + "temp.zip", webPath);
                FileUtils.deleteQuietly(new File(realPath + File.separator + "temp.zip"));
            }
//            JarFile jarFile = new JarFile(this.getClass().getClassLoader())
            /*重命名*/
//            String src = realPath + File.separator + "chaosApi";
//            if (!src.equals(webPath)) {
//                FileUtils.moveDirectoryToDirectory(new File(src), new File(webPath), true);
//            }


//            URL webFile = this.getClass().getClassLoader().getResource("/chaosApi.zip");
//            ZipUtil.unZip(webFile.getPath(), webPath);

//this.getClass().getClassLoader().getResource("")
            /*移动默认目录*/
//            if (!getConfig().getName().equals("api")) {
//                FileUtils.moveDirectoryToDirectory(new File(defPath), new File(webPath), true);
//            }

//            VelocityContext context = new VelocityContext();
//            VelocityEngine ve = new VelocityEngine();

//            Properties properties = new Properties();
//            properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, webPath);

//            properties.setProperty("eventhandler.referenceinsertion.class", EscapeJavaScriptReference.class.getName());

//            properties.setProperty("eventhandler.escape.javascript.match", "/^(?!\\$\\!.*?Json).*/");
//                properties.setProperty(Velocity.MAX_NUMBER_LOOPS, "5");
//            ve.init(properties);
//            Template t = ve.getTemplate("api.vm", "utf-8");
//            context.put("form", listMap);
            String tojSON = JsonUtils.tojSON(groupModels);
//            String tojSON = JsonUtils.tojSON(listMap);


            IOUtils.write(tojSON, new FileOutputStream(webPath + File.separator + "data.json"), UtilsConstant._coding.UTF8);

//            FileOutputStream fos = new FileOutputStream(webPath + "/api.html");
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));//设置写入的文件编码,解决中文问题
//            t.merge(context, writer);
//            writer.close();
        } catch (Exception e) {
            log.warn("加载模板异常！", e);
        }
        log.info("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝生成apihtml完成＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝==");
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
