package com.osifojohncode.Springboot.crud.repository;

import com.osifojohncode.Springboot.crud.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department1;
    private Department department2;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void init() {
        ///Arrange
        department1 = new Department();
        department1.setDepartmentName("Geomatics Engr");
        department1.setDepartmentCode("GEM - 011");
        department1.setDepartmentAddress("Benin");

        department2 = new Department();
        department2.setDepartmentName("Civil Engr");
        department2.setDepartmentCode("CVE - 022");
        department2.setDepartmentAddress("Ugbowo");
    }

//    @BeforeEach
//    void setUp() {
//        Department department =
//                Department.builder()
//                        .departmentName("Geomatics")
//                        .departmentCode("GEM - 011")
//                        .departmentAddress("Benin")
//                        .build();
//        testEntityManager.persist(department);
//    }
    @Test
    @DisplayName("It should save the department to the database")
    public void saveDepartmentToDatabase(){
        /// Act
        Department newDepartment = departmentRepository.save(department1);
        //Assert
        assertNotNull(newDepartment);
        assertEquals(department1.getDepartmentName(),"Geomatics Engr");
    }

    @Test
    @DisplayName("It should return the department list with size of 2")
    void fetchDepartmentListFromDatabase() {
        departmentRepository.save(department1);
        departmentRepository.save(department2);

        List<Department> list = departmentRepository.findAll();

        assertNotNull(list);
        assertThat(list).isNotNull();
        assertEquals(2, list.size());
    }
    @Test
    @DisplayName("It should return the department by its departmentId")
    public void whenFindById_thenReturnDepartment(){
        departmentRepository.save(department1);
        departmentRepository.save(department2);
        Department department = departmentRepository.findById(department1.getDepartmentId()).get();
        assertEquals(department.getDepartmentName(),"Geomatics Engr");
    }

    @Test
    @DisplayName("It should return the department  with department name")
    void  whenFindDepartmentByName_thenReturnDepartment() {
        departmentRepository.save(department1);
        departmentRepository.save(department2);

       Department foundDepartment = departmentRepository.findByDepartmentNameIgnoreCase("geomatics EnGr");
        assertNotNull(foundDepartment);
    }

    @Test
    @DisplayName("It should update the department with Surveying")
    void updateDepartmentByDepartmentName() {

        departmentRepository.save(department1);

        Department existingDepartment = departmentRepository.findById(department1.getDepartmentId()).get();
        existingDepartment.setDepartmentName("Surveying");
        Department updatedDepartment = departmentRepository.save(existingDepartment);

        assertEquals("Surveying", updatedDepartment.getDepartmentName());
    }

    @Test
    @DisplayName("It should delete the existing department by departmentId")
    void deleteDepartmentById() {
        departmentRepository.save(department1);
        Long departmentId = department1.getDepartmentId();

        departmentRepository.save(department1);
        departmentRepository.save(department2);

        departmentRepository.deleteById(departmentId);

        List<Department> list = departmentRepository.findAll();

        Optional<Department> exitingDepartment = departmentRepository.findById(departmentId);

        assertEquals(1, list.size());
        assertThat(exitingDepartment).isEmpty();
    }

}