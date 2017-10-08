Currently (?) running on https://taximeter.kimptoc.net/ (server and play browser version).
Can point the cordova version at this backend.

HOW TO RUN
- all locally, run the play app and the ./cordova apps.  Have to amend main.scala.html and cordova/www/index.html to refer to localhost:9000
- can run play app via docker on a server. See docker_build.sh and Dockerfile and prod/Dockerfile. Above links should be changed to refer to your server url
- then run the cordova app locally, changing cordova/www/index.html to point to your server.
- cannot run cordova app on server, as cli does not support it.  Should be a proper published app.

DONE
- TDD/implemented first/basic cut of taxi fare calc - learnt some of the quirks of it
- webservice to take in time/location updates and provide current fare
- Raw/ugly UI - gives ability to fake location for testing
- TravisCI linked, but fails for some reason - seems to be config related
- Docker-ised the app.
- made a start on some supporting model objects, eg Money, Tariff1, Location, LocationUpdate and TaxiFare
- tests/implement taxi fare calc, done using this forked library https://github.com/kimptoc/taxi-meter - assumed acceptable, given other 3rd party libs that are needed.
- PhoneGap/Cordova based app - done, via ScalaJS
- UI in Javascipt using browser GPS to track location, also a "manual option"

TODO 
- none :) 

LATER

- tests for webservice to take in time/location updates and provide current fare, not done as its a thin layer on the model.
-- some kind of session indicator so that can track multiple journeys (v2)
- tests for client - currently not that involved, but might need it later.
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
- the Play compile ScalaJS is manually copied in the Cordova area - ./cordova/www/js/client-fastopt.js 



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