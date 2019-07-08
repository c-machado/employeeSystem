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
        <a href="/ServletRegulations?action=new">Add New Regulation</a>
        &nbsp;&nbsp;
        <a href="/ServletStatusReport?action=new">Assign Regulation</a>
        &nbsp;
        <a href="/ServletRegulations?action=list">List All Regulations</a>
        &nbsp;
        <a href="/ServletStatusReport?action=list">List Assigned Regulations</a>
        &nbsp;
        <a href="/ServletDepartment?action=list">Manage Departments</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Regulations</h2></caption>
        <tr>
            <th>ID</th>
            <th>Regulation Type</th>
            <th>Details</th>
            <th>Create Date</th>
            <th>Department</th>

        </tr>
        <c:forEach var="Regulation" items="${listRegulations}">
            <tr>
                <td><c:out value="${Regulation.getComplianceId()}" /></td>
                <td><c:out value="${Regulation.getRlType()}" /></td>
                <td><c:out value="${Regulation.getDetails()}" /></td>
                <td><c:out value="${Regulation.getCreateDate()}"/></td>
                <td><c:out value="${Regulation.department.departmentName}" /></td>
     </tr>
 </c:forEach>
</table>
</div>
</body>
</html>
