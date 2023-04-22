package com.ruoyi.hotel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hotel.mapper.HotelMapper;
import com.ruoyi.hotel.domain.Hotel;
import com.ruoyi.hotel.service.IHotelService;

/**
 * 酒店管理Service业务层处理
 * 
 * @author luomoqi
 * @date 2023-04-20
 */
@Service
public class HotelServiceImpl implements IHotelService 
{
    @Autowired
    private HotelMapper hotelMapper;

    /**
     * 查询酒店管理
     * 
     * @param hotelId 酒店管理主键
     * @return 酒店管理
     */
    @Override
    public Hotel selectHotelByHotelId(Long hotelId)
    {
        return hotelMapper.selectHotelByHotelId(hotelId);
    }

    @Override
    public String selectHotelName(Hotel hotel) {
        return hotelMapper.selectHotelName(hotel);
    }

    /**
     * 查询酒店管理列表
     * 
     * @param hotel 酒店管理
     * @return 酒店管理
     */
    @Override
    public List<Hotel> selectHotelList(Hotel hotel)
    {
        return hotelMapper.selectHotelList(hotel);
    }

    /**
     * 新增酒店管理
     * 
     * @param hotel 酒店管理
     * @return 结果
     */
    @Override
    public int insertHotel(Hotel hotel)
    {
        return hotelMapper.insertHotel(hotel);
    }

    /**
     * 修改酒店管理
     * 
     * @param hotel 酒店管理
     * @return 结果
     */
    @Override
    public int updateHotel(Hotel hotel)
    {
        return hotelMapper.updateHotel(hotel);
    }

    /**
     * 批量删除酒店管理
     * 
     * @param hotelIds 需要删除的酒店管理主键
     * @return 结果
     */
    @Override
    public int deleteHotelByHotelIds(Long[] hotelIds)
    {
        return hotelMapper.deleteHotelByHotelIds(hotelIds);
    }

    /**
     * 删除酒店管理信息
     * 
     * @param hotelId 酒店管理主键
     * @return 结果
     */
    @Override
    public int deleteHotelByHotelId(Long hotelId)
    {
        return hotelMapper.deleteHotelByHotelId(hotelId);
    }
}
