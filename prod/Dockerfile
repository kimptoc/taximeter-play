FROM taximeter-play

RUN sbt clean stage

ENV PATH $PROJECT_HOME/app/server/target/universal/stage/bin:$PATH

ENTRYPOINT server -Dplay.http.secret.key=0123456789
