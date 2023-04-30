package com.osifojohncode.Springboot.crud.service;

import com.osifojohncode.Springboot.crud.entity.Department;
import com.osifojohncode.Springboot.crud.error.DepartmentNotFoundException;
import com.osifojohncode.Springboot.crud.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    private Department department1;
    private Department department2;

//    @BeforeEach
//    void setUp() {
//        Department department =
//                 Department.builder()
//                         .departmentName("IT")
//                         .departmentAddress("UNIBEN")
//                         .departmentCode("01-06")
//                         .departmentId(1L)
//                         .build();
//        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
//                .thenReturn(department);
//    }
@BeforeEach
void init() {
    ///Arrange
    department1 = new Department();
    department1.setDepartmentName("Geomatics Engr");
    department1.setDepartmentId(1L);
    department1.setDepartmentCode("GEM - 011");
    department1.setDepartmentAddress("Benin");

    department2 = new Department();
    department2.setDepartmentName("Civil Engr");
    department2.setDepartmentId(2L);
    department2.setDepartmentCode("CVE - 022");
    department2.setDepartmentAddress("Ugbowo");
}
    @Test
    @DisplayName("should save department object to  database")
    void saveDepartmentToDatabase(){
        when(departmentRepository.save(any(Department.class))).thenReturn(department1);

        Department newDepartment = departmentServiceImpl.saveDepartment(department1);

        assertNotNull(newDepartment);
        assertThat(newDepartment.getDepartmentName()).isEqualTo("Geomatics Engr");
    }

    @Test
    void fetchDepartmentList() {

        List<Department> list = new ArrayList<>();
        list.add(department1);
        list.add(department2);

        when(departmentRepository.findAll()).thenReturn(list);

        List<Department> departments = departmentServiceImpl.fetchDepartmentList();

        assertEquals(2, departments.size());
        assertNotNull(departments);
    }

    @Test
    void fetchDepartmentById() throws DepartmentNotFoundException {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department1));
        Department existingDepartment = departmentServiceImpl.fetchDepartmentById(department1.getDepartmentId());
        assertNotNull(existingDepartment);
        assertThat(existingDepartment.getDepartmentId()).isNotEqualTo(null);
    }

    @Test
    void updateDepartment() {

        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department2));

        when(departmentRepository.save(any(Department.class))).thenReturn(department2);
        department2.setDepartmentName("EEE - Engr");
        Department exisitingDepartment = departmentServiceImpl.updateDepartment(department2.getDepartmentId(), department2);

        assertNotNull(exisitingDepartment);
        assertEquals("EEE - Engr", department2.getDepartmentName());
    }

}