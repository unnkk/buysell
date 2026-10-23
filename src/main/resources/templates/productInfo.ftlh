<!DOCTYPE html>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
<h1>BUYSELL</h1><hr>

<#list images as image>
    <img src="/images/${image.id}" height="60px"><br><br>
</#list>

<b>Название: </b>${product.title}<br>
<b>Описание: </b>${product.description}<br>
<b>Цена: </b>${product.price}<br>
<b>Город: </b>${product.city}<br>
<b>Продавец: </b><a href="/users/${product.user.id}">${product.user.name}</a><br>

<hr>
</body>
</html>