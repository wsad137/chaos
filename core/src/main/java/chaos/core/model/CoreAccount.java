package chaos.core.model;

/**
 * commons 通用用户接口
 * 当前会话用户
 * ©chaos-parent
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-09-12
 */
public interface CoreAccount {
    /**
     * 获取用户id
     *
     * @return
     */
    String getUid();

    /**
     * 获取用户名称
     *
     * @return
     */
    String getUname();

}
