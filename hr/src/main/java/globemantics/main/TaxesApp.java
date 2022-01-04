package globemantics.main;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import globemantics.logging.ConsoleLogger;
import globemantics.persistence.EmployeeFileSerializer;
import globemantics.persistence.EmployeeRepository;
import globemantics.personnel.Employee;
import globemantics.taxes.TaxCalculator;
import globemantics.taxes.TaxCalculatorFactory;

public class TaxesApp {
    public static void main(String[] args) {
        // Create employees
        ConsoleLogger consoleLogger = new ConsoleLogger();
        EmployeeFileSerializer employeeFileSerializer = new EmployeeFileSerializer();
        EmployeeRepository employeeRepository = new EmployeeRepository(employeeFileSerializer);

        // Get Employees
        List<Employee> employees = employeeRepository.findAll();

        // Calculate taxes
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        double totalTaxes = 0;
        for (Employee employee : employees) {
            TaxCalculator taxCalculator = TaxCalculatorFactory.create(employee);
            // Compute individual tax
            double tax = taxCalculator.calculate(employee);
            String formattedTax = currencyFormatter.format(tax);
            consoleLogger.writeInfo(employee.getFullName() + " taxes: " + formattedTax);

            // Add to company total taxes
            totalTaxes += taxCalculator.calculate(employee);
        }
        consoleLogger.writeInfo("Total taxes = " + currencyFormatter.format(totalTaxes));
    }
}
