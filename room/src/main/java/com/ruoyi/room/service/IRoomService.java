package com.ruoyi.room.service;

import java.util.List;
import com.ruoyi.room.domain.Room;

/**
 * 房间管理Service接口
 * 
 * @author luomoqi
 * @date 2023-04-20
 */
public interface IRoomService 
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
     * 批量删除房间管理
     * 
     * @param roomIds 需要删除的房间管理主键集合
     * @return 结果
     */
    public int deleteRoomByRoomIds(Long[] roomIds);

    /**
     * 删除房间管理信息
     * 
     * @param roomId 房间管理主键
     * @return 结果
     */
    public int deleteRoomByRoomId(Long roomId);
}
