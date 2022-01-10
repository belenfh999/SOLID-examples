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

| Base Health Insurance | Income Tax | Retirement Tax |
| :-------------------: | :--------: | :------------: |
|         $100          |    16%     |      10%       |

- At first, a class called TaxCalculator is created to implement this feature directly.

* Then new requirements are added for other types of employees:
  - Part-time employees:

| Base Health Insurance | Income Tax | Retirement Tax |
| :-------------------: | :--------: | :------------: |
|         $100          |    16%     |       5%       |

- Interns: - Income < 350 -> no tax due to state aid - Else ->

| Base Health Insurance | Income Tax | Retirement Tax |
| :-------------------: | :--------: | :------------: |
|          ---          |    16%     |      ---       |

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

# Interface Seggregation Principle (ISP)

In this module the ISP will be applied, it can be defined as:

> "Larger interfaces should be splitted into smaller interfaces to avoid clients being forced to depend on methods they do not use."

- A new business feature must be implemented: Pay Slips

  - A Pay Slip should contain the following information:
    - month of income
    - name of employee
    - income of employee for the given month
  - A Pay Slip can be exported to text but in the future it might also be exported to csv or json.

- At first, it looks like we could reuse the exportable document interface.
- But, since we will have only an implementation for the toTxt method, we decide to break this “fat” interface into smaller interfaces for each type of file extension (txt, pdf, json…)
- Having multiple specific interfaces is better than having only one general purpose interface.

# Dependency Inversion Principle (DIP)

In this module the DIP will be applied, it can be defined by two statements:

> "High Level Modules should not depend on Low Level Modules; both should depend on abstractions."

> "Abstractions should not depend on details, it should be the other way around."

## Definitions

### High Level Modules

- Modules written to solve real problems and use cases. (aka business logic)
  - They are more abstract and map to the business domain.

### Low Level Modules

- They contain implementation details that are required to execute the business policies.
  - They contain the "internals" of the Application
  - E. g. Logging, Data Access, Network Communication or IO.

### Abstraction

- Something that is not concrete.
- For instance, in Java it would be an interface or an abstract class.

### Dependency Injection (DI)

- It is a technique that allows the creation of dependent objects outside of a class and provides those objects to a class.

### Inversion of Control (IoC)

- It is a design principle in which the control of object creation, configuration, and lifecycle is passed to a container or framework.
  - The control of object creation is inverted.

## Demo

In this demo we will apply the DIP.

- The new features to be added are:
  - a program capable of paying all employees their income.
  - an Email Sender of Payslips (after the payment is done).

* Requirements:
  - We need a system that can retrieve all employees and pay their incomes.
  - Upon completion, the total paid sum must be calculated in order to compare it with the finance department.
  - Each employee should receive an email after the income payment was sent successfully.
* We remove the direct dependency between the EmailSender and PayEmployeesMain, by using an abstraction (interface).
* In the PaymentProcessor, the class is refactored, and DI is applied to the constructor.
* Also, in this module we added a test for the payment processor which uses Mocks for dependencies like the EmployeeFileRepository’s interface (EmployeeRepository)
