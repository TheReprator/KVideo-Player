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

# Run Desktop hot reload build
buildDesktop: cleanBuild
	@echo "⏳Desktop Hot reload build"
	$(GRADLEW) sample:run
	@echo "✅ Done!"

# Generate KMP Player framework build
buildTvKMP: cleanBuild
	@echo "⏳KMP XCframework generation in progress"
	$(GRADLEW) :framework:KMP-player:tasks --all; $(GRADLEW) :framework:KMP-player:assembleKMP-playerDebugXCFramework
	rm -rf framework/AppleTvPlayerUi/Framework; mkdir -p framework/AppleTvPlayerUi/Framework;
	cp -r Framework/KMP-player/build/XCFrameworks/debug/KMP_player.xcframework/tvos-arm64 framework/AppleTvPlayerUi/Framework;
	cp -r Framework/KMP-player/build/XCFrameworks/debug/KMP_player.xcframework/tvos-arm64_x86_64-simulator framework/AppleTvPlayerUi/Framework
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
