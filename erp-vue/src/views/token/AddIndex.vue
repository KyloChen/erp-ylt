<template>
  <div>
    <el-dialog title="新增权限" :visible.sync="dialogFormVisible" @close="clear">
      <el-form ref="list" :model="list" :rules="rules">
        <el-form-item label="权限名称" :label-width="formLabelWidth">
          <el-input v-model="list.tokenName" auto-complete="off" />
        </el-form-item>
        <el-form-item label="权限介绍" :label-width="formLabelWidth">
          <el-input v-model="list.introduction" auto-complete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" :loading="addIndexLoading" @click="addToken()">添 加</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import qs from 'qs'
import { add } from '@/api/token/token'

export default {
  name: 'AddIndex',
  data() {
    return {
      list: {
        tokenName: '',
        introduction: ''
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
        tokenName: '',
        introduction: ''
      }
    },
    addToken() {
      console.log(this.list)
      add(qs.stringify({
        tokenName: this.list.tokenName,
        introduction: this.list.introduction
      })).then(resp => {
        this.dialogFormVisible = false
        this.$emit('onSubmit')
        console.log(resp.data)
      })
    }
  }
}
</script>

<style scoped>

</style>
