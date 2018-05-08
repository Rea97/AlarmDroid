# AlarmDroid
AlarmDroid es un proyecto destinado a la monitorización de posibles peligros para personal de
zonas industriales, esto mediante el uso de un robot (AlarmDroid) con sensores de fuego, gas, entre otros
el cual estará en constante monitoreo de niveles que puedan representar un daño. Ante tales
peligros será capaz de enviar alertas a un [servicio web](https://github.com/Rea97/AlarmDroid-api), dichas alertas serán mostradas en
esta aplicación.

## Módulos
### Login
Login sencillo que conecta con la [api de AlarmDroid](https://github.com/Rea97/AlarmDroid-api) enviando una petición de tipo
post a la ruta `http://alarmdroid.herokuapp.com/api/login`, si el login es exitoso se retorna al cliente un token, el cual necesitará en peticiones
posteriores tales como recuperar la lista de alertas.
### Lista de últimas alertas
Un vistazo rápido a solo algunas de las alertas, ordenadas de más reciente a menos reciente. Es posible acceder al detalle
de la alerta dando un toque a alguna alerta de la lista.
### Lista completa de alertas
Lista completa de todas las alertas generadas por el AlarmDroid del usuario. Esta sección cuenta con un
buscador para poder navegar entre las alertas más facilmente.
### Detalle de alerta
Un completo detalle de la alerta el cual muestra título, descripción y timestamp del momento en el que se creó
la alerta.
### Conducción de AlarmDroid
Vistazo a la visión del AlarmDroid a través del uso de un WebView. También es posible cambiar el estatus del AlarmDroid
para indicarle que avance, retroceda o gire a la derecha y la izquierda. Esto último es posible mediante la [api de AlarmDroid](https://github.com/Rea97/AlarmDroid-api).

### Mi AlarmDroid
Pantalla con dos paneles que simplemente despliegan información del usuario, tanto sus datos como los del AlarmDroid que tiene
asignado.
### Acerca de
Información acerca del proyecto [AlarmDroid](https://github.com/Rea97/AlarmDroid).