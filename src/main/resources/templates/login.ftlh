<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h4>Авторизация</h4>
<form action="/login" method="post">
    Email: <input type="email" name="username" required="required"><br><br>
    Пароль: <input type="password" name="password"required="required"><br><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="Войти">
</form>
</body>
</html>