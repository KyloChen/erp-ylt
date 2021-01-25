<template>
  <el-container>
    <el-header>
      <el-button v-show="serverShown" type="primary" icon="el-icon-circle-plus-outline" @click="addUser">新增用户</el-button>
      <el-button type="primary" icon="el-icon-refresh" @click="fetchData">刷新</el-button>
      <el-button type="primary" icon="el-icon-search">搜索</el-button>
    </el-header>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="用户名" width="250" align="center">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column label="姓名" width="250" align="center">
        <template slot-scope="scope">
          {{ scope.row.fullName }}
        </template>
      </el-table-column>
      <el-table-column label="联系方式" width="250" align="center">
        <template slot-scope="scope">
          {{ scope.row.phoneNumber }}
        </template>
      </el-table-column>
      <el-table-column label="所属权限" width="250" align="center">
        <template slot-scope="scope">
          {{ scope.row.tokenId }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.createdDate | formatDate }}
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100"
      >
        <template slot-scope="scope">
          <el-button v-show="serverShown" type="text" size="small" @click="handleClick(scope.row)">删除</el-button>
          <el-button v-show="serverShown" type="text" size="small" @click="handleEdit(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <add-index ref="addIndex" @onSubmit="fetchData" />
    <!--    <edit-index ref="editIndex" @onSubmit="fetchData" />-->
    <el-footer>
      <div class="page">
        <el-pagination
          :current-page="paginations.page_index"
          :page-sizes="paginations.page_sizes"
          :page-size="paginations.page_size"
          :layout="paginations.layout"
          :total="paginations.total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-footer>
  </el-container>
</template>

<script>
import qs from 'qs'
import { getList, deleteUser } from '@/api/user/user'
import { formatDate } from '../../../dataFormat'
import AddIndex from '@/views/user/AddIndex'
// import EditIndex from '@/views/user/EditIndex'

export default {
  name: 'Index',
  filters: {
    formatDate(time) {
      const date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd hh:mm:ss')
    }
  },
  components: {
    // EditIndex,
    AddIndex
  },
  data() {
    return {
      list: null,
      listLoading: true,
      serverShown: true,
      paginations: {
        page_index: 1,
        total: 0,
        page_size: 10,
        page_sizes: [5, 10],
        layout: 'total, sizes, prev, pager, next, jumper'
      }
    }
  },
  created() {
    this.fetchData()
    if (process.env.NODE_ENV === 'production') {
      this.serverShown = false
    }
  },
  methods: {
    addUser() {
      this.$refs.addIndex.dialogFormVisible = true
      this.$refs.addIndex.getTokensMethod()
    },
    handleClick(row) {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUser(qs.stringify({
          id: row.id
        })).then(resp => {
          this.fetchData()
          this.$message({
            type: 'success',
            message: resp.data
          })
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    handleEdit(row) {
      console.log('row: ', row)
      this.$refs.editIndex.editDialogVisible = true
      this.$refs.editIndex.list = row
    },
    handleSizeChange(pageSize) {
      this.paginations.page_index = 1 // 第一页
      this.paginations.page_size = pageSize // 每页先显示多少数据
      this.list = this.allTableData.filter((item, index) => {
        return index < pageSize
      })
    },
    handleCurrentChange(page) {
      // 跳转页数
      const index = this.paginations.page_size * (page - 1)
      // 获取总数
      const allData = this.paginations.page_size * page

      const tablist = []
      for (let i = index; i < allData; i++) {
        if (this.allTableData[i]) {
          tablist.push(this.allTableData[i])
        }
        this.list = tablist
      }
    },
    setPaginations() {
      this.paginations.total = this.allTableData.length // 数据的数量
      this.paginations.page_index = 1 // 默认显示第一页
      this.paginations.page_size = 10 // 每页显示多少数据

      // 显示数据
      this.list = this.allTableData.filter((item, index) => {
        return index < this.paginations.page_size
      })
    },
    fetchData() {
      this.listLoading = true
      getList().then(response => {
        console.log('fetching data ... ')
        console.log(response.data)
        this.allTableData = response.data
        this.setPaginations()
        this.listLoading = false
      })
    }
  }
}
</script>

<style scoped>
</style>

<style>
  .el-container{
    padding: 20px;
    height: 800px;
  }
</style>
