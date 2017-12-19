package chaos.api.annoatation;

import java.lang.annotation.*;

/**
 * 多接口注解
 * qq:1413221142
 * 作者：王健(chaos)
 * 时间：2016-02-02
 */
@Documented //文档
@Retention(RetentionPolicy.RUNTIME) //在运行时可以获取
@Target({ElementType.METHOD}) //作用到类，方法，接口上等
@Inherited //子类会继承
public @interface Apis {

    /**
     * @return
     */
    Mode[] value();

}
