package com.ruoyi.district.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 行政区对象 qxbm_district
 * 
 * @author luomoqi
 * @date 2023-04-21
 */
public class QxbmDistrict extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 行政区ID */
    @Excel(name = "行政区ID")
    private Long districtId;

    /** 行政区父id */
    @Excel(name = "行政区父id")
    private Long districtPid;

    /** 行政区名称 */
    @Excel(name = "行政区名称")
    private String districtName;

    /** 行政区简称 */
    @Excel(name = "行政区简称")
    private String districtShortname;

    /** 行政区纬度(y轴) */
    @Excel(name = "行政区纬度(y轴)")
    private String districtLngX;

    /** 行政区经度(x轴) */
    @Excel(name = "行政区经度(x轴)")
    private String districtLatY;

    /** 行政区等级 */
    @Excel(name = "行政区等级")
    private Integer districtLevel;

    /** 行政区排序 */
    @Excel(name = "行政区排序")
    private Integer districtSort;

    /** 行政区是否有效(0:无效,1:有效) */
    @Excel(name = "行政区是否有效(0:无效,1:有效)")
    private Integer districtStatus;

    public void setDistrictId(Long districtId) 
    {
        this.districtId = districtId;
    }

    public Long getDistrictId() 
    {
        return districtId;
    }
    public void setDistrictPid(Long districtPid) 
    {
        this.districtPid = districtPid;
    }

    public Long getDistrictPid() 
    {
        return districtPid;
    }
    public void setDistrictName(String districtName) 
    {
        this.districtName = districtName;
    }

    public String getDistrictName() 
    {
        return districtName;
    }
    public void setDistrictShortname(String districtShortname) 
    {
        this.districtShortname = districtShortname;
    }

    public String getDistrictShortname() 
    {
        return districtShortname;
    }
    public void setDistrictLngX(String districtLngX) 
    {
        this.districtLngX = districtLngX;
    }

    public String getDistrictLngX() 
    {
        return districtLngX;
    }
    public void setDistrictLatY(String districtLatY) 
    {
        this.districtLatY = districtLatY;
    }

    public String getDistrictLatY() 
    {
        return districtLatY;
    }
    public void setDistrictLevel(Integer districtLevel) 
    {
        this.districtLevel = districtLevel;
    }

    public Integer getDistrictLevel() 
    {
        return districtLevel;
    }
    public void setDistrictSort(Integer districtSort) 
    {
        this.districtSort = districtSort;
    }

    public Integer getDistrictSort() 
    {
        return districtSort;
    }
    public void setDistrictStatus(Integer districtStatus) 
    {
        this.districtStatus = districtStatus;
    }

    public Integer getDistrictStatus() 
    {
        return districtStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("districtId", getDistrictId())
            .append("districtPid", getDistrictPid())
            .append("districtName", getDistrictName())
            .append("districtShortname", getDistrictShortname())
            .append("districtLngX", getDistrictLngX())
            .append("districtLatY", getDistrictLatY())
            .append("districtLevel", getDistrictLevel())
            .append("districtSort", getDistrictSort())
            .append("districtStatus", getDistrictStatus())
            .toString();
    }
}
