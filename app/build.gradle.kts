plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.mio.plugin.renderer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mio.plugin.renderer"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        configureEach {
            //驱动名/后缀可用 -PdriverName=xxx -PdriverSuffix=.xxx 覆盖，默认沿用模板值
            val driverName = (findProperty("driverName") as String?) ?: "Turnip 24.3.2"
            val driverSuffix = (findProperty("driverSuffix") as String?) ?: ".turnip2432"
            resValue("string", "app_name", driverName)
            applicationIdSuffix = driverSuffix
            manifestPlaceholders["driver"] = driverName
            //驱动库文件名（须存在于 src/main/jniLibs/<abi>/ 下）
            //Driver library file name (must exist under src/main/jniLibs/<abi>/)
            manifestPlaceholders["driverLib"] = "libvulkan_freedreno.so"
            //驱动适配的 GPU 家族：adreno / mali / any
            //The GPU family the driver targets: adreno / mali / any
            manifestPlaceholders["driverGpu"] = "adreno"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
}