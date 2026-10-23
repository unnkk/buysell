<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL ADMIN</title>
</head>
<body>
<h1>BUYSELL admin panel</h1><hr>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Email</th>
        <th>Phone num.</th>
        <th>Active</th>
        <th>Roles</th>
        <th>Ban</th>
        <th>Edit</th>
        <th>More...</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.id}</th>
            <th>${user.email}</th>
            <th>${user.phoneNumber}</th>
            <th>${user.active?c}</th>
            <th><#list user.roles as role>${role}<#sep>, </#list></th>
            <th>
                <form action="/admin/ban/${user.id}" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <input type="submit" value="Ban!">
                </form>
            <th>
            <th><a href="/admin/edit/${user.id}">Edit</a></th>
            <th><a href="users/${user.id}">More</a></th>
        </tr>
        <#else>
        Empty list
    </#list>
</table>
</body>
</html>