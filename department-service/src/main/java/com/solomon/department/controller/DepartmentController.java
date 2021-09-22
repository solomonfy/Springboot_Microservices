package com.solomon.department.controller;

import com.solomon.department.entity.Department;
import com.solomon.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartments() {
        log.info("Inside getAllDepartments method in DepartmentController");
        return new ResponseEntity(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("Inside getDepartmentById method in DepartmentController");
        return new ResponseEntity(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
    }

    @GetMapping("/name/{string}")
    public ResponseEntity<Department> getDepartmentsByName(@PathVariable("string") String name) {
        return new ResponseEntity(departmentService.getDepartmentsByName(name), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        return new ResponseEntity(departmentService.addDepartment(department), HttpStatus.CREATED);
    }

    @PostMapping("/saveall")
    public ResponseEntity<List<Department>> saveAllDepartments(@RequestBody List<Department> departments) {
        return new ResponseEntity(departmentService.saveAllDepartments(departments), HttpStatus.CREATED);
    }

}
