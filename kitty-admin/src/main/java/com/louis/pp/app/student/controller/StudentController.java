package com.louis.pp.app.student.controller;


import com.louis.platform.common.http.HttpResult;
import com.louis.platform.common.page.PageRequest;
import com.louis.pp.app.student.model.StudentHoliday;
import com.louis.pp.app.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PreAuthorize("hasAuthority('student:holiday:view')")
    @PostMapping(value="/holiday/query")
    public HttpResult queryHoliday(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(studentService.queryHoliday(pageRequest));
    }

    @PostMapping(value="/holiday/startProcess")
    public HttpResult startHolidayProcess(@RequestBody List<StudentHoliday> studentHoliday) {

        return HttpResult.ok(studentService.startHolidayProcess(studentHoliday.get(0).getId()));
    }
}
