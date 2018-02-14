package chaos.core.service;

import chaos.core.model.SAccount;
import chaos.core.model.SAccountRole;

/**
 * Created by chaos on 2018/1/16.
 * 作者：王健
 * qq:1413221142
 */
public interface SAccountService_ {

    /**
     * 登录
     *
     * @param sAccount
     * @return
     */
    SAccount logIn(SAccount sAccount);

    /**
     * 登出
     *
     * @param sAccount
     * @return
     */
    boolean logOut(SAccount sAccount);

    /**
     * 添加账户
     *
     * @param sAccount
     * @return
     */
    boolean addAccount(SAccount sAccount);

    /**
     * 更新账户
     *
     * @param sAccount
     * @return
     */
    boolean updateAccount(SAccount sAccount);

    /**
     * 删除账户
     *
     * @param aId
     * @return
     */
    boolean delAccount(String aId);

    /**
     * 用户名是否存在
     *
     * @param userName
     * @return
     */
    boolean existAccount(String userName);

    /**
     * 根据token查询
     *
     * @param token
     * @return
     */
    SAccount byTokenAccount(String token);

    /**
     * 根据用户名称查询
     *
     * @param username
     * @return
     */
    SAccount byNameAccount(String username);

    SAccount byNameExtAccount(String username);

    /**
     * 添加或者保存
     *
     * @param account
     * @return
     */
    boolean addOrUpdateAccount(SAccount account);

    /**
     *
     * @param accountRole
     * @return
     */
    boolean addOrUpdateAccountRrole(SAccountRole accountRole);
}
