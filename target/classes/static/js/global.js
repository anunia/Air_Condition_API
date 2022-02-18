$(document).ready(function(){

    $("#data-button").click(function(){
        var url = "http://localhost:8080/api/breeze?";

        url += "lat="+$("#lat").val();
        url += "&lon="+$("#lon").val();

        var polutants = "";
        $.each($("input[name='feature']:checked"), function(){
            polutants+= $(this).val()+",";
        });
        
        var health_recommendations = "";
        $.each($("input[name='recommendations']:checked"), function(){
            health_recommendations+= $(this).val()+",";
        });
        
        url += "&polutants=" + polutants.slice(0, -1);
        url += "&health_recommendations=" + health_recommendations.slice(0, -1);
        
        console.log(url);
        $.ajax({
            type: "GET",
            url: url,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                console.log(data)
                $("#get-result").css("display", "block");
                $("#ask-params").css("display", "none");
                $("#get-header").append( document.createTextNode( "(" +  $("#lat").val() + "," + $("#lon").val() + ")") )
                $("#baqi-results").append(
                    "<div class='big c100 p"+data.baqi.aqi +"' id='baqi-results-graph'>" +
                        "<span>"+data.baqi.aqi + "</span>" +
                        "<div height = 100px > <div class=\"slice\">" +
                            "<div class=\"bar\"style='border-color: "+data.baqi.color+"'></div>" +
                            "<div class=\"fill\" style='border-color: "+data.baqi.color+"'></div>" +
                        " </div>"+
                    "</div> </div>" +
                    "<p id='baqi-category'>"+data.baqi.category+"<p>"
                )
                if (polutants != ""){
                    $("#pollutants").append("<div height = 100px ><div id='pollutants-list'></div></div>");
                    $.each( data.listOfPollutants, function( key, value ) {
                        console.log( key + ": " + value.name );
                        $("#pollutants-list").append(
                            "<div height = 400px class='col-2'>" +
                                "<h5>" + value.name + "</h5>"+
                                "<div class='c100 p"+value.baqi.aqi +"'>" +
                                    "<span>"+value.baqi.aqi + "</span>" +
                                    "<div class=\"slice\">" +
                                        "<div class=\"bar\"style='border-color: "+value.baqi.color+"'></div>" +
                                        "<div class=\"fill\" style='border-color: "+value.baqi.color+"'></div>" +
                                    " </div>"+
                                "</div>" +
                                "<p>"+value.baqi.category+"<p>"+
                                "<p> Concentreation: " + value.concentration + value.concentrationunits + "</p>" +
                            "</div>"
                        )
                    });
                }
                if (health_recommendations != ""){
                	console.log( data.listOfRecommendations );
                    $("#recommendations").append("<div height = 100px ><div id='health_recommendations-list'></div></div>");
                    $.each( data.listOfRecommendations, function( key, value ) {
                        console.log( key + ": " + value.name );
                        $("#health_recommendations-list").append(
                            "<div class='col-2'>" +
                                "<h5>" + value.name + "</h5>"+
                                "<h5>"+ value.recommendation +"<h5>"+
                            "</div>"
                        )
                    });
                }
            }
        });
    });

    $("#back-button").click(function(){
        $("#get-header").html("<h4 class=\"modal-title\" id=\"get-header\">Results for </h4>");
        $("#baqi-results-graph").remove();
        $("#baqi-category").remove();
        $("#pollutants-list").remove();
        $("#health_recommendations-list").remove();
        $("#get-result").css("display", "none");
        $("#ask-params").css("display", "block");
    });

    $("#cache-button").click(function(){
         var url = "http://localhost:8080/api/cache";
         $.ajax({
             type: "GET",
             url: url,
             contentType: "application/json; charset=utf-8",
             dataType: "json",
             success: function (data) {
                 console.log(data)
                 $("#ask-params").css("display", "none");
                 $("#get-cache").css("display", "block");
                 $("#cache-results").append(
                     "<div id='cache-stats'>" +
                         "<h3>Number of Requests</h3>" +
                         "<h3 id='no-reqs'>" + data.Requests + "</h3>" +
                         "<div>" +
                             "<h4> Misses </h4>"+
                             "<h4 id='no-miss'>" + data.Misses + "</h4>"+
                             "<h4> Hits </h4>" +
                             "<h4 id='no-hits'>" + data.Hits + "</h4>" +
                         "</div>" +
                     "</div>"
                 )
             }
         });
    });

    $("#back-cache-button").click(function(){
        $("#cache-stats").remove();
        $("#get-cache").css("display", "none");
        $("#ask-params").css("display", "block");
    });

    $("#clear-cache-button").click(function(){
        $("#cache-stats").remove();
        $("#get-cache").css("display", "none");
        $("#ask-params").css("display", "block");
        var url = "http://localhost:8080/api/clear";
        $.ajax({
             type: "GET",
             url: url,
             contentType: "application/json; charset=utf-8",
             dataType: "json"
        })

    });


});
