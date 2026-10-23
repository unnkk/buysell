<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL ADMIN EDITOR</title>
</head>
<body>
<h1>Editing user ${user.name}(${user.id})</h1><hr>
<form action="/admin/edit/" method="post">
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
        </div>
    </#list>
    <input type="hidden" name="userId" value="${user.id}">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit">Save</button>
</form>
</body>
</html>