import axios from '../axios'

/*
 * 学生管理模块
 */

//----------------------------------------------------假期管理-----------------------
// 启动流程
export const holiday_startProcess = (data) => {
  return axios({
    url: '/student/holiday/startProcess',
    method: 'post',
    data
  })
}

// 分页查询
export const holiday_query = (data) => {
  return axios({
    url: '/student/holiday/query',
    method: 'post',
    data
  })
}


//----------------------------------------------------xx管理-----------------------

