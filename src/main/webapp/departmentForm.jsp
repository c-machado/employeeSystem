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
        &nbsp;&nbsp;&nbsp;
        <a href="/ServletDepartment?action=list">List All Departments</a>

    </h2>
</center>
<div align="center">
    <c:if test="${Department.getDepartmentName() != null}">
    <form action="/ServletDepartment?action=update" method="post">
        </c:if>
        <c:if test="${Department.getDepartmentName() == null}">
        <form action="/ServletDepartment?action=insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${Department.getDepartmentName() != null}">
                            Edit Department
                        </c:if>
                        <c:if test="${Department.getDepartmentName() == null}">
                            Add New Department
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${Department.getDepartmentName() != null}">
                    <input type="hidden" name="id" value="<c:out value='${Department.getDepartmentid()}' />" />
                </c:if>
                <tr>
                    <th>Department Name: </th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${Department.getDepartmentName()}' />"
                        />
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
