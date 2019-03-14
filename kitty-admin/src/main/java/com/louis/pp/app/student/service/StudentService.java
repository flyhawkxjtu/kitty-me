package com.louis.pp.app.student.service;

import com.louis.platform.common.page.PageRequest;
import com.louis.platform.common.page.PageResult;
import com.louis.pp.app.student.model.StudentHoliday;

import java.util.List;
import java.util.Map;

public interface StudentService {


    public StudentHoliday getStudentHolidayProcess(Long id);
    public PageResult queryHoliday(PageRequest pageRequest);
    public int startHolidayProcess(Long id);
}
