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
public @interface ApiRes {

    /**
     * 字段集合
     *
     * @return
     */
    F[] value() default {};

    /**
     * 接口名称
     *
     * @return
     */
    String name() default "";

    /**
     * 详细说明
     *
     * @return
     */
    String desc() default "";

    /**
     * class对象
     *
     * @return
     */
    Class[] beans() default {};

    /**
     * 需要排除的字段
     *
     * @return
     */
    String[] excFields() default {};

    /**
     * 字段简写模式
     * <pre>
     *     item: { 字段名称 / 字段说明 / 字段类型 / 默认值 }
     *     [/]分割
     *     默认值，为time时，会直接转成时间戳
     * </pre>
     *
     * @return
     */
    String[] fieldStr() default {};


}
