<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Offices</title>
</head>
<body>
<table border="1">
    <caption>Office List</caption>
    <tr>
        <th>objectId</th>
        <th>name</th>
    </tr>
    <#list offices as office>
        <tr>
            <td><a href="/office/${office.objectId}">${office.objectId}</a></td>
            <td>${office.name}</td>
            <!--<td><a href="/delete/${office.objectId}">delete</a></td>
            <td><a href="/update/${office.objectId}">update</a></td>-->
        </tr>
    </#list>
</table>
</body>
</html>