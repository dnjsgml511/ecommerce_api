import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
}

group = "com.ecommerce.api"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("io.github.microutils:kotlin-logging-jvm:2.0.10")

	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	runtimeOnly("dev.miku:r2dbc-mysql:0.8.2.RELEASE")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx3")

//	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
//
//	// https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-reactor
//	runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")

	// jwt
	implementation("io.jsonwebtoken:jjwt-api:0.11.2")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")

	// 암호화
	implementation("de.svenkubiak:jBCrypt:0.4.1")


//	// spring security
//	implementation("org.springframework.boot:spring-boot-starter-security")
//	implementation("org.springframework.security:spring-security-test")
//	// https://mvnrepository.com/artifact/org.springframework.security/spring-security-config
//	implementation("org.springframework.security:spring-security-config:5.7.3")
//
//
//	// jwt
//	implementation("io.jsonwebtoken:jjwt:0.9.1")


	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-r2dbc
//	implementation("org.springframework.boot:spring-boot-starter-webflux")
//	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
//	implementation("com.github.jasync-sql:jasync-r2dbc-mysql:2.0.8")
//	runtimeOnly("mysql:mysql-connector-java")
//
//	runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")
//	implementation("io.projectreactor:reactor-core:2.0.0.RC1")
//
//	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
//	runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.0")
//	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.6.0")
//
//	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
//	testImplementation("io.projectreactor:reactor-test")
//
//	// https://mvnrepository.com/artifact/io.projectreactor/reactor-core
//	implementation("io.projectreactor:reactor-core:3.4.23")

//	implementation("org.springframework.boot:spring-boot-starter-webflux")
//	implementation("org.springframework.boot.experimental:spring-boot-starter-data-r2dbc")
//	testImplementation("org.springframework.boot.experimental:spring-boot-test-autoconfigure-r2dbc")
//	testImplementation("io.projectreactor:reactor-test")

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
