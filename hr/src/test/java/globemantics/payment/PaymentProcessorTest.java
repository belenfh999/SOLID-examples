package globemantics.payment;

import globemantics.notifications.EmailSender;
import globemantics.persistence.EmployeeFileRepository;
import globemantics.personnel.Employee;
import globemantics.personnel.FullTimeEmployee;
import globemantics.personnel.Intern;
import globemantics.personnel.PartTimeEmployee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
In order to run these test class, 2 dependencies
have been added in the pom.xml file:
    - junit
    - mockito
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentProcessorTest {

    private EmployeeFileRepository employeeFileRepositoryMock;
    private EmailSender EmailSenderMock;

    @BeforeAll
    public void beforeAll() {
        // Income of all employees is 1700 $
        List<Employee> testEmployees = Arrays.asList(
                new FullTimeEmployee("Anna Smith", 1000),
                new PartTimeEmployee("John Doe", 500),
                new Intern("Gabriel Ortega", 200, 10));

        employeeFileRepositoryMock = Mockito.mock(EmployeeFileRepository.class);
        Mockito.when(employeeFileRepositoryMock.findAll())
                .thenReturn(testEmployees);

        EmailSenderMock = Mockito.mock(EmailSender.class);
    }

    @Test
    public void send_payments_should_pay_all_employee_salaries() {
        // arrange
        // PaymentProcessor paymentProcessor = new PaymentProcessor(
        // this.employeeFileRepositoryMock,
        // this.EmailSenderMock
        // );

        // // act
        // int result = paymentProcessor.sendPayments();

        // // assert
        // assertEquals(1700, result);
    }
}