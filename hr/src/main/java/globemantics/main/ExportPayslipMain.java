package globemantics.main;

import globemantics.documents.Payslip;
import globemantics.logging.ConsoleLogger;
import globemantics.persistence.EmployeeFileSerializer;
import globemantics.persistence.EmployeeFileRepository;
import globemantics.personnel.Employee;

import java.time.Month;
import java.util.List;

public class ExportPayslipMain {
    public static void main(String[] args) {
        // Create dependencies
        ConsoleLogger consoleLogger = new ConsoleLogger();
        EmployeeFileSerializer employeeFileSerializer = new EmployeeFileSerializer();
        EmployeeFileRepository repository = new EmployeeFileRepository(employeeFileSerializer);

        // Grab employees
        List<Employee> employees = repository.findAll();

        for (Employee employee : employees) {
            Payslip payslip = new Payslip(employee, Month.AUGUST);

            String exportableText = payslip.toTxt().toUpperCase();
            System.out.println(exportableText);
        }
    }
}
