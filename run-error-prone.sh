#!/bin/bash

# This script must be used with Java 9+

# Error Prone newer than 2.10.0 doesn't work on Java 8-10
EP_VERSION="2.10.0"

# Install needed jars
wget -nc https://repo1.maven.org/maven2/com/google/errorprone/error_prone_core/${EP_VERSION?}/error_prone_core-${EP_VERSION?}-with-dependencies.jar
wget -nc https://repo1.maven.org/maven2/org/checkerframework/dataflow-errorprone/3.15.0/dataflow-errorprone-3.15.0.jar
wget -nc https://repo1.maven.org/maven2/com/google/code/findbugs/jFormatString/3.0.0/jFormatString-3.0.0.jar

# Run Error Prone on the library and test suite
javac \
  -J--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED \
  -J--add-exports=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED \
  -J--add-exports=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED \
  -J--add-exports=jdk.compiler/com.sun.tools.javac.model=ALL-UNNAMED \
  -J--add-exports=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED \
  -J--add-exports=jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED \
  -J--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED \
  -J--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED \
  -J--add-opens=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED \
  -J--add-opens=jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED \
  -XDcompilePolicy=simple \
  -processorpath error_prone_core-${EP_VERSION?}-with-dependencies.jar:dataflow-errorprone-3.15.0.jar:jFormatString-3.0.0.jar \
  '-Xplugin:ErrorProne -XepDisableAllChecks -Xep:CollectionIncompatibleType:ERROR' \
  src/main/java/com/easypost/**/*.java src/test/java/com/easypost/**/*.java
