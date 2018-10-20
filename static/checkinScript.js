window.addEventListener("load", start, false);

function start(){
    
}

$(document).ready(function(){
    $("#checkinForm").submit(function(event){
        var name = $("name").val();
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: '/api/availablities',
            data: {
                id: name
            },
            error:function(){
                $("message").append("hi");
            }
        });
    });
});