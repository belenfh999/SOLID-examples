package globemantics.main;

import globemantics.logging.ConsoleLogger;
import globemantics.persistence.EmployeeRepository;
import globemantics.persistence.EmployeeFileSerializer;
import globemantics.personnel.Employee;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Grab employees
        EmployeeFileSerializer employeeFileSerializer = new EmployeeFileSerializer();
        ConsoleLogger consoleLogger = new ConsoleLogger();

        EmployeeRepository repository = new EmployeeRepository(employeeFileSerializer);
        List<Employee> employees = repository.findAll();

        // Save all
        for (Employee e : employees) {
            try {
                repository.save(e);
                consoleLogger.writeInfo("Saved employee " + e.toString());
            } catch (IOException exception) {
                consoleLogger.writeError("Error saving employee", exception);

            }
        }
    }
}
