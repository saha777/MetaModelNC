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
            <td><a href="/departments/${office.objectId}">${office.objectId}</a></td>
            <td>${office.name}</td>
        </tr>
    </#list>
</table>
</body>
</html>