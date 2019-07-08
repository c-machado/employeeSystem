<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type="text/javascript">
        window.onload=function() {
            document.getElementById("selectDept").value = document.getElementById("depId").value;
            document.getElementById("selectCompliance").value = document.getElementById("compId").value;
            document.getElementById("selectUser").value = document.getElementById("userId").value;
        }
        function selectDeptChange() {
            document.getElementById("depId").value = document.getElementById("selectDept").value;
        }
        function selectCompChange() {
            document.getElementById("compId").value = document.getElementById("selectCompliance").value;
        }
        function selectUserChange() {
            document.getElementById("userId").value = document.getElementById("selectUser").value;
        }
    </script>
    <title>Employee Management System</title>
</head>
<body >
<center>
    <h1>Regulation Management</h1>
    <h2>
        <a href="/ServletStatusReport?action=list">List All Regulations</a>

    </h2>
</center>
<div align="center">
    <c:if test="${Employee.getCompliance() != null}">
    <form action="/ServletStatusReport?action=update" method="post">
        </c:if>
        <c:if test="${Employee.getCompliance() == null}">
        <form action="/ServletStatusReport?action=insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${Regulation.getCompliance() != null}">
                            Edit Regulation
                        </c:if>
                        <c:if test="${Regulation.getCompliance() == null}">
                            Add New Regulation
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${Regulation.getCompliance() != null}">
                    <input type="hidden" name="id" value="<c:out value='${Regulation.getEmpId()}' />" />
                </c:if>
                <tr>
                    <th>Compliance: </th>
                    <td>
                        <select id = "selectCompliance" onchange="selectCompChange()">
                            <c:forEach items="${compList}" var="compliance">
                                <option value="${compliance.getComplianceId()}">${compliance.getRlType()}</option>
                            </c:forEach>
                        </select>
                        <input id = "compId" name = "compId" type="hidden" value="<c:out value='${Regulation.compliance.getComplianceId()}' />" />
                    </td>
                </tr>

                <tr>
                    <th>User: </th>
                    <td>
                        <select id = "selectUser" onchange="selectUserChange()">
                            <c:forEach items="${userList}" var="userList">
                                <option value="${userList.getEmpId()}">${userList.getLastName()}</option>
                            </c:forEach>
                        </select>
                        <input id = "userId" name = "userId" type="hidden" value="<c:out value='${Regulation.employee.getEmpId()}' />" />
                    </td>
                </tr>

                <tr>
                    <th>Comments: </th>
                    <td>
                        <input type="text" name="comments" size="45"
                               value="<c:out value='${Regulation.getComments()}' />"
                        />
                    </td>
                </tr><

                <tr>
                    <th>Date Of Creation: </th>
                    <td>
                        <input type="text" name="createDate" size="45"
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
