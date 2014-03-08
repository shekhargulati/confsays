<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ConfSays: ${requestScope.conference.name} </title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/css/bootstrap.css"></c:url>" rel="stylesheet">

    <link href="<c:url value="/css/simple-sidebar.css"></c:url>" rel="stylesheet">

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
                        <li><a href="<c:url value="/conferences"></c:url>">View All Conferences</a></li>
                    </ul>
                </li>
                <li><a href="<c:url value="quiz"></c:url>">OpenShift Quiz</a></li>
            </ul>
        </div>
        <!--/.navbar-collapse -->
    </div>
</div>

<input type="hidden" name="conferenceId" id="conferenceId" value="${requestScope.conferenceId}">

<div id="wrapper">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li><a href="#home"><i class="fa fa-home"></i> Home</a>
            </li>
            <li><a href="#timeline"><i class="fa fa-tasks"></i> Timeline</a>
            </li>
            <li><a href="#retweets"><i class="fa fa-retweet"></i> Most Retweeted</a>
            </li>
            <li><a href="#users"><i class="fa fa-user"></i> Active Users</a>
            </li>
            <li><a href="#mentions/speakers"><i class="fa fa-microphone"></i> Speaker Mentions</a>
            </li>
            <li><a href="#mentions/users"><i class="fa fa-comments"></i> User Mentions</a>
            </li>
        </ul>
    </div>

    <!-- Page content -->
    <div id="page-content-wrapper">
        <!-- Keep all page content within the page-content inset div! -->
        <div class="page-content inset" id="main">
        	
        </div>
    </div>

</div>

<script type="text/x-mustache-template" id="home-template">
    <a href="${requestScope.conference.conferenceUrl}" target="_blank"></a><img src="${requestScope.conference.bannerImgUrl}"></a>

    <h2>
        ${requestScope.conference.name}
    </h2>
    <p class="lead">${requestScope.conference.description}</p>
</script>

<script type="text/x-mustache-template" id="container-template">
    <h2> {{header}} </h2>

    <div id="timeline">

    </div>
</script>

<script type="text/x-mustache-template" id="tweet-template">
    <div class="row">
		<div class="col-md-12">
			<p>{{text}}</p>
			<span> <a href="//twitter.com/{{tweetedBy}}">{{tweetedBy}}</a> </span>
    	</div>
	</div>
	<hr>
</script>


<script type="text/x-mustache-template" id="retweet-template">
    <div class="row">
		<div class="col-md-12">
        <p>{{text}}</p>
 		<span> <a href="//twitter.com/{{tweetedBy}}">{{tweetedBy}}</a> tweet retweeted {{retweetCount}} times</span>
	<div>
    </div>
	<hr>
</script>

<script type="text/x-mustache-template" id="user-template">
    <div class="row">
		<div class="col-md-12">
        	<a href="//twitter.com/{{twitterHandle}}" target="_blank">{{twitterHandle}}</a> tweeted {{count}} times
    	</div>
	<div>
	<hr>
</script>

<script type="text/x-mustache-template" id="speaker-mention-template">
    <div class="row">
		<div class="col-md-12">
        	<p><a href="//twitter.com/{{twitterHandle}}" target="_blank">{{twitterHandle}}</a> mentioned {{count}} times</p>
    	</div>
	</div>
	<hr>
</script>

<script type="text/x-mustache-template" id="user-mention-template">
    <div class="row">
		<div class="col-md-12">
        	<p><a href="//twitter.com/{{twitterHandle}}" target="_blank">{{twitterHandle}}</a> mentioned {{count}} times</p>
    	</div>
	</div>
	<hr>
</script>

<!-- JavaScript -->
<script src="<c:url value="/js/jquery-1.10.2.js"/>"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/flot/0.8.2/jquery.flot.min.js"></script>
<script src="<c:url value="/js/bootstrap.js"/>"></script>
<script src="<c:url value="/js/underscore.js"/>"></script>
<script src="<c:url value="/js/backbone.js"/>"></script>
<script src="<c:url value="/js/mustache.js"/>"></script>
<script src="<c:url value="/js/conference_app.js"/>"></script>

<!-- Custom JavaScript for the Menu Toggle -->
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("active");
    });
</script>
</body>

</html>
