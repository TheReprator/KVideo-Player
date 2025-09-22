// swift-tools-version: 6.1
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "AppleSPM",
    platforms: [
        .iOS(.v16), .watchOS(.v9), .tvOS(.v16)
    ],
    products: [
        .library(name: "AppleWatchPlayer", targets: ["AppleWatch"]),
        .library(name: "AppleiPhoneCompanion", targets: ["AppleiPhoneCompanion"]),
        .library(name: "AppleTVPlayer", targets: ["AppleTV"])
    ],
    targets: [
        // watchOS-only: plays HLS via AVPlayer, sends requests via WCSession
        .target(
            name: "AppleWatch",
            dependencies: [
                .target(name: "VideoFrameWorkKMP")
            ],
            path: "Sources/AppleWatch",
            swiftSettings: [
                .define("WATCHOS_ONLY", .when(platforms: [.watchOS]))
            ]
        ),

        // iOS-only: companion app that uses your KMP logic
        .target(
            name: "AppleiPhoneCompanion",
            dependencies: [
                .target(name: "VideoFrameWorkKMP")
            ],
            path: "Sources/AppleiPhoneCompanion",
            swiftSettings: [
                .define("IOS_ONLY", .when(platforms: [.iOS]))
            ]
        ),

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
