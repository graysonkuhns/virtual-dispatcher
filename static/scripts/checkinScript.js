var pilots = [
    {
        f_name: "Jon",
        l_name: "Bockhorst",
        id: 1
    }, 
    {
        f_name: "Joe",
        l_name: "Smith",
        id: 34
    }, 
    {
        f_name: "Bob",
        l_name: "Larry",
        id: 47
    }];

$(document).ready(function(){
    var url = "/api/pilot"
    $.getJSON(url, function(data) { 
        data.foreach(function(pilot){
            pilots.push(pilot);
        });
    });
    pilots.sort();

    $("#name").on("input", function() {
        $("#searchList").empty();
        var name = this.value;
        if(name != ""){
            pilots.forEach(function(pilot){
                var fullName = pilot.f_name + " " + pilot.l_name;
                if(pilot.f_name.startsWith(name) || pilot.f_name.toLowerCase().startsWith(name) || 
                   pilot.l_name.startsWith(name) || pilot.l_name.toLowerCase().startsWith(name) || 
                   fullName.startsWith(name) || fullName.toLowerCase().startsWith(name)
                ){
                    $("#searchList").append("<div class='searchItem' data-id=" + pilot.id + ">" + pilot.f_name + " " + pilot.l_name + "</div>");
                }
            });
        }

        $(".searchItem").on("click", function() {
            $("#name").val($(this).html());
            $("#name").attr("data-id", $(this).attr("data-id"));
            $("#searchList").empty();
        });
    });

    $("#checkinForm").submit(function(event){
        var name = $("#name").val();
        var pilot_id = $("#name").attr("data-id");
        $("#name").val("");
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/api/availablities',
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
    });
});