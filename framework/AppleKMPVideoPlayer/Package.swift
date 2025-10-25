// swift-tools-version: 6.1
// The swift-tools-version declares the minimum version of Swift required to build this package.
//https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-spm-export.html#exporting-multiple-modules-as-an-xcframework

import PackageDescription

let package = Package(
    name: "AppleKMPVideoPlayer",
    platforms: [
        .tvOS(.v16)
    ],
    products: [
        .library(name: "AppleVideoPlayer", targets: ["VideoPlayer"])
    ],
    targets: [
        .target(
            name: "VideoPlayer",
            dependencies: [
                .target(name: "VideoFrameWork")
            ],
            path: "Sources/VideoPlayer"
        ),

        .binaryTarget(
            name: "VideoFrameWork",
            path: "Framework/VideoFrameWorkKMP.xcframework"
        )
    ]
)