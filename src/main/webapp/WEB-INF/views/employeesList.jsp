<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!-- <%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>-->

<a  href="addEmployee"> add more employee </a> 
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
<!-- <a href="logoutMe">SignOut</a>-->

<table border=1>
  <tr>
    <th>empno</th>
    <th>ename</th>
    <th>sal</th>
    <th>deptno</th>
    <th>actions</th>
  </tr>    

  <c:if test="${!empty empModelList}">
     <c:forEach  items="${empModelList}"  var="emp">
        <tr>
          <td> <c:out value="${emp.empno}"/> </td>
          <td> <c:out value="${emp.ename}"/> </td>
          <td> <c:out value="${emp.sal}"/> </td>
          <td> <c:out value="${emp.deptno}"/> </td>
            <td>
                <a href="editEmployee?id=${emp.empno}">
                    <img src="${pageContext.request.contextPath}/images/edit_image.png" width="40" height="40">
                </a>
                &nbsp; &nbsp; &nbsp; &nbsp;
                <a href="deleteEmployee?id=${emp.empno}">
                    <img src="${pageContext.request.contextPath}/images/delete_image.jpg" width="40" height="40">
                </a>
            </td>
        </tr>
     </c:forEach>
  </c:if>

</table>