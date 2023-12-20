package pro.sky.homework2_8.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pro.sky.homework2_8.Employee;
import pro.sky.homework2_8.Exeption.EmployeeAlreadyAddedException;
import pro.sky.homework2_8.Exeption.EmployeeNotFoundException;
import pro.sky.homework2_8.Exeption.EmployeeStorageIsFullException;
import pro.sky.homework2_8.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    final int MAX_EMPLOYEE = 5;
    Map<String, Employee> EmployeeStorage = new HashMap<>(MAX_EMPLOYEE);

    public Employee addEmployee(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException, EmployeeNotFoundException {
        if (EmployeeStorage.size() >= MAX_EMPLOYEE) {
            throw new EmployeeStorageIsFullException("Хранилище заполнено");
        } else if (EmployeeStorage.containsKey(firstName + lastName)) {
            throw new EmployeeAlreadyAddedException("Уже есть такой сотрудник");
        } else {
            Employee newEmployee = new Employee(firstName, lastName);
            EmployeeStorage.put(newEmployee.getFirstName() + newEmployee.getLastName(), newEmployee);
            return EmployeeStorage.get(newEmployee.getFirstName() + newEmployee.getLastName());
        }
    }

    public String listEmployee() {
        return EmployeeStorage.toString();
    }

    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee newEmployee = new Employee(firstName, lastName);
        if (EmployeeStorage.containsKey(newEmployee.getFirstName() + newEmployee.getLastName())) {
            return EmployeeStorage.get(newEmployee.getFirstName() + newEmployee.getLastName());
        } else {
            throw new EmployeeNotFoundException("Не найдено");
        }
    }

    public Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee newEmployee = new Employee(firstName, lastName);
        if (!EmployeeStorage.containsKey(newEmployee.getFirstName() + newEmployee.getLastName())) {
            throw new EmployeeNotFoundException("Удаляемый сотрудник не найден");
        } else {
            return EmployeeStorage.remove(newEmployee.getFirstName() + newEmployee.getLastName());
        }
    }
}


