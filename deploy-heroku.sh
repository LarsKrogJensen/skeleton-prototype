#!/usr/bin/env bash

echo "Appname: " ${heroku_app_name_base}
gradle shadowJar
heroku apps:create ${heroku_app_name_base}-${CIRCLE_BRANCH} --no-remote --region eu
heroku deploy:jar build/libs/skeleton-prototype-fat.jar --app ${heroku_app_name_base}-${CIRCLE_BRANCH}
