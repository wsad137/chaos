package chaos.core.service.impl;

import chaos.core.model.BstParam;
import chaos.core.model.ExceptionModel_;
import chaos.core.service.ExceptionService_;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chaos on 2018/1/19.
 * 作者：王健
 * qq:1413221142
 */
@Service
public class ExceptionService_Impl implements ExceptionService_ {

    private final static Logger log = LoggerFactory.getLogger(ExceptionService_Impl.class);

    @Override
    public boolean insertExceptionModel(String title, String context) {
        return false;
    }

    @Override
    public boolean insertExceptionModel(Throwable throwable) {
        return false;
    }

    @Override
    public List<ExceptionModel_> selectByUid(String uId) {
        return null;
    }

    @Override
    public List<ExceptionModel_> selectByCreateDate(long start, long end) {
        return null;
    }

    @Override
    public PageInfo<ExceptionModel_> selectAll(BstParam model) {
        return null;
    }
}
