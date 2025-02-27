package projectDependencies

import projectDependencies.DependencyVersion.ACTIVITY_KTX_VERSION
import projectDependencies.DependencyVersion.ANNOTATION_VERSION
import projectDependencies.DependencyVersion.APPCOMPAT_VERSION
import projectDependencies.DependencyVersion.BCPKIX_VERSION
import projectDependencies.DependencyVersion.BCPROV_VERSION
import projectDependencies.DependencyVersion.BROWSER_VERSION
import projectDependencies.DependencyVersion.CARDVIEW_VERSION
import projectDependencies.DependencyVersion.CONSTRAINT_LAYOUT_VERSION
import projectDependencies.DependencyVersion.COORDINATOR_LAYOUT_VERSION
import projectDependencies.DependencyVersion.CORE_KTX_VERSION
import projectDependencies.DependencyVersion.CORE_VERSION
import projectDependencies.DependencyVersion.COROUTINES_VERSION
import projectDependencies.DependencyVersion.DEEPLINK_VERSION
import projectDependencies.DependencyVersion.EXPANDABLE_LAYOUT_VERSION
import projectDependencies.DependencyVersion.FINGERPRINT_VERSION
import projectDependencies.DependencyVersion.FIREBASE_ANALYTICS_VERSION
import projectDependencies.DependencyVersion.FIREBASE_CONFIG_VERSION
import projectDependencies.DependencyVersion.FIREBASE_CRASHLYTICS_VERSION
import projectDependencies.DependencyVersion.FIREBASE_MESSAGING_VERSION
import projectDependencies.DependencyVersion.FLEX_BOX_VERSION
import projectDependencies.DependencyVersion.FRAGMENT_KTX_VERSION
import projectDependencies.DependencyVersion.GLIDE_VERSION
import projectDependencies.DependencyVersion.GSON_VERSION
import projectDependencies.DependencyVersion.HILT_COMPILER_VERSION
import projectDependencies.DependencyVersion.HILT_VERSION
import projectDependencies.DependencyVersion.HILT_WORKER_VERSION
import projectDependencies.DependencyVersion.KEYBOARD_LISTENER_VERSION
import projectDependencies.DependencyVersion.KOTLIN_VERSION
import projectDependencies.DependencyVersion.LEAK_CANARY_VERSION
import projectDependencies.DependencyVersion.SDP_VERSION
import projectDependencies.DependencyVersion.LEGACY_SUPPORT_VERSION
import projectDependencies.DependencyVersion.LIB_PHONE_NUMBER_VERSION
import projectDependencies.DependencyVersion.LIFECYCLE_EXTENSIONS_VERSION
import projectDependencies.DependencyVersion.LIFECYCLE_KTX_VERSION
import projectDependencies.DependencyVersion.MATERIAL_VERSION
import projectDependencies.DependencyVersion.NAVIGATION_VERSION
import projectDependencies.DependencyVersion.OKHTTP
import projectDependencies.DependencyVersion.OVERSCROLL_LIBS_VERSION
import projectDependencies.DependencyVersion.PAGING_VERSION
import projectDependencies.DependencyVersion.PLAY_AUTH_VERSION
import projectDependencies.DependencyVersion.PLAY_CORE_VERSION
import projectDependencies.DependencyVersion.PLAY_LOCATION_VERSION
import projectDependencies.DependencyVersion.REACTIVESTREAMS_KTX_VERSION
import projectDependencies.DependencyVersion.RECYCLER_VIEW_VERSION
import projectDependencies.DependencyVersion.REFRESH_LAYOUT_VERSION
import projectDependencies.DependencyVersion.RETROFIT2_VERSION
import projectDependencies.DependencyVersion.ROOM_VERSION
import projectDependencies.DependencyVersion.RX_ANDROID_VERSION
import projectDependencies.DependencyVersion.RX_BINDING_VERSION
import projectDependencies.DependencyVersion.RX_JAVA_VERSION
import projectDependencies.DependencyVersion.RX_KOTLIN_VERSION
import projectDependencies.DependencyVersion.RX_LIFECYCLE_VERSION
import projectDependencies.DependencyVersion.SECURITY_VERSION
import projectDependencies.DependencyVersion.SHIMMER_VERSION
import projectDependencies.DependencyVersion.SIMPLE_CROP_VIEW_VERSION
import projectDependencies.DependencyVersion.SPLASH_SCREEN_VERSION
import projectDependencies.DependencyVersion.SQLITE_VERSION
import projectDependencies.DependencyVersion.SQL_CIPHER_VERSION
import projectDependencies.DependencyVersion.TIMBER_VERSION
import projectDependencies.DependencyVersion.VIEWMODEL_KTX_VERSION
import projectDependencies.DependencyVersion.VIEWPAGER2_VERSION
import projectDependencies.DependencyVersion.WORK_MANAGER_VERSION


