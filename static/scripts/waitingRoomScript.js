// Currently filled with test data
var pilots = [
    {id:"1", f_name:"Jon", l_name:"Doe"}, 
    {id:"2", f_name:"Jack", l_name:"Blorb"},
    {id:"3", f_name:"Maggy", l_name:"Sue"}
];
/*var flights = [
    {id:"43", completed:"false", aircraft_id:"3", pilot_id:"1", zone_id:"2", started:"false"},
    {id:"44", completed:"true", aircraft_id:"2", pilot_id:"3", zone_id:"1", started:"true"}
];*/

var host = "http://code-a-thon.xellitix.com:8080";

$(document).ready(function(){
    //Check for new data every second
    setInterval(function(){
        $("#flightList").empty();

        
        
        
        
        
        
        //Get all of the pilots
        /*var url = "/api/pilot"
        $.getJSON(host + url, function(rData) { 
            rData.foreach(function(pilot){
                pilots.push(pilot);
            });
        });*/

        //When new planes have been assigned
        var url = "/api/flights"
        $.getJSON(host + url, function(rData) { 
            
            $(rData).each(function(index, flight){
                // Convert the flight.started string to a boolean
                var started = (flight.started == 'true');
                // If the flight hasn't started
                if(!started){
                    //Get pilot name of that flight
                    console.log( getPilotName(flight.pilot_id) );
                    var name = getPilotName(flight.pilot_id);
                    //Add the flight to the waiting room board
                    $("#flightList").append('<div class="listItem"><p>' + name + ' can now fly Aircraft ' + flight.aircraft_id + '</p></div>');
                }
            });
            
            /*data.foreach(function(flight){
                if(!flight.started){
                    //Get pilot name of that flight
                    var name = getPilotName(flight.pilot_id);

                    //Add the flight to the waiting room board
                    $("#flightList").append('<div class="listItem"><p>' + name + ' can now fly Aircraft ' + flight.aircraft_id + '</p></div>');
                }
            });*/
            
        });
    }, 1000);
    
    
    
});

function getPilotName(id){
    for (var i = 0; i < pilots.length; i++) {
        if(pilots[i].id == id){
            return pilots[i].f_name + " " + pilots[i].l_name;
        }
    }
    // Default return case reached if the pilot was not returned by the query of the database
    return "Unknown Pilot";
}