package chaos.core.model;

import chaos.api.annoatation.ApiField;
import chaos.utils.ConvertUtil;
import chaos.utils.json.JsonUtils;
import chaos.utils.object.ObjectUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ©chaos-parent
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-09-26
 */
public class BstParam {

    private final static Logger log = LoggerFactory.getLogger(BstParam.class);

    @ApiField("limit/limit分页//0")
    private int limit = 10;
    @ApiField("offset/limit分页://0")
    private int offset = 0;
    private String order;
    private String sort;
    @ApiField("search/分页搜索条件")
    private BstSearch search;

    /**
     * 前置条件map
     */
    @ApiField("beforeWhere/分页前置条件")
    private Map<String, Object> beforeWhere = new HashMap();

    @JsonIgnore
    private String[] beforeWhereStrs = new String[]{};

    /**
     * 普通分页模式
     */
    @ApiField("pageType/page分页://false")
    private boolean pageType;
    @ApiField("pageCount/page分页://10")
    private int pageCount = 10;
    @ApiField("page/page分页:当前页码//1")
    private int page = -1;

    public BstParam() {

    }

    public int getPage() {
        if (page == 0) page = 1;
        return page;
    }

    /**
     * <pre>
     *     page 模式分页
     *     只需要传 当前页码
     * </pre>
     *
     * @return
     */
    public boolean isPageType() {
        if (getPage() >= 1) pageType = true;
        return pageType;
    }

    public void setPageType(boolean pageType) {
        this.pageType = pageType;
    }

    public void setPage(int page) {
        this.page = page;
    }


    public int getPageCount() {
        if (pageCount == 0) pageCount = 10;
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * <pre>
     *     排序
     * </pre>
     *
     * @return
     */
    public String getOrderWhere() {
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            return ConvertUtil.toTableStyle(sort) + " " + order;
        }
        return "";
    }

    /**
     * <pre>
     *     默认排序
     *     id desc = select * from XXX where id desc
     * </pre>
     *
     * @param defaultStr 默认排序  sort_order ASC
     * @return
     */
    public String getOrderWhere(String defaultStr) {
        if (ObjectUtils.isEmpty(getOrderWhere())) return defaultStr;
        return getOrderWhere();
    }


    public boolean isOrder() {
        return !StringUtils.isEmpty(getOrderWhere());
    }

    public int getLimit() {
        if (isPageType()) limit = getPageCount();
        return limit;
    }

    public void setLimit(int limit) {
        if (limit == 0) return;
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    /**
     * <pre style='color:red'>
     * 注意：order 需要和 sort 同时使用
     * </pre>
     *
     * @param order asc 升序或降序 desc
     */
    public void setOrder(String order) {
        this.order = order;
    }


    public BstSearch getSearch() {
        BstSearch bstSearch = JsonUtils.toObj(search, BstSearch.class);
        if (bstSearch != null) {
            if (ObjectUtils.isEmpty(bstSearch.getField())) return null;
            if (ObjectUtils.isEmpty(bstSearch.getNewValue())) return null;
        }
        return bstSearch;
    }

    /**
     * 包含搜索内容
     *
     * @return
     */
    @JsonIgnore
    public boolean isSearch() {
        if (ObjectUtils.isEmpty(search)) return false;
//        BstSearch searchBST = JsonUtils.toObj(search, BstSearch.class);
//        if (ObjectUtils.isEmpty(searchBST)) return false;
        if (ObjectUtils.isEmpty(search.getField())) return false;
        if (ObjectUtils.isEmpty(search.getNewValue())) return false;
        return true;
    }

    /**
     * 拼接添加条件
     * <pre>
     *     whrer id = 123456
     *     whrer name LINK CONCAT('%参数%')
     * </pre>
     *
     * @return
     */
    @JsonIgnore
    public String getSearchWhere() {
        return getSearchWhere(null);
    }

    /**
     * 在搜索条件后 增加 自定义条件
     * <pre>
     *     whrer id = 123456 jion
     *     whrer name LINK CONCAT('%参数%') jion
     *
     *     注意：
     *     字段中如果包含 Str、All 会被去掉，这个字段需要和数据库字段吻合。
     * </pre>
     *
     * @param jion
     * @return
     */
    @JsonIgnore
    public String getSearchWhere(String jion) {
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(getBeforeWhereString())) {
            sb.append(" where ");
            sb.append(getBeforeWhereString());
        }
        if (ObjectUtils.isEmpty(getSearch())) return sb.toString();
        if (!sb.toString().contains("where")) sb.append(" where ");

        if (!StringUtils.isEmpty(getBeforeWhereString())) {
            sb.append(getBeforeWhereString());
        }

        String newValue = getSearch().getNewValue();
        String field = getSearch().getField();
        if (field.contains("id")) {
            sb.append(field).append(" = ").append("'").append(newValue).append("'").append(" ");
        } else {
            sb.append(field).append(" LIKE ").append("CONCAT('%").append(newValue).append("%')").append(" ");
        }
        if (!ObjectUtils.isEmpty(jion)) sb.append(jion);
        return sb.toString();
    }

    @JsonDeserialize(as = BstSearch.class)
    public void setSearch(BstSearch search) {
        this.search = search;
    }

    public int getOffset() {
        if (isPageType()) offset = getPage() * getPageCount() - getLimit();
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * 请使用 getOrderWhere 排序
     *
     * @return
     */
    @Deprecated
    public String getSort() {
        return sort;
    }


    /**
     * <pre style='color:red'>
     * 注意：order 需要和 sort 同时使用
     * </pre>
     *
     * @param sort 需要排序的 字段
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    public Map<String, Object> getBeforeWhere() {
        return beforeWhere;
    }

    @JsonIgnore
    public String getBeforeWhereString() {

//        CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,"ClassName")-->class_name
//        CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "class_name")-->className
        StringBuffer sb = new StringBuffer();
//        getBeforeWhere()
        Iterator<Map.Entry<String, Object>> entryIterator = getBeforeWhere().entrySet().iterator();
        for (int i = 0; entryIterator.hasNext(); i++) {

            Map.Entry<String, Object> entry = entryIterator.next();
            if (ObjectUtils.isEmpty(entry.getValue())) {
                i--;
                continue;
            }
            try {
                String key = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey());
                if (i > 0) sb.append(" and ");
                sb.append(key).append("=").append(entry.getValue());
            } catch (Exception e) {
                log.warn("拼接参数" + entry.getKey() + "异常！");
            }
        }
//        for (Map.Entry<String, Object> entry : getBeforeWhere().entrySet()) {
//            try {
//                String key = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, entry.getKey());
//                sb.append(key).append("=").append(entry.getValue());
//            } catch (Exception e) {
//                log.warn("拼接参数" + entry.getKey() + "异常！");
//            }
//        }

        String join = StringUtils.join(getBeforeWhereStrs(), " ");
        if (!StringUtils.isEmpty(join)) {
            sb.append(" ");
            sb.append(join);
            sb.append(" ");
        }
        return sb.toString();
    }

    public void setBeforeWhere(Map<String, Object> beforeWhere) {
        this.beforeWhere = beforeWhere;
    }

    public String[] getBeforeWhereStrs() {
        return beforeWhereStrs;
    }

    public void setBeforeWhereStrs(String[] beforeWhereStrs) {
        this.beforeWhereStrs = beforeWhereStrs;
    }
}
