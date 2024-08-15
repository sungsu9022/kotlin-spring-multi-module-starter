# Kotlin-spring-multi-module-starter

## Tech Stack
### Back-end
- spring boot 3.0.5
- kotlin 1.8.10
- Spring-Data-Jpa
- MySQL( docker-compose )
- Caffeine Local Cache
- Mapstruct
- CoroutinVersion
- Webflux/Jetty(for Spring6 HTPP Interface)
- Kotest

### Front-end
 - React
 - MUI

## Build

### 1. admin
``` sh
./gradlew server:admin:build -Pprofile=local
```

### 2. file 
``` sh
./gradlew server:file:build -Pprofile=local
```

## Intellij Plugins
### Back-end
- kotest
- ktlint
