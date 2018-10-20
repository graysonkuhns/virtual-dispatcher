var pilots = [];
var pilotName = "";
var pilotId = "";

var host = "http://code-a-thon.xellitix.com:8080";

$(document).ready(function(){
    var loggedIn  = false;

    //Show correct page based on login status
    if(!loggedIn){
        showLogin()
    } else {
        showFlight();
    }

    var url = host + "/api/pilots"
    $.getJSON(url, function(data) { 
        data.forEach(function(pilot){
            pilots.push(pilot);
        });
    });

    //When name box is typed into
    $("#name").on("input", function() {
        $("#searchList").empty();
        var name = this.value;
        if(name != ""){
            pilots.forEach(function(pilot){
                //Check if full name is equal to searched
                //Check if only part of searched name is found
                var fullName = pilot.firstName + " " + pilot.lastName;
                if(fullName.toLowerCase() == name.toLowerCase()){
                    $("#name").val(fullName);
                    $("#name").attr("data-id", pilot.id);
                } else if(pilot.firstName.startsWith(name) || pilot.firstName.toLowerCase().startsWith(name.toLowerCase()) || 
                   fullName.startsWith(name) || fullName.toLowerCase().startsWith(name.toLowerCase())
                ){
                    $("#searchList").append("<div class='searchItem' data-id=" + pilot.id + ">" + pilot.firstName + " " + pilot.lastName + "</div>");
                }
            });

            //Check last names at the end
            pilots.forEach(function(pilot){
                if(pilot.lastName.startsWith(name) || pilot.lastName.toLowerCase().startsWith(name.toLowerCase())){
                    $("#searchList").append("<div class='searchItem' data-id=" + pilot.id + ">" + pilot.firstName + " " + pilot.lastName + "</div>");
                }
            });
        }

        $(".searchItem").on("click", function() {
            $("#name").val($(this).html());
            var id = $(this).attr("data-id");
            $("#name").attr("data-id", id);
            $("#searchList").empty();
        });
    });

    //When checkin/out button is clicked
    $("#loginForm").submit(function(event){
        event.preventDefault();

        //Get name and pilot_id
        var name = $("#name").val();
        var pilot_id = $("#name").attr("data-id");
        $("#name").val("");

        pilotName = name;
        pilotId = pilot_id;
        showFlight();
    });

    function showLogin(){
        $("#loginView").addClass("visible");
        $("#loginView").removeClass("hidden");
        $("#flightView").addClass("hidden");
        $("#flightView").removeClass("visible");
    }

    function refreshStatus(){
        var currentFlight;

        var url = host + "/api/flights"
        $.getJSON(url, function(flightList) { 
            for(let flight of flightList){
                if(flight.pilotId == pilotId && !flight.completeted){
                    currentFlight = flight;
                    break;
                }
            }
        });

        $("#pilotName").html(pilotName);
        $("#flightNumber").html("Flight# " + currentFlight.id);
        $("#aircraftNumber").html("Plane " + currentFlight.aircraftId);

        if(currentFlight.started)

        setInterval(function(){
            $("#pilotName").html(pilotName);
        }, 1000);
    }
    
    function showFlight(){
        refreshStatus();
        $("#loginView").addClass("hidden");
        $("#loginView").removeClass("visible");
        $("#flightView").addClass("visible");
        $("#flightView").removeClass("hidden");
    }
});

