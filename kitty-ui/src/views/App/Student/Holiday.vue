<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="search_div">
      <el-row type="flex" align="middle" style="margin-top: 10px;height: 50px">
        <el-col :span="2" style="text-align: center">
          <span>标题：</span>
        </el-col>
        <el-col :span="3">
          <el-input v-model="filters.holidayTitle" size="mini"></el-input>
        </el-col>
        <el-col :span="2" style="text-align: center">
          <span>学生姓名：</span>
        </el-col>
        <el-col :span="3">
          <el-input v-model="filters.studentName" size="mini"></el-input>
        </el-col>
        <el-col :span="4">
          <kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:user:view" type="danger" @click="findPage(null)"/>
          <el-button type="warning" icon="el-icon-delete" size="mini" @click="clearFilter">清空</el-button>
        </el-col>
      </el-row>
    </div>
    <!--表格内容栏-->
    <div class="table_div">
      <el-row type="flex" align="middle" style="height: 50px">
        <el-col :span="6" style="text-align: left;margin-left: 20px">
          <kt-button icon="fa fa-plus" :label="$t('action.add')" perms="sys:user:add" type="danger" @click="handleAdd" />
          <kt-button icon="fa fa-delete" :label="$t('action.batchDelete')" perms="sys:user:delete"  @click="handleBatchDelete" />
          <kt-button icon="fa fa-plus" :label="$t('action.startProcess')" perms="student:holiday:view"  @click="handleStartProcess" />
          <el-button icon="fa fa-refresh" @click="findPage(null)"  size="mini">刷新</el-button>
        </el-col>
      </el-row>
      <kt-table permsEdit="sys:user:edit" permsDelete="sys:user:delete"
                :data="pageResult" :columns="filterColumns"
                @findPage="findPage" @handleEdit="handleEdit" @handleDelete="handleDelete" @selectionChange="selectionChange">
      </kt-table>
    </div>

    <!--新增编辑界面-->
    <el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size"
               label-position="right" style="text-align:left;">
        <el-form-item label="ID" prop="id" v-if="false">
          <el-input v-model="dataForm.id" :disabled="true" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="account">
          <el-input v-model="dataForm.account" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="dataForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="dataForm.password" type="password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="机构" prop="deptName">
          <popup-tree-input
            :data="deptData"
            :props="deptTreeProps"
            :prop="dataForm.deptName"
            :nodeKey="''+dataForm.deptId"
            :currentChangeHandle="deptTreeCurrentChangeHandle">
          </popup-tree-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="dataForm.email" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="mobile">
          <el-input v-model="dataForm.mobile" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色" prop="userRoles" v-if="!operation">
          <el-select v-model="dataForm.userRoles" multiple placeholder="请选择"
                     style="width: 100%;">
            <el-option v-for="item in roles" :key="item.id"
                       :label="item.name" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item  label="序号" prop="sort">
          <el-input-number v-model="dataForm.sort"  :min="0"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :size="size" @click.native="dialogVisible = false">{{$t('action.cancel')}}</el-button>
        <el-button :size="size" type="danger" @click.native="submitForm" :loading="editLoading">{{$t('action.submit')}}</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import PopupTreeInput from "@/components/PopupTreeInput"
  import KtTable from "@/views/Core/KtTable"
  import KtButton from "@/views/Core/KtButton"
  import TableColumnFilterDialog from "@/views/Core/TableColumnFilterDialog"
  import { format } from "@/utils/datetime"

  export default {
    name: 'holiday',
    components:{
      PopupTreeInput,
      KtTable,
      KtButton,
      TableColumnFilterDialog,
    },
    data() {
      return {
        size: 'small',
        selections: [],
        filters: {
          holidayTitle: '',
          studentName: ''
        },
        columns: [],
        filterColumns: [],
        pageRequest: { pageNum: 1, pageSize: 10 },
        pageResult: {},

        operation: false, // true:新增, false:编辑
        dialogVisible: false, // 新增编辑界面是否显示
        editLoading: false,
        dataFormRules: {
          account: [
            { required: true, message: '请输入账号', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '请输入姓名', trigger: 'blur' }
          ]
        },
        // 新增编辑界面数据
        dataForm: {
          id: 0,
          account: '',
          name: '',
          password: '123456',
          deptId: 1,
          deptName: '',
          email: 'test@qq.com',
          mobile: '13889700023',
          status: 1,
          userRoles: []
        },
        deptData: [],
        deptTreeProps: {
          label: 'name',
          children: 'children'
        },
        roles: [],
        ids:[]
      }
    },
    methods: {
      clearFilter:function () {
        this.filters.name=''
      },
      // 获取分页数据
      selectionChange(selections){
        let idstr = selections.selections.map(item => item.id).toString()
        this.ids = (idstr+'').split(',')
        console.log(this.ids)
      },
      findPage: function (data) {
        if(data !== null) {
          this.pageRequest = data.pageRequest
        }
        this.pageRequest.columnFilters = {holidayTitle: {name:'holidayTitle', value:''},studentName: {name:'studentName', value: ''}}
        this.$api.student.holiday_query(this.pageRequest).then((res) => {
          this.pageResult = res.data
        }).then(data!=null?data.callback:'')
      },
      // 启动流程
      handleStartProcess: function () {
        let params = [];
        params.push({'id':this.ids[0]})
        this.$api.student.holiday_startProcess(params).then(data!=null?data.callback:'')
      },
      // 时间格式化
      dateFormat: function (row, column, cellValue, index){
        return format(row[column.property])
      },
      // 处理表格列过滤显示
      displayFilterColumnsDialog: function () {
        this.$refs.tableColumnFilterDialog.setDialogVisible(true)
      },
      // 处理表格列过滤显示
      handleFilterColumns: function (data) {
        this.filterColumns = data.filterColumns
        this.$refs.tableColumnFilterDialog.setDialogVisible(false)
      },

      // 处理表格列过滤显示
      initColumns: function () {
        this.columns = [
          {prop:"holidayTitle", label:"标题", minWidth:120},
          {prop:"studentName", label:"学生姓名", minWidth:120},
          {prop:"holidayStart", label:"起始日期", minWidth:120},
          {prop:"holidayEnd", label:"结束日期", minWidth:100},
          {prop:"createTime", label:"创建时间", minWidth:100},
          {prop:"updateTime", label:"更新时间", minWidth:70},
        ]
        this.filterColumns = JSON.parse(JSON.stringify(this.columns));
      }
    },
    mounted() {
      this.initColumns()
    }
  }
</script>

<style scoped>
  .page-container{
    width: 98%;
    height: 780px;
    margin: 10px auto;
  }
  .search_div{
    background-color: white;
  }
  .table_div{
    margin-top: 10px;
    background-color: white;
  }
</style>
<style>
</style>
