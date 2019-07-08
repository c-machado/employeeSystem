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
    <h1>Regulation Management</h1>
    <h2>
        &nbsp;&nbsp;&nbsp;
        <a href="/ServletRegulations?action=list">List All Regulations</a>

    </h2>
</center>
<div align="center">

        <form action="/ServletRegulations?action=insert" method="post">
            <table border="1" cellpadding="5">
                <caption>
                    <h2>Add New Regulation</h2>
                </caption>
                <tr>
                    <th>Regulation Type: </th>
                    <td>
                        <input type="text" name="type" size="45"
                               value="<c:out value='${Regulation.getRlType()}' />"
                        />
                    </td>
                </tr><
                <tr>
                    <th>Details: </th>
                    <td>
                        <input type="text" name="details" size="250" width="80%"
                               value="<c:out value='${Regulation.getDetails()}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Creation Date: </th>
                    <td>
                        <input type="text" name="creationDate" size="45"
                               value="<c:out value='${Regulation.getCreateDate()}' />"
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
                        <input id = "depId" name = "depId" type="hidden" value="<c:out value='${Regulation.department.getDepartmentid()}' />" />
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
