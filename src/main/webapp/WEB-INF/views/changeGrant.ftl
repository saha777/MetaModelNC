<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Change grant</title>
</head>
<body>
<form action="/changeGrant" method="post">
    Grant: <input type="number" name="grant" value="${grant}" min="1" max="10">
    <input type="submit" value="Change">
</form>
</body>
</html>