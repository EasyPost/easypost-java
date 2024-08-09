## help - Display help about make targets for this Makefile
help:
	@cat Makefile | grep '^## ' --color=never | cut -c4- | sed -e "`printf 's/ - /\t- /;'`" | column -s "`printf '\t'`" -t

## build - Builds the project for development
build:
	mvn install -DskipTests=true -Dgpg.skip=true -Dcheckstyle.skip=true -Ddependency-check.skip=true -Djavadoc.skip=true -Djacoco.skip=true

## clean - Cleans the project
clean:
	mvn clean

## coverage - Test (and build) the project to generate a coverage report
coverage:
	mvn verify -Dgpg.skip=true -Dcheckstyle.skip=true -Ddependency-check.skip=true -Djavadoc.skip=true jacoco:report

## checkstyle - Check if project follows CheckStyle rules (must run install-checkstyle first)
checkstyle:
	java -jar checkstyle.jar src -c examples/style_guides/java/easypost_java_style.xml -d

## docs - Generates library documentation
docs:
	mvn install -DskipTests=true -Dgpg.skip=true -Dcheckstyle.skip=true -Ddependency-check.skip=true -Djacoco.skip=true
	cp -R target/apidocs/ ./docs/

## install-checkstyle - Install the Checkstyle tool (Unix only)
install-checkstyle: | install-styleguide
	curl -LJs https://github.com/checkstyle/checkstyle/releases/download/checkstyle-10.3.1/checkstyle-10.3.1-all.jar -o checkstyle.jar

## install-styleguide - Install style guides
install-styleguide: | init-examples-submodule
	sh examples/symlink_directory_files.sh examples/style_guides/java .

## init-examples-submodule - Initialize the examples submodule
init-examples-submodule:
	git submodule init
	git submodule update

## install - Install requirements
install: | install-checkstyle

## lint - Lints the project
lint: checkstyle scan

## publish - Publish a release of the project (will build the project via the `mvn deploy` command)
# @parameters:
# pass= - The GPG password to sign the release
publish:
	mvn clean deploy -Dgpg.passphrase=${pass}

## publish-dry - Build the project as a dry run to publishing (will build the project via the `mvn install` command)
# @parameters:
# pass= - The GPG password to sign the release
publish-dry:
	mvn clean install -Dgpg.passphrase=${pass}

## release - Cuts a release for the project on GitHub (requires GitHub CLI)
# tag = The associated tag title of the release
# target = Target branch or full commit SHA
release:
	gh release create ${tag} target/*.jar target/*.asc target/*.pom --target ${target}

## scan - Scan the project for serious security issues
scan:
	mvn verify -DskipTests=true -Dgpg.skip=true -Dcheckstyle.skip=true -Djavadoc.skip=true -Djacoco.skip=true -Ddependency-check.failBuildOnCVSS=0 -Ddependency-check.junitFailOnCVSS=0

## test - Test the project
test:
	mvn surefire:test

## update-examples-submodule - Update the examples submodule
update-examples-submodule:
	git submodule init
	git submodule update --remote

.PHONY: help build clean coverage docs install-checkstyle install-styleguide install lint publish publish-dry release scan scan-strict test update-examples-submodule
