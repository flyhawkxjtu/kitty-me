import axios from '../axios'

/*
 * 工作流外围模块
 */

// 查询待办
export const queryTodo = (data) => {
  return axios({
    url: '/workflow/queryTodo',
    method: 'post',
    data
  })
}
// 查询流程处理日志
export const queryWorkflowTraceLog = (data) => {
  return axios({
    url: '/workflow/queryWorkflowTrace/log',
    method: 'post',
    data
  })
}
// 查询流程处定义文件
export const getProcessDefination = (data) => {
  return axios({
    url: '/workflow/getProcessDefination?resourceType=image&processInstanceId='+data,
    method: 'get',
    headers: {
      Accept: 'image/webp,image/apng,image/*,*/*;q=0.8'
    }
  })
}
// 查询流程处理-图片,各节点位置
export const queryWorkflowTraceImage = (data) => {
  return axios({
    url: '/workflow/queryWorkflowTrace/image?processInstanceId='+data,
    method: 'get'
  })
}


