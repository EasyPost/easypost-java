#!/bin/bash

DEVTOOLS_FOLDER="dev_tools"
STYLE_FILE="easypost_java_style.xml"
SUPPRESSIONS_FILE="style_suppressions.xml"

load() {
  # Create dev_tools directory if non-existent
  if [ ! -d $DEVTOOLS_FOLDER ]
  then
    echo "Creating '$DEVTOOLS_FOLDER' folder..."
    mkdir $DEVTOOLS_FOLDER
  fi

  # Download CheckStyle JAR from GitHub
  echo "Downloading latest CheckStyle JAR file..."
  DOWNLOAD_URL=$(curl -s https://api.github.com/repos/checkstyle/checkstyle/releases/latest | grep ".jar" | cut -d '"' -f 4 | tail -1)
  wget -q --show-progress -O $DEVTOOLS_FOLDER/checkstyle.jar $DOWNLOAD_URL
}

check_style() {
  # Run CheckStyle checks
  
  # First, check if CheckStyle JAR exists, and download if not
  if [ ! -f $DEVTOOLS_FOLDER/checkstyle.jar ]
  then
    echo "Downloading missing CheckStyle JAR file..."
    load
  fi

  # Download EasyPost CheckStyle files from GitHub (every time in case they've changed)
  echo "Downloading EasyPost CheckStyle config files..."
  wget -q --show-progress -O $DEVTOOLS_FOLDER/$STYLE_FILE https://raw.githubusercontent.com/EasyPost/easypost-java/style/easypost_java_style.xml
  wget -q --show-progress -O $DEVTOOLS_FOLDER/$SUPPRESSIONS_FILE https://raw.githubusercontent.com/EasyPost/easypost-java/style/style_suppressions.xml

  # Run CheckStyle checks with the custom style guide (based on https://github.com/nikitasavinov/checkstyle-action/blob/master/entrypoint.sh)
  echo "Running CheckStyle checks..."
  cd $DEVTOOLS_FOLDER
  exec java -jar checkstyle.jar . -c $STYLE_FILE
}

"$@"
