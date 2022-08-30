## help - Display help about make targets for this Makefile
help:
	@cat Makefile | grep '^## ' --color=never | cut -c4- | sed -e "`printf 's/ - /\t- /;'`" | column -s "`printf '\t'`" -t

## build - Builds the project for development
build:
	mvn clean install -DskipTests=true -Dgpg.skip=true -Dcheckstyle.skip=true -Dcheckstyle.skip=true -Ddependency-check.skip=true -Djavadoc.skip=true

## clean - Cleans the project
clean:
	mvn clean

## coverage - Test the project and generate a coverage report
coverage:
	mvn --batch-mode install -Dgpg.skip=true -Dcheckstyle.skip=true -Dcheckstyle.skip=true -Ddependency-check.skip=true -Djavadoc.skip=true jacoco:report

## install-checkstyle - Install CheckStyle
install-checkstyle:
	wget -O checkstyle.jar -q https://github.com/checkstyle/checkstyle/releases/download/checkstyle-10.3.1/checkstyle-10.3.1-all.jar

## install - Install requirements
install:
	git submodule init
	git submodule update

## lint - Check if project follows CheckStyle rules (must run install-checkstyle first)
lint:
	java -jar checkstyle.jar src -c easypost_java_style.xml -d

## publish - Publish a release of the project
# @parameters:
# pass= - The GPG password to sign the release
publish:
	mvn clean deploy -Dgpg.passphrase=${pass}

## publish-dry - Build the project as a dry run to publishing
# @parameters:
# pass= - The GPG password to sign the release
publish-dry:
	mvn clean install -Dgpg.passphrase=${pass}

## release - Cuts a release for the project on GitHub (requires GitHub CLI)
# tag = The associated tag title of the release
release:
	gh release create ${tag} target/*.jar target/*.asc target/*.pom

## scan - Scan the project for serious security issues
scan:
	mvn verify -DskipTests=true -Dgpg.skip=true -Dcheckstyle.skip=true -Djavadoc.skip=true -Ddependency-check.failBuildOnCVSS=0 -Ddependency-check.junitFailOnCVSS=0

## test - Test the project
test:
	mvn --batch-mode install -Dgpg.skip=true -Dcheckstyle.skip=true -Dcheckstyle.skip=true -Ddependency-check.skip=true -Djavadoc.skip=true

.PHONY: help build clean install-checkstyle install lint publish publish-dry release scan scan-strict test
