
var planes = [];
var aircraftList = [];
var host = "";

window.addEventListener("load", start, false);

function start(){
    loadPlanes();
    loadWaitingList();
}

function loadPlanes(){
    planeTimer = setInterval(planeLoader, 1000);

    $("#planesList").on('mouseenter', '#maintenanceTrigger', function() {
        //Do not refresh page
        clearInterval(planeTimer);
    });

    $("#planesList").on('mouseleave', '#maintenanceTrigger', function() {
        //Start refreshing page again
        planeTimer = setInterval(planeLoader, 1000);
    });

    $("#planesList").on('change', '#maintenanceTrigger', function() {
        var planeId = $(this).attr("data-id");
        if($(this).is(':checked')){
            //Maintenance mode is now on
            var newPlane = '<div class = "plane"><div class="planeBox">';
            newPlane += '<div class="planeInfoBox" id="matienenceBox"><img class="infoImg" src="images/maintenance.png"/><div id="maintenance" class="infoText">Maintenance</div>';
            newPlane += '<form action="#" method="POST"><input type="checkbox" id="maintenanceTrigger" data-id="' + planeId + '" checked></form></div></div>';
            newPlane += '<img class="tailBottom" src="images/tail_maintenance.png"/><img class="tailTop" src="images/tail_top.png"/><div id="planeNumber">' + planeId + '</div></div>';
            aircraftList[planeId - 1] = newPlane;

            var htmlList = "";
            aircraftList.forEach(function(item){
                htmlList += item;
            });

            //Set the planes list html
            $("#planesList").html(htmlList);

            $.ajax({
                type: 'POST',
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                url: host + '/api/aircraft/' + planeId,
                data: JSON.stringify({
                    operational: false
                })
            });
        } else {
            //Maintenance mode is now off
            var newPlane = '<div class = "plane"><div class="planeBox">';
            newPlane += '<div class="planeInfoBox" id="matienenceBox"><img class="infoImg" src="images/maintenance.png"/><div id="maintenance" class="infoText">Maintenance</div>';
            newPlane += '<form action="#" method="POST"><input type="checkbox" id="maintenanceTrigger" data-id="' + planeId + '"></form></div></div>';
            newPlane += '<img class="tailBottom" src="images/tail_available.png"/><img class="tailTop" src="images/tail_top.png"/><div id="planeNumber">' + planeId + '</div></div>';
            aircraftList[planeId - 1] = newPlane;

            var htmlList = "";
            aircraftList.forEach(function(item){
                htmlList += item;
            });

            //Set the planes list html
            $("#planesList").html(htmlList);

            $.ajax({
                type: 'POST',
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                url: host + '/api/aircraft/' + planeId,
                data: JSON.stringify({
                    operational: true
                })
            });
        }
    });
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
                            var newList = '<div class = "pilot"><div class = "pilotBox"><div class = "pilotInfoBoxBig">';
                            newList += '<img class="infoImg" src="images/pilot.png"/> <div id="pilotName" class="bigInfoText">';
                            newList += name;
                            newList += '</div></div><div class = "pilotInfoBox"><img class="infoImg" src="images/time.png"/>';
                            newList += '<div id="waitTime" class="infoText">';
                            newList += 'Has been waiting for ' + timeDiff;
                            newList += '</div> </div></div></div>';

                            waitingList[counter++] = newList;
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

            waitingList = waitingList.slice(0, waitingPilots.length);

            var htmlList = "";
            waitingList.forEach(function(item){
                htmlList += item;
            });

            $("#pilotList").html(htmlList);
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

    //Take off last s if minute is 1
    if(minutesDiff == 1){
        timeDiff = timeDiff.substr(0, timeDiff.length - 1);
    }

    return timeDiff;
}

function getPilotName(id){
    for (let pilot of pilots) {
        if(pilot.id == id){
            return pilot.firstName + " " + pilot.lastName;
        }
    }
    // Default return case, reached if the pilot with given id was not found in pilots array
    return "Unknown Pilot";
}

function planeLoader(){
    var url = host + "/api/aircraft"; 
    $.getJSON(url, function(planes) {
        planes.forEach(function(plane) {
            //Check if plane is operational
            if(plane.operational){
                //Get flights
                var url = host + "/api/flights?aircraftId=" + plane.id + "&completed=false"; 
                $.getJSON(url, function(flight) {
                    //Get pilots
                    var url = host + "/api/pilots"; 
                    $.getJSON(url, function(pilots) {
                        var pilotName = "";

                        if(flight.length > 0){
                            //Find pilot with matching id
                            for(let p of pilots){
                                if(p.id == flight[0].pilotId){
                                    pilotName = p.firstName + " " + p.lastName;
                                    break;
                                }
                            }
                        }
                        
                        //Generate plane html code
                        var newPlane = '<div class="plane"><div class="planeBox">';
                        if(flight.length > 0){
                            newPlane += '<div class="planeInfoBox"><img class="infoImg" src="images/pilot.png"/><div class="infoText" id="pilotName">' + pilotName + '</div></div>';
                            newPlane += '<div class="planeInfoBox"><img class="infoImg" src="images/zone.png"/><div class="infoText" id="zone">Zone ' + flight[0].zoneId + '</div></div>';
                        } else {
                            newPlane += '<div class="planeInfoBox';
                            newPlane += '" id="maintenanceBox"><img class="infoImg" src="images/maintenance.png"/><div id="maintenance" class="infoText">Maintenance</div>';
                            newPlane += '<form action="#" method="POST"><input type="checkbox" id="maintenanceTrigger" data-id="' + plane.id + '"></form></div>';
                        }

                        if(flight.length > 0){
                            if(flight[0].started){
                                newPlane += '<div class="planeInfoBox"><img class="infoImg" src="images/status.png"/><div class="infoText">In the air</div></div>';
                            } else {
                                newPlane += '<div class="planeInfoBox"><img class="infoImg" src="images/status.png"/><div class="infoText">On the ground</div></div>';
                            }
                        }

                        newPlane += '</div><img class="tailBottom" src="images/';

                        if(flight.length > 0){
                            //The plane is in use
                            newPlane += 'tail_inuse';
                        } else {
                            //The plane is available
                            newPlane += 'tail_available';
                        }
                        newPlane += '.png"/><img class="tailTop" src="images/tail_top.png"/><div id="planeNumber">' + plane.id + '</div></div>';
                        aircraftList[plane.id - 1] = newPlane;
                        
                        var htmlList = "";
                        aircraftList.forEach(function(item){
                            htmlList += item;
                        });

                        //Set the planes list html
                        $("#planesList").html(htmlList);
                    });
                });
            } else {
                //Create plane that needs maintenance
                var newPlane = '<div class = "plane"><div class="planeBox">';
                newPlane += '<div class="planeInfoBox" id="matienenceBox"><img class="infoImg" src="images/maintenance.png"/><div id="maintenance" class="infoText">Maintenance</div>';
                newPlane += '<form action="#" method="POST"><input type="checkbox" id="maintenanceTrigger" data-id="' + plane.id + '" checked></form></div></div>';
                newPlane += '<img class="tailBottom" src="images/tail_maintenance.png"/><img class="tailTop" src="images/tail_top.png"/><div id="planeNumber">' + plane.id + '</div></div>';
                
                aircraftList[plane.id - 1] =  newPlane;

                var htmlList = "";
                aircraftList.forEach(function(item){
                    htmlList += item;
                });

                $("#planesList").html(htmlList);
            }
        });
    });
}

$(document).ready(function(){
    $("#toolTipTable").hide();
    $("#toolTipTable").removeClass("hidden");

    $("#toolTipImg").on("mouseenter", function(){
        $("#toolTipTable").fadeIn("fast");
    });

    $("#toolTipTable").on("mouseleave", function(){
        $("#toolTipTable").fadeOut("fast");
    });

    $("#toolTipImg").on("mouseleave", function(event){
        //Dont hide if leaving up
        if(event.pageY > $("#toolTipImg").offset().top){
            $("#toolTipTable").fadeOut("fast");
        }
    });
});