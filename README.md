ConfSays
================

A Java EE 7 application to track twitter interactions happening during a conference.

## How to deploy the application on OpenShift##

* Create an OpenShift Online account https://www.openshift.com/

* Install OpenShift command-line tool rhc https://www.openshift.com/developers/rhc-client-tools-install

* Create a Wildfly application using OpenShift Online web console.

* Add MySQL 5.5 cartridge
```
$ rhc cartridge-add --app <app_name> mysql-5.5
```
* Clone the application to local machine.
```
$ rhc git-clone <app-name>
```
* Pull the source code from Git repository
```
$ git remote add upstream -m master https://github.com/shekhargulati/confsays.git
$ git pull -s recursive -X theirs upstream master
```
* Create a new twitter application https://dev.twitter.com/

* Create four environment variables
```
$ rhc env-set --app <app_name> TWITTER_CONSUMER_KEY=<consumer_key>
$ rhc env-set --app <app_name> TWITTER_CONSUMER_SECRET=<consumer_secret>
$ rhc env-set --app <app_name> TWITTER_ACCESS_TOKEN=<access_token>
$ rhc env-set --app <app_name> TWITTER_ACCESS_TOKEN_SECRET=<access_secret>
```
* Deploy the application
```
$ git push
```



