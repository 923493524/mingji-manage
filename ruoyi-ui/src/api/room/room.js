import request from '@/utils/request'

// 查询房间管理列表
export function listRoom(query) {
  return request({
    url: '/room/room/list',
    method: 'get',
    params: query
  })
}

// 查询房间管理详细
export function getRoom(roomId) {
  return request({
    url: '/room/room/' + roomId,
    method: 'get'
  })
}

// 新增房间管理
export function addRoom(data) {
  return request({
    url: '/room/room',
    method: 'post',
    data: data
  })
}

// 修改房间管理
export function updateRoom(data) {
  return request({
    url: '/room/room',
    method: 'put',
    data: data
  })
}

// 删除房间管理
export function delRoom(roomId) {
  return request({
    url: '/room/room/' + roomId,
    method: 'delete'
  })
}


