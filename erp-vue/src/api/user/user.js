import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/user/getList',
    method: 'get',
    params
  })
}

export function add(data) {
  return request({
    url: '/user/register',
    method: 'post',
    data
  })
}

export function deleteUser(data) {
  return request({
    url: '/user/delete',
    method: 'post',
    data
  })
}
