plugins {
    java
    id("org.springframework.boot") version ("2.3.4.RELEASE")
    id("io.spring.dependency-management") version ("1.0.10.RELEASE")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation(group = "org.postgresql", name = "postgresql", version = "42.2.18")
    // https://mvnrepository.com/artifact/org.jooq/jooq
    implementation(group = "org.jooq", name = "jooq", version = "3.14.0")

    testImplementation("org.springframework.boot", "spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.compileJava{
    options.encoding = "utf-8"
}

tasks.test {
    useJUnitPlatform()
}
