package com.jp.api.services;

import com.jp.api.dto.EmployeeDto;
import com.jp.api.models.Employee;
import com.jp.api.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeDto> findAll(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDto.add(EmployeeDto.create(employee));
        }
        return employeesDto;
    }

    public Optional<EmployeeDto> findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(EmployeeDto.create(employee.get()));
    }

    public EmployeeDto save(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeDto.create(savedEmployee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDto> findByName(String name, Pageable pageable) {
        Page<Employee> employees = employeeRepository.findByName(name, pageable);
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for (Employee employee : employees) {
            employeesDto.add(EmployeeDto.create(employee));
        }
        return employeesDto;
    }
}