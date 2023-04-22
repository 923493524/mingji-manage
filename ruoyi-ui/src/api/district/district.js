import request from '@/utils/request'

// 查询行政区列表
export function listDistrict(query) {
  return request({
    url: '/district/district/list',
    method: 'get',
    params: query
  })
}

// 查询行政区详细
export function getDistrict(districtId) {
  return request({
    url: '/district/district/' + districtId,
    method: 'get'
  })
}

// 新增行政区
export function addDistrict(data) {
  return request({
    url: '/district/district',
    method: 'post',
    data: data
  })
}

// 修改行政区
export function updateDistrict(data) {
  return request({
    url: '/district/district',
    method: 'put',
    data: data
  })
}

// 删除行政区
export function delDistrict(districtId) {
  return request({
    url: '/district/district/' + districtId,
    method: 'delete'
  })
}
