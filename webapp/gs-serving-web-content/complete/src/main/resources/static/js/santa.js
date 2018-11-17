$( document ).ready(function() {
    console.log( "ready!" );
    $('.santa').hide();
});

function showSanta(id) {
    console.log("glenn smells like pooop!");
    $("#"+id+"santa").toggle();
}