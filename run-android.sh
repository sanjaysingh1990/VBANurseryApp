#!/bin/bash

# Script to run the VBANurseryApp on Android emulator/device

echo "Starting VBANurseryApp on Android..."

# Check if emulator/device is connected
echo "Checking for connected devices..."
DEVICES=$(/Users/sanjaysinghbisht/Library/Android/sdk/platform-tools/adb devices | grep emulator | wc -l)

if [ $DEVICES -gt 0 ]; then
    echo "Device/emulator found. Installing and running the app..."
    
    # Install the app
    echo "Installing the app..."
    ./gradlew :composeApp:installDebug
    
    # Launch the app
    echo "Launching the app..."
    /Users/sanjaysinghbisht/Library/Android/sdk/platform-tools/adb shell am start -n org.example.project/.MainActivity
    
    echo "App should now be running on your Android device/emulator!"
else
    echo "No Android device/emulator found. Please start an emulator or connect a device."
fi