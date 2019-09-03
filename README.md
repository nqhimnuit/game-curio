# game-curio
JavaEE 7 playground

### build and deploy:

`$ gw clean ass && yes | cp build/libs/game-curio.war wildfly-17.0.1.Final/standalone/deployments/`

### stop container:

`$ wildfly-17.0.1.Final/bin/jboss-cli.sh --connect --command=:shutdown`

### test CurioServlet with curl:

```
$ curl -k -s -o /null -X GET http://localhost:8080/game-curio/curio
$ curl -k -s -o /null -X POST http://localhost:8080/game-curio/curio -d '{"line1": "test"}'
```

Note: if you want to put multiple parameters in cURL request, put the url in single quote (') otherwise it won't work
e.g.:
```
$ curl -k -s -o /null -X POST 'http://localhost:8080/game-curio/update-game?id=6&title=My+Game&description=my+description&price=888.8'
```