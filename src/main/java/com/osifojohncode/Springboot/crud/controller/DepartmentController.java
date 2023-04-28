package com.osifojohncode.Springboot.crud.controller;

import com.osifojohncode.Springboot.crud.entity.Department;
import com.osifojohncode.Springboot.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping("/departments")
    public Department saveDepartment(@RequestBody Department department){
      return departmentService.saveDepartment(department);
    }
}
