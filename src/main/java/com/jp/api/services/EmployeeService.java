package com.jp.api.services;

import com.jp.api.dto.EmployeeDto;
import com.jp.api.models.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDto> findAll(Pageable pageable);
    Optional<EmployeeDto> findById(Long id);
    EmployeeDto save(Employee employee);
    void deleteById(Long id);
    List<EmployeeDto> findByName(String name, Pageable pageable);
}