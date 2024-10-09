package com.springboot.project.controller;

import com.springboot.project.entity.Address;
import com.springboot.project.entity.Employee;
import com.springboot.project.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    /*    Create Employee */
    @PostMapping(value = "/create-employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return applicationService.createEmployee(employee);
    }

    /*    Update Employee details */
    @PutMapping(value = "/update-employee")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return applicationService.updateEmployee(employee);
    }

    /*    Get Employee details based on employeeId */
    @GetMapping(value = "/get-employee-by-employeeId")
    public Employee getEmployeeByEmployeeId(@RequestParam String employeeId) {
        return applicationService.getEmployeeByEmployeeId(employeeId);
    }

    /*    Get Employee details based on pagination - need page no & size input parameters*/
    @GetMapping(value = "/get-all-employees")
    public List<Employee> getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return applicationService.getAllEmployees(page, size);
    }

    /*    Delete Employee based on employeeId */
    @DeleteMapping(value = "/delete-employee" )
    public String deleteEmployee(@RequestParam String employeeId) {
        applicationService.deleteEmployee(employeeId);
        return "Employee with ID: " + employeeId + " deleted successfully.";
    }

    /*    Add Address to Employee */
    @PostMapping(value = "/add-address")
    public Address addAddressToEmployee(@RequestParam String employeeId, @RequestBody Address address) {
        applicationService.addAddressToEmployee(employeeId, address);
        return address;
    }

    /*    Call External API */
    @GetMapping(value = "/call-external-api")
    public String callExternalApi() {
        return applicationService.callExternalApi();
    }
}