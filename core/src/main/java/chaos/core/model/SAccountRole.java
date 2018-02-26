package chaos.core.model;

import chaos.api.annoatation.ApiField;

import java.io.Serializable;

public class SAccountRole implements Serializable {
    @ApiField("id///")
    private Long id;

    /**
     * 账号ID
     */
    @ApiField("aId/账户ID//")
    private Long aId;

    /**
     * 角色ID
     */
    @ApiField("rId/角色ID//")
    private Long rId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_account_role
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 账户ID
     */
    public Long getaId() {
        return aId;
    }

    /**
     * 账户ID
     */
    public void setaId(Long aId) {
        this.aId = aId;
    }

    /**
     * 角色ID
     */
    public Long getrId() {
        return rId;
    }

    /**
     * 角色ID
     */
    public void setrId(Long rId) {
        this.rId = rId;
    }
}