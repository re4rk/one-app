import com.re4rk.oneApp.configureKotlinJvm
import com.re4rk.oneApp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("java-library")
                apply("org.jetbrains.kotlin.jvm")
                apply("org.jetbrains.kotlin.kapt")
                configureKotlinJvm()
            }

            project.tasks.withType(Test::class.java) {
                useJUnitPlatform()
            }

            dependencies {
                add("implementation", libs.findLibrary("hilt.core").get())
                "kapt"(libs.findLibrary("hilt.compiler").get())

                add("testImplementation", libs.findLibrary("assertj.core").get())

                add("testImplementation", libs.findLibrary("junit5.jupiter.api").get())
                add("testRuntimeOnly", libs.findLibrary("junit5.jupiter.engine").get())
            }
        }
    }
}
