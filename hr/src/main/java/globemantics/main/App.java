package globemantics.main;

import globemantics.persistence.EmployeeRepository;
import globemantics.personnel.Employee;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Grab employees
        EmployeeRepository repository = new EmployeeRepository();
        List<Employee> employees = repository.findAll();

        // Save all
        for (Employee e : employees) {
            Employee.save(e);
        }
    }
}
