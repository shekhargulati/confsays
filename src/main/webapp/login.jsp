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

    <title>ConfSays: Learn about conferences better</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <link href="css/jumbotron.css" rel="stylesheet">
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
    <form role="form" action="<c:url value="/login"/>" method="post">
        <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" class="form-control" id="email"  name="email" placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password">
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox"> Remember me
            </label>
        </div>
        <button type="submit" class="btn btn-success">Sign in</button>
    </form>
    </div>

    <hr>

    <div id="footer">
        <div class="container">
            <p class="text-muted credit">Made with &hearts; by <a href="https://twitter.com/shekhargulati/" target="_blank">Shekhar Gulati</a>.</p>
        </div>
    </div>
</div>


<script src="js/jquery-1.10.2.js"></script>
<script src="js/bootstrap.js"></script>
</body>
</html>