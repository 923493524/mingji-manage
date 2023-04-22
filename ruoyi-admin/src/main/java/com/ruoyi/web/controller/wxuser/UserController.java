package com.ruoyi.web.controller.wxuser;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.wxuser.domain.User;
import com.ruoyi.wxuser.service.IUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 微信用户管理Controller
 * 
 * @author luomoqi
 * @date 2023-04-20
 */
@RestController
@RequestMapping("/wxuser/wxuser")
public class UserController extends BaseController
{
    @Autowired
    private IUserService userService;

    /**
     * 查询微信用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('wxuser:wxuser:list')")
    @GetMapping("/list")
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 导出微信用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('wxuser:wxuser:export')")
    @Log(title = "微信用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, User user)
    {
        List<User> list = userService.selectUserList(user);
        ExcelUtil<User> util = new ExcelUtil<User>(User.class);
        util.exportExcel(response, list, "微信用户管理数据");
    }

    /**
     * 获取微信用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('wxuser:wxuser:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(userService.selectUserByUserId(userId));
    }

    /**
     * 新增微信用户管理
     */
    @PreAuthorize("@ss.hasPermi('wxuser:wxuser:add')")
    @Log(title = "微信用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody User user)
    {
        return toAjax(userService.insertUser(user));
    }

    /**
     * 修改微信用户管理
     */
    @PreAuthorize("@ss.hasPermi('wxuser:wxuser:edit')")
    @Log(title = "微信用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody User user)
    {
        return toAjax(userService.updateUser(user));
    }

    /**
     * 删除微信用户管理
     */
    @PreAuthorize("@ss.hasPermi('wxuser:wxuser:remove')")
    @Log(title = "微信用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userService.deleteUserByUserIds(userIds));
    }
}
