package chaos.core.service;

import chaos.core.model.ConfigModel_;

import java.util.List;

/**
 * Created by 王健 on 2016-12-29.
 * qq:1413221142
 */
public interface ConfigService_ {

    boolean deleteByPrimaryKey(Integer id);

    boolean insert(ConfigModel_ record);

    boolean insertSelective(ConfigModel_ record);

    ConfigModel_ selectByPrimaryKey(Integer id);

    boolean deleteByMarkKey(String markKey);

    ConfigModel_ selectByMarkKey(String markKey);

    boolean updateByPrimaryKeySelective(ConfigModel_ record);

    boolean updateByPrimaryKeyWithBLOBs(ConfigModel_ record);

    boolean updateByPrimaryKey(ConfigModel_ record);

    List<ConfigModel_> selectAll();

    boolean saveOrUpdate(ConfigModel_ record);
}
