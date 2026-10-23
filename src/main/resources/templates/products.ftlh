<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>
<#if user.email??>
    <form action="/logout" method="post">
        ${user.getName()}
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="submit" value="Выйти">
    </form>
        <#else>
    <a href="/login">Войти</a>/<a href="/register">Зарегистрироваться</a>
</#if>
<form action="/" method="get">
    Поиск по названию: <input type="text" name="title"><br>
    <input type="submit" value="Поиск"/>
</form><hr>
<#list products as product>
    <div>
        <p><img src="images/${product.previewImageId}" height="60px" alt="There should have been a preview here"><br>
            ${product.title} - ${product.price}₽ | <a href="/products/${product.id}">Подробнее...</a> </p>
    </div>
    <#else>
    <h3>Товаров нет</h3>
</#list>

<hr>
<#if user.email??>
<h3>Выложить новый товар</h3>
    <form action="/products/create" method="post" enctype="multipart/form-data">
        Название: <input type="text" name="title" required="required"><br><br>
        Описание: <input type="text" name="description" required="required"><br><br>
        Цена: <input type="number" step="0.01" name="price" required="required"><br><br>
        Город: <input type="text" name="city" required="required"><br><br>
        Фото 1: <input type="file" name="file1" required="required"><br><br>
        Фото 2: <input type="file" name="file2"><br><br>
        Фото 3: <input type="file" name="file3"><br><br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="submit" value="Выложить">
    </form>
</#if>
</body>
</html>