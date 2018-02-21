<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
</head>
<body>
    <h1>${employee.name}</h1>
    <p>ID : ${employee.objectId}</p>
    <p>Speciality : ${employee.speciality}</p>
    <p>Experience : ${employee.experience}</p>
    <p>Age : ${employee.age}</p>

    <#if employee.salary??>
        <p>Salary : ${employee.salary}</p>
    </#if>

    <#if employee.hiredate??>
        <p>Hiredate : ${employee.hiredate}</p>
    </#if>
    <a href="/employees/update/${employee.objectId}">Update</a><br>

    <a href="/employees/department/${employee.parentId}">Back</a>
</body>
</html>