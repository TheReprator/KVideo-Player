//
//  ContentView.swift
//  AppleTVPlayer
//
//  Created by Deep Sandhya on 08/09/25.
//

import SwiftUI
import AppleTvPlayerUi

struct ContentView: View {
    let viewModel: ASVideoPlayerViewModel
    init(viewModel: ASVideoPlayerViewModel = .init(initialUrl: "https://bitmovin-a.akamaihd.net/content/sintel/sintel.mpd")) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        VStack {
            Button("Change Source") {
                viewModel.changeSource("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
            }
            ASVideoPlayerView(viewModel: viewModel)
        }
    }
}

#Preview {
    ContentView()
}
