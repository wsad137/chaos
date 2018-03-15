package chaos.core.service.impl;

import chaos.core.dao.SAccountMapper;
import chaos.core.dao.SAccountRoleMapper;
import chaos.core.dao.SRoleMapper;
import chaos.core.model.SAccount;
import chaos.core.model.SAccountRole;
import chaos.core.service.SAccountService_;
import chaos.utils.object.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Created by chaos on 2018/1/16.
 * 作者：王健
 * qq:1413221142
 */
@Service
public class SAccountService_Impl implements SAccountService_ {
    private final static Logger log = LoggerFactory.getLogger(SAccountService_Impl.class);

    @Autowired
    SAccountMapper sAccountMapper;

    @Autowired
    SRoleMapper sRoleMapper;


    @Autowired
    SAccountRoleMapper sAccountRoleMapper;


    @Override
    public SAccount logIn(SAccount sAccount) {
        return null;
    }

    @Override
    public boolean logOut(SAccount sAccount) {
        return false;
    }

    @Override
    public boolean addAccount(SAccount sAccount) {
        sAccount.setCt(Instant.now().toEpochMilli());
        if (sAccount.getStatus() == null) sAccount.setStatus((short) 1);
        return sAccountMapper.insert(sAccount) > 0;
    }

    @Override
    public boolean updateAccount(SAccount sAccount) {
        return false;
    }

    @Override
    public boolean delAccount(String aId) {
        return false;
    }

    @Override
    public boolean existAccount(String userName) {
        return false;
    }

    @Override
    public SAccount byTokenAccount(String token) {
        return null;
    }

    @Override
    public SAccount byNameAccount(String username) {
        SAccount sAccount = sAccountMapper.selectByUserName(username);
        return sAccount;
    }

    @Override
    public SAccount byNameExtAccount(String username) {
        SAccount sAccount = sAccountMapper.selectByUserNameExt(username);
        return sAccount;
    }

    @Override
    public boolean addOrUpdateAccount(SAccount account) {
        if (ObjectUtils.isEmpty(account.getId())) return addAccount(account);
        return sAccountMapper.updateByPrimaryKeySelective(account) > 0;
    }

    @Override
    public boolean addOrUpdateAccountRrole(SAccountRole accountRole) {

        SAccountRole sAccountRole = sAccountRoleMapper.selectByAId(accountRole.getaId() + "");

        if (sAccountRole == null) {
            return sAccountRoleMapper.insert(accountRole) > 0;
        } else {
            return sAccountRoleMapper.updateByPrimaryKeySelective(accountRole) > 0;
        }
    }
}
