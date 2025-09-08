// swift-tools-version: 6.1
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "AppleTvPlayerUi",
    platforms: [
        .tvOS(.v16)
    ],
    products: [
        .library(
            name: "AppleTvPlayerUi",
            targets: ["AppleTvPlayerUi"]
        ),
    ],
    targets: [
        .target(
            name: "AppleTvPlayerUi",
            dependencies: [
                .target(name: "VideoFrameWorkKMP")
            ]),
        .binaryTarget(
            name: "VideoFrameWorkKMP",
            path: "Framework/KMP_player.xcframework"
        )
    ]
)
