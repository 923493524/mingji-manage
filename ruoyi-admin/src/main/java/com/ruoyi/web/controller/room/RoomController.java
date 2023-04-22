package com.ruoyi.web.controller.room;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.room.domain.Room;
import com.ruoyi.room.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 房间管理Controller
 * 
 * @author luomoqi
 * @date 2023-04-20
 */
@RestController
@RequestMapping("/room/room")
public class RoomController extends BaseController
{
    @Autowired
    private IRoomService roomService;

    /**
     * 查询房间管理列表
     */
    @PreAuthorize("@ss.hasPermi('room:room:list')")
    @GetMapping("/list")
    public TableDataInfo list(Room room)
    {
        startPage();
        List<Room> list = roomService.selectRoomList(room);
        return getDataTable(list);
    }

    /**
     * 导出房间管理列表
     */
    @PreAuthorize("@ss.hasPermi('room:room:export')")
    @Log(title = "房间管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Room room)
    {
        List<Room> list = roomService.selectRoomList(room);
        ExcelUtil<Room> util = new ExcelUtil<Room>(Room.class);
        util.exportExcel(response, list, "房间管理数据");
    }

    /**
     * 获取房间管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('room:room:query')")
    @GetMapping(value = "/{roomId}")
    public AjaxResult getInfo(@PathVariable("roomId") Long roomId)
    {
        return success(roomService.selectRoomByRoomId(roomId));
    }

    /**
     * 新增房间管理
     */
    @PreAuthorize("@ss.hasPermi('room:room:add')")
    @Log(title = "房间管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Room room)
    {
        return toAjax(roomService.insertRoom(room));
    }

    /**
     * 修改房间管理
     */
    @PreAuthorize("@ss.hasPermi('room:room:edit')")
    @Log(title = "房间管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Room room)
    {
        return toAjax(roomService.updateRoom(room));
    }

    /**
     * 删除房间管理
     */
    @PreAuthorize("@ss.hasPermi('room:room:remove')")
    @Log(title = "房间管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roomIds}")
    public AjaxResult remove(@PathVariable Long[] roomIds)
    {
        return toAjax(roomService.deleteRoomByRoomIds(roomIds));
    }
}
