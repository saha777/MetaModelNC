<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Departments</title>
</head>
<body>
<table border="1">
    <caption>Department List</caption>
    <tr>
        <th>objectId</th>
        <th>name</th>
    </tr>
    <#list departments as department>
        <tr>
            <td><a href="/employees/department/${department.objectId}">${department.objectId}</a></td>
            <td>${department.name}</td>
            <!--<td><a href="/delete/${department.objectId}">delete</a></td>
            <td><a href="/update/${department.objectId}">update</a></td>-->
        </tr>
    </#list>
</table>
</body>
</html>