import { getMenus } from '@/api/menus/menus'
import { getToken } from '@/utils/auth'
import { dynamicRoutes } from '@/router'
/* Layout */
import Layout from '@/layout'
const getDefaultState = () => {
  return {
    token: getToken(),
    menuList: []
  }
}

const state = getDefaultState()

const mutations = {
  SET_MENUS: (state, menus) => {
    state.menuList = menus
  }
}

// 动态菜单还是定义在前端，后台只会返回有权限的菜单列表，通过遍历服务端的菜单数据，没有的将对于菜单进行隐藏
// 这样的好处是服务端无需返回前端菜单相关结构，并且菜单显示又可以通过服务端来控制，进行菜单的动态控制
// 前端新增页面也无需先通过服务端进行菜单添加，遵循了前后端分离原则
export function generaMenu(routes, srvMenus) {
  console.log('response ', routes, srvMenus)
  for (let i = 0; i < routes.length; i++) {
    const routeItem = routes[i]
    var showItem = false
    for (let j = 0; j < srvMenus.length; j++) {
      const srvItem = srvMenus[j]
      // 前后端数据通过 srvName 属性来匹配
      console.log(routeItem, srvItem)
      if (routeItem.srvName !== undefined && routeItem.srvName === srvItem.srvName) {
        console.log('hidden become true')
        showItem = true
        routes[i]['hidden'] = false
        break
      }
    }
    if (showItem === false) {
      routes[i]['hidden'] = true
    }

    // if (routeItem['children'] !== undefined && routeItem['children'].length > 0) {
    //   generaMenu(routes[i]['children'], srvMenus)
    // }
  }
}

const actions = {

  getMenus({ commit }) {
    return new Promise((resolve, reject) => {
      getMenus(getToken()).then(response => {
        const { data } = response
        if (!data) {
          reject('Verification failed, please Login again.')
        }
        const srvMenus = data
        var pushRouter = dynamicRoutes
        // console.log('push router', pushRouter)
        // console.log(srvMenus)
        generaMenu(pushRouter, srvMenus)
        commit('SET_MENUS', pushRouter)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  }
  // getMenus({ commit }) {
  //   return new Promise((resolve, reject) => {
  //     getMenus(getToken()).then(response => {
  //       const { data } = response
  //       console.log('获取菜单成功：', data)
  //       if (!data) {
  //         reject('Verification failed, please Login again.')
  //       }
  //       // const srvMenus = data
  //       var pushRouter = dynamicRoutes
  //       for (let i = 0; i < data.length; i++) {
  //         console.log('获取内容成功：', data[i].id, data[i].menuName, data[i].srvName, data[i].tokenId)
  //         const router = {
  //           path: data[i].srvName,
  //           component: Layout,
  //           children: [
  //             {
  //               path: 'index',
  //               name: data[i].menuName,
  //               component: (resolve) => require(['@/views' + data[i].srvName + '/Index'], resolve),
  //               meta: { title: data[i].menuName, icon: 'camera' }
  //             }]
  //         }
  //         console.log('router：', router)
  //         pushRouter.push(router)
  //       }
  //       pushRouter.push({ path: '*', redirect: '/404', hidden: true })
  //       console.log('获取router成功：', pushRouter)
  //       // var pushRouter = dynamicRoutes
  //       // console.log('push router', pushRouter)
  //       // console.log(srvMenus)
  //       // generaMenu(pushRouter, srvMenus)
  //       commit('SET_MENUS', pushRouter)
  //       resolve(data)
  //     }).catch(error => {
  //       reject(error)
  //     })
  //   })
  // }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
