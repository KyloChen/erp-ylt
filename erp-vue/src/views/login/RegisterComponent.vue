<template>
  <div>
    <el-dialog title="新增工厂" :visible.sync="registerShower" @close="clear">
      <el-form :model="registerUserForm">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="registerUserForm.username" auto-complete="off" />
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="registerUserForm.password" auto-complete="off" />
        </el-form-item>
        <el-form-item label="联系方式" :label-width="formLabelWidth">
          <el-input v-model="registerUserForm.phoneNumber" auto-complete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="registerShower = false">取 消</el-button>
        <el-button type="primary" :loading="addIndexLoading" @click="registerMethod()">添 加</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { add } from '@/api/user/user'
export default {
  name: 'RegisterComponent',
  data() {
    return {
      registerUserForm: {
        username: '',
        password: '',
        phoneNumber: ''
      },
      addIndexLoading: false,
      formLabelWidth: '120px',
      registerShower: false
    }
  },
  methods: {
    clear() {
      this.registerUserForm = {
        username: '',
        password: '',
        phoneNumber: ''
      }
      this.registerShower = false
    },
    registerMethod() {
      console.log('register')
      add(this.registerUserForm).then(resp => {
        if (resp.code === 20000) {
          this.registerShower = false
          this.$message({
            type: 'success',
            message: resp.data
          })
        } else if (resp.code === 20001) {
          this.$message({
            type: 'error',
            message: resp.data
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
