package com.jp.api.services;

import com.jp.api.dto.EmployeeDto;
import com.jp.api.models.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDto> findAll();
    Optional<EmployeeDto> findById(Long id);
    EmployeeDto save(Employee employee);
    void deleteById(Long id);
    List<EmployeeDto> findByName(String name);
}