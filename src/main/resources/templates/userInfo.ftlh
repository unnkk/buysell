<!DOCTYPE>
<html>
<head>
    <title>BUYSELL</title>
</head>
<body>
    <h3>Информация о продавце ${user.name}</h3>
    <b>Номер телефона:</b> ${user.phoneNumber}<br>
    <b>Email:</b> ${user.email}<hr>
    <h4>Товары пользователя ${user.name}</h4>
    <#list user.products as product>
        <div>
            <p><img src="/images/${product.previewImageId}" height="60px" alt="There should have been a preview here"><br>
                ${product.title} - ${product.price}₽ | <a href="/products/${product.id}">Подробнее...</a> </p>
        </div>
    <#else>
        <h3>Товаров нет</h3>
    </#list>
</body>
</html>