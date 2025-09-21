//
// Created by Deep Sandhya on 20/09/25.
//

import SwiftUI
import AVFoundation
import _AVKit_SwiftUI

public struct WatchVideoPlayerView: View {
    @StateObject private var wcManager = WatchConnectivityManager()

    public init() {}

    public var body: some View {
        VStack {
            if let url = wcManager.hlsURL {
                VideoPlayer(player: AVPlayer(url: url))
            } else {
                Button("Play MPD") {
                    wcManager.requestMPDPlayback(mpdUrl: "https://example.com/video.mpd")
                }
            }
        }
    }
}
