import axios from '../axios'

/*
 * 机构管理模块
 */

// 保存
export const save = (data) => {
  return axios({
    url: '/dict/save',
    method: 'post',
    data
  })
}
// 删除
export const batchDelete = (data) => {
  return axios({
    url: '/dict/delete',
    method: 'post',
    data
  })
}
// 查询机构树
export const findTree = () => {
  return axios({
    url: '/dict/findTree',
    method: 'get'
  })
}
