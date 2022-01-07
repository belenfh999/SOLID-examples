package globemantics.payment;

import globemantics.notifications.EmployeeNotifier;
import globemantics.persistence.EmployeeRepository;
import globemantics.personnel.Employee;

import java.util.List;

public class PaymentProcessor {

    private EmployeeRepository employeeRepository;
    private EmployeeNotifier employeeNotifier;

    public PaymentProcessor(EmployeeRepository employeeRepository, EmployeeNotifier employeeNotifier) {
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
