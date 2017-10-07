// very basic bit of javascript to get things going.
// subscribe to geolocation API, but does not use it for now
// should wrap this in a closure but does not add any value so far

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

function handleLocation(latitude, longitude)
{
    console.log("Got location:"+latitude+","+longitude);
}

