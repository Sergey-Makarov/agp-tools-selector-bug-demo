# Android manifest's tools:selector attribute bug demo

## Description
This is a demo app that has a dependency on [fingerprintjs-android](https://github.com/fingerprintjs/fingerprintjs-android) library. This library uses, amongh others, the `android.permission.USE_BIOMETRIC` permission.

If we don't want this permission to be declared inside the final manifest file, we are supposed (according to [docs](https://developer.android.com/build/manage-manifests#marker_selector)) to include this piece of code into the app's manifest file:
```
<uses-permission
    android:name="android.permission.USE_BIOMETRIC"
    tools:node="remove"
    tools:selector="com.fingerprintjs.android.fingerprint" />
```

The goal of this sample project is to demonstrate that the solution above does NOT work.
The only way to remove the aforementioned permission declaration is to use this piece of code, i.e. without `tools:selector` attribute:
```
<uses-permission
    android:name="android.permission.USE_BIOMETRIC"
    tools:node="remove" />
```

But this may not be a solution for many people because of a lack of a fine-grained control over the manifest merging process.

## Reproduction steps
1. call `./gradlew app:assembleDebug` task
2. compare the manifest files inside those APKs:
    * `app/build/outputs/apk/withSelector/debug/app-withSelector-debug.apk`
    * `app/build/outputs/apk/withoutSelector/debug/app-withoutSelector-debug.apk`

### Actual result:
The former APK still has the `USE_BIOMETRIC` permission declaration, whereas the latter doesn't.

### Expected result:
Both APK's don't have `<uses-permission android:name="android.permission.USE_BIOMETRIC">` inside their manifests.

## Bug report

Issued [here](https://issuetracker.google.com/issues/335640701).

## Posts on that topic
Here are some posts I found mentioning the exact same issue: [1](https://stackoverflow.com/questions/29675423/is-gradle-toolsselector-for-permissions-works-ever), [2](https://stackoverflow.com/questions/27962789/android-gradle-manifest-merger-override-uses-permission-attribute-coming-from#comment45905521_27963177), [3](https://stackoverflow.com/questions/27962789/android-gradle-manifest-merger-override-uses-permission-attribute-coming-from#comment44320864_27963177).
