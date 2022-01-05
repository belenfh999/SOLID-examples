package globemantics.payment;

import globemantics.notifications.EmailSender;
import globemantics.persistence.EmployeeFileRepository;
import globemantics.persistence.EmployeeFileSerializer;
import globemantics.personnel.Employee;

import java.util.List;

public class PaymentProcessor {

    private EmployeeFileRepository employeeRepository;

    public PaymentProcessor() {
        EmployeeFileSerializer serializer = new EmployeeFileSerializer();
        this.employeeRepository = new EmployeeFileRepository(serializer);
    }

    public int sendPayments() {
        List<Employee> employees = this.employeeRepository.findAll();
        int totalPayments = 0;

        for (Employee employee : employees) {
            totalPayments += employee.getMonthlyIncome();
            EmailSender.notify(employee);
        }

        return totalPayments;
    }
}
