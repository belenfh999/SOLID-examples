package globemantics.main;

import java.util.List;

import globemantics.logging.ConsoleLogger;
import globemantics.persistence.EmployeeFileSerializer;
import globemantics.persistence.EmployeeFileRepository;
import globemantics.personnel.Employee;
import globemantics.personnel.FullTimeEmployee;

public class NatHolidayEmployeeTimeOffApp {
    public static void main(String[] args) {
        // Create dependencies
        ConsoleLogger consoleLogger = new ConsoleLogger();
        EmployeeFileSerializer employeeFileSerializer = new EmployeeFileSerializer();
        EmployeeFileRepository repository = new EmployeeFileRepository(employeeFileSerializer);

        // Grab employees
        List<Employee> employees = repository.findAll();
        Employee manager = new FullTimeEmployee("Steve Jackson", 5000);

        // Request time off for each employee on
        // national holiday
        for (Employee employee : employees) {
            employee.requestTimeOff(1, manager);
        }
    }
}
