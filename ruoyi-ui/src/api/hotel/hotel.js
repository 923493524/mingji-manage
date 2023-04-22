import request from '@/utils/request'

// 查询酒店管理列表
export function listHotel(query) {
  return request({
    url: '/hotel/hotel/list',
    method: 'get',
    params: query
  })
}

export function hotelName(query) {
  return request({
    url: '/hotel/hotel/name',
    method: 'get',
    params: query
  })
}

// 查询酒店管理详细
export function getHotel(hotelId) {
  return request({
    url: '/hotel/hotel/' + hotelId,
    method: 'get'
  })
}



// 新增酒店管理
export function addHotel(data) {
  return request({
    url: '/hotel/hotel',
    method: 'post',
    data: data
  })
}

// 修改酒店管理
export function updateHotel(data) {
  return request({
    url: '/hotel/hotel',
    method: 'put',
    data: data
  })
}

// 删除酒店管理
export function delHotel(hotelId) {
  return request({
    url: '/hotel/hotel/' + hotelId,
    method: 'delete'
  })
}
