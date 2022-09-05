plugins {
  // https://github.com/node-gradle/gradle-node-plugin
  id("com.github.node-gradle.node") version "3.1.1"
  java
}

// Add dist directory to resources
sourceSets {
  main {
    resources {
      srcDirs("build/webapp")
    }
  }
}

node {
  download.set(true)
  version.set("16.10.0")
}

tasks {

  register("buildWebApp") {
    dependsOn("npm_run_build")
  }

  processResources {
    dependsOn("buildWebApp")
  }

  "npm_run_build" {
    inputs.dir("$projectDir/src")
    inputs.file("package.json")
    outputs.dir("$buildDir/webapp")
  }

  processResources {
    includeEmptyDirs = false
    // Move resource files into "webapp" directory
    eachFile {
      path = "webapp/$path"
    }
  }
}
