package com.ruoyi.wxuser.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微信用户管理对象 h_user
 * 
 * @author luomoqi
 * @date 2023-04-20
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long userId;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date userCreateTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date userUpdateTime;

    /** openid */
    @Excel(name = "openid")
    private String openid;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setUserCreateTime(Date userCreateTime) 
    {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserCreateTime() 
    {
        return userCreateTime;
    }
    public void setUserUpdateTime(Date userUpdateTime) 
    {
        this.userUpdateTime = userUpdateTime;
    }

    public Date getUserUpdateTime() 
    {
        return userUpdateTime;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("nickname", getNickname())
            .append("userCreateTime", getUserCreateTime())
            .append("userUpdateTime", getUserUpdateTime())
            .append("openid", getOpenid())
            .toString();
    }
}
