
var planes = []

var waitingPilots = []

var host = "http://code-a-thon.xellitix.com:8080";

window.addEventListener("load", start, false);

function start(){
    loadPlanes();
    loadWaitingList();
}

function loadPlanes(){
    var url = host + "/api/aircraft"; 
    $.getJSON(url, function(data) {
        planes = data;
    });

    setInterval(function(){
        var aircraftList = "";

        planes.forEach(function(plane) {
            var url = host + "/api/flights?aircraftId=" + plane.id + "&completed=false"; 
            $.getJSON(url, function(flight) {
                if(flight.length > 0){
                    planes.push(flight[0]);
                    aircraftList += '<div class="plane" data-id="' + plane.id + '">Plane ' + plane.id + ' <br><p class="detailInfo">Jon Smith Zone 6 Due back: 2:00 PM (17 Mins)</p></div>';
                } else {
                    aircraftList += '<div class="plane availablePlane" data-id="' + plane.id + '">Plane ' + plane.id + ' <br><p class="detailInfo">Available</p></div>';
                }
            });
        });

        //Set the planes list html
        $("#planesList").html(aircraftList);
    }, 1000);
}

function loadWaitingList(){
    setInterval(function(){
        var url = host + "/api/availablility"; 
        $.getJSON(url, function(data) {
            waitingPilots = data;
        });

        var waitList = "";

        waitingPilots.forEach(function(pilot){
            var timeDiff = getTimeDiff(pilot.checkinTime);        

            waitList += '<div class="waitingPilot">' + pilot.name + '<br><p class="detailInfo">Has been waiting for '+ timeDiff + '</p></div>';
        });
        
        $("#pilotList").html(waitList);
    }, 1000);
}

function getTimeDiff(oldTime){
    var time = new Date();

    var timeComponents = oldTime.split(":");

    var currentMinutes = time.getHours() * 60 + time.getMinutes();
    var checkinMinutes = (timeComponents[0] * 60) + parseInt(timeComponents[1]);

    var minutesDiff = (currentMinutes - checkinMinutes);
    hoursDiff = Math.floor(minutesDiff / 60);

    minutesDiff = minutesDiff % 60;

    var timeDiff;
    
    if(hoursDiff == 0){
        timeDiff = minutesDiff + " minutes";
    } else {
        timeDiff = hoursDiff + " hours and " + minutesDiff + " minutes";
    }

    return timeDiff;
}