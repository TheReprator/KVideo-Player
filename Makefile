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

# Git count on a branch
gitCount:
	@echo "⏳Git count on a branch"
	git rev-list --count HEAD
	@echo "✅ Done!"
