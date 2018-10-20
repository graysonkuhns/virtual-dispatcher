var pilots = [];

var host = "http://code-a-thon.xellitix.com:8080";

$(document).ready(function(){
    //Check for new data every second
    setInterval(function(){
        
        //Get all of the pilots
        var url = "/api/pilots";
        $.getJSON(host + url, function(rData) {
            var newPilots = [];
            $(rData).each(function(index, pilot){
                newPilots.push(pilot);
            });
            pilots = newPilots;
        });

        //URL query for flights that have not started
        var url = "/api/flights?started=false&completed=false";
        $.getJSON(host + url, function(rData) { 
            
            var flightDivs = "";
            
            $(rData).each(function(index, flight){
                //Get pilot name of that flight
                var name = getPilotName(flight.pilotId);
                //Add the flight to the waiting room board
                flightDivs += ('<div class="listItem" data-id=""><p>' + name + ' can now fly Aircraft ' + flight.aircraftId + ' in zone 1</p></div>');
            });
            
            // Set the flightList div html equal to the generated flightDivs string
            $("#flightList").html(flightDivs);
            
        });
    }, 1000);
    
    
    
});

// Precondition: The pilots array has been filled with pilot data
// Postcondition: Returns the name of the pilot with the given pilotId
function getPilotName(id){
    for (var i = 0; i < pilots.length; i++) {
        if(pilots[i].id == id){
            return pilots[i].firstName + " " + pilots[i].lastName;
        }
    }
    // Default return case, reached if the pilot with given id was not found in pilots array
    return "Unknown Pilot";
}

// CURRENTLY UNUSED/UNFINISHED FUNCTION
// Returns true if arr1 and arr2 contain identical JSON data
// Time Complexity: arr1.length * arr2.length (roughly N^2)
/*function areEqualJSONArrays(arr1, arr2) {
    // If the arrays are of different length, not equal
    if (arr1.length !== arr2.length)
        return false;
    
    for (var i = 0; i < arr1.length; i++) {
        // if this JSON object's key number doesn't match the same object in arr2, not equal
        if (Object.keys(arr1[i]).length !== Object.keys(arr2[i]).length)
            return false;
        
        // For
    }
}*/