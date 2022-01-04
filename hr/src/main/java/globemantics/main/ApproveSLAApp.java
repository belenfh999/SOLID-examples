package globemantics.main;

import globemantics.logging.ConsoleLogger;
import globemantics.persistence.EmployeeFileSerializer;
import globemantics.persistence.EmployeeRepository;
import globemantics.personnel.Employee;
import globemantics.personnel.ServiceLicenseAgreement;
import globemantics.personnel.Subcontractor;

import java.util.List;

public class ApproveSLAApp {
    public static void main(String[] args) {
        // Create dependencies
        ConsoleLogger consoleLogger = new ConsoleLogger();
        EmployeeFileSerializer employeeFileSerializer = new EmployeeFileSerializer();
        EmployeeRepository repository = new EmployeeRepository(employeeFileSerializer);

        // Define SLA
        int minTimeOffPercent = 98;
        int maxResolutionDays = 2;
        ServiceLicenseAgreement companySla = new ServiceLicenseAgreement(
                minTimeOffPercent,
                maxResolutionDays);

        // Grab subcontractors
        List<Employee> subcontractors = repository.findAll();

        for (Employee e : subcontractors) {
            if (e instanceof Subcontractor) {
                Subcontractor s = (Subcontractor) e;
                s.approveSLA(companySla);
            }
        }
    }
}
