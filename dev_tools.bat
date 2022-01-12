@echo off
Rem Auto-generated Batch script converted from Bash script via https://daniel-sc.github.io/bash-shell-to-bat-converter/
Rem wget on *Unix replaced with curl for Windows

SET DEVTOOLS_FOLDER=dev_tools
SET STYLE_FILE=easypost_java_style.xml
SET SUPPRESSIONS_FILE=style_suppressions.xml

EXIT /B %ERRORLEVEL%

:load
IF ! -d %DEVTOOLS_FOLDER% (
  echo "Creating '%DEVTOOLS_FOLDER%' folder..."
  mkdir %DEVTOOLS_FOLDER%
)
echo "Downloading latest CheckStyle JAR file..."
Rem Hard-coded v9.2.1 until we better handle parsing GitHub API JSON
SET DOWNLOAD_URL=https://github.com/checkstyle/checkstyle/releases/download/checkstyle-9.2.1/checkstyle-9.2.1-all.jar
curl -o %DEVTOOLS_FOLDER%\checkstyle.jar %DOWNLOAD_URL%
EXIT /B 0

:check_style
IF ! -f %DEVTOOLS_FOLDER%\checkstyle.jar (
  echo "Downloading missing CheckStyle JAR file..."
  CALL :load
)
echo "Downloading EasyPost CheckStyle config files..."
curl -o %DEVTOOLS_FOLDER%/%STYLE_FILE% https:/\raw.githubusercontent.com\EasyPost\easypost-java\style\easypost_java_style.xml
curl -o %DEVTOOLS_FOLDER%/%SUPPRESSIONS_FILE% https:/\raw.githubusercontent.com\EasyPost\easypost-java\style\style_suppressions.xml
echo "Running CheckStyle checks..."
cd %DEVTOOLS_FOLDER%
exec java -jar checkstyle.jar %CD% -c %STYLE_FILE%
EXIT /B 0
