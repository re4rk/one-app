import com.re4rk.oneApp.configureKotlinJvm
import com.re4rk.oneApp.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("java-library")
                configureKotlinJvm()
            }

            dependencies {
                add("implementation", libs.findLibrary("hilt.core").get())
            }
        }
    }
}