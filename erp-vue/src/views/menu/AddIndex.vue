<template>
  <div>
    <el-dialog title="新增菜单" :visible.sync="dialogFormVisible" @close="clear">
      <el-form ref="list" :model="list" :rules="rules">
        <el-form-item label="菜单名称" :label-width="formLabelWidth">
          <el-input v-model="list.menuName" auto-complete="off" />
        </el-form-item>
        <el-form-item label="菜单路由" :label-width="formLabelWidth">
          <el-input v-model="list.srvName" auto-complete="off" />
        </el-form-item>
        <el-form-item label="菜单名" :label-width="formLabelWidth">
          <el-input v-model="list.name" auto-complete="off" />
        </el-form-item>
        <el-form-item label="所属权限" :label-width="formLabelWidth">
          <el-select v-model="list.tokenId" placeholder="请选择权限">
            <el-option
              v-for="token in tokens"
              :key="token.id"
              :label="token.introduction"
              :value="token.id"
              placeholder="请选择权限"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" :loading="addIndexLoading" @click="addMenu()">添 加</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import qs from 'qs'
import { add, getTokens } from '@/api/menu/menu'

export default {
  name: 'AddIndex',
  data() {
    return {
      list: {
        menuName: '',
        srvName: '',
        name: '',
        tokenId: ''
      },
      tokens: [],
      addIndexLoading: false,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      rules: {
      }
    }
  },
  mounted() {
  },
  methods: {
    clear() {
      this.list = {
        menuName: '',
        srvName: '',
        name: '',
        tokenId: ''
      }
    },
    addMenu() {
      console.log(this.list)
      add(qs.stringify({
        menuName: this.list.menuName,
        srvName: this.list.srvName,
        name: this.list.name,
        tokenId: this.list.tokenId
      })).then(resp => {
        // init new battery code
        this.dialogFormVisible = false
        this.$emit('onSubmit')
        console.log(resp.data)
      })
    },
    getTokensMethod() {
      getTokens().then(resp => {
        console.log('tokens', resp)
        this.tokens = resp.data
      })
    }
  }
}
</script>

<style scoped>

</style>
