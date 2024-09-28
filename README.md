# 🌍 MiGps - Aplicación de Geolocalización 📍

¡Bienvenido al repositorio de **MiGps**! Este proyecto fue desarrollado para el ramo de **Desarrollo de Aplicaciones Móviles** del Instituto **AIEP**. **MiGps** es una aplicación sencilla que utiliza geolocalización en tiempo real y Firebase para autenticar usuarios y almacenar su ubicación actual.

## 📱 Funcionalidades

- 🔒 **Autenticación**: Los usuarios pueden iniciar sesión utilizando Firebase Authentication.
- 🗺️ **Geolocalización en Tiempo Real**: Captura la latitud y longitud del dispositivo móvil usando los servicios de ubicación de Google.
- 📊 **Almacenamiento en Firebase**: La ubicación del usuario se guarda en tiempo real en Firebase Realtime Database.
- 🔄 **Relocalización**: Permite al usuario relocalizar su posición con un solo clic.
- 🔐 **Cerrar Sesión**: Los usuarios pueden cerrar sesión y volver a iniciar sesión con otro usuario.

## 🎯 Objetivo del Proyecto

El objetivo principal de este proyecto es poner en práctica el desarrollo de aplicaciones móviles, utilizando las tecnologías más comunes como **Kotlin**, **Jetpack Compose**, **Firebase** y los **Servicios de Google Play** para obtener la ubicación del dispositivo.

Este proyecto es parte del **Instituto AIEP** en el ramo de **Desarrollo de Aplicaciones Móviles**.

## 🛠️ Tecnologías Utilizadas

- **Kotlin**: El lenguaje de programación utilizado en el desarrollo de la aplicación.
- **Jetpack Compose**: La moderna herramienta de UI de Android para construir interfaces de usuario.
- **Firebase Authentication**: Para la autenticación de los usuarios.
- **Firebase Realtime Database**: Para guardar y recuperar datos en tiempo real.
- **Google Play Services**: Para obtener la geolocalización del dispositivo.

## 🚀 Instalación y Configuración

### 1. Clona este repositorio:
```bash
git clone https://github.com/tu-usuario/MiGps.git
```

### 2. Configura Firebase:
   - Crea un proyecto en [Firebase Console](https://console.firebase.google.com/).
   - Habilita **Firebase Authentication** y selecciona el método de autenticación por correo y contraseña.
   - Habilita **Firebase Realtime Database** y asegúrate de que las reglas permitan lectura y escritura.
   - Descarga el archivo `google-services.json` y agrégalo al directorio `app/` de tu proyecto.

### 3. Configura los permisos:
   Asegúrate de que los siguientes permisos estén en el archivo `AndroidManifest.xml` para acceder a la ubicación del dispositivo:

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.INTERNET" />
```

### 4. Compila y ejecuta la aplicación:
   Una vez configurado, ejecuta la aplicación desde Android Studio.

## 📝 Estructura del Proyecto

```bash
MiGps/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/migps/
│   │       │   ├── MainActivity.kt       # Actividad principal con Jetpack Compose
│   │       │   ├── data/
│   │       │   │   ├── AuthManager.kt    # Manejo de autenticación con Firebase
│   │       │   │   ├── LocationManager.kt# Manejo de geolocalización y Firebase Database
│   │       │   └── ui/
│   │       │       ├── LoginScreen.kt    # Pantalla de login con Compose
│   │       │       └── theme/
│   │       └── res/
│   └── build.gradle
├── google-services.json                   # Archivo de configuración de Firebase
└── README.md                              # Este archivo
```

## 🎓 Proyecto Académico

Este proyecto es parte de la asignatura **Desarrollo de Aplicaciones Móviles** del Instituto AIEP, donde se profundiza en el uso de **Kotlin**, el **SDK de Android**, y herramientas de backend como **Firebase** para crear aplicaciones funcionales.

## 🧑‍💻 Autor

- **madKoding** - Estudiante de Desarrollo de Aplicaciones Móviles en AIEP

## 🛡️ Licencia

Este proyecto está bajo la licencia MIT - consulta el archivo [LICENSE](LICENSE) para más detalles.
