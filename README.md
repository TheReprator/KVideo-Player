# Cross-Platform Media Player

This can play HLS, DASH and SmoothStreaming files across all platforms.

For **iOS**, there are limitations:
- The URL should end with `.mpd`
- Its format should match as described in **MPD_DASH_FORMAT.md**

---

## 🙌 Inspiration & Credits

### Inspired by:
- [Video.js](https://github.com/videojs/video.js)  
  A powerful and extensible web video player — this project builds upon its robust playback capabilities by wrapping it for use in Kotlin Multiplatform WASM/JS.

### Credits:
- [Hamamas/Kotlin-Wasm-Html-Interop](https://github.com/Hamamas/Kotlin-Wasm-Html-Interop)  
  Special thanks for providing seamless HTML and DOM interop in Kotlin/WASM. This library made it possible to build this project **without relying on `dynamic`, `js()`, or unsafe interop hacks**.

- [ShreyashKore/wonderous_compose](https://github.com/ShreyashKore/wonderous_compose)  
  Helped me figure out the trick to make it run on Kotlin/JS.

- [open-ani/mediamp (mediamp-vlc)](https://github.com/open-ani/mediamp/tree/main/mediamp-vlc)  
  Thanks to this, **Desktop support** is now possible without requiring VLC to be installed on the system — VLC binaries are shipped with the application itself.

- [MPEGDASHAVPlayerDemo](https://github.com/h-anders-unext/MPEGDASHAVPlayerDemo)  
  Thanks to this, I got some idea to parse DASH files.

---

## 🎬 Demo

**Web Demo:**  
![Web Demo](others/demo/web.gif)

**Desktop Demo:**  
![Desktop Demo](others/demo/desktop.gif)

**iOS Demo:**  
![iOS Demo](others/demo/ios.gif)

**Android Demo:**  
![Android Demo](others/demo/android.gif)

---

## ✅ Completed Platform Targets
- Kotlin/WASM
- Kotlin/JS
- Kotlin/Desktop (**bundled VLC binaries — no external installation needed**)
- Android/Phone
- iOS

---

## 🚧 Pending Platform Targets
- TV

---

## ✅ Features Completed
- Initialization
- Play
- Pause
- Dispose
- Change media

---

## 🔜 Pending Features
- Event tracking
- Custom UI
- Testing

---

## ⏭️ Next Upcoming Target
- **Desktop (With own parsing, without depending on VLC, may be VLC can be removed so that i can move back to Apache licence)**

---

## 📦 Native Binary Packaging (Desktop)

Desktop support ships with **pre-bundled VLC binaries**, meaning it works even if VLC is not installed on the user’s machine.

### Example folder structure:
```
desktopApp/
 |- appResources/
 |  |- macos-arm64/
 |  |  |- lib/
 |  |  |  |- libvlc.dylib
 |  |  |- plugins/
 |  |  |  |- xxx.dylib
 |  |- macos-x64/
 |  |  |- lib/
 |  |  |  |- libvlc.dylib
 |  |  |- plugins/
 |  |  |  |- xxx.dylib
 |  |- windows-x64/
 |  |  |- lib/
 |  |  |  |- libvlc.dll
 |  |  |  |- plugins/
 |  |- linux-x64/
 |  |  |- lib/
 |  |  |  |- libvlc.so
 |- src/
 |- build.gradle.kts
```

### Notes:
- It is in very early stage, so mostly for development purposes. Once all platforms are integrated, we’ll deep dive into proper functionality.
- Currently, **macOS binaries** are included. You can extend this to other platforms by adding respective VLC builds.
- On the **first run**, VLC may take ~2 minutes to cache/generate `plugins.dat`.  
  From the **second run onward**, startup time reduces to ~7–10 seconds.
