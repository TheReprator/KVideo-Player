## 🙌 Inspiration & Credits

### Inspired by:
- [Video.js](https://github.com/videojs/video.js)  
  A powerful and extensible web video player — this project builds upon its robust playback capabilities by wrapping it for use in Kotlin Multiplatform WASM/JS.

### Credits:
- [Hamamas/Kotlin-Wasm-Html-Interop](https://github.com/Hamamas/Kotlin-Wasm-Html-Interop)  
  Special thanks for providing seamless HTML and DOM interop in Kotlin/WASM. This library made it possible to build this project **without relying on `dynamic`, `js()`, or unsafe interop hacks**.

- [ShreyashKore/wonderous_compose](https://github.com/ShreyashKore/wonderous_compose)  
  Helped me figure out the trick to make it run on Kotlin/JS.

- [https://github.com/open-ani/mediamp/tree/main/mediamp-vlc)  
  Thanks to this, now i can run the desktop app without depending on system, whether vlc is installed or not, i
   can ship the vlc binaries with my code, to run

---

## 🎬 Demo

Check out the video demo showing how `VideoWeb-KMP` works:  
![Demo Preview](./others/demo/demo.gif)

---

## ✅ Completed Platform Targets
- Kotlin/WASM
- Kotlin/JS

## 🚧 Pending Platform Targets
- Desktop
- Android
- iOS
- TV
---

## ✅ Features Completed
- `initialization`, `play`, `pause`, `dispose`, `changeMedia`

## 🔜 Pending Features
- Event tracking
- Custom UI
- Testing

## ⏭️ Next Upcoming Target
- Android or Desktop


## To add native binaries
2. So far, your file tree should look like this:
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
