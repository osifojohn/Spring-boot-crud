package com.osifojohncode.Springboot.crud.service;

import com.osifojohncode.Springboot.crud.entity.Department;
import com.osifojohncode.Springboot.crud.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
