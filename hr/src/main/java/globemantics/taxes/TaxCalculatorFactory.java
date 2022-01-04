package globemantics.taxes;

import globemantics.personnel.Employee;
import globemantics.personnel.FullTimeEmployee;
import globemantics.personnel.Intern;
import globemantics.personnel.PartTimeEmployee;

public class TaxCalculatorFactory {
    public static TaxCalculator create(Employee employee) {
        if (employee instanceof FullTimeEmployee) {
            return new FullTimeTaxCalculator();
        }
        if (employee instanceof PartTimeEmployee) {
            return new PartTimeTaxCalculator();
        }
        if (employee instanceof Intern) {
            return new InternTaxCalculator();
        }
        throw new RuntimeException("ERROR: Invalid employee type.");
    }
}
