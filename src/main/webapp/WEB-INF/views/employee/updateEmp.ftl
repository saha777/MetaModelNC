<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
</head>
<body>
<form action="/employees/update" method="post" name="employee">
    <p>ID : <input type="number" name="objectId" value="${employee.objectId}" readonly="readonly"></p>
    <p>Department ID : <input type="number" name="parentId" value="${employee.parentId}" readonly="readonly"></p>
    <p>Name : <input type="text" name="name" value="${employee.name}" readonly="readonly"></p>
    <p>Speciality : <input type="text" name="speciality" value="${employee.speciality}" readonly="readonly"></p>
    <p>Experience : <input type="number" name="experience" value="${employee.experience}" readonly="readonly"></p>
    <p>Age : <input type="number" name="age" value="${employee.age}" readonly="readonly"></p>
    <p>Salary : <input type="number" name="salary" value="${employee.salary}" readonly="readonly"/></p>
    <br><input type="submit" value="Update">
</form>
</body>
</html>