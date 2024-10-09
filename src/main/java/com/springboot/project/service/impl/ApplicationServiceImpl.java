package com.springboot.project.service.impl;

import com.springboot.project.entity.Address;
import com.springboot.project.entity.Employee;
import com.springboot.project.repository.AddressRepository;
import com.springboot.project.repository.EmployeeRepository;
import com.springboot.project.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Employee createEmployee(Employee employee) {

        if(employee.getEmployeeId() == null) {
            employee.setEmployeeId("EMP-" + UUID.randomUUID().toString().substring(0,8).toUpperCase());
            return employeeRepository.save(employee);
        } else {
            return employeeRepository.save(employee);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeByEmployeeId(String employeeId) {
        return employeeRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Employee> getAllEmployees(int page, int size) {
        return employeeRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public void deleteEmployee(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if(employee!= null) {
            employeeRepository.delete(employee);
        }
    }

    @Override
    public void addAddressToEmployee(String employeeId, Address address) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if(employee!= null) {
            address.setEmployeeId(employee);
            addressRepository.save(address);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with ID: " + employeeId);
        }
    }

    @Override
    public String callExternalApi() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://www.google.com", String.class);
    }
}