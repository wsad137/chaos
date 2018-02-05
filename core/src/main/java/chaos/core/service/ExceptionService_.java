package chaos.core.service;


import chaos.core.model.BstParam;
import chaos.core.model.ExceptionModel_;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 异常操作
 * <p>
 * ©chaos
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-08-12
 */
public interface ExceptionService_ {

    /**
     * 添加异常记录
     *
     * @return
     */
    boolean insertExceptionModel(String title, String context);

    /**
     * 添加异常管理
     *
     * @return
     */
    boolean insertExceptionModel(Throwable throwable);

    /**
     * 查询异常 根据用户
     *
     * @return
     */
    List<ExceptionModel_> selectByUid(String uId);

    /**
     * 查询异常根据 时间
     *
     * @param start
     * @param end
     * @return
     */
    List<ExceptionModel_> selectByCreateDate(long start, long end);


    /**
     * @param model
     * @return
     */
    PageInfo<ExceptionModel_> selectAll(BstParam model);

}
