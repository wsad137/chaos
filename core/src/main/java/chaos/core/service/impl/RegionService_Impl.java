package chaos.core.service.impl;

import chaos.core.dao.RegionModel_Mapper;
import chaos.core.model.RegionModel_;
import chaos.core.service.RegionService_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionService_Impl implements RegionService_ {

    @Autowired
    RegionModel_Mapper regionModel_mapper;


    private void toDb(RegionModel_ model) {
        RegionModel_ temp, temp1, temp2;
//        for (RegionModel_ regionModel : model.getState()) {
        temp = new RegionModel_();
        temp.setParentId((short) 0);
        temp.setName(model.getName());
        temp.setCode((short) model.getCode());
        regionModel_mapper.insert(temp);
        for (RegionModel_ regionModel2 : model.getChild()) {
            temp1 = new RegionModel_();
            temp1.setParentId(temp.getId());
            temp1.setName(regionModel2.getName());
            temp1.setCode((short) regionModel2.getCode());
            regionModel_mapper.insert(temp1);
            for (RegionModel_ regionModel1 : regionModel2.getChild()) {
                temp2 = new RegionModel_();
                temp2.setParentId(temp1.getId());
                temp2.setName(regionModel1.getName());
                temp2.setCode((short) regionModel1.getCode());
                regionModel_mapper.insert(temp2);
            }
        }
//        }
    }


    /**
     * 0 为一级菜单
     *
     * @param parentId
     * @return
     */
//    @Cacheable(cacheNames = CommonKey.Cache.region, key = "#" + CommonKey.Cache.Region.parentId)
//    @Cacheable(value = CommonKey.Cache.region, key = "'parentId_'+#parentId")
    @Override
    public List<RegionModel_> getRegion(int parentId) {

        List<RegionModel_> list = regionModel_mapper.selectByParentId(parentId);

        return list;
    }

    //    @Cacheable(cacheNames = CommonKey.Cache.region, key = "'code_'+#code")
    @Override
    public String getAddressByCode(String code) {
        return null;
    }

    @Override
    public String getAddressById(int id) {
        RegionModel_ model = regionModel_mapper.selectByPrimaryKey((short) id);
        return model.getName();
    }

    @Override
    public String getAddressByIds(Integer[] ids) {
        String idss = Arrays.stream(ids).map(String::valueOf).collect(Collectors.joining(","));
        List<RegionModel_> models = regionModel_mapper.selectByIds(idss);
        if (models == null) return "";
        String address = models.stream().map(RegionModel_::getName).collect(Collectors.joining());
        return address;
    }


    public static void main(String[] args) {

    }
}
