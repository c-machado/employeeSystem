<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Management System</title>
</head>
<body>
<center>
    <h1>Regulation Management</h1>
    <h2>
        <a href="/ServletStatusReport?action=list">List All Regulations</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Regulations</h2></caption>
        <tr>
            <th>Compliance</th>
            <th>Status</th>
            <th>User</th>
            <th>Comments</th>
            <th>Creation Date</th>
            <th>Department</th>
            <th>Actions</th>

        </tr>
        <c:forEach var="Regulation" items="${listRegulations}">
            <tr>
                <td><c:out value="${Regulation.compliance.getComplianceId()}" /></td>
                <td><c:out value="${Regulation.getStatusRptId()}" /></td>
                <td><c:out value="${Regulation.empId.getEmail()}" /></td>
                <td><c:out value="${Regulation.getComments()}" /></td>
                <td><c:out value="${Regulation.getCreateDate()}"/></td>
                <td><c:out value="${Regulation.department.departmentName}" /></td>
                <td><a href="/ServletStatusReport?action=edit&id=<c:out value='${Regulation.empId.getEmpId()}' />">Edit</a></td>
            </tr>
        </c:forEach>
</table>
</div>
</body>
</html>
