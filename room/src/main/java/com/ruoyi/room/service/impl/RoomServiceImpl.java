package com.ruoyi.room.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.room.mapper.RoomMapper;
import com.ruoyi.room.domain.Room;
import com.ruoyi.room.service.IRoomService;

/**
 * 房间管理Service业务层处理
 * 
 * @author luomoqi
 * @date 2023-04-20
 */
@Service
public class RoomServiceImpl implements IRoomService 
{
    @Autowired
    private RoomMapper roomMapper;

    /**
     * 查询房间管理
     * 
     * @param roomId 房间管理主键
     * @return 房间管理
     */
    @Override
    public Room selectRoomByRoomId(Long roomId)
    {
        return roomMapper.selectRoomByRoomId(roomId);
    }

    /**
     * 查询房间管理列表
     * 
     * @param room 房间管理
     * @return 房间管理
     */
    @Override
    public List<Room> selectRoomList(Room room)
    {
        return roomMapper.selectRoomList(room);
    }

    /**
     * 新增房间管理
     * 
     * @param room 房间管理
     * @return 结果
     */
    @Override
    public int insertRoom(Room room)
    {
        return roomMapper.insertRoom(room);
    }

    /**
     * 修改房间管理
     * 
     * @param room 房间管理
     * @return 结果
     */
    @Override
    public int updateRoom(Room room)
    {
        return roomMapper.updateRoom(room);
    }

    /**
     * 批量删除房间管理
     * 
     * @param roomIds 需要删除的房间管理主键
     * @return 结果
     */
    @Override
    public int deleteRoomByRoomIds(Long[] roomIds)
    {
        return roomMapper.deleteRoomByRoomIds(roomIds);
    }

    /**
     * 删除房间管理信息
     * 
     * @param roomId 房间管理主键
     * @return 结果
     */
    @Override
    public int deleteRoomByRoomId(Long roomId)
    {
        return roomMapper.deleteRoomByRoomId(roomId);
    }
}
