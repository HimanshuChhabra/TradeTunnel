 $(document).ready(function() {
    $("#firstName").hide();
    $("#lastName").hide();
    $("#confirmPassword").hide();
    $("#submitSignup").hide();
    $("#back").hide();


    $("#register").click(function(){
        $("#signin").toggle();
        $("#firstName").toggle();
        $("#lastName").toggle();
        $("#confirmPassword").toggle();
        $("#submitSignup").toggle();
        $("#back").toggle();
        $("#register").toggle();
        $("#myModalLabel").text("Register");
    });

    $("#back").click(function() {
        $("#firstName").toggle();
        $("#lastName").toggle();
        $("#confirmPassword").toggle();
        $("#submitSignup").toggle();
        $("#back").toggle();
        $("#signin").toggle();
        $("#register").toggle();
        $("#myModalLabel").text("Login");
    });
});