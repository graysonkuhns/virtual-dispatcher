var pilots = ["Jon Bockhorst", "Joe Smith", "Tom Gerry"];

$(document).ready(function(){
    // var url = "/api/pilot"
    // $.getJSON(url, function(data) { 
    //     data.foreach(function(pilot){
    //         pilots.push(pilot.f_name + " " + pilot.l_name);
    //     });
    // });
    pilots.sort();

    $("#name").on("input", function() {
        $("#searchList").empty();
        var name = this.value;
        if(name != ""){
            pilots.forEach(function(pilot){
                if(pilot.startsWith(name) || pilot.toLowerCase().startsWith(name)){
                    $("#searchList").append("<div class='searchItem'>" + pilot + "</div>");
                }
            });
        }

        $(".searchItem").on("click", function() {
            $("#name").val($(this).html());
            $("#searchList").empty();
        });
    });

    $("#checkinForm").submit(function(event){
        var name = $("#name").val();
        $("#name").val("");
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/api/availablities',
            data: {
                id: name
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