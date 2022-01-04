package globemantics.taxes;

import globemantics.personnel.Employee;

public class InternTaxCalculator implements TaxCalculator {
    private final int INCOME_TAX_PERCENTAGE = 16;

    public double calculate(Employee employee) {
        if (employee.getMonthlyIncome() < 350) {
            return 0;
        } else {
            return (employee.getMonthlyIncome() * INCOME_TAX_PERCENTAGE) / 100;
        }
    }
}
