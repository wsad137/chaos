package chaos.core.service;


import chaos.core.model.RegionModel_;

import java.util.List;

/**
 * ©chaos-parent
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-08-15
 */
public interface RegionService_ {

    /**
     * 0 为一级菜单
     *
     * @return
     */
    List<RegionModel_> getRegion(int parentId);

    String getAddressByCode(String code);

    String getAddressById(int id);

    String getAddressByIds(Integer[] ids);
}
