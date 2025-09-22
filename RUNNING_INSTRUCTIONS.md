# Running VBANurseryApp

## Prerequisites

1. Android SDK configured (already set up with path: `/Users/sanjaysinghbisht/Library/Android/sdk`)
2. Android emulator or device connected (emulator detected: Kiosk_15inch_1200x1920)

## Running on Different Platforms

### Android
The app has been successfully built and installed on the Android emulator.

To build the APK:
```bash
./gradlew :composeApp:assembleDebug
```

To install on connected device/emulator:
```bash
./gradlew :composeApp:installDebug
```

To run directly on device/emulator:
```bash
./gradlew :composeApp:run
```

Or use the convenience script:
```bash
./run-android.sh
```

APK location: `composeApp/build/outputs/apk/debug/composeApp-debug.apk`

### Desktop
To run the desktop application:
```bash
./gradlew :composeApp:run
```

### Web (WASM)
To run the web application with WASM:
```bash
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

### Web (JS)
To run the web application with JS:
```bash
./gradlew :composeApp:jsBrowserDevelopmentRun
```

### iOS
To run the iOS application:
1. Open `iosApp/iosApp.xcodeproj` in Xcode
2. Select target device/simulator
3. Click Run

### Server
To run the server application:
```bash
./gradlew :server:run
```

## Splash Screen Details

- Display duration: 5 seconds
- Localization: English and Hindi
- Theme support: Light and Dark mode
- Navigation: After 5 seconds, navigates to Home screen

The splash screen implementation follows Clean Architecture principles with proper separation of concerns across domain, data, presentation, and UI layers.