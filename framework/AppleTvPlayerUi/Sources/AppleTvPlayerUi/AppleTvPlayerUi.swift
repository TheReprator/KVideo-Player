import SwiftUI
import VideoFrameWorkKMP
import AVKit

public struct KVideoPlayerView: View {
  @MainActor private let stateController = PlaybackStateControllerImplIos()
  let videoInitOptions: VideoInitOptionModal
  @State private var isAppInitialized: Bool? = nil

  public init(videoInitOptions: VideoInitOptionModal) {
    self.videoInitOptions = videoInitOptions
  }

  public var body: some View {
    ZStack {
      if isAppInitialized == nil {
        ProgressView()
      } else if isAppInitialized == true {
//       stateController.doInitPlayer(initOptions: videoInitOptions)
//        PrepareVideoSetupView(
//          stateController: stateController,
//          videoInitOptions: videoInitOptions
//        )
      } else {
        // Player setup failed
        Text("An unknown error occurred during video setup.")
      }
    }
    .frame(maxWidth: .infinity, maxHeight: .infinity)
//    .onAppear {
//      Task { @MainActor in
//        let controller = stateController
//        let kmpSuccess = try await controller.setupPlayer()
//        isAppInitialized = kmpSuccess.boolValue
//      }
//    }

  }
}


public struct PrepareVideoSetupView: View {
    
  public let viewModel: PrepareVideoSetupViewModel
    
  public init(viewModel: PrepareVideoSetupViewModel) {
    self.viewModel = viewModel
  }

  public  var body: some View {

    VideoPlayer(player: viewModel.stateController.playerController.player)
      .frame(maxWidth: .infinity, maxHeight: .infinity)
      .onDisappear {
        let concretePlayer = viewModel.stateController.player
        if !concretePlayer.isDisposed() {
          concretePlayer.dispose()
        }
      }
      .task {
        viewModel.setup()
      }
  }
}

public class PrepareVideoSetupViewModel: ObservableObject {
  @MainActor @Published var stateController: PlaybackStateControllerImplIos = PlaybackStateControllerImplIos()
  var videoInitOptions: VideoInitOptionModal
  public init() {
    let videoSource = VideoSource(
      src: "https://bitmovin-a.akamaihd.net/content/sintel/sintel.mpd",
      poster: ""
    )
    self.videoInitOptions = VideoInitOptionModal(controls: false, autoplay: true, poster: nil, preload: "auto", muted: false, id: nil, sources: [videoSource])
  }

  @MainActor public func updateSource(source: String) {
    let videoSource = VideoSource(
      src: source,
      poster: ""
    )
    stateController.player.changeMedia(videoSource: videoSource)
  }

  @MainActor
  func setup() {
    Task {
      do {
        let controller = stateController
        let ss = try await controller.setupPlayer()
        stateController.doInitPlayer(initOptions: videoInitOptions)
      } catch {
        print("Error initializing player: \(error)")
      }
    }
  }

  @MainActor
  public func play() {
    stateController.player.play()
  }

  @MainActor
  public func pause() {
    stateController.player.pause()
  }
}
