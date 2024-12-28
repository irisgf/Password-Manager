CREA UNA BASE DE DATOS PARA ALMACENAR USUARIOS Y CONTRASEÑAS
Yo utilicé:

- INTELLIJ como IDE
- SQLITE como base de datos
- Javalin como framework

- También encripte la contraseña de esta forma:
Cuenta cuenta = new Cuenta(nombre, DigestUtils.sha256Hex(contrasena));
dao.createCuenta(cuenta);

-> todo se recibe en formato JSON y los tokens los almaceno con SharedPreferences
