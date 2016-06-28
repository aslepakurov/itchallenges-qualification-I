$(function () {
    console.log("Initialized...");
    $("#submitButton").on("click", function () {
        var pointA = $("#pointAInput").val();
        var pointB = $("#pointBInput").val();
        var estimate = parseInt($("#estInput").val());
        if (pointA == "" || pointB == "" || estimate == 0) {
            $("#submitPoint").addClass("text-danger");
            $("#submitPoint").text("No points present or estimate is empty or zero");
        } else {
            console.log("Request - " + pointA + " " + pointB + " " + estimate);
            var json = "{\"pointA\":\"" + pointA + "\", \"pointB\":\"" + pointB + "\", \"estimate\":" + estimate + "}"
            console.log(json);
            $.ajax({
                method: "POST",
                url: "http://localhost:9999/rest/pathfinder/path",
                data: json,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (json) {
                $("#submitPoint").text(json.message);
            }).error(function (json) {
                $("#submitPoint").text(json.message);
            })
        }
    });

    $("#submitButton2").on("click", function () {
        var pointA = $("#pointAInput2").val();
        var pointB = $("#pointBInput2").val();
        if (pointA == "" || pointB == "") {
            $("#submitPoint2").addClass("text-danger");
            $("#submitPoint2").text("No points present");
        } else {
            console.log("Request - " + pointA + " " + pointB);
            var json = "{\"pointA\":\"" + pointA + "\", \"pointB\":\"" + pointB + "\"}";
            console.log(json)
            $.ajax({
                method: "POST",
                url: "http://localhost:9999/rest/pathfinder/estimate",
                data: json,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (json) {
                $("#submitPoint2").text(json.message);
            }).error(function (json) {
                $("#submitPoint2").text(json.message);
            })
        }
    });

    $("#submitButton3").on("click", function () {
        var pointA = $("#pointAInput3").val();
        var pointB = $("#pointBInput3").val();
        if (pointA == "" || pointB == "") {
            $("#submitPoint3").addClass("text-danger");
            $("#submitPoint3").text("No points present");
        } else {
            console.log("Request - " + pointA + " " + pointB);
            var json = "{\"pointA\":\"" + pointA + "\", \"pointB\":\"" + pointB + "\"}";
            console.log(json);
            $.ajax({
                method: "DELETE",
                url: "http://localhost:9999/rest/pathfinder/path",
                data: json,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (json) {
                $("#submitPoint3").text(json.message);
            }).error(function (json) {
                $("#submitPoint3").text(json.message);
            })
        }

    });

    $("#submitButton4").on("click", function () {
        $.ajax({
            method: "GET",
            url: "http://localhost:9999/rest/pathfinder/paths",
            dataType: "json"
        }).success(function (json) {
            $("#submitPoint4").text(json.message);
        })
    });
});