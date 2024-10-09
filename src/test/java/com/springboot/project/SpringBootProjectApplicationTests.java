package com.springboot.project;

import com.springboot.project.controller.ApplicationController;
import com.springboot.project.entity.Address;
import com.springboot.project.entity.Employee;
import com.springboot.project.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

import java.util.List;

@SpringBootTest
class SpringBootProjectApplicationTests {

	@InjectMocks
	private ApplicationController applicationController;

	@Mock
	private ApplicationService applicationService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createEmployee_ShouldReturnEmployee() {
		Employee employee = new Employee();
		when(applicationService.createEmployee(any(Employee.class))).thenReturn(employee);

		Employee createdEmployee = applicationController.createEmployee(employee);

		assertEquals(employee, createdEmployee);
		verify(applicationService).createEmployee(employee);
	}

	@Test
	void updateEmployee_ShouldReturnUpdatedEmployee() {
		Employee employee = new Employee();
		when(applicationService.updateEmployee(any(Employee.class))).thenReturn(employee);

		Employee updatedEmployee = applicationController.updateEmployee(employee);

		assertEquals(employee, updatedEmployee);
		verify(applicationService).updateEmployee(employee);
	}

	@Test
	void getEmployeeByEmployeeId_ShouldReturnEmployee() {
		String employeeId = "123";
		Employee employee = new Employee();
		when(applicationService.getEmployeeByEmployeeId(employeeId)).thenReturn(employee);

		Employee retrievedEmployee = applicationController.getEmployeeByEmployeeId(employeeId);

		assertEquals(employee, retrievedEmployee);
		verify(applicationService).getEmployeeByEmployeeId(employeeId);
	}

	@Test
	void getAllEmployees_ShouldReturnListOfEmployees() {
		List<Employee> employees = Arrays.asList(new Employee(), new Employee());
		when(applicationService.getAllEmployees(0, 10)).thenReturn(employees);

		List<Employee> retrievedEmployees = applicationController.getAllEmployees(0, 10);

		assertEquals(employees.size(), retrievedEmployees.size());
		verify(applicationService).getAllEmployees(0, 10);
	}

	@Test
	void deleteEmployee_ShouldReturnSuccessMessage() {
		String employeeId = "123";

		String message = applicationController.deleteEmployee(employeeId);

		assertEquals("Employee with ID: " + employeeId + " deleted successfully.", message);
		verify(applicationService).deleteEmployee(employeeId);
	}

	@Test
	void addAddressToEmployee_ShouldInvokeService() {
		String employeeId = "123";
		Address address = new Address();

		// Call the method
		applicationController.addAddressToEmployee(employeeId, address);

		// Verify that the service method was called with the correct parameters
		verify(applicationService).addAddressToEmployee(employeeId, address);
	}


	@Test
	void callExternalApi_ShouldReturnResponse() {
		String expectedResponse = "API Response";
		when(applicationService.callExternalApi()).thenReturn(expectedResponse);

		String response = applicationController.callExternalApi();

		assertEquals(expectedResponse, response);
		verify(applicationService).callExternalApi();
	}
}
