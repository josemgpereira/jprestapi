package com.jp.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.api.dto.EmployeeDto;
import com.jp.api.models.Employee;
import com.jp.api.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService mockEmployeeService;

    @Before
    public void init() {
        Employee employee = new Employee(1L, "jose", "jose@gmail.com", "912222222");
        when(mockEmployeeService.findById(1L)).thenReturn(Optional.of(EmployeeDto.create(employee)));
    }

    @Test
    public void findAll() throws Exception {

        List<Employee> employees = Arrays.asList(
                new Employee(1L, "jose", "jose@gmail.com", "912222222"),
                new Employee(2L, "maria", "maria@gmail.com", "913333333"));

        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDto.add(EmployeeDto.create(employee));
        }

        when(mockEmployeeService.findAll()).thenReturn(employeesDto);

        mockMvc.perform(get("/api/v1/employees")
                .with(user("user").roles("USER")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("jose")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("maria")));

        //verify(mockUserService, times(1)).findAll();
    }

    @Test
    public void findById() throws Exception {

        mockMvc.perform(get("/api/v1/employees/1")
                .with(user("user").roles("USER")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("jose")));

        //verify(mockUserService, times(1)).findById(1L);
    }

    @Test
    public void findByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/employees/5")
                .with(user("user").roles("USER")))
                .andExpect(status().isNotFound());
    }


    @Test
    public void create() throws Exception {

        Employee employee = new Employee(1L, "jose", "jose@gmail.com", "912222222");
        EmployeeDto employeeDto = EmployeeDto.create(employee);

        when(mockEmployeeService.save(any(Employee.class))).thenReturn(employeeDto);

        mockMvc.perform(post("/api/v1/employees")
                .content(om.writeValueAsString(employee))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .with(user("admin").roles("ADMIN")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("jose")));

        //verify(mockUserService, times(1)).save(any(Employee.class));
    }

    @Test
    public void update() throws Exception {

        Employee employee = new Employee(1L, "joseUpdate", "joseUpdate@gmail.com", "912222222");
        EmployeeDto employeeDto = EmployeeDto.create(employee);

        when(mockEmployeeService.save(any(Employee.class))).thenReturn(employeeDto);

        mockMvc.perform(put("/api/v1/employees/1")
                .content(om.writeValueAsString(employee))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .with(user("user").roles("USER")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("joseUpdate")));

        //verify(mockUserService, times(1)).save(any(Employee.class));
    }

    @Test
    public void deleteByID() throws Exception {

        doNothing().when(mockEmployeeService).deleteById(1L);

        mockMvc.perform(delete("/api/v1/employees/1")
                .with(user("user").roles("USER")))
                .andExpect(status().isOk());

        //verify(mockUserService, times(1)).deleteById(1L);
    }

    @Test
    public void findByName() throws Exception {

        List<Employee> employees = Arrays.asList(
                new Employee(1L, "jose", "jose@gmail.com", "912222222"),
                new Employee(2L, "jose", "jose2@gmail.com", "913333333"));

        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDto.add(EmployeeDto.create(employee));
        }

        when(mockEmployeeService.findByName("jose")).thenReturn(employeesDto);

        mockMvc.perform(get("/api/v1/employees/name/jose")
                .with(user("user").roles("USER")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("jose")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("jose")));


        //verify(mockUserService, times(1)).findByName("jose");
    }
}