
var planes = [
    {"id":"1", "status":"Available"}, 
    {"id":"2", "status":"In Flight"},
    {"id":"3", "status":"In Flight"}
]

var waitingPilots = [
    {"name":"Joe", "checkinTime":"22:00:12"}, 
    {"name":"Bob", "checkinTime":"22:20:00"},
    {"name":"Tim", "checkinTime":"22:40:00"}
]

window.addEventListener("load", start, false);

function start(){
    loadPlanes();
    loadWaitingList();
}

function loadPlanes(){
    var planeList = document.getElementById("planeInfo");
    planes.forEach(function(plane){
        if(plane.status == "Available"){
            planeList.innerHTML += '<div class="plane availablePlane">Plane ' + plane.id + ' <br><p class="detailInfo">Available</p></div>';
        } else {
            planeList.innerHTML += '<div class="plane">Plane ' + plane.id + ' <br><p class="detailInfo">Due back: 2:00 PM (17 Mins)</p></div>';
        }
        
    });
}

function loadWaitingList(){
    var watingList = document.getElementById("waitingList");
    waitingPilots.forEach(function(pilot){
        var timeDiff = getTimeDiff(pilot.checkinTime);        

        watingList.innerHTML += '<div class="waitingPilot">' + pilot.name + '<br><p class="detailInfo">Has been waiting for '+timeDiff+'</p></div>';
    });
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