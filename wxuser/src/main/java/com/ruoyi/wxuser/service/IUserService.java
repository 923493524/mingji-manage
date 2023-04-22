package com.ruoyi.wxuser.service;

import java.util.List;
import com.ruoyi.wxuser.domain.User;

/**
 * 微信用户管理Service接口
 * 
 * @author luomoqi
 * @date 2023-04-20
 */
public interface IUserService 
{
    /**
     * 查询微信用户管理
     * 
     * @param userId 微信用户管理主键
     * @return 微信用户管理
     */
    public User selectUserByUserId(Long userId);

    /**
     * 查询微信用户管理列表
     * 
     * @param user 微信用户管理
     * @return 微信用户管理集合
     */
    public List<User> selectUserList(User user);

    /**
     * 新增微信用户管理
     * 
     * @param user 微信用户管理
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 修改微信用户管理
     * 
     * @param user 微信用户管理
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 批量删除微信用户管理
     * 
     * @param userIds 需要删除的微信用户管理主键集合
     * @return 结果
     */
    public int deleteUserByUserIds(Long[] userIds);

    /**
     * 删除微信用户管理信息
     * 
     * @param userId 微信用户管理主键
     * @return 结果
     */
    public int deleteUserByUserId(Long userId);
}
