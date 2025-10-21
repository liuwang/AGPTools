plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "2.0.0"
}

dependencies {
    implementation("com.android.tools.build:gradle:8.12.3")
}

version = "1.0"
group = "io.github.liuwang"
gradlePlugin {
    website.set("https://github.com/liuwang/AGPTools")
    vcsUrl.set("https://github.com/liuwang/AGPTools")
    plugins {
        create("AGPToolsPlugin") {
            id = "io.github.liuwang.gradle.delete-mapping"
            implementationClass = "DeleteMappingPlugin"
            displayName = "Delete Mapping Plugin"
            description = "A Gradle plugin that automatically removes proguard.map files from Android App Bundle (AAB) build outputs. This plugin helps reduce AAB file size and simplifies the release process when mapping files are not needed."
            tags.set(listOf("AGP", "proguard", "Android"))
        }
    }
}
