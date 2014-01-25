<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ConfSays: Create a new conference</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/css/bootstrap.css"></c:url>" rel="stylesheet">

    <link href="<c:url value="/css/jumbotron.css"></c:url>" rel="stylesheet">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]>
    <script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/>">ConfSays</a>
        </div>

        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Conferences<b
                            class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/app/conferences"></c:url>">View All Conferences</a></li>
                        <li><a href="<c:url value="/app/conferences/new"></c:url>">Create New Conference</a></li>
                    </ul>
                </li>
            </ul>
            <shiro:notAuthenticated>
                <form class="navbar-form navbar-right" action="<c:url value="/login"/>" method="post">
                    <div class="form-group">
                        <input type="text" placeholder="Email" id="email" name="email" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="Password" id="password" name="password"
                               class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-success">Sign in</button>
                </form>
            </shiro:notAuthenticated>
            <shiro:authenticated>

                <ul class="nav navbar-nav pull-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><shiro:principal/><b
                                class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<c:url value="/logout"></c:url>">Logout</a></li>

                        </ul>
                    </li>
                </ul>
            </shiro:authenticated>


        </div>
        <!--/.navbar-collapse -->
    </div>
</div>

<div class="container">
    <form class="form-horizontal" role="form" id="conferenceForm">
        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">Title</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="title" placeholder="Conference Name(eg. JUDCon India)"
                       required>
            </div>
        </div>

        <div class="form-group">
            <label for="description" class="col-sm-2 control-label">Description</label>

            <div class="col-sm-10">
                <textarea id="description" class="form-control" name="description" cols="5"
                          placeholder="Conference Description(eg. JUDCon:India is the world's most heavily-attended developer conference focusing on Enterprise Application Development within the JBoss Community.)"
                          required></textarea>
            </div>
        </div>

        <div class="form-group">
            <label for="bannerImgUrl" class="col-sm-2 control-label">Conference Banner Image Url</label>

            <div class="col-sm-10">
                <input type="url" class="form-control" id="bannerImgUrl" name="bannerImgUrl"
                       placeholder="Image url(eg. http://www.jboss.org/dms/judcon/2014india/judcon2014india_banner_1180px_speakersannounced.png)">
            </div>
        </div>


        <div class="form-group">
            <label for="hashtags" class="col-sm-2 control-label">HashTags</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="hashtags" name="hashtags"
                       placeholder="Comma separated list of Twitter hashtags to track(eg. judcon,judconin)" required>
            </div>
        </div>

        <div class="form-group">
            <label for="speakers" class="col-sm-2 control-label">Speakers Twitter Handles</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="speakers" name="speakers"
                       placeholder="Comma separated list of Speaker Twitter handles to track(eg. shekhargulati)" required>
            </div>
        </div>

        <div class="form-group">
            <label for="startDate" class="col-sm-2 control-label">Conference Start Date</label>

            <div class="col-sm-10">
                <input type="date" class="form-control" id="startDate" name="startDate"
                       placeholder="Conference Start Date">
            </div>
        </div>

        <div class="form-group">
            <label for="endDate" class="col-sm-2 control-label">Conference End Date</label>

            <div class="col-sm-10">
                <input type="date" class="form-control" id="endDate" name="endDate" placeholder="Conference End Date">
            </div>
        </div>

        <div class="form-group">
            <label for="conferenceUrl" class="col-sm-2 control-label">Conference Homepage Url</label>

            <div class="col-sm-10">
                <input type="url" class="form-control" id="conferenceUrl" name="conferenceUrl"
                       placeholder="Conference Url(eg. http://www.jboss.org/events/JUDCon/2014/india)">
            </div>
        </div>

        <div class="form-group">
            <label for="agendaUrl" class="col-sm-2 control-label">Conference Agenda Url</label>

            <div class="col-sm-10">
                <input type="url" class="form-control" id="agendaUrl" name="agendaUrl"
                       placeholder="Conference Agenda url(eg. http://www.jboss.org/events/JUDCon/2014/india/sessions)">
            </div>
        </div>


        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" id="track" name="track"> Track Conference
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-success">Create New Conference</button>
            </div>
        </div>
    </form>
</div>

<hr>

<div id="footer">
    <div class="container">
        <p class="text-muted credit">Made with &hearts; by <a href="https://twitter.com/shekhargulati/" target="_blank">Shekhar
            Gulati</a>.</p>
    </div>
</div>
</div>


<script src="<c:url value="/js/jquery-1.10.2.js"></c:url>"></script>
<script src="<c:url value="/js/bootstrap.js"></c:url>"></script>

<script>
    $("#conferenceForm").submit(function (event) {
        event.preventDefault();
        console.log("Form submission");
        var data = {
            title: $("#title").val(),
            description: $("#description").val(),
            bannerImgUrl: $("#bannerImgUrl").val(),
            hashtags: $("#hashtags").val().split(","),
            speakers: $("#speakers").val().split(","),
            startDate: $("#startDate").val(),
            endDate: $("#endDate").val(),
            conferenceUrl: $("#conferenceUrl").val(),
            agendaUrl: $("#agendaUrl").val()
        };
        console.log(data);
        var ctx = "${pageContext.request.contextPath}";
        $.ajax({
            method: "POST",
            url: ctx + "/" + "app/api/conferences",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                console.log("Form submitted " + data);
                window.location.href = ctx + "/app/conferences/" + data.urlFragment;
            },
            error: function (errorMsg) {
                alert("Error creating conference. Try creating again..");
                console.log(JSON.stringify(errorMsg));
            }
        })
    });

</script>
</body>
</html>
