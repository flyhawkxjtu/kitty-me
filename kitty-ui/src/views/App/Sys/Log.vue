<template>
  <div class="page-container">
    <!--工具栏-->
    <div class="search_div">
      <el-row type="flex" align="middle" style="margin-top: 10px;height: 50px">
        <el-col :span="2" style="text-align: center">
          <span>账号：</span>
        </el-col>
        <el-col :span="3">
          <el-input v-model="filters.creator" size="mini"></el-input>
        </el-col>
        <el-col :span="4">
          <kt-button icon="fa fa-search" :label="$t('action.search')" perms="sys:log:view" type="danger" @click="findPage(null)"/>
          <el-button type="warning" icon="el-icon-delete" size="mini" @click="clearFilter">清空</el-button>
        </el-col>
      </el-row>
    </div>
	<!--表格内容栏-->

    <div class="table_div">
      <kt-table
        :data="pageResult" :columns="columns" :showOperation="showOperation" @findPage="findPage">
      </kt-table>
    </div>
  </div>
</template>

<script>
import KtTable from "@/views/Core/KtTable"
import KtButton from "@/views/Core/KtButton"
import { format } from "@/utils/datetime"
export default {
	components: {
		KtTable,
		KtButton
	},
	data() {
		return {
			size: 'small',
			filters: {
				creator: ''
			},
			columns: [
        {prop:"createTime", label:"操作时间", minWidth:120, formatter:this.dateFormat},
				{prop:"creator", label:"操作人", minWidth:100},
				{prop:"method", label:"方法", minWidth:180},
				{prop:"params", label:"参数", minWidth:220},
				{prop:"ip", label:"IP", minWidth:120},
				{prop:"time", label:"耗时", minWidth:80},
			],
			pageRequest: { pageNum: 1, pageSize: 10 },
      pageResult: {},
      showOperation:false
		}
	},
	methods: {
    clearFilter:function () {
      this.filters.name=''
    },
		// 获取分页数据
		findPage: function (data) {
			if(data !== null) {
				this.pageRequest = data.pageRequest
			}
			this.pageRequest.columnFilters = {creator: {name:'creator', value:this.filters.creator}}
			this.$api.log.findPage(this.pageRequest).then((res) => {
				this.pageResult = res.data
			}).then(data!=null?data.callback:'')
		},
		// 时间格式化
      	dateFormat: function (row, column, cellValue, index){
          	return format(row[column.property])
      	}
	},
	mounted() {
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
