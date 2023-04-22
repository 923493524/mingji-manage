package com.ruoyi.hotel.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 酒店管理对象 h_hotel
 * 
 * @author luomoqi
 * @date 2023-04-20
 */
public class Hotel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long hotelId;

    /** 名称 */
    @Excel(name = "名称")
    private String hotelName;

    /** 地址 */
    @Excel(name = "地址")
    private String hotelAddress;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String hotelTel;

    /** 负责人 */
    @Excel(name = "负责人")
    private String hotelPerson;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hotelCreateTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hotelUpdateTime;

    /** 封面图 */
    @Excel(name = "封面图")
    private String hotelPic;

    /** 省市区 */
    @Excel(name = "省市区")
    private String hotelSsq;

    public void setHotelId(Long hotelId) 
    {
        this.hotelId = hotelId;
    }

    public Long getHotelId() 
    {
        return hotelId;
    }
    public void setHotelName(String hotelName) 
    {
        this.hotelName = hotelName;
    }

    public String getHotelName() 
    {
        return hotelName;
    }
    public void setHotelAddress(String hotelAddress) 
    {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelAddress() 
    {
        return hotelAddress;
    }
    public void setHotelTel(String hotelTel) 
    {
        this.hotelTel = hotelTel;
    }

    public String getHotelTel() 
    {
        return hotelTel;
    }
    public void setHotelPerson(String hotelPerson) 
    {
        this.hotelPerson = hotelPerson;
    }

    public String getHotelPerson() 
    {
        return hotelPerson;
    }
    public void setHotelCreateTime(Date hotelCreateTime) 
    {
        this.hotelCreateTime = hotelCreateTime;
    }

    public Date getHotelCreateTime() 
    {
        return hotelCreateTime;
    }
    public void setHotelUpdateTime(Date hotelUpdateTime) 
    {
        this.hotelUpdateTime = hotelUpdateTime;
    }

    public Date getHotelUpdateTime() 
    {
        return hotelUpdateTime;
    }
    public void setHotelPic(String hotelPic) 
    {
        this.hotelPic = hotelPic;
    }

    public String getHotelPic() 
    {
        return hotelPic;
    }
    public void setHotelSsq(String hotelSsq) 
    {
        this.hotelSsq = hotelSsq;
    }

    public String getHotelSsq() 
    {
        return hotelSsq;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("hotelId", getHotelId())
            .append("hotelName", getHotelName())
            .append("hotelAddress", getHotelAddress())
            .append("hotelTel", getHotelTel())
            .append("hotelPerson", getHotelPerson())
            .append("hotelCreateTime", getHotelCreateTime())
            .append("hotelUpdateTime", getHotelUpdateTime())
            .append("hotelPic", getHotelPic())
            .append("hotelSsq", getHotelSsq())
            .toString();
    }
}
