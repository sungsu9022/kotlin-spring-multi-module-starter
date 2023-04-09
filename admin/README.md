# starter-admin(web)

## Build

``` sh 
./gradlew admin:build -Pprofile=local
```

## Run(after Build)

``` sh
# from root
cd admin/build/libs
# Heap size is 1G. It is changeable
java -server -Xms1G -Xmx1G -XX:+UseG1GC -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -jar -Dspring.profiles.active=local -Dlog4j2.formatMsgNoLookups=true admin-1.0.0-SNAPSHOT.jar
```

## Local Development
### 1. Run AdminApplication
 - IDE 환경에 따라 다를수 있음.

### 2. RUn Webpack Dev Server
``` sh
# start webpack dev server
cd admin
yarn watch
```

### 3. WebPage 접근
> local 환경에서는 static resoruces 서빙을 webpack-dev-server을 통해 처리함.
> spring boot에서 랜더링하는 페이지 Template을 webpack-dev-server가 proxy해서 제공

 - `http://localhost:3002/` 로 접근하여 개발
 
