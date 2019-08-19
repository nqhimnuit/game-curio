# game-curio
JavaEE 7 playground

### build and deploy:

$ gw clean ass && yes | cp build/libs/game-curio.war wildfly-17.0.1.Final/standalone/deployments/

### stop container:

$ wildfly-17.0.1.Final/bin/jboss-cli.sh --connect --command=:shutdown
