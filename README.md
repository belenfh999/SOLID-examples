# SOLID-examples

SOLID Exercises for practicing based on a PluralSight Course.

# Brief explanation of the Application

The app is going to be based on a popular HR framework built by Globomantics.

It is a framework that is highly appreciated by its customers because it offers features like:

- Employee management
- Tax calculations
- Pay slip generation
- Reporting (sending emails)

# Single Responsibility Principle (SRP) Module

In this module the SRP will be applied to separate different responsibilities into separate classes.

- Originally, the business entity Employee had some functionality in its class, like persisting a record of an employee and logging into the console.
  - The persisting logic was moved to a new class called Employee Serializer.
  - The Logging was encapsulated in a logger class which is used in the App (main).
- The transactional class EmployeeRepository is the one in charge of saving the data of the employees by using the EmployeeFileSerializer.
- The exception handling is passed to the invoking method (a.k.a the App/Main class).

# Open Closed Principle (OCP) Module

In this module the OCP will be used so that classes/methods and modules are closed for modification, but open for extension.

- The new feature to be implemented is a Tax Calculator (initially) with the following requirements:
  - For each (full-time) employee, the taxes consist of three parts:
  <p align="center">

| Base Health Insurance | Income Tax | Retirement Tax |
| :-------------------: | :--------: | :------------: |
|         $100          |    16%     |      10%       |

</p>

- At first, a class called TaxCalculator is created to implement this feature directly.

* Then new requirements are added for other types of employees:
  - Part-time employees:

<p align="center">

| Base Health Insurance | Income Tax | Retirement Tax |
| :-------------------: | :--------: | :------------: |
|         $100          |    16%     |       5%       |

</p>

- Interns: - Income < 350 -> no tax due to state aid - Else ->
<p align="center">

| Base Health Insurance | Income Tax | Retirement Tax |
| :-------------------: | :--------: | :------------: |
|          ---          |    16%     |      ---       |

</p>

- Now there is an interface called TaxCalculator which is implemented by different classes (FullTimeTaxCalculator, PartTimeTaxCalculator, InternTaxCalculator)
  - This way the OCP is not violated by applying the strategy pattern.

* Also, in the App (main) a factory is used to generate the desired TaxCalculator class
  - This way when a new type of employee is added:
    - We do not need to modify existing code
    - The new tax calculator should be added to the factory and done!

# Liskov Substitution Principle (LSP)

In this module the LSP will be applied to learn about this principle which states that:

> "Any object of a type must be substitutable by objects of a derived type without altering the correctness of that program"

- The new feature to be implemented is a new type of Employee: Subcontractor
  - A subcontractor is an entity that works similar to an employee for various projects.
  - We are interested in the contact info (name, own email), cost (monthly income) and nb of hours worked.
  - They need to have an SLA approved before working.
  - Because subcontractors are external:
    - They are not eligible to take time off.
    - It is not needed to calculate taxes for them.

* The first approach we can think of is to consider a subcontractor as a subtype of Employee.

  - However, they are not substitutable by one another, so, this would violate the LSP.

* The correct approach is to consider Subcontractors as separate classes which have no relationship with Employees.
