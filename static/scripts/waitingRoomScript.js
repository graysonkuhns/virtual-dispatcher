var pilots = [];

var host = "http://code-a-thon.xellitix.com:8080";

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
                    flightDivs += ('<div class="listItem"><p>' + name + ' can now fly Aircraft ' + flight.aircraftId + ' in zone 1</p></div>');
                });
                
                // Set the flightList div html equal to the generated flightDivs string
                $("#flightList").html(flightDivs);
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