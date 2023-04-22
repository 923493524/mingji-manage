package com.ruoyi.wxuser.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wxuser.mapper.UserMapper;
import com.ruoyi.wxuser.domain.User;
import com.ruoyi.wxuser.service.IUserService;

/**
 * 微信用户管理Service业务层处理
 * 
 * @author luomoqi
 * @date 2023-04-20
 */
@Service
public class UserServiceImpl implements IUserService 
{
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询微信用户管理
     * 
     * @param userId 微信用户管理主键
     * @return 微信用户管理
     */
    @Override
    public User selectUserByUserId(Long userId)
    {
        return userMapper.selectUserByUserId(userId);
    }

    /**
     * 查询微信用户管理列表
     * 
     * @param user 微信用户管理
     * @return 微信用户管理
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增微信用户管理
     * 
     * @param user 微信用户管理
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        return userMapper.insertUser(user);
    }

    /**
     * 修改微信用户管理
     * 
     * @param user 微信用户管理
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 批量删除微信用户管理
     * 
     * @param userIds 需要删除的微信用户管理主键
     * @return 结果
     */
    @Override
    public int deleteUserByUserIds(Long[] userIds)
    {
        return userMapper.deleteUserByUserIds(userIds);
    }

    /**
     * 删除微信用户管理信息
     * 
     * @param userId 微信用户管理主键
     * @return 结果
     */
    @Override
    public int deleteUserByUserId(Long userId)
    {
        return userMapper.deleteUserByUserId(userId);
    }
}
