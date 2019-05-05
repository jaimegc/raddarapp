# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Desarrollo/Android-SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

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

# Raddar
## https://raddarapp.com ##
-keep class com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading
-keep class com.raddarapp.presentation.general.presenter.base.BasePresenterSwipeRefreshWithLoading
-keep class com.raddarapp.data.general.datasource.base.BaseDataSourceFactory
-keep class com.raddarapp.data.general.datasource.base.contract.preferences.base.DefaultValuesPrefererences
-keep class com.raddarapp.presentation.android.** { *; }
-keep class com.raddarapp.presentation.general.presenter.** { *; }
####################################################################################################

# Retrofit 2.X
## https://square.github.io/retrofit/ ##
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
####################################################################################################

# BottomNavigationView
## https://github.com/ittianyu/BottomNavigationViewEx ##
-keep public class android.support.design.widget.BottomNavigationView { *; }
-keep public class android.support.design.internal.BottomNavigationMenuView { *; }
-keep public class android.support.design.internal.BottomNavigationPresenter { *; }
-keep public class android.support.design.internal.BottomNavigationItemView { *; }
####################################################################################################

# Picasso
## https://github.com/square/picasso ##
-dontwarn com.squareup.okhttp.**
####################################################################################################

# Butter Knife
## https://github.com/JakeWharton/butterknife ##

# Retain generated class which implement Unbinder.
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }

# Prevent obfuscation of types which use ButterKnife annotations since the simple name
# is used to reflectively look up the generated ViewBinding.
-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }
####################################################################################################

# Dagger
## https://github.com/square/dagger ##
#Keep the annotated things annotated
-keepattributes *Annotation*

#Keep the dagger annotation classes themselves
-keep @interface dagger.*,javax.inject.*

#Keep the Modules intact
-keep @dagger.Module class *

#-Keep the fields annotated with @Inject of any class that is not deleted.
-keepclassmembers class * {
  @javax.inject.* <fields>;
}

#-Keep the names of classes that have fields annotated with @Inject and the fields themselves.
-keepclasseswithmembernames class * {
  @javax.inject.* <fields>;
}

-keep class dagger.Lazy

# Keep the generated classes by dagger-compile
-keep class **$$ModuleAdapter
-keep class **$$InjectAdapter
-keep class **$$StaticInjection
####################################################################################################

# Guava (Unofficial Proguard rules)
## https://github.com/google/guava ##
-dontwarn sun.misc.Unsafe
-dontwarn com.google.common.collect.MinMaxPriorityQueue
-dontwarn com.google.common.util.concurrent.FuturesGetChecked**
-dontwarn javax.lang.model.element.Modifier
-dontwarn afu.org.checkerframework.**
-dontwarn org.checkerframework.**
####################################################################################################

# Fabric
## https://fabric.io/
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**
####################################################################################################

# RxAudioPlayer
## https://github.com/Piasy/RxAndroidAudio ##
-dontwarn java.lang.invoke**
####################################################################################################

# Tests
# Proguard rules that are applied to your test apk/code.
-ignorewarnings

-keepattributes *Annotation*

-dontnote junit.framework.**
-dontnote junit.runner.**

-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**
-dontwarn org.hamcrest.**
-dontwarn com.squareup.javawriter.JavaWriter
-dontwarn org.mockito.**
####################################################################################################

# Rosie #
## https://github.com/Karumi/Rosie ##
-keep class com.karumi.rosie.** { *; }

-keepclassmembers class ** {
  @com.karumi.rosie.domain.usecase.annotation.UseCase public *;
}
####################################################################################################

# AVLoadingIndicatorView
## https://github.com/81813780/AVLoadingIndicatorView ##
-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }
####################################################################################################

# Unknown
-dontwarn org.bouncycastle.**
