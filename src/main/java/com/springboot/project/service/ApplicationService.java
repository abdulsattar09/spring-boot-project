package com.springboot.project.service;

import com.springboot.project.entity.Address;
import com.springboot.project.entity.Employee;

import java.util.List;

public interface ApplicationService {

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Employee getEmployeeByEmployeeId(String employeeId);

    List<Employee> getAllEmployees(int page, int size);

    void deleteEmployee(String employeeId);

    void addAddressToEmployee(String employeeId, Address address);

    String callExternalApi();
}