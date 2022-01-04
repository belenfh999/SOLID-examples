package globemantics.taxes;

import globemantics.personnel.Employee;

public interface TaxCalculator {
    double calculate(Employee employee);
}
