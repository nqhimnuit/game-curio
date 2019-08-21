# game-curio
JavaEE 7 playground

### build and deploy:

`$ gw clean ass && yes | cp build/libs/game-curio.war wildfly-17.0.1.Final/standalone/deployments/`

### stop container:

`$ wildfly-17.0.1.Final/bin/jboss-cli.sh --connect --command=:shutdown`

### test CurioServlet with curl:

```
$ curl -k -s -o -X GET /null http://localhost:8080/game-curio/curio
$ curl -k -s -o -X POST /null http://localhost:8080/game-curio/curio -d '{"line1": "test"}'
```
