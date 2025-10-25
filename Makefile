# === CONFIG ===
GRADLEW=./gradlew

# clear terminal
clear:
	clear

# clear build
cleanBuild: clear
	@echo "⏳ Clean build"
	$(GRADLEW) clean
	@echo "✅ Done!"

# List gradle task
taskList: cleanBuild
	@echo "⏳ Task List"
	$(GRADLEW) task
	@echo "✅ Done!"

# Run wasm build
buildWasmWeb: cleanBuild
	@echo "⏳Web Wasm build"
	$(GRADLEW) sample:wasmJsBrowserDevelopmentRun --continuous
	@echo "✅ Done!"

# Run js build
buildJsWeb: cleanBuild
	@echo "⏳Web JS Hot build"
	$(GRADLEW) sample:jsBrowserDevelopmentRun --continuous
	@echo "✅ Done!"

# Run Desktop build
buildDesktop: cleanBuild
	@echo "⏳Desktop build"
	$(GRADLEW) sample:run
	@echo "✅ Done!"

# Run Desktop hot reload build
buildDesktopHot: cleanBuild
	@echo "⏳Desktop Hot build"
	$(GRADLEW) sample:hotRunDesktop --auto
	@echo "✅ Done!"

# Generate KMP Player framework build
buildAppleSPM: cleanBuild
	@echo "⏳KMP XCFramework generation in progress"
	$(GRADLEW) :framework:KMP-player:tasks --all; $(GRADLEW) :framework:KMP-player:assembleVideoFrameWorkKMPDebugXCFramework
	 mkdir -p framework/AppleKMPVideoPlayer/Framework; cp -r Framework/KMP-player/build/XCFrameworks/debug/VideoFrameWorkKMP.xcframework framework/AppleKMPVideoPlayer/Framework
	@echo "✅ Done!"

# Run IOS build
buildIOS: cleanBuild
	@echo "⏳IOS build"
	chmod +x ./scripts/ios_script.sh
	./scripts/ios_script.sh
	@echo "✅ Done!"

# Git count on a branch
gitCount:
	@echo "⏳Git count on a branch"
	git rev-list --count HEAD
	@echo "✅ Done!"
