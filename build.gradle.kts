// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

}
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0") // ใช้เวอร์ชัน 8.2 ขึ้นไป
    }
}
