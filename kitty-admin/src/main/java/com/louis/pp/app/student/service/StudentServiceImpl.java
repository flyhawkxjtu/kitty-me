package com.louis.pp.app.student.service;

import com.louis.platform.common.constants.PlatformConstant;
import com.louis.platform.common.page.MybatisPageHelper;
import com.louis.platform.common.page.PageRequest;
import com.louis.platform.common.page.PageResult;
import com.louis.platform.common.security.SecurityUtils;
import com.louis.platform.app.sevice.WorkflowService;
import com.louis.pp.app.student.dao.StudentDAO;
import com.louis.pp.app.student.model.StudentHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/*

 */

@Service
public class StudentServiceImpl  implements  StudentService{
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private WorkflowService workflowService;


    @Override
    public StudentHoliday getStudentHolidayProcess(Long id) {
        return studentDAO.getStudentHoliday(id);
    }

    @Override
    public PageResult queryHoliday(PageRequest pageRequest) {
        PageResult pageResult =  new PageResult();;
        pageResult = MybatisPageHelper.findPage(pageRequest, studentDAO,"queryHolidayByCondition",pageRequest.getCondition());
        return pageResult;
    }

    @Override
    public int startHolidayProcess(Long id) {
        StudentHoliday studentHoliday;
        //查询业务数据
        studentHoliday=studentDAO.getStudentHoliday(id);
        if(studentHoliday!=null){
            //补充参数
            Map<String, Object> variables = new HashMap<String, Object>();
            String account = SecurityUtils.getUsername();
            variables.put(PlatformConstant.WORKFLOW_INSTANCE_CREATOR_KEY, account);
            //TODO 要在变量中查询前一个处理人，这里的写法不知道对不对！
            variables.put(PlatformConstant.WORKFLOW_INSTANCE_PREVIOUS_USERNAME, account);
            variables.put(PlatformConstant.WORKFLOW_INSTANCE_TITLE_KEY, studentHoliday.getHolidayTitle());//表单标题,用于存储首页待办标题
            workflowService.startProcessAndCompleteSubmitActivity(PlatformConstant.WORKFLOW_DEFINATIONKEY_HOLIDAY,id.toString(),variables);
            return 1;
        }else{
            return 0;
        }

    }
}
