package com.louis.pp.app.student.dao;

import com.louis.platform.app.model.SysUser;
import com.louis.pp.app.student.model.StudentHoliday;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDAO {
    StudentHoliday getStudentHoliday(Long id);
    List<StudentHoliday> queryHolidayByCondition(@Param("condition") Map<String, Object> condition);
}
