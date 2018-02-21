<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employees</title>
</head>
<body>
<table border="1">
    <caption>Employee List</caption>
    <tr>
        <th>objectId</th>
        <th>name</th>
    </tr>
    <#list employees as employee>
        <tr>
            <td><a href="/employees/${employee.objectId}">${employee.objectId}</a></td>
            <td>${employee.name}</td>
            <!--<td><a href="/delete/${employee.objectId}">delete</a></td>
            <td><a href="/employees/update/${employee.objectId}">update</a></td>-->
        </tr>
    </#list>
</table>
</body>
</html>