package pro.sky.homework2_8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework2_8.Exeption.EmployeeAlreadyAddedException;
import pro.sky.homework2_8.Exeption.EmployeeNotFoundException;
import pro.sky.homework2_8.Exeption.EmployeeStorageIsFullException;
import pro.sky.homework2_8.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/add")
    public String add(String firstName, String lastName) {

        try {
            return employeeService.addEmployee(firstName, lastName).toString();
        } catch (EmployeeStorageIsFullException e) {
            return e.getMessage();
        } catch (EmployeeAlreadyAddedException e) {
            return e.getMessage();
        } catch (EmployeeNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/remove")
    public String remove(String firstName, String lastName) {
        try {
            return employeeService.removeEmployee(firstName, lastName).toString();
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/find")
    public String find(String firstName, String lastName) {
        try {
            return employeeService.findEmployee(firstName, lastName).toString();
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/list")
    public String list() {
        return employeeService.listEmployee();
    }
}
