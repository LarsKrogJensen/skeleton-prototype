#!/usr/bin/env bash

gradle shadowJar
heroku apps:create larkj-app1-${CIRCLE_BRANCH} --no-remote --region eu
heroku deploy:jar build/libs/skeleton-prototype-fat.jar --app larskj-app1-${CIRCLE_BRANCH}
