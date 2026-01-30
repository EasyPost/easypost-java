## Builds the project for development
build:
    mvn install -DskipTests=true -Dgpg.skip=true -Dcheckstyle.skip=true -Ddependency-check.skip=true -Djavadoc.skip=true -Djacoco.skip=true

## Cleans the project
clean:
    mvn clean

## Test (and build) the project to generate a coverage report
coverage:
    mvn test -Dgpg.skip=true -Dcheckstyle.skip=true -Ddependency-check.skip=true -Djavadoc.skip=true jacoco:report

## Check if project follows CheckStyle rules (must run install-checkstyle first)
checkstyle:
    java -jar checkstyle.jar src -c examples/style_guides/java/easypost_java_style.xml -d

## Generates library documentation
docs:
    mvn install -DskipTests=true -Dgpg.skip=true -Dcheckstyle.skip=true -Ddependency-check.skip=true -Djacoco.skip=true
    cp -R target/reports/apidocs/ ./docs/

## Install the Checkstyle tool (Unix only)
install-checkstyle: install-styleguide
    curl -LJs https://github.com/checkstyle/checkstyle/releases/download/checkstyle-10.3.1/checkstyle-10.3.1-all.jar -o checkstyle.jar

## Install style guides
install-styleguide: init-examples-submodule
    sh examples/symlink_directory_files.sh examples/style_guides/java .

## Initialize the examples submodule
init-examples-submodule:
    git submodule init
    git submodule update

## Install requirements
install: install-checkstyle

## Lints the project
lint: checkstyle

## Publish a release of the project (will build the project via the `mvn deploy` command)
# pass= - The GPG password to sign the release
publish pass:
    mvn clean deploy -Dgpg.passphrase={{pass}}

## Build the project as a dry run to publishing (will build the project via the `mvn install` command)
# pass= - The GPG password to sign the release
publish-dry pass:
    mvn clean install -Dgpg.passphrase={{pass}}

## Cuts a release for the project on GitHub (requires GitHub CLI)
# tag = The associated tag title of the release
# target = Target branch or full commit SHA
release tag target:
    gh release create {{tag}} target/*.jar target/*.asc target/*.pom --target {{target}}

## Test the project
test:
    mvn test -Dgpg.skip=true -Dcheckstyle.skip=true -Ddependency-check.skip=true -Djavadoc.skip=true -Djacoco.skip=true

## Test the project on CI (does not rebuild the project)
test-ci:
    mvn surefire:test

## Update the examples submodule
update-examples-submodule:
    git submodule init
    git submodule update --remote
