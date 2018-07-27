package chaos.api.annoatation;

import java.lang.annotation.*;

/**
 * 单接口返回值注解
 * qq:1413221142
 * 作者：王健(chaos)
 * 时间：2016-02-02
 */
@Documented //文档
@Retention(RetentionPolicy.RUNTIME) //在运行时可以获取
@Target({ElementType.ANNOTATION_TYPE}) //作用到类，方法，接口上等
@Inherited //子类会继承
public @interface Dict {

    /**
     * class对象 apiModel 对象
     *
     * @return
     */
    Class value() default Class.class;

    /**
     * 字段集合
     *
     * @return
     */
    F[] fs() default {};

    /**
     * 详细说明
     *
     * @return
     */
    String desc() default "";


}
