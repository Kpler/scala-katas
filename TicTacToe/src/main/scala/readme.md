Lots of the operations below can be delegated to the IDEA to generate a working code.

- Create a class Employee
- Add a few fields: firstName, lastName (as strings), create the associated constructor for it. In the Main class print the result of an instanciation of an employee. If the result is un-readable make it readable.
These fields can be accessible from outside the class. Print them in the main. But they cannot be set. They must stay immutable
- For the fun propose a second way to do it in another class Employee2: with fields firstName, lastName.
- Come back to Employee
Add a new field date of birth (an instant should work fine for now). This field does not have to be passed in each of the instanciations and must be null by default.
Provide the capacity to get the date of birth as well as the capacity of setting the value
- Propose a function that gives us the age of the employee
- Add an address field to the employee. This field can be passed with the date of birth or just set.
- In a unit test: Create two employees with the exact same attibutes for each fields. See the difference between employee1.getName.equals(employee2.getName) and employee1.getName == employee2.getName
- In a unit test: Create two employees with the exact same attibutes for each fields. See the difference between employee1.equals(employee2) and employee1 == employee2
- Re-define equals and hashcode and re-do the same comparison: employee1.equals(employee2) and employee1 == employee2. (For the sake of the exercise create another Employee class)
- What can we deduct from that ?
