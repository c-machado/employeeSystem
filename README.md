# employeeSystem

1. To navigate to the Employees Management enter this url: http://localhost:8383/ServletEmployees?action=list, 
please update the base url, based on the configure port in your machine.
You'll find the following options: 

* Add New Employee (http://localhost:8383/ServletEmployees?action=new)
* Edit (http://localhost:8383/ServletEmployees?action=edit&id=1)
* Delete (http://localhost:8383/ServletEmployees?action=delete&id=#idnumber)
* List All Employees (http://localhost:8383/ServletEmployees?action=list)
* Manage Departments (http://localhost:8383/ServletDepartment?action=list)
* Manage Regulations (http://localhost:8383/ServletRegulations?action=list)

2. To Manage the content of the Departments, go to: http://localhost:8383/ServletDepartment?action=list
You're going find how to add a new department (http://localhost:8383/ServletDepartment?action=new), and also, these options:

* List all departments: http://localhost:8383/ServletDepartment?action=list
* Manage Employees (http://localhost:8383/ServletEmployees?action=list)
* Manage Regulations (http://localhost:8383/ServletRegulations?action=list)

3. In case you want to explore regulations, please go to: http://localhost:8383/ServletRegulations?action=list. In this view, you'll find 
many options to manage this content, such as:

* Add New Regulation (http://localhost:8383/ServletRegulations?action=new)
* Assign Regulation (http://localhost:8383/ServletStatusReport?action=new)
* List All Regulations (http://localhost:8383/ServletRegulations?action=list)
* List Assigned Regulations (http://localhost:8383/ServletStatusReport?action=list)
* Manage Departments (http://localhost:8383/ServletDepartment?action=list)

4. Finally you'll have the option to update the regulation assigned to you. This is something under a minor fixed right now. 
(http://localhost:8383/ServletStatusReport?action=edit)

Note: Login is being added to the workflow, however the main functionlity and all the CRUD's are working as expected.

Please contact me, if any doubt or comment. Email:dcmachadou@gmail.com
Thanks!
Carolina Machado
