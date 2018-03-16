<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
</head>
<body>
<form action="/employees/update" method="post" name="employee">
    <#if employee.objectId??>
        <p>ID : <input type="number" name="objectId" value="${employee.objectId}" readonly="readonly"></p>
    </#if>
    <#if employee.parentId??>
        <p>Department ID : <input type="number" name="parentId" value="${employee.parentId}" readonly="readonly"></p>
    </#if>
    <#if employee.name??>
        <p>Name : <input type="text" name="name" value="${employee.name}" readonly="readonly"></p>
    </#if>
    <#list user?keys as prop>
        ${prop} = ${user.get(prop)}
    </#list>
    <#if employee.speciality??>
        <p>Speciality : <input type="text" name="speciality" value="${employee.speciality}"></p>
    </#if>
    <#if employee.experience??>
        <p>Experience : <input type="number" name="experience" value="${employee.experience}"></p>
    </#if>
    <#if employee.age??>
        <p>Age : <input type="number" name="age" value="${employee.age}"></p>
    </#if>
    <#if employee.salary??>
        <p>Salary : <input type="number" name="salary" value="${employee.salary}"/></p>
    </#if>
    <#if employee.hiredate??>
        <p>Hiredate : <input type="date" name="hiredate" value="${employee.hiredate}"/></p>
    </#if>
    <br><input type="submit" value="Update">
</form>
</body>
</html>