var pilots = [];

var availablilty = [];

var host = "http://code-a-thon.xellitix.com:8080";

$(document).ready(function(){
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

                    //Set button if checking in or out
                    setAction(pilot.id);
                    
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

            //Set button if checking in or out
            setAction(id);
        });
    });

    //When checkin/out button is clicked
    $("#checkinForm").submit(function(event){
        event.preventDefault();

        //Get name and pilot_id
        var name = $("#name").val();
        var pilot_id = $("#name").attr("data-id");
        $("#name").val("");

        //Get checkin or checkout
        var type = $("#checkin").attr("name");

        if(type == "checkin"){
            //Check in post request
            availablilty.push(pilot_id);

            $.ajax({
                type: 'POST',
                url: host + '/api/availablities',
                data: {
                    id: pilot_id
                },
                success:function(){
                    var good = true;
                    //Test if good message
                    if(good){
                        //Try to schedule flight
                        $(".message").html("You have checked in successfully");
                        $(".message").attr("id", "goodMessage");
                    } else {
                        $(".message").html("Your name was not found in the system");
                        $(".message").attr("id", "badMessage");
                    }
                    //Check if plane is available
                    //Create flight and give info if there is
                    //If not, put on waiting list
                    //Set message id for good or bad message

                    //Fade out message after 10 seconds
                    setTimeout(function(){
                        $(".message p").fadeOut("slow", function () {
                            $(".message p").remove();
                        });
                        
                    }, 10000);
                }
            });
        } else {
            //Check out delete request
            remove(pilot_id);

            $.ajax({
                type: 'DELETE',
                url: host + '/api/availablities',
                data: {
                    id: pilot_id
                },
                success:function(){
                    var good = true;
                    //Test if good message
                    if(good){
                        //Try to schedule flight
                        $(".message").html("You have checked out successfully");
                        $(".message").attr("id", "goodMessage");
                    } else {
                        $(".message").html("Your name was not found in the system");
                        $(".message").attr("id", "badMessage");
                    }

                    //Fade out message after 10 seconds
                    setTimeout(function(){
                        $(".message p").fadeOut("slow", function () {
                            $(".message p").remove();
                        });
                        
                    }, 10000);
                }
            });
        }

        //Reset checkin/out button
        $("#checkin").attr("name", "checkin/out");
        $("#checkin").val("Enter a valid name");
        $("#checkin").attr("disabled", true);
    });
});

function setAction(id){
    var action = getAction(id);
    $("#checkin").attr("name", action);

    if(action == "checkin"){
        $("#checkin").val("Check In");
    } else {
        $("#checkin").val("Check Out");
    }

    $("#checkin").attr("disabled", false);
}

function getAction(id){
    for(let pilot of availablilty){
        if(pilot == id){
            return "checkout";
        }
    }

    return "checkin";
}

function remove(val){
    var index = availablilty.indexOf(val);
 
    if (index > -1) {
       availablilty.splice(index, 1);
    }
}