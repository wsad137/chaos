package chaos.api.annoatation;

import java.lang.annotation.*;

/**
 * 字段注解
 * <p>
 * Created by chaos on 2017/12/7.
 * 作者：王健
 * qq:1413221142
 */
@Documented //文档
@Retention(RetentionPolicy.RUNTIME) //在运行时可以获取
@Target(ElementType.ANNOTATION_TYPE)
public @interface F {
    /**
     * 字段名称
     *
     * @return
     */
    String value() default "";

    /**
     * 字段名称
     *
     * @return
     */
    String name() default "";

    /**
     * 类型
     *
     * @return
     */
    String type() default "string";

    /**
     * 默认值
     *
     * @return
     */
    String def() default "";

    /**
     * 描述
     *
     * @return
     */
    String desc() default "";

    /**
     * 是否排除 默认false
     */
    boolean exclude() default false;

    /**
     * 是否非可以为空
     *
     * @return
     */
    boolean notEmpty() default false;

    /**
     * 长度
     *
     * @return
     */
    int lang() default 20;


}
