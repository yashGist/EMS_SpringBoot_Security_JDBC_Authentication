<%@taglib  uri="http://www.springframework.org/tags/form"  prefix="form"%>

<H3>  Edit employee form</H3>

<form:form  action="updateEmployee"  method="post"  modelAttribute="emodel">
 <table>
   <tr>
     <td> empno </td> <td> <form:input path="empno"  readonly="true"/> </td>
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