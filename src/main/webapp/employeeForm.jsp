<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type="text/javascript">
        window.onload=function() {
            document.getElementById("selectDept").value = document.getElementById("depId").value;
        }
        function selectDeptChange() {
            document.getElementById("depId").value = document.getElementById("selectDept").value;
        }
    </script>
    <title>Employee Management System</title>
</head>
<body >
<center>
    <h1>Employee Management</h1>
    <h2>
        <a href="/ServletEmployees?action=new">Add New Employee</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/ServletEmployees?action=list">List All Employees</a>

    </h2>
</center>
<div align="center">
    <c:if test="${Employee.getFirstName() != null}">
    <form action="/ServletEmployees?action=update" method="post">
        </c:if>
        <c:if test="${Employee.getFirstName() == null}">
        <form action="/ServletEmployees?action=insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${Employee.getFirstName() != null}">
                            Edit Employee
                        </c:if>
                        <c:if test="${Employee.getFirstName() == null}">
                            Add New Employee
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${Employee.getFirstName() != null}">
                    <input type="hidden" name="id" value="<c:out value='${Employee.getEmpId()}' />" />
                </c:if>
                <tr>
                    <th>First Name: </th>
                    <td>
                        <input type="text" name="firstName" size="45"
                               value="<c:out value='${Employee.getFirstName()}' />"
                        />
                    </td>
                </tr><
                <tr>
                    <th>Last Name: </th>
                    <td>
                        <input type="text" name="lastName" size="45"
                               value="<c:out value='${Employee.getLastName()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Date Of Birthday: </th>
                    <td>
                        <input type="text" name="dob" size="45"
                               value="<c:out value='${Employee.getDob()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Email: </th>
                    <td>
                        <input type="text" name="email" size="45"
                               value="<c:out value='${Employee.getEmail()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Department: </th>
                    <td>
                        <select id = "selectDept" onchange="selectDeptChange()">
                            <c:forEach items="${deptList}" var="dept">
                                <option value="${dept.getDepartmentid()}">${dept.getDepartmentName()}</option>
                            </c:forEach>
                        </select>
                        <input id = "depId" name = "depId" type="hidden" value="<c:out value='${Employee.department.getDepartmentid()}' />" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
