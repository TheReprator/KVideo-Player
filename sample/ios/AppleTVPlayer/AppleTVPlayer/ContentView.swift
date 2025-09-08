//
//  ContentView.swift
//  AppleTVPlayer
//
//  Created by Deep Sandhya on 08/09/25.
//

import SwiftUI
import AppleTvPlayerUi

struct ContentView: View {
    let viewModel = PrepareVideoSetupViewModel()
    var body: some View {
        VStack {
            PrepareVideoSetupView(viewModel: viewModel)
            HStack {
                Button("play") {
                    viewModel.play()
                }
                Button("pause") {
                    viewModel.pause()
                }
                Button("change media") {
                    viewModel.updateSource(source: "http://sample.vodobox.com/planete_interdite/planete_interdite_alternate.m3u8")
                }
            }
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
