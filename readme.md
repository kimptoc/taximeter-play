DONE
- TDD/implemented first/basic cut of taxi fare calc - learnt some of the quirks of it
- webservice to take in time/location updates and provide current fare
- Raw/ugly UI - gives ability to fake location for testing
- TravisCI linked, but fails for some reason - seems to be config related
- Docker-ised the app.
- made a start on some supporting model objects, eg Money, Tariff1, Location, LocationUpdate and TaxiFare
- tests/implement taxi fare calc, done using this forked library https://github.com/kimptoc/taxi-meter

TODO 

- PhoneGap/Cordova based app - as this is just more javascript, plan to leave this or now.
- UI in Javascipt using browser GPS to track location

LATER

- tests for webservice to take in time/location updates and provide current fare
-- some kind of session indicator so that can track multiple journeys (v2)
- CI/CD
-- CI/travis unit test builds
-- CD/auto-deploy all checkins to running server

UI
- 2 modes
  - pure GPS - start journey, live fare updates, stop journey, location/time/fare updating over time
  - manual - start, update with random move/buttons for London locations, show time/fare live updating over time, stop journey




PREP

- learn/read/watch Scala/Play material
- build small app - https://github.com/kimptoc/pinboard-play


SCALA JS NOTES
- want to have ScalaJS code for Phonegap - so compile it to dedicated js file
- want to have browser ScalaJS code in Play - so compile to another dedicated js file
- want to have shared ScalaJS code - build into shared.js file, and also build for JVM
- and also the server side Scala code, on the JVM
 



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

KNOWN GLITCHES
- elapsed still ticks over after journey marked as ended
- switched from GET to POST for endpoints, but causes some problems
- CSRF - restricts access to POST requests disabled this, need to review if ok. Perhaps in a secured setup, eg OAuth
- CORS - cordova client worked with previous setup, but failing - maybe due to switch from GET to POST. But ok with server based setup :()