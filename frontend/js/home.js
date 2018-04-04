$(document).ready(function() {
    $(".images").addClass("blurImage");
    
   $("#buy").mouseenter(function() {
       $(this).addClass("hoverProp");
       $("#image1").removeClass("blurImage");
   });
    $("#buy").mouseleave(function() {
       $(this).removeClass("hoverProp");
        $("#image1").addClass("blurImage");
   });
    
    $("#sell").mouseenter(function() {
       $(this).addClass("hoverProp");
        $("#image2").removeClass("blurImage");
   });
    $("#sell").mouseleave(function() {
       $(this).removeClass("hoverProp");
        $("#image2").addClass("blurImage");
   });
});