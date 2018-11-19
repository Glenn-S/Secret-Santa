$( document ).ready(function() {
    console.log( "ready!" );
    hideAllSantas();
});

function hideAllSantas() {
    $('.santa').css('visibility', 'hidden');
}

function showSanta(id) {
    console.log("glenn smells like pooop!");
    $('.santa').css('visibility', 'hidden');
    $("#"+id+"santa").css('visibility', 'visible');
}