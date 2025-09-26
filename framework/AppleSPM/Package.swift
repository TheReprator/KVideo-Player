// swift-tools-version: 6.1
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "AppleSPM",
    platforms: [
        .iOS(.v16), .watchOS(.v9), .tvOS(.v16)
    ],
    products: [
        .library(name: "AppleTVPlayer", targets: ["AppleTV"])
    ],
    targets: [
      
        // tvOS-only
        .target(
            name: "AppleTV",
            dependencies: [
                .target(name: "VideoFrameWorkKMP")
            ],
            path: "Sources/AppleTV",
            swiftSettings: [
                .define("TVOS_ONLY", .when(platforms: [.tvOS]))
            ]
        ),

        .binaryTarget(
            name: "VideoFrameWorkKMP",
            path: "Framework/KMP_player.xcframework"
        )
    ]
)
