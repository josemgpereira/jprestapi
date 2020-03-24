package com.jp.api.controllers;

import com.jp.api.models.Employee;
import com.jp.api.dto.EmployeeDto;
import com.jp.api.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/v1/employees"})
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(employeeService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        Optional<EmployeeDto> employee = employeeService.findById(id);
        if (!employee.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee.get());
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody Employee employee) {
        EmployeeDto createdEmployee = employeeService.save(employee);
        //URI location = getUri(createdEmployee.getId());
        //return ResponseEntity.created(location).build();
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        if (!employeeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        employee.setId(id);
        EmployeeDto updatedEmployee = employeeService.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!employeeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        employeeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<EmployeeDto>> findByName(@PathVariable String name, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        List<EmployeeDto> employees = employeeService.findByName(name, PageRequest.of(page, size));
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }
}