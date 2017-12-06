<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<table border="1">
    <caption>Location List</caption>
    <tr>
        <th>objectId</th>
        <th>name</th>
    </tr>
    <#list locations as location>
        <tr>
            <td><a href="/location/${location.objectId}">${location.objectId}</a></td>
            <td>${location.name}</td>
            <!--<td><a href="/delete/${location.objectId}">delete</a></td>
            <td><a href="/update/${location.objectId}">update</a></td>-->
        </tr>
    </#list>
</table>
</body>
</html>