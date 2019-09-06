# game-curio
JavaEE 7 playground

### build and deploy:

```
$ gw clean ass && yes | cp build/libs/game-curio.war wildfly-17.0.1.Final/standalone/deployments/
```

### stop container:

```
$ wildfly-17.0.1.Final/bin/jboss-cli.sh --connect --command=:shutdown
```

### test CurioServlet with curl:

```
$ curl -k -s -o /null -X GET http://localhost:8080/game-curio/curio
$ curl -k -s -o /null -X POST http://localhost:8080/game-curio/curio -d '{"line1": "test"}'

$ curl -X GET http://localhost:8080/game-curio/get-game
$ curl -X DELETE 'http://localhost:8080/game-curio/delete-game?id=8'
$ curl -X PUT 'http://localhost:8080/game-curio/insert-game?title=Batminh&description=this+is+a+game+of+minh&price=1000'
$ curl -X POST 'http://localhost:8080/game-curio/update-game?id=6&title=My+Game&description=my+description&price=888.8'

$ curl -H 'Accept-Charset: utf-8' -X GET 'http://localhost:8080/game-curio/game?id=3' -v
```

Notes:
- if you want to put multiple parameters in cURL request, put the url in single quote (') otherwise it won't work
e.g.:
```
$ curl -k -s -o /null -X POST 'http://localhost:8080/game-curio/update-game?id=6&title=My+Game&description=my+description&price=888.8'
```
- UTF-8 vs ISO-8859-1

Try to fix: UT015005: Error invoking method contextDestroyed on listener class com.sun.faces.config.ConfigureListener: java.lang.OutOfMemoryError: Metaspace
add this option to JAVA_OPTS in $JBOSS_HOME/bin/standalone.conf
```
-Dcom.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize=true
```
