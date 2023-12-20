package pro.sky.homework2_8.service;


import pro.sky.homework2_8.Employee;
import pro.sky.homework2_8.Exeption.EmployeeAlreadyAddedException;
import pro.sky.homework2_8.Exeption.EmployeeNotFoundException;
import pro.sky.homework2_8.Exeption.EmployeeStorageIsFullException;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException, EmployeeNotFoundException;

    Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    String listEmployee();

}
