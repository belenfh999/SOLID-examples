package globemantics.main;

import globemantics.notifications.EmailSender;
import globemantics.notifications.EmployeeNotifier;
import globemantics.payment.PaymentProcessor;
import globemantics.persistence.EmployeeFileRepository;
import globemantics.persistence.EmployeeFileSerializer;
import globemantics.persistence.EmployeeRepository;

public class PayEmployeesMain {

    /*
     * Will take a couple of seconds to execute due to the
     * sending of mails.
     */

    public static void main(String[] args) {
        EmployeeFileSerializer serializer = new EmployeeFileSerializer();
        EmployeeRepository employeeRepository = new EmployeeFileRepository(serializer);
        EmployeeNotifier employeeNotifier = new EmailSender();
        PaymentProcessor paymentProcessor = new PaymentProcessor(employeeRepository, employeeNotifier);

        int totalPayments = paymentProcessor.sendPayments();
        System.out.println("Total payments " + totalPayments);
    }
}
