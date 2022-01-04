package globemantics.taxes;

import globemantics.personnel.Employee;

public class PartTimeTaxCalculator implements TaxCalculator {
    private final int INCOME_TAX_PERCENTAGE = 16;
    private final int BASE_HEALTH_INSURANCE = 100;

    public double calculate(Employee employee) {
        double monthlyIncome = employee.getMonthlyIncome();

        return BASE_HEALTH_INSURANCE
                + ((monthlyIncome * 5) / 100)
                + ((monthlyIncome * INCOME_TAX_PERCENTAGE) / 100);
    }
}