object ProjectDependencies {
    val coreUI = arrayOf(
        "androidx.appcompat:appcompat:$APPCOMPAT_VERSION",
        "androidx.activity:activity-ktx:$ACTIVITY_KTX_VERSION",
        "androidx.fragment:fragment:$FRAGMENT_KTX_VERSION",
        "androidx.fragment:fragment-ktx:$FRAGMENT_KTX_VERSION",
        "com.google.android.material:material:$MATERIAL_VERSION",
        "androidx.cardview:cardview:$CARDVIEW_VERSION",
        "androidx.recyclerview:recyclerview:$RECYCLER_VIEW_VERSION",
        "androidx.core:core:$CORE_VERSION",
        "androidx.core:core-ktx:$CORE_KTX_VERSION",
        "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION",
        "androidx.swiperefreshlayout:swiperefreshlayout:$REFRESH_LAYOUT_VERSION",
        "androidx.coordinatorlayout:coordinatorlayout:$COORDINATOR_LAYOUT_VERSION",
        "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION",
        "androidx.constraintlayout:constraintlayout-solver:$CONSTRAINT_LAYOUT_VERSION",
        "androidx.viewpager2:viewpager2:$VIEWPAGER2_VERSION",
        "androidx.browser:browser:$BROWSER_VERSION",
        "androidx.core:core-splashscreen:$SPLASH_SCREEN_VERSION",
    )

    val supportLibs = arrayOf(
        "androidx.annotation:annotation:$ANNOTATION_VERSION",
        "androidx.biometric:biometric:$FINGERPRINT_VERSION",
        "androidx.legacy:legacy-support-v4:$LEGACY_SUPPORT_VERSION",
    )

    val extrasLibs = arrayOf(
        "com.github.ravindu1024:android-keyboardlistener:$KEYBOARD_LISTENER_VERSION", //to listen show/hide keyboard -> show/hide list recent search
        "com.isseiaoki:simplecropview:$SIMPLE_CROP_VIEW_VERSION",
        "com.facebook.shimmer:shimmer:$SHIMMER_VERSION",
        "com.github.cachapa:ExpandableLayout:$EXPANDABLE_LAYOUT_VERSION",
        "com.googlecode.libphonenumber:libphonenumber:$LIB_PHONE_NUMBER_VERSION",
        "io.github.everythingme:overscroll-decor-android:$OVERSCROLL_LIBS_VERSION",
        "com.airbnb:deeplinkdispatch:$DEEPLINK_VERSION",
        "com.google.android.flexbox:flexbox:$FLEX_BOX_VERSION",
        "com.github.bumptech.glide:glide:$GLIDE_VERSION",
    )

    val security = arrayOf(
        "androidx.security:security-crypto:$SECURITY_VERSION",
        "org.bouncycastle:bcpkix-jdk15to18:$BCPKIX_VERSION",
        "org.bouncycastle:bcprov-jdk15to18:$BCPROV_VERSION",
        "net.zetetic:android-database-sqlcipher:$SQL_CIPHER_VERSION",
        "androidx.sqlite:sqlite:$SQLITE_VERSION"
    )

