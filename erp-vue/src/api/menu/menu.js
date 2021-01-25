import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/menu/getList',
    method: 'get',
    params
  })
}

export function add(data) {
  return request({
    url: '/menu/add',
    method: 'post',
    data
  })
}

export function getTokens(params) {
  return request({
    url: '/token/getList',
    method: 'get',
    params
  })
}

export function deleteMenu(data) {
  return request({
    url: '/menu/delete',
    method: 'post',
    data
  })
}
