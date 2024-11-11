plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.ceudegraos"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ceudegraos"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Adicionando o driver JDBC para SQL Server diretamente
    implementation("com.microsoft.sqlserver:mssql-jdbc:10.2.1.jre8")

    // Dependências do AndroidX e Material Design
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.activity:activity:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Dependências de Teste
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}