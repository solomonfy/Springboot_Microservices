package com.solomon.department.service;

import com.solomon.department.entity.Department;
import com.solomon.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        log.info("Inside getAllDepartments method in DepartmentService");
        return departmentRepository.findAll();
    }

    ;

    public Object getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Object getDepartmentsByName(String name) {
        log.info("Inside getDepartmentsByName method in DepartmentService!");
        System.out.println("Printing after log!");
        List<Department> allDepartments = getAllDepartments();
        List<Department> foundDepartments = new ArrayList<Department>();
        for (Department d : allDepartments) {
            if (d.getDepartmentName().toLowerCase().equals(name.toLowerCase())) {
                foundDepartments.add(d);
            }
        }
        return foundDepartments;
    }

    public List<Department> saveAllDepartments(List<Department> departments) {

        departments.forEach((department) -> {
            String departmentName = department.getDepartmentName();
            if (departmentName != null) {
                department.setDepartmentCode(departmentName.substring(0, 3).toUpperCase(Locale.ROOT) + "-001");
            }
        });
        return departmentRepository.saveAll(departments);
    }
}
