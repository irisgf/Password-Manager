# Administrador de contraseñas de usuarios
Parte de mi tfg fue crear una aplicación de escritorio donde un administrador pudiera cambiarles a los usuarios la contraseña, obviamente es una aplicación muy pequeña ya que un solo administrador no daría a basto a cambiar contraseñas desde la terminal y no tiene mucho sentido, por lo que con el tiempo y con el crecimiento y modificaciones de la aplicación, se mejorará para que la base de datos sea mayor y los usuarios puedan gestionarse por si solos.

*esto no es mas que un ejemplo sencillo de como empezar

## CREA UNA BASE DE DATOS PARA ALMACENAR USUARIOS Y CONTRASEÑAS

- INTELLIJ como IDE
- SQLITE como base de datos
- Javalin como framework

- También encripte la contraseña con sha256Hex, un ejemplo:
Cuenta cuenta = new Cuenta(nombre, DigestUtils.sha256Hex(contrasena));
dao.createCuenta(cuenta);

-> todo se recibe en formato JSON y los tokens los almaceno con SharedPreferences
