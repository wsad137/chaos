package chaos.api.annoatation;

import java.lang.annotation.*;

/**
 *  api 实体类注解，会生成字段列表
 * qq:1413221142
 * 作者：王健(chaos)
 * 时间：2016-02-02
 */
@Documented //文档
@Retention(RetentionPolicy.RUNTIME) //在运行时可以获取
@Target({ElementType.TYPE}) //作用到类，方法，接口上等
@Inherited //子类会继承
public @interface ApiModel {
    /**
     * 实体类名称
     *
     * @return
     */
    String value() default "";

    /**
     * class name
     *
     * @return
     */
    String name() default "";

    /**
     * 描述
     *
     * @return
     */
    String desc() default "";

    /**
     * 是否自动扫描字段 默认true
     *
     * @return
     */
    boolean autoSca() default true;


}
