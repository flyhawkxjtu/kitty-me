package com.louis.platform.app.controller;


import com.louis.platform.app.sevice.WorkflowService;
import com.louis.platform.common.http.HttpResult;
import com.louis.platform.common.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@RestController
@RequestMapping("workflow")
public class WorkflowController {
    @Autowired
    private WorkflowService workflowService;

    /**
     * 查询workflow的待办事项
     * @param pageRequest
     * @return
     */
    @PostMapping(value="/queryTodo")
    public HttpResult queryTodo(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(workflowService.queryTodo(pageRequest));
    }

    /**
     * 查询流程处理信息-日志
     * @param pageRequest
     * @return
     */
    @PostMapping(value="/queryWorkflowTrace/log")
    public HttpResult queryWorkflowTraceLog(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(workflowService.queryWorkflowTraceLog(pageRequest));
    }

    /**
     * 获取流程定义文件(image或者xml)
     *
     * @param resourceType      资源类型(xml|image)
     * @param processInstanceId 流程实例ID
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/getProcessDefination")
    public void getProcessDefination(@RequestParam String resourceType, @RequestParam String processInstanceId, HttpServletResponse response)
            throws Exception {
        InputStream resourceAsStream= workflowService.getProcessDefination(resourceType,processInstanceId);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }

    /**
     * 查询流程处理信息-各个节点在图片上的展示位置(x,y,width,height,是否为当前节点)
     * @param processInstanceId 流程实例id
     * @return
     */
    @GetMapping(value="/queryWorkflowTrace/image")
    public HttpResult queryWorkflowTraceImage(@RequestParam String processInstanceId) {
        return HttpResult.ok(workflowService.queryWorkflowTraceImage(processInstanceId));
    }
}
