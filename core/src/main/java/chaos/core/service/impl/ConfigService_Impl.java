package chaos.core.service.impl;

import chaos.core.dao.ConfigModel_Mapper;
import chaos.core.model.ConfigModel_;
import chaos.core.service.ConfigService_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConfigService_Impl implements ConfigService_ {

    @Autowired
    ConfigModel_Mapper configModel_mapper;


    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        return configModel_mapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean insert(ConfigModel_ record) {
        return configModel_mapper.insert(record) > 0;
    }

    @Override
    public boolean insertSelective(ConfigModel_ record) {
        return configModel_mapper.insertSelective(record) > 0;
    }

    @Override
    public ConfigModel_ selectByPrimaryKey(Integer id) {
        return configModel_mapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean deleteByMarkKey(String markKey) {
        return configModel_mapper.deleteByMarkKey(markKey) > 0;
    }

    @Override
    public ConfigModel_ selectByMarkKey(String markKey) {
        return configModel_mapper.selectByMarkKey(markKey);
    }

    @Override
    public boolean updateByPrimaryKeySelective(ConfigModel_ record) {
        return configModel_mapper.updateByPrimaryKeySelective(record) > 0;
    }

    @Override
    public boolean updateByPrimaryKeyWithBLOBs(ConfigModel_ record) {
        return configModel_mapper.updateByPrimaryKeyWithBLOBs(record) > 0;
    }

    @Override
    public boolean updateByPrimaryKey(ConfigModel_ record) {
        return configModel_mapper.updateByPrimaryKey(record) > 0;
    }

    @Override
    public List<ConfigModel_> selectAll() {
        ArrayList<ConfigModel_> def = new ArrayList<>();
        List<ConfigModel_> configModels = configModel_mapper.selectAll();
        if (configModels != null) def.addAll(configModels);
        return def;
    }

    @Override
    public boolean saveOrUpdate(ConfigModel_ record) {
        if (record == null) return false;
        if (record.getKeyMark() == null) return false;


        String keyMark = record.getKeyMark();
        ConfigModel_ ConfigModel_ = selectByMarkKey(keyMark);
        if (ConfigModel_ != null) {
            ConfigModel_.setValue(record.getValue());
            ConfigModel_.setDescription(record.getDescription());
            ConfigModel_.setTitle(record.getTitle());
            configModel_mapper.updateByPrimaryKeyWithBLOBs(ConfigModel_);
        } else {
            configModel_mapper.insert(record);
        }
        return true;
    }
}
