package com.osifojohncode.Springboot.crud.controller;

import com.osifojohncode.Springboot.crud.entity.Department;
import com.osifojohncode.Springboot.crud.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department =
                Department.builder()
                        .departmentName("Geomatics")
                        .departmentCode("GEM - 011")
                        .departmentAddress("Benin")
                        .departmentId(1L)
                        .build();
    }

    @Test
    void saveDepartment() {
        Department inputDepartment =
                Department.builder()
                        .departmentName("Geomatics")
                        .departmentCode("GEM - 011")
                        .departmentAddress("Benin")
                        .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);


    }

    @Test
    void fetchDepartmentById() {
    }
}