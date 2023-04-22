package com.ruoyi.room.mapper;

import java.util.List;
import com.ruoyi.room.domain.Room;

/**
 * 房间管理Mapper接口
 * 
 * @author luomoqi
 * @date 2023-04-20
 */
public interface RoomMapper 
{
    /**
     * 查询房间管理
     * 
     * @param roomId 房间管理主键
     * @return 房间管理
     */
    public Room selectRoomByRoomId(Long roomId);

    /**
     * 查询房间管理列表
     * 
     * @param room 房间管理
     * @return 房间管理集合
     */
    public List<Room> selectRoomList(Room room);

    /**
     * 新增房间管理
     * 
     * @param room 房间管理
     * @return 结果
     */
    public int insertRoom(Room room);

    /**
     * 修改房间管理
     * 
     * @param room 房间管理
     * @return 结果
     */
    public int updateRoom(Room room);

    /**
     * 删除房间管理
     * 
     * @param roomId 房间管理主键
     * @return 结果
     */
    public int deleteRoomByRoomId(Long roomId);

    /**
     * 批量删除房间管理
     * 
     * @param roomIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRoomByRoomIds(Long[] roomIds);
}
