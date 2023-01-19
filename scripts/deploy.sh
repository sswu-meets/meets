#!/usr/bin/env bash

REPOSITORY=/home/ec2-user/app
PROJECT_NAME=meets

echo "> Copy Build file"
echo "> cp $REPOSITORY/deploy/*.jar $REPOSITORY/"
cp $REPOSITORY/deploy/*.jar $REPOSITORY/

echo "> Check current application pid"

CURRENT_PID=$(pgrep -f $PROJECT_NAME)

echo "Current application pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "> No applications are currently running"
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> Deploy new application"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> Add Execution Permission to $JAR_NAME"

chmod +x $JAR_NAME

echo "> Run $JAR_NAME"

nohup java -jar \
        -Dspring.config.location=/home/ec2-user/app/application.properties, classpath:/application-prod.properties \
        -Dspring.profiles.active=prod \
        $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &