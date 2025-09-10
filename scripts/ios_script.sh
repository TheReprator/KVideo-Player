#!/bin/bash

echo "🚀 Building Compose Multiplatform App..."

# Build the shared Compose framework
echo "📦 Building shared Compose Multiplatform framework..."
./gradlew :sample:compileKotlinIosSimulatorArm64
./gradlew :sample:linkDebugFrameworkIosSimulatorArm64

if [ $? -eq 0 ]; then
    echo "✅ Compose Multiplatform framework built successfully!"

    echo "📱 Starting iOS Simulator..."
    xcrun simctl boot "iPhone 16 Pro" 2>/dev/null || echo "Simulator already running"
    open -a Simulator

    echo "🍎 Opening iOS project in Xcode..."
    open sample/ios/iosApp/iosApp.xcodeproj

    echo ""
    echo "🎯 NEXT STEPS IN XCODE:"
    echo "1. Wait for Xcode to open"
    echo "2. Click on 'Any iOS Device' dropdown (top-left)"
    echo "3. Select 'iPhone 16 Pro' from the list"
    echo "4. Press Cmd+R or click ▶️ to run"
    echo ""
else
    echo "❌ Framework build failed!"
    exit 1
fi