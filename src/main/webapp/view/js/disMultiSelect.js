$(document).ready(function(){
    var cityStart = $("select[name=departureCity]");
    var cityEnd = $("select[name=destinationCity]");
    var pilotStart = $("select[name=firstPilot]");
    var pilotEnd = $("select[name=secondPilot]");
    var stewardessStart = $("select[name=firstStewardess]");
    var stewardessEnd = $("select[name=secondStewardess]");

     $(cityStart).on("change", function(){
        DisableAnalogueOption(cityStart, cityEnd);
    });
    $(cityEnd).on("change", function(){
        DisableAnalogueOption(cityEnd, cityStart);
    });
    $(pilotStart).on("change", function(){
        DisableAnalogueOption(pilotStart, pilotEnd);
    });
    $(pilotEnd).on("change", function(){
        DisableAnalogueOption(pilotEnd, pilotStart);
    });
    $(stewardessStart).on("change", function(){
        DisableAnalogueOption(stewardessStart, stewardessEnd);
    });
    $(stewardessEnd).on("change", function(){
        DisableAnalogueOption(stewardessEnd, stewardessStart);
    });
})


function DisableAnalogueOption(object, target){
    $(target).find("option[disabled=disabled][value]").removeAttr("disabled");

    var selected = $(object).find(":selected");
    if(selected.lenght == 0){
        return;
    }

    var optionToDisable = $(target).find("option[value=\u0022"+ selected.val() +"\u0022]");

    if(optionToDisable.length == 0){
        return;
    }

    optionToDisable.attr("disabled", "disabled");
}
