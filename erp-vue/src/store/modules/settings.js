import defaultSettings from '@/settings'

const { showSettings, fixedHeader, sidebarLogo, curPlantId, curPitId, curId, curTerminalId, borderIndex, curCellarCode, curRowCode } = defaultSettings

const state = {
  showSettings: showSettings,
  fixedHeader: fixedHeader,
  sidebarLogo: sidebarLogo,
  curPlantId: curPlantId,
  curPitId: curPitId,
  curId: curId,
  curTerminalId: curTerminalId,
  borderIndex: borderIndex,
  curRowCode: curRowCode,
  curCellarCode: curCellarCode
}

const mutations = {
  CHANGE_SETTING: (state, { key, value }) => {
    // eslint-disable-next-line no-prototype-builtins
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  },
  SET_PLANTID: (state, plantId) => {
    state.curPlantId = plantId
  },
  SET_PITID: (state, pitId) => {
    state.curPitId = pitId
  },
  SET_ID: (state, id) => {
    state.curId = id
  },
  SET_TERMINALID: (state, terminalId) => {
    state.curTerminalId = terminalId
  },
  SET_BORDERINDEX: (state, index) => {
    state.borderIndex = index
  },
  SET_ROWCODE: (state, rowCode) => {
    state.curRowCode = rowCode
  },
  SET_CELLARCODE: (state, cellarCode) => {
    state.curCellarCode = cellarCode
  }
}

const actions = {
  changeSetting({ commit }, data) {
    commit('CHANGE_SETTING', data)
  },
  setPlantId({ commit }, data) {
    commit('SET_PLANTID', data)
  },
  setPitId({ commit }, data) {
    commit('SET_PITID', data)
  },
  setId({ commit }, data) {
    commit('SET_ID', data)
  },
  setTerminalId({ commit }, data) {
    commit('SET_TERMINALID', data)
  },
  setBorderIndex({ commit }, data) {
    commit('SET_BORDERINDEX', data)
  },
  setRowCode({ commit }, data) {
    console.log('set row code', data)
    commit('SET_ROWCODE', data)
  },
  setCellarCode({ commit }, data) {
    commit('SET_CELLARCODE', data)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

