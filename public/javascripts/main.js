console.log("main js loaded");

if ("geolocation" in navigator) {
    /* geolocation is available */
    navigator.geolocation.getCurrentPosition(function(position) {
        handleLocation(position.coords.latitude, position.coords.longitude);
    });
    var watchID = navigator.geolocation.watchPosition(function(position) {
        handleLocation(position.coords.latitude, position.coords.longitude);
    });
} else {
    console.log("GeoLocation is not available - use the buttons to fake it")
}

document.getElementById("start1").addEventListener("click", startNewJourney);
document.getElementById("move1").addEventListener("click", updateLocation);

function handleLocation(latitude, longitude)
{
    console.log("Got location:"+latitude+","+longitude);
}

function startNewJourney()
{
    console.log("start new journey");
    latitude = document.getElementById("latitude").value;
    longitude = document.getElementById("longitude").value;
    ajax.get('start_journey/'+latitude+'/'+longitude, {}, function(result) {
        result = JSON.parse(result);
        console.log("callback?", result);
        document.getElementById("fare").value = result.fare;
    });

}

function updateLocation()
{
    console.log("updating location")
    latitude = document.getElementById("latitude").value;
    longitude = document.getElementById("longitude").value;
    ajax.get('location_update/'+latitude+'/'+longitude, {}, function(result) {
        result = JSON.parse(result);
        console.log("callback?", result);
        document.getElementById("fare").value = result.fare;
    });
}