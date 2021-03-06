package chaos.core.dao;

import chaos.core.model.ExceptionModel_;
import org.apache.ibatis.annotations.Insert;

public interface ExceptionModel_Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exceptions_
     *
     * @mbg.generated
     */
    @Insert({
        "insert into exceptions_ (ip, user_id, ",
        "title, device, c_date, ",
        "context)",
        "values (#{ip,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, ",
        "#{title,jdbcType=VARCHAR}, #{device,jdbcType=VARCHAR}, #{cDate,jdbcType=BIGINT}, ",
        "#{context,jdbcType=LONGVARCHAR})"
    })
    int insert(ExceptionModel_ record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table exceptions_
     *
     * @mbg.generated
     */
    int insertSelective(ExceptionModel_ record);
}