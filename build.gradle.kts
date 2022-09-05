subprojects {

    repositories {
        mavenCentral()
    }

    tasks {
        register("listAllDependencies", DependencyReportTask::class) {
        }
    }
}

project.version = project.property("projectVersion").toString()
