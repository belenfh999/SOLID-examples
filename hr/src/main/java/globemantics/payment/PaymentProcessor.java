package globemantics.payment;

import globemantics.notifications.EmployeeNotifier;
import globemantics.persistence.EmployeeFileRepository;
import globemantics.personnel.Employee;

import java.util.List;

public class PaymentProcessor {

    private EmployeeFileRepository employeeRepository;
    private EmployeeNotifier employeeNotifier;

    public PaymentProcessor(EmployeeFileRepository employeeRepository, EmployeeNotifier employeeNotifier) {
        this.employeeRepository = employeeRepository;
        this.employeeNotifier = employeeNotifier;
    }

    public int sendPayments() {
        List<Employee> employees = this.employeeRepository.findAll();
        int totalPayments = 0;

        for (Employee employee : employees) {
            totalPayments += employee.getMonthlyIncome();
            this.employeeNotifier.notify(employee);
        }

        return totalPayments;
    }
}
