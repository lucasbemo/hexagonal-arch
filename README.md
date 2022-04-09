# hexagonal-arch
A Hexagonal Arch Model Project

Run a MySQL docker image:
```
docker run \
--detach \
--name=my-mysql \
--env="MYSQL_ROOT_PASSWORD=12345" \
--publish 6603:3306 \
mysql
```

Insert a Person:
```
{
    "name": "Lucas",
    "email": "lucas@empresa.com",
    "yearBirth": 1990,
    "birthDate": "1990-12-13",
    "cpf": "63234630089",
    "phone": "(55)11993188120"
}
```

Insert a Person:
```
{
    "name": "Ana",
    "email": "ana@empresa.com",
    "yearBirth": 1990,
    "birthDate": "1990-12-13",
    "cpf": "13870994002",
    "phone": "(55)11962184121"
}
```