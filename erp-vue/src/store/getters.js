const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  menuList: state => state.menus.menuList,
  introduction: state => state.user.introduction,
  curPlantId: state => state.settings.curPlantId,
  curPitId: state => state.settings.curPitId,
  curId: state => state.settings.curId,
  curTerminalId: state => state.settings.curTerminalId,
  curRowCode: state => state.settings.curRowCode,
  curCellarCode: state => state.settings.curCellarCode
}
export default getters
