import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/terminal/getList',
    method: 'get',
    params
  })
}
export function getPlants(params) {
  return request({
    url: '/plant/getList',
    method: 'get',
    params
  })
}
export function getPlantById(data) {
  return request({
    url: '/plant/getPlantById',
    method: 'post',
    data
  })
}
export function getPitById(data) {
  return request({
    url: '/pit/getPitById',
    method: 'post',
    data
  })
}
export function getTerritories(data) {
  return request({
    url: '/territory/getListByPlantId',
    method: 'post',
    data
  })
}
export function getTeams(data) {
  return request({
    url: '/team/getListByTerritoryId',
    method: 'post',
    data
  })
}
export function getPits(data) {
  return request({
    url: '/pit/getListByTeamId',
    method: 'post',
    data
  })
}
export function getPitsByPlant(data) {
  return request({
    url: '/pit/getListByPlantId',
    method: 'post',
    data
  })
}
export function getPitsByPlantCode(data) {
  return request({
    url: '/pit/getListByPlantCode',
    method: 'post',
    data
  })
}
export function getRowsAndCellars(data) {
  return request({
    url: '/terminal/getRowsAndCellars',
    method: 'post',
    data
  })
}

