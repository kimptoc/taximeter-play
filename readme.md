DONE
- TDD/implemented first/basic cut of taxi fare calc - learnt some of the quirks of it
- webservice to take in time/location updates and provide current fare
- Raw/ugly UI - gives ability to fake location for testing
- TravisCI linked, but fails for some reason - seems to be config related
- Docker-ised the app.
- made a start on some supporting model objects, eg Money, Tariff1, Location, LocationUpdate and TaxiFare

TODO 

- tests/implement taxi fare calc
- tests for webservice to take in time/location updates and provide current fare
-- some kind of session indicator so that can track multiple journeys (v2)
- UI in Javascipt using browser GPS to track location
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

