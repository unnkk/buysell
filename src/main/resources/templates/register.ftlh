<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h4>Регистрация:</h4>
<form action="/register" method="post">
    Имя пользователя: <input type="text" name="name">
    Email: <input type="email" name="email" required="required"><br><br>
    Номер телефона <input type="text" name="phoneNumber">
    Пароль: <input type="password" name="password" required="required"><br><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться">
</form>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>