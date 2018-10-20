var pilots;

$(document).ready(function(){
    //Check for new data every second
    setInterval(function(){
        $("#flightList").empty();

        //Get all of the pilots
        var url = "/api/pilot"
        $.getJSON(url, function(data) { 
            data.foreach(function(pilot){
                pilots.push(pilot);
            });
        });

        //When new planes have been assigned
        var url = "/api/flights"
        $.getJSON(url, function(data) { 
            data.foreach(function(flight){
                if(!flight.started){
                    //Get pilot name of that flight
                    var name = getPilotName(flight.pilot_id);

                    //Add the flight to the waiting room board
                    $("#flightList").append('<div class="listItem"><p>' + name + ' can now fly Aircraft ' + flight.aircraft_id + '</p></div>');
                }
            });
        });
    }, 1000);
});

function getPilotName(id){
    pilots.foreach(function(p){
        if(p.id == id){
            return p.f_name + " " + p.l_name;
        }
    });
}