import request from '@/utils/request'

export function getMenus(token) {
  return request({
    url: '/menu/get',
    method: 'get',
    params: { token }
  })
}
