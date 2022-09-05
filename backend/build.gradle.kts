import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.13.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "benter.tech"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
tasks{
	processResources {
		dependsOn(":frontend:build")
	}
}
tasks {
	bootBuildImage {
		imageName = "bentertech/todoapp:" + "latest"
		isPublish = true
		docker {
			publishRegistry {
				url = "best way to set container registry URL"
				username = "best way to set user name for CR"
				password = "best way to set password for CR"
			}
		}
	}
}

//fun gitBranch(): String {
//	return try {
//		val byteOut = org.gradle.internal.impldep.org.apache.commons.io.output.ByteArrayOutputStream()
//		project.exec {
//			commandLine = "git rev-parse --abbrev-ref HEAD".split(" ")
//			standardOutput = byteOut
//		}
//		String(byteOut.toByteArray()).trim().also {
//			if (it == "HEAD")
//				logger.warn("Unable to determine current branch: Project is checked out with detached head!")
//		}
//	} catch (e: Exception) {
//		logger.warn("Unable to determine current branch: ${e.message}")
//		"Unknown Branch"
//	}
//}