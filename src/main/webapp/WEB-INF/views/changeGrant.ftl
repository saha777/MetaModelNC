<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Change grant</title>
</head>
<body>
<form action="/changeGrant" method="post">
    <select name="role">
        <#list roles as role>
            <option value="${role.name}">${role.name}</option>
        </#list>
    </select>
    <input type="submit" value="Change">
</form>
</body>
</html>