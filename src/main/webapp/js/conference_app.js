(function ($) {
    var Confsays = {};
    window.Confsays = Confsays;

    var conferenceId = $("#conferenceId").val();
    console.log("ConferenceId: " + conferenceId);

    var ctx = "/resources/public/conferences/" + conferenceId;

    console.log("Context: " + ctx);


    var template = function (name) {
        return $('#' + name + '-template').html();
    };

    Confsays.HomeView = Backbone.View.extend({
        render: function () {
            $('#main').html(template("home"));
        }
    });

    Confsays.UserMentionsView = Backbone.View.extend({
        render: function () {
            $.ajax(ctx + "/mentions/users", {
                method: "GET",
                success: function (data) {
                	$("#main").html("<div id='placeholder' style='width:600px;height:300px'></div>");
                	var rawData = [];
                	var ticks = [];
                	for(var i = 0;i<data.length;i++){
                		var arr = [];
                		
                		arr.push(data[i].count);
                		arr.push(i);
                		rawData.push(arr);
                		var labelArr = [];
                		labelArr.push(i);
                		labelArr.push(data[i].twitterHandle);
                		ticks.push(labelArr);
                	}
                	console.log("Raw Data "+rawData);
                	var dataSet = [{ label: "Users Mentions", data: rawData, color: "#EA0011" }];
                	var options = {
                            series: {
                                bars: {
                                    show: true
                                }
                            },
                            bars: {
                                align: "center",
                                barWidth: 0.5,
                                horizontal: true,
                                fillColor: { colors: [{ opacity: 0.5 }, { opacity: 1}] },
                                lineWidth: 1
                            },
                            xaxis: {
                                axisLabel: "Number of Mentions",
                                axisLabelUseCanvas: true,
                                axisLabelFontSizePixels: 12,
                                axisLabelFontFamily: 'Verdana, Arial',
                                axisLabelPadding: 10,
                                tickColor: "#5E5E5E",
                                color: "black"
                            },
                            yaxis: {
                                axisLabel: "Twitter Handle",
                                axisLabelUseCanvas: true,
                                axisLabelFontSizePixels: 12,
                                axisLabelFontFamily: 'Verdana, Arial',
                                axisLabelPadding: 3,
                                tickColor: "#5E5E5E",
                                ticks: ticks,
                                color: "black"
                            }


                	};
                	$.plot($("#placeholder"), dataSet,options);
                    $('#main').append(Mustache.to_html(template("container"), {"header": "User Mentions"}));
                    if (null != data) {
                        _.each(data, function (mentionJson) {
                            var mentionHtml = Mustache.to_html(template("user-mention"), mentionJson);
                            $("#timeline").append(mentionHtml);
                        });
                    }

                }, error: function (xhr, textStatus, errorThrown) {
                    alert("Error in getting user mentions: " + errorThrown);
                    console.log(errorThrown);
                }
            });
        }
    });

    Confsays.SpeakerMentionsView = Backbone.View.extend({
        render: function () {
            $.ajax(ctx + "/mentions/speakers", {
                method: "GET",
                success: function (data) {
                	$("#main").html("<div id='placeholder' style='width:600px;height:300px'></div>");
                	var rawData = [];
                	var ticks = [];
                	for(var i = 0;i<data.length;i++){
                		var arr = [];
                		
                		arr.push(data[i].count);
                		arr.push(i);
                		rawData.push(arr);
                		var labelArr = [];
                		labelArr.push(i);
                		labelArr.push(data[i].twitterHandle);
                		ticks.push(labelArr);
                	}
                	console.log("Raw Data "+rawData);
                	var dataSet = [{ label: "Speaker Mentions", data: rawData, color: "#EA0011" }];
                	var options = {
                            series: {
                                bars: {
                                    show: true
                                }
                            },
                            bars: {
                                align: "center",
                                barWidth: 0.5,
                                horizontal: true,
                                fillColor: { colors: [{ opacity: 0.5 }, { opacity: 1}] },
                                lineWidth: 1
                            },
                            xaxis: {
                                axisLabel: "Number of Mentions",
                                axisLabelUseCanvas: true,
                                axisLabelFontSizePixels: 12,
                                axisLabelFontFamily: 'Verdana, Arial',
                                axisLabelPadding: 10,
                                tickColor: "#5E5E5E",
                                color: "black"
                            },
                            yaxis: {
                                axisLabel: "Speaker Twitter Handle",
                                axisLabelUseCanvas: true,
                                axisLabelFontSizePixels: 12,
                                axisLabelFontFamily: 'Verdana, Arial',
                                axisLabelPadding: 3,
                                tickColor: "#5E5E5E",
                                ticks: ticks,
                                color: "black"
                            }


                	};
                	$.plot($("#placeholder"), dataSet,options);
                    $('#main').append(Mustache.to_html(template("container"), {header: "Speaker Mentions"}));
                    if (null != data) {
                        _.each(data, function (mentionJson) {
                            var mentionHtml = Mustache.to_html(template("speaker-mention"), mentionJson);
                            $("#timeline").append(mentionHtml);
                        });
                    }

                }, error: function (xhr, textStatus, errorThrown) {
                    alert("Error in getting speaker mentions: " + errorThrown);
                    console.log(errorThrown);
                }
            });
        }
    });

    Confsays.ActiveUsersView = Backbone.View.extend({
        render: function () {
            $.ajax(ctx + "/users", {
                method: "GET",
                success: function (data) {
                	$("#main").html("<div id='placeholder' style='width:600px;height:300px'></div>");
                	var rawData = [];
                	var ticks = [];
                	for(var i = 0;i<data.length;i++){
                		var arr = [];
                		
                		arr.push(data[i].count);
                		arr.push(i);
                		rawData.push(arr);
                		var labelArr = [];
                		labelArr.push(i);
                		labelArr.push(data[i].twitterHandle);
                		ticks.push(labelArr);
                	}
                	console.log("Raw Data "+rawData);
                	var dataSet = [{ label: "Active Users", data: rawData, color: "#EA0011" }];
                	var options = {
                            series: {
                                bars: {
                                    show: true
                                }
                            },
                            bars: {
                                align: "center",
                                barWidth: 0.5,
                                horizontal: true,
                                fillColor: { colors: [{ opacity: 0.5 }, { opacity: 1}] },
                                lineWidth: 1
                            },
                            xaxis: {
                                axisLabel: "Number of Tweets",
                                axisLabelUseCanvas: true,
                                axisLabelFontSizePixels: 12,
                                axisLabelFontFamily: 'Verdana, Arial',
                                axisLabelPadding: 10,
                                tickColor: "#5E5E5E",
                                color: "black"
                            },
                            yaxis: {
                                axisLabel: "Twitter Handle",
                                axisLabelUseCanvas: true,
                                axisLabelFontSizePixels: 12,
                                axisLabelFontFamily: 'Verdana, Arial',
                                axisLabelPadding: 3,
                                tickColor: "#5E5E5E",
                                ticks: ticks,
                                color: "black"
                            }


                	};
                	$.plot($("#placeholder"), dataSet,options);
                    $('#main').append(Mustache.to_html(template("container"), {header: "Top Active Users"}));
                    if (null != data) {
                        _.each(data, function (userJson) {
                            var userHtml = Mustache.to_html(template("user"), userJson);
                            $("#timeline").append(userHtml);
                        });
                    }

                }, error: function (xhr, textStatus, errorThrown) {
                    alert("Error in getting users: " + errorThrown);
                    console.log(errorThrown);
                }
            });
        }
    });


    Confsays.RetweetsView = Backbone.View.extend({
        render: function () {
            $.ajax(ctx + "/retweets", {
                method: "GET",
                success: function (data) {
                	$("#main").html("<div id='placeholder' style='width:600px;height:300px'></div>");
                	var rawData = [];
                	var ticks = [];
                	for(var i = 0;i<data.length;i++){
                		var arr = [];
                		
                		arr.push(data[i].retweetCount);
                		arr.push(i);
                		rawData.push(arr);
                		var labelArr = [];
                		labelArr.push(i);
                		labelArr.push(data[i].tweetedBy);
                		ticks.push(labelArr);
                	}
                	console.log("Raw Data "+rawData);
                	var dataSet = [{ label: "Top Retweets", data: rawData, color: "#EA0011" }];
                	var options = {
                            series: {
                                bars: {
                                    show: true
                                }
                            },
                            bars: {
                                align: "center",
                                barWidth: 0.5,
                                horizontal: true,
                                fillColor: { colors: [{ opacity: 0.5 }, { opacity: 1}] },
                                lineWidth: 1
                            },
                            xaxis: {
                                axisLabel: "Number of Retweets",
                                axisLabelUseCanvas: true,
                                axisLabelFontSizePixels: 12,
                                axisLabelFontFamily: 'Verdana, Arial',
                                axisLabelPadding: 10,
                                tickColor: "#5E5E5E",
                                color: "black"
                            },
                            yaxis: {
                                axisLabel: "Twitter Handle",
                                axisLabelUseCanvas: true,
                                axisLabelFontSizePixels: 12,
                                axisLabelFontFamily: 'Verdana, Arial',
                                axisLabelPadding: 3,
                                tickColor: "#5E5E5E",
                                ticks: ticks,
                                color: "black"
                            }


                	};
                	$.plot($("#placeholder"), dataSet,options);
                    $('#main').append(Mustache.to_html(template("container"), {header: "Top Retweets"}));
                    if (null != data) {
                        _.each(data, function (retweetJson) {
                            var retweetHtml = Mustache.to_html(template("retweet"), retweetJson);
                            $("#timeline").append(retweetHtml);
                        });
                    }

                }, error: function (xhr, textStatus, errorThrown) {
                    alert("Error in getting retweets: " + errorThrown);
                    console.log(errorThrown);
                }
            });
        }
    });

    Confsays.TimelineView = Backbone.View.extend({
        render: function () {
            $.ajax(ctx + "/tweets", {
                method: "GET",
                success: function (data) {
                    $('#main').html(Mustache.to_html(template("container"), {header: "Timeline "}));
                    if (null != data) {
                        _.each(data, function (tweetJson) {
                            var tweetHtml = Mustache.to_html(template("tweet"), tweetJson);
                            $("#timeline").append(tweetHtml);
                        });
                    }

                }, error: function (xhr, textStatus, errorThrown) {
                    alert("Error in getting tweets: " + errorThrown);
                    console.log(errorThrown);
                }
            });
        }

    });

    Confsays.Router = Backbone.Router.extend({
        currentView: null,

        routes: {
            "": "showHome",
            "home": "showHome",
            "timeline": "showTimeline",
            "retweets": "showMostRetweeted",
            "users": "showActiveUsers",
            "mentions/speakers": "showSpeakerMentions",
            "mentions/users": "showUserMentions",
            "stop-tracking": "stopTracking"
        },

        changeView: function (view) {
            if (null != this.currentView) {
                this.currentView.undelegateEvents();
            }
            this.currentView = view;
            this.currentView.render();
        },

        showHome: function () {
            console.log("in showHome()..");
            this.changeView(new Confsays.HomeView());
        },

        showTimeline: function () {
            console.log("in showTimeline()..");
            this.changeView(new Confsays.TimelineView());
        },

        showMostRetweeted: function () {
            console.log("in showMostRetweeeted()..");
            this.changeView(new Confsays.RetweetsView());
        },

        showActiveUsers: function () {
            console.log("in showActiveUsers()..");
            this.changeView(new Confsays.ActiveUsersView());
        },

        showSpeakerMentions: function () {
            console.log("in showSpeakerMentions()..");
            this.changeView(new Confsays.SpeakerMentionsView())
        },

        showUserMentions: function () {
            console.log("in showUserMentions()..");
            this.changeView(new Confsays.UserMentionsView())
        },

        stopTracking: function () {
            console.log("Stop Tracking..");
            $.ajax({
                url: ctx + "/stop",
                type: "DELETE",
                success: function (result) {
                    console.log(result);
                    router.navigate('home', {trigger: true});
                }
            })

        }

    });

    var router = new Confsays.Router();
    Backbone.history.start();


})(jQuery);