    val networking = arrayOf(
        "com.squareup.okhttp3:okhttp:$OKHTTP",
        "com.squareup.okhttp3:logging-interceptor:$OKHTTP",
        "com.squareup.retrofit2:retrofit:$RETROFIT2_VERSION",
        "com.squareup.retrofit2:converter-gson:$RETROFIT2_VERSION",
        "com.google.code.gson:gson:$GSON_VERSION",
    )

    val rx = arrayOf(
        "io.reactivex.rxjava3:rxjava:$RX_JAVA_VERSION",
        "io.reactivex.rxjava3:rxkotlin:$RX_KOTLIN_VERSION",
        "io.reactivex.rxjava3:rxandroid:$RX_ANDROID_VERSION",
        "com.jakewharton.rxbinding4:rxbinding:$RX_BINDING_VERSION",
    )

    val di = arrayOf(
        "com.google.dagger:hilt-android:$HILT_VERSION",
        "androidx.hilt:hilt-work:$HILT_WORKER_VERSION"
    )

    val lifecycle = arrayOf(
        "androidx.lifecycle:lifecycle-extensions:$LIFECYCLE_EXTENSIONS_VERSION",
        "androidx.lifecycle:lifecycle-livedata:$LIFECYCLE_KTX_VERSION",
        "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_KTX_VERSION",
        "androidx.lifecycle:lifecycle-reactivestreams:$LIFECYCLE_KTX_VERSION",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:$VIEWMODEL_KTX_VERSION",
        "androidx.lifecycle:lifecycle-reactivestreams-ktx:$REACTIVESTREAMS_KTX_VERSION",
    )

    val paging = arrayOf(
        "androidx.paging:paging-common:$PAGING_VERSION",
        "androidx.paging:paging-runtime:$PAGING_VERSION",
    )

    val database = arrayOf(
        "androidx.room:room-runtime:$ROOM_VERSION",
        "androidx.room:room-ktx:$ROOM_VERSION",
    )

    val work = arrayOf(
        "androidx.work:work-runtime-ktx:$WORK_MANAGER_VERSION",
        "androidx.work:work-runtime:$WORK_MANAGER_VERSION"
    )

    val navigation = arrayOf(
        "androidx.navigation:navigation-ui-ktx:$NAVIGATION_VERSION",
        "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION",
    )

    val playServices = arrayOf(
        "com.google.android.play:core:$PLAY_CORE_VERSION",
        "com.google.android.gms:play-services-location:$PLAY_LOCATION_VERSION",
        "com.google.android.gms:play-services-auth:$PLAY_AUTH_VERSION",
    )

    val firebase = arrayOf(
        "com.google.firebase:firebase-messaging:$FIREBASE_MESSAGING_VERSION",
        "com.google.firebase:firebase-config-ktx:$FIREBASE_CONFIG_VERSION",
    )

    val processing = arrayOf(
        "com.google.dagger:hilt-android-compiler:$HILT_VERSION",
        "com.google.dagger:hilt-compiler:$HILT_COMPILER_VERSION",
//        "com.github.bumptech.glide:compiler:$GLIDE_VERSION",
//        "androidx.room:room-compiler:$ROOM_VERSION",
//        "com.airbnb:deeplinkdispatch-processor:$DEEPLINK_VERSION"
    )

    val logging = arrayOf(
        "com.jakewharton.timber:timber:$TIMBER_VERSION"
    )

    val analytics = arrayOf(
        "com.google.firebase:firebase-crashlytics-ktx:$FIREBASE_CRASHLYTICS_VERSION",
        "com.google.firebase:firebase-analytics-ktx:$FIREBASE_ANALYTICS_VERSION",
    )

    val devDependencies = arrayOf(
        "com.squareup.leakcanary:leakcanary-android:$LEAK_CANARY_VERSION",
    )

    val sdpDependency = arrayOf(
        "com.intuit.sdp:sdp-android:$SDP_VERSION",
    )
}