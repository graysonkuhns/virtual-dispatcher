var pilots = [];
var lastName = "";
var currentName = "";

var host = "";

$(document).ready(function(){
    //Check for new data every second
    setInterval(function(){
        
        //Get all of the pilots
        var url = "/api/pilots";
        $.getJSON(host + url, function(data) {
            pilots = data;

            //URL query for flights that have not started
            var url = host + "/api/flights?started=false&completed=false";
            $.getJSON(url, function(rData) { 
                var flightDivs = "";
                
                rData.forEach(function(flight){
                    //Get pilot name of that flight
                    var name = getPilotName(flight.pilotId);
                    //Add the flight to the waiting room board
                    if(flight == rData[rData.length - 1]){
                        currentName = name;
                        flightDivs = ('<div class="listItem" id ="last"><p>' + name + ' can now fly Aircraft ' + flight.aircraftId + ' in Zone ' + flight.zoneId + '</p></div>') + flightDivs;
                    } else {
                        flightDivs = ('<div class="listItem"><p>' + name + ' can now fly Aircraft ' + flight.aircraftId + ' in Zone ' + flight.zoneId + '</p></div>') + flightDivs;
                    }
                });

                // Set the flightList div html equal to the generated flightDivs string
                $("#flightList").html(flightDivs);

                if(currentName != lastName){
                    $("#last").effect('highlight', {color: "rgb(150, 0, 0)"}, 1000);
                }
                
                lastName = currentName;
            });
        });
    }, 1000);

    
});

// Precondition: The pilots array has been filled with pilot data
// Postcondition: Returns the name of the pilot with the given pilotId
function getPilotName(id){
    for (let pilot of pilots) {
        if(pilot.id == id){
            return pilot.firstName + " " + pilot.lastName;
        }
    }
    // Default return case, reached if the pilot with given id was not found in pilots array
    return "Unknown Pilot";
}