package com.staleylabs.resteasy;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * Main execution for the Heroku application to run.
 *
 * @author Sean M. Staley
 * @version 1.0 (6/22/13)
 */

public class RestEasyApp {

    public static void main(String[] args) throws Exception {
        System.out.println("Launching the RestEasy application.");

        String webappDirLocation = "src/main/java/com/staleylabs/resteasy/";
        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}
