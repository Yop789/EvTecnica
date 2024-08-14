# EvTecnica

### Cuenta

post localhost:8080/api/cuentas

```json
{
  "ingreso": 1,
  "numeroCuenta": 1234562345
}
```

put localhost:8080/api/cuentas

```json
{
  "id": 2,
  "numeroCuenta": 1244562345,
  "ingreso": 1
}
```

get localhost:8080/api/cuentas

getById localhost:8080/api/cuentas/`{id}`

delete localhost:8080/api/cuentas/`{id}`

### Direcciones

post localhost:8080/api/direcciones

```json
{
  "estado": "Mexico",
  "codigoPostal": "123344"
}
```

put localhost:8080/api/direcciones

```json
{
  "id": 1,
  "estado": "tet",
  "codigoPostal": "123344"
}
```

get localhost:8080/api/direcciones

getById localhost:8080/api/direcciones/`{id}`

delete localhost:8080/api/direcciones/`{id}`

### usuarios

post localhost:8080/api/usuarios

```json
{
  "apellidoMaterno": "j",
  "apellidoPaterno": "jndj",
  "fechaNacimiento": "23/08/2023",
  "nombre": "dud",
  "id_cuenta": 1,
  "id_direccion": 1
}
```

put localhost:8080/api/usuarios

```json
{
  "id": 1,
  "nombre": "dud",
  "apellidoPaterno": "jndj",
  "apellidoMaterno": "Juarez",
  "fechaNacimiento": "23/08/2003",
  "id_direccion": 1,
  "id_cuenta": 1
}
```

get localhost:8080/api/usuarios

getById localhost:8080/api/usuarios/`{id}`

delete localhost:8080/api/usuarios/`{id}`
