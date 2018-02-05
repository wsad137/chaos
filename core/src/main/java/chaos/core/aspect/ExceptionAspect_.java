package chaos.core.aspect;

import chaos.core.commons.ExceptionUtils_;
import chaos.utils.web.model.CaseRes;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.UndeclaredThrowableException;


/**
 * ©chaos
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-03-18
 */
// @Aspect : 标记为切面类
// @Pointcut : 指定匹配切点集合
// @Before : 指定前置通知，value中指定切入点匹配
// @AfterReturning ：后置通知，具有可以指定返回值
// @AfterThrowing ：异常通知
//注意：前置/后置/异常通知的函数都没有返回值，只有环绕通知有返回值
@ControllerAdvice
@Component    //首先初始化切面类
@Aspect      //声明为切面类，底层使用动态代理实现AOP
public class ExceptionAspect_ {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAspect_.class);

//    JoinPoint
//    String toString();         //连接点所在位置的相关信息
//    String toShortString();     //连接点所在位置的简短相关信息
//    String toLongString();     //连接点所在位置的全部相关信息
//    Object getThis();         //返回AOP代理对象
//    Object getTarget();       //返回目标对象
//    Object[] getArgs();       //返回被通知方法参数列表
//    Signature getSignature();  //返回当前连接点签名
//    SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置
//    String getKind();        //连接点类型
//    StaticPart getStaticPart(); //返回连接点静态部分

    @AfterThrowing(value = "execution(* chaos.core..*.*(..))", throwing = "ex")
//    @AfterThrowing(value = "execution(* *.**.controller.*.*(..)) || execution(* controller.xljt.gypsc.service.*.*(..)) && !execution(* LogModelMapper.*(..))", throwing = "ex")
    public void doAfterThrowing(JoinPoint jp, Throwable ex) throws Throwable {
//        ex.printStackTrace();
//        logger.error("异常捕获", ex);
//        if (ex.getMessage() == null) {
//            throw new IllegalArgumentException(jp.getSignature() + ex.toString());
//        }

        if (ex instanceof DuplicateKeyException) {
            throw new Exception("唯一约束异常！");
        } else if (ex instanceof BadSqlGrammarException) {
            throw new Exception("mysql语法错误！");
//        } else if (ex instanceof UcException) {
//            throw new UcException(ex.getLocalizedMessage());
        } else {
            throw ex;
//            if (ex.getMessage() == null) {
//                throw new IllegalArgumentException(jp.getSignature() + ex.toString());
//            }
//            throw new Exception(jp.getSignature() + ex.toString());
        }
    }


    /**
     * 其他异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ShiroException.class})
    @ResponseBody
    public CaseRes shiroException(ShiroException ex) {
        logger.error("shiro异常捕获", ex);

        String message;

        if (ex instanceof IncorrectCredentialsException) {
            message = "登录密码错误";
        } else if (ex instanceof ExcessiveAttemptsException) {
            message = "登录失败次数过多";
        } else if (ex instanceof LockedAccountException) {
            message = "帐号已被锁定";
        } else if (ex instanceof DisabledAccountException) {
            message = "帐号已被禁用";
        } else if (ex instanceof ExpiredCredentialsException) {
            message = "帐号已过期";
        } else if (ex instanceof UnknownAccountException) {
            message = "帐号不存在";
        } else if (ex instanceof UnauthorizedException) {
            message = "您没有得到相应的授权";
        } else if (ex instanceof AuthorizationException || ex instanceof AuthenticationException) {
            message = "身份验证授权失败！拒绝访问。";
        } else {
            message = ex.getMessage();
            if (StringUtils.isEmpty(message)) message = "授权失败！";
        }
//        message = ex.getMessage() == null ? ex.toString() : ex.getMessage();
        CaseRes res = CaseRes.New();
        res.seteCode(-1);
        ExceptionUtils_.add(ex);
        return res.setMessage(message);
    }

    /**
     * 其他异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({Throwable.class, Exception.class})
    @ResponseBody
    public CaseRes exception(Exception ex) {
//        ex.printStackTrace();
        logger.error("异常捕获", ex);
        ExceptionUtils_.add(ex);
        String message;
        if (ex instanceof UndeclaredThrowableException) {
            message = ((UndeclaredThrowableException) ex).getUndeclaredThrowable().getLocalizedMessage();
        } else {
            message = ex.getMessage();
        }
        return CaseRes.message(message);
    }


}
