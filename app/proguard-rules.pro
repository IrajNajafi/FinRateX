# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# حفظ ViewModelها
-keep class androidx.lifecycle.ViewModel
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# جلوگیری از حذف انوتیشن‌ها (مهم برای Gson، Hilt و Room)
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes EnclosingMethod

########################################
# ✅ Hilt / Dagger
########################################

-keep class dagger.** { *; }
-dontwarn dagger.**
-keep class javax.inject.** { *; }
-dontwarn javax.inject.**

-keep class * extends dagger.hilt.android.HiltAndroidApp
-keep class **_HiltModules { *; }
-keep class **_HiltComponents { *; }

########################################
# ✅ Retrofit + Gson
########################################

-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
-keep interface retrofit2.** { *; }

-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

# حفظ مقادیر Annotated توسط Gson
-keep class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# ابزار دیباگ (در صورت فعال بودن preview یا tooling)
-keep class androidx.compose.ui.tooling.** { *; }
-dontwarn androidx.compose.ui.tooling.**

########################################
# ✅ حذف لاگ‌ها از نسخه‌ی ریلیز
########################################

-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}