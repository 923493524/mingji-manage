package com.ruoyi.hotel.mapper;

import java.util.List;
import com.ruoyi.hotel.domain.Hotel;

/**
 * 酒店管理Mapper接口
 * 
 * @author luomoqi
 * @date 2023-04-20
 */
public interface HotelMapper 
{
    /**
     * 查询酒店管理
     * 
     * @param hotelId 酒店管理主键
     * @return 酒店管理
     */
    public Hotel selectHotelByHotelId(Long hotelId);

    public String selectHotelName(Hotel hotel);
    /**
     * 查询酒店管理列表
     * 
     * @param hotel 酒店管理
     * @return 酒店管理集合
     */
    public List<Hotel> selectHotelList(Hotel hotel);

    /**
     * 新增酒店管理
     * 
     * @param hotel 酒店管理
     * @return 结果
     */
    public int insertHotel(Hotel hotel);

    /**
     * 修改酒店管理
     * 
     * @param hotel 酒店管理
     * @return 结果
     */
    public int updateHotel(Hotel hotel);

    /**
     * 删除酒店管理
     * 
     * @param hotelId 酒店管理主键
     * @return 结果
     */
    public int deleteHotelByHotelId(Long hotelId);

    /**
     * 批量删除酒店管理
     * 
     * @param hotelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHotelByHotelIds(Long[] hotelIds);
}
