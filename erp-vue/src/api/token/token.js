import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/token/getList',
    method: 'get',
    params
  })
}

export function add(data) {
  return request({
    url: '/token/add',
    method: 'post',
    data
  })
}

export function deleteToken(data) {
  return request({
    url: '/token/delete',
    method: 'post',
    data
  })
}

