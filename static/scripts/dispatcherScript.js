
var planes = [];

var host = "http://code-a-thon.xellitix.com:8080";

window.addEventListener("load", start, false);

function start(){
    loadPlanes();
    
    loadWaitingList();
}

function loadPlanes(){
    var aircraftList = [];
    setInterval(function(){
        var url = host + "/api/aircraft"; 
        $.getJSON(url, function(planes) {
            planes.forEach(function(plane) {
                //Check if plane is operational
                if(plane.operational){
                    var url = host + "/api/flights?aircraftId=" + plane.id + "&completed=false"; 
                    $.getJSON(url, function(flight) {
                        if(flight.length > 0){
                            aircraftList[plane.id - 1] = '<div class="plane inUsePlane">Plane ' + plane.id + ' <br><p class="detailInfo">In use by Joe Smith</p></div>';
                        } else {
                            aircraftList[plane.id - 1] = '<div class="plane availablePlane">Plane ' + plane.id + ' <br><p class="detailInfo">Available</p></div>';
                        }

                        
                        var htmlList = "";
                        aircraftList.forEach(function(item){
                            htmlList += item;
                        });

                        //Set the planes list html
                        $("#planesList").html(htmlList);
                    });
                } else {
                    aircraftList[plane.id - 1] = '<div class="plane maintenancePlane">Plane ' + plane.id + ' <br><p class="detailInfo">Needs Maintenance</p></div>';

                    var htmlList = "";
                    aircraftList.forEach(function(item){
                        htmlList += item;
                    });

                    $("#planesList").html(htmlList);
                }
            });
        });
    }, 1000);
}

function loadWaitingList(){
    var waitingList = [];
    
    setInterval(function(){
        var url = host + "/api/availability"; 
        $.getJSON(url, function(waitingPilots) {
            waitingPilots.sort(function(a, b) {
                return a.timeCreated - b.timeCreated;
            });

            var waitList = "";

            var counter = 0;
            waitingPilots.forEach(function(pilot){
                var timeDiff = getTimeDiff(pilot.timeCreated); 
                
                var url = host + "/api/pilots"; 
                $.getJSON(url, function(p) {
                    for(let pilotInfo of p){
                        if(pilotInfo.id == pilot.pilotId){
                            var name = pilotInfo.firstName + " " + pilotInfo.lastName;
                            waitingList[counter++] = '<div class="waitingPilot">' + name + '<br><p class="detailInfo">Has been waiting for '+ timeDiff + '</p></div>';
                            break;
                        }
                    }

                    var htmlList = "";
                    waitingList.forEach(function(item){
                        htmlList += item;
                    });

                    $("#pilotList").html(htmlList);
                });
            });
        });
    }, 1000);
}

function getTimeDiff(oldTime){
    var time = Date.now();
    var timeOffset = (new Date).getTimezoneOffset() * 60 * 1000;
    time -= timeOffset;

    var timeDiff;
    var hoursDiff = 0;

    var millsDiff = time - oldTime;
    var secondsDiff = millsDiff / 1000;
    var minutesDiff = Math.floor(secondsDiff / 60) % 60;
    var hoursDiff = Math.floor(secondsDiff / 60 / 60);
    
    if(hoursDiff == 0){
        timeDiff = minutesDiff + " minutes";
    } else {
        timeDiff = hoursDiff + " hours and " + minutesDiff + " minutes";
    }

    return timeDiff;
}