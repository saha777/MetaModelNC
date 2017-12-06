<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
</head>
<body>
<form action="/employees/create" method="post" name="employee">
    <p>Department ID: <input type="number" name="parentId" value="${parentId}"></p>
    <p>Name: <input type="text" name="name" /></p>
    <p>Speciality: <input type="text" name="speciality" /></p>
    <p>Experience: <input type="number" name="experience" /></p>
    <p>Age: <input type="number" name="age" /></p>
    <p>Salary: <input type="number" name="salary" /></p>
    <p>Hiredate: <input type="date" name="hiredate"></p>
    <br><input type="submit" value="Submit">
</form>
</body>
</html>