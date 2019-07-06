<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Management System</title>
</head>
<body>
<center>
    <h1>Department Management</h1>
    <h2>
        <a href="/ServletDepartment?action=new">Add New Department</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="/ServletDepartment?action=list">List All Departments</a>
           &nbsp;
        <a href="/ServletEmployees?action=list">Manage Employees</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Departments</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>

        </tr>
        <c:forEach var="Department" items="${listDepartment}">
            <tr>
                <td><c:out value="${Department.getDepartmentid()}" /></td>
                <td><c:out value="${Department.getDepartmentName()}" /></td>
                   <td>
                        <a href="/ServletDepartment?action=edit&id=<c:out value='${Department.getDepartmentid()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;

                        <a href="/ServletDepartment?action=delete&id=<c:out value='${Department.getDepartmentid()}' />">Delete</a>
                   </td>
            </tr>
 </c:forEach>
</table>
</div>
</body>
</html>
