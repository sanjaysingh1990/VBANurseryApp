# Android Setup Instructions

To run the VBANurseryApp on Android, you need to configure the Android SDK properly.

## Prerequisites

1. Android Studio installed
2. Android SDK installed
3. Environment variables set up

## Setup Steps

1. **Create local.properties file** in the project root directory:
   ```
   sdk.dir=/path/to/your/android/sdk
   ```

   On macOS, this is typically:
   ```
   sdk.dir=/Users/{username}/Library/Android/sdk
   ```

2. **Or set environment variable**:
   ```bash
   export ANDROID_HOME=/path/to/your/android/sdk
   ```

## Running the App

After setting up the Android SDK, you can run the app using:

```bash
# Assemble debug APK
./gradlew :composeApp:assembleDebug

# Install and run on connected device/emulator
./gradlew :composeApp:installDebug

# Or run directly (if device/emulator is connected)
./gradlew :composeApp:run
```

## Alternative: Using Android Studio

1. Open the project in Android Studio
2. Android Studio will automatically detect and configure the SDK
3. Select the composeApp run configuration
4. Click Run

The splash screen implementation we've created will work on Android exactly the same way as it does on Desktop, since we're using Compose Multiplatform for shared UI.