FROM taximeter-play

RUN sbt clean stage

# TODO copy prod js file(s) to cordova directory
# but via host file system - as needs to be in different docker container, one with different ancestry
#RUN cp ./client/target/scala-2.12/client-opt.js cordova/www/js/client-fastopt.js

ENV PATH $PROJECT_HOME/app/server/target/universal/stage/bin:$PATH

ENTRYPOINT server -Dplay.http.secret.key=0123456789
