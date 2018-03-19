<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
</head>
<body>
    <#if employee.name??>
        <h1>${employee.name}</h1>
    </#if>
    <#if employee.objectId??>
        <p>ID : ${employee.objectId}</p>
    </#if>
    <#if employee.speciality??>
        <p>Speciality : ${employee.speciality}</p>
    </#if>
    <#if employee.experience??>
        <p>Experience : ${employee.experience}</p>
    </#if>
    <#if employee.age??>
        <p>Age : ${employee.age}</p>
    </#if>
    <#if employee.salary??>
        <p>Salary : ${employee.salary}</p>
    </#if>

    <#if employee.hiredate??>
        <p>Hiredate : ${employee.hiredate}</p>
    </#if>
    <#if updatable>
        <a href="/employees/update/${employee.objectId}">Update</a><br>
    </#if>
    <a href="/employees/department/${employee.parentId}">Back</a>
</body>
</html>