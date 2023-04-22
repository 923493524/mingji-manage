package com.ruoyi.district.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.district.mapper.QxbmDistrictMapper;
import com.ruoyi.district.domain.QxbmDistrict;
import com.ruoyi.district.service.IQxbmDistrictService;

/**
 * 行政区Service业务层处理
 * 
 * @author luomoqi
 * @date 2023-04-21
 */
@Service
public class QxbmDistrictServiceImpl implements IQxbmDistrictService 
{
    @Autowired
    private QxbmDistrictMapper qxbmDistrictMapper;

    /**
     * 查询行政区
     * 
     * @param districtId 行政区主键
     * @return 行政区
     */
    @Override
    public QxbmDistrict selectQxbmDistrictByDistrictId(Long districtId)
    {
        return qxbmDistrictMapper.selectQxbmDistrictByDistrictId(districtId);
    }

    /**
     * 查询行政区列表
     * 
     * @param qxbmDistrict 行政区
     * @return 行政区
     */
    @Override
    public List<QxbmDistrict> selectQxbmDistrictList(QxbmDistrict qxbmDistrict)
    {
        return qxbmDistrictMapper.selectQxbmDistrictList(qxbmDistrict);
    }

    /**
     * 新增行政区
     * 
     * @param qxbmDistrict 行政区
     * @return 结果
     */
    @Override
    public int insertQxbmDistrict(QxbmDistrict qxbmDistrict)
    {
        return qxbmDistrictMapper.insertQxbmDistrict(qxbmDistrict);
    }

    /**
     * 修改行政区
     * 
     * @param qxbmDistrict 行政区
     * @return 结果
     */
    @Override
    public int updateQxbmDistrict(QxbmDistrict qxbmDistrict)
    {
        return qxbmDistrictMapper.updateQxbmDistrict(qxbmDistrict);
    }

    /**
     * 批量删除行政区
     * 
     * @param districtIds 需要删除的行政区主键
     * @return 结果
     */
    @Override
    public int deleteQxbmDistrictByDistrictIds(Long[] districtIds)
    {
        return qxbmDistrictMapper.deleteQxbmDistrictByDistrictIds(districtIds);
    }

    /**
     * 删除行政区信息
     * 
     * @param districtId 行政区主键
     * @return 结果
     */
    @Override
    public int deleteQxbmDistrictByDistrictId(Long districtId)
    {
        return qxbmDistrictMapper.deleteQxbmDistrictByDistrictId(districtId);
    }
}
