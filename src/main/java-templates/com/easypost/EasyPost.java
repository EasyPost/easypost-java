package com.easypost;

import com.easypost.easyvcr.VCR;

/**
 * Hello weary traveler, welcome to the EasyPost Java client library.
 * <p>
 * This file exists as a template for the Templating Maven Plugin (https://www.mojohaus.org/templating-maven-plugin/)
 * If you notice the VERSION = ${project.version} below, that's an example of a template variable.
 * <p>
 * In Maven, you can set variables inside the pom.xml file
 * (https://maven.apache.org/guides/introduction/introduction-to-the-pom.html#available-variables)
 * <p>
 * The Templating Maven Plugin, at compile time, will extract these variables and add them into the source code.
 * <p>
 * Specifically here, VERSION = ${project.version} will be replaced with, i.e. VERSION = 1.0.0 when compiling the code.
 * NOTE: The VERSION will not populate if built with Gradle.
 * <p>
 * The placement of this file is important.
 * The Templating Maven Plugin will look for any template files in this specific `java-templates` directory.
 * As a result, you can not remove this file.
 * (https://www.hascode.com/2013/09/filtering-source-files-using-the-templating-maven-plugin/)
 * <p>
 * Most of the other variables in this file are not template variables.
 * Instead, we simply use this file as a catch-all for all global variables.
 * <p>
 * If you ever run across a section of code where your IDE says it cannot find, i.e. `EasyPost.apiKey`,
 * it's likely because this file has not been compiled yet.
 * Simply run `make build` to compile this code and your IDE will be able to find the variable.
 */

public abstract class EasyPost {
    public static final String VERSION = "${project.version}";
    public static final String API_BASE = "https://api.easypost.com/v2";
    public static final String BETA_API_BASE = "https://api.easypost.com/beta";

    /**
     * Set a VCR to be used for all HTTP requests.
     * <p>
     * NOTE: This is meant for unit testing purposes only. Do not use in production.
     */
    public static VCR _vcr = null;
}
