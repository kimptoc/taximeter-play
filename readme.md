TODO 

- tests/implement taxi fare calc
- tests/webservice to take in time/location updates and provide current fare
-- some kind of session indicator so that can track multiple journeys (v2)
- UI in Javascipt using browser GPS to track location
- Ability to fake time/location for testing
- PhoneGap/Cordova based app - as this is just more javascript, plan to leave this or now.
- CI/CD
-- CI/travis unit test builds
-- CD/auto-deploy all checkins to running server

UI
- 2 modes
  - pure GPS - start journey, live fare updates, stop journey
  - manual - start, update with random move, show current fare, stop journey


PREP

- learn/read/watch Scala/Play material
- build small app - https://github.com/kimptoc/pinboard-play


DOCKER NOTES

To run this via Docker:

First build it.
$ docker build -t taximeter-play .

Then run it.
$ docker run --rm --name taximeter-play -i -p 9000:9000 taximeter-play sbt run

setup prod image
$ sudo docker build -t taximeter-play-prod prod

run prod image
$ sudo docker run --rm --name taximeter-play -i -p 9000:9000 taximeter-play-prod 

