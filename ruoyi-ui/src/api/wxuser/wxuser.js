import request from '@/utils/request'

// 查询微信用户管理列表
export function listWxuser(query) {
  return request({
    url: '/wxuser/wxuser/list',
    method: 'get',
    params: query
  })
}

// 查询微信用户管理详细
export function getWxuser(userId) {
  return request({
    url: '/wxuser/wxuser/' + userId,
    method: 'get'
  })
}

// 新增微信用户管理
export function addWxuser(data) {
  return request({
    url: '/wxuser/wxuser',
    method: 'post',
    data: data
  })
}

// 修改微信用户管理
export function updateWxuser(data) {
  return request({
    url: '/wxuser/wxuser',
    method: 'put',
    data: data
  })
}

// 删除微信用户管理
export function delWxuser(userId) {
  return request({
    url: '/wxuser/wxuser/' + userId,
    method: 'delete'
  })
}
