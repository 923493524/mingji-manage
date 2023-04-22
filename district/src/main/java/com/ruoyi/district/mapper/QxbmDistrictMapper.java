package com.ruoyi.district.mapper;

import java.util.List;
import com.ruoyi.district.domain.QxbmDistrict;

/**
 * 行政区Mapper接口
 * 
 * @author luomoqi
 * @date 2023-04-21
 */
public interface QxbmDistrictMapper 
{
    /**
     * 查询行政区
     * 
     * @param districtId 行政区主键
     * @return 行政区
     */
    public QxbmDistrict selectQxbmDistrictByDistrictId(Long districtId);

    /**
     * 查询行政区列表
     * 
     * @param qxbmDistrict 行政区
     * @return 行政区集合
     */
    public List<QxbmDistrict> selectQxbmDistrictList(QxbmDistrict qxbmDistrict);

    /**
     * 新增行政区
     * 
     * @param qxbmDistrict 行政区
     * @return 结果
     */
    public int insertQxbmDistrict(QxbmDistrict qxbmDistrict);

    /**
     * 修改行政区
     * 
     * @param qxbmDistrict 行政区
     * @return 结果
     */
    public int updateQxbmDistrict(QxbmDistrict qxbmDistrict);

    /**
     * 删除行政区
     * 
     * @param districtId 行政区主键
     * @return 结果
     */
    public int deleteQxbmDistrictByDistrictId(Long districtId);

    /**
     * 批量删除行政区
     * 
     * @param districtIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQxbmDistrictByDistrictIds(Long[] districtIds);
}
