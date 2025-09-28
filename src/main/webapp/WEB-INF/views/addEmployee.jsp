<%@taglib  uri="http://www.springframework.org/tags/form"  prefix="form"%>

<%--@elvariable id="empModel" type=""--%>
<form:form  action="saveEmployee"  method="post"  modelAttribute="empModel">
 <table>
   <tr>
     <td> empno </td> <td> <form:input path="empno"/> </td>
   </tr>
   <tr>
     <td> ename </td> <td> <form:input path="ename"/> </td>
   </tr>
   <tr>
     <td> sal </td> <td> <form:input path="sal"/> </td>
   </tr>
   <tr>
     <td> deptno </td> <td> <form:input path="deptno"/> </td>
   </tr>     
   <tr>
      <td  colspan="2"> <input type="submit"  value="SUBMIT"> </td>
   </tr>
 </table>

</form:form>