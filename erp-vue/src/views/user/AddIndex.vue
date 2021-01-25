<template>
  <div>
    <el-dialog title="新增用户" :visible.sync="dialogFormVisible" @close="clear">
      <el-form ref="list" :model="list" :rules="rules">
        <el-form-item label="用户名称" :label-width="formLabelWidth">
          <el-input v-model="list.username" auto-complete="off" />
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="list.password" auto-complete="off" />
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="list.fullName" auto-complete="off" />
        </el-form-item>
        <el-form-item label="联系方式" :label-width="formLabelWidth">
          <el-input v-model="list.phoneNumber" auto-complete="off" />
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
        <el-button type="primary" :loading="addIndexLoading" @click="addUser()">添 加</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { add } from '@/api/user/user'
import { getTokens } from '@/api/menu/menu'

export default {
  name: 'AddIndex',
  data() {
    return {
      list: {
        username: '',
        password: '',
        fullName: '',
        phoneNumber: '',
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
        username: '',
        password: '',
        fullName: '',
        phoneNumber: '',
        tokenId: ''
      }
    },
    addUser() {
      console.log(this.list)
      add(this.list).then(resp => {
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
