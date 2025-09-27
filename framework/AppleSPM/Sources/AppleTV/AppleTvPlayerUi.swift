import SwiftUI
import VideoFrameWorkKMP
import AVKit

public struct ASVideoPlayerView: View {
    @ObservedObject public var viewModel: ASVideoPlayerViewModel

    public init(viewModel: ASVideoPlayerViewModel) {
        self.viewModel = viewModel
    }
    
    public var body: some View {
        VStack {
            switch viewModel.loadingState {
            case .loading:
                ProgressView()
            case .hasData(let t):
                VideoPlayer(player: viewModel.stateController.playerController.player)
            case .hasNoData:
                Text("Video not available")
            case .error(let err):
                Text(err)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .task {
            viewModel.loadInitialPlayerData()
        }
    }
}

public class ASVideoPlayerViewModel: ObservableObject {
    enum LoadingState<T> {
        case loading
        case hasData(T)
        case hasNoData
        case error(String)
    }
    @Published var loadingState: LoadingState<Bool> = .loading
    
    @MainActor private(set) var stateController: PlaybackStateControllerImplIos
    private(set) var videoInitOptions: VideoInitOptionModal
    
    public init(initialUrl: String) {
        self.loadingState = .loading
        
        // Create DashHandler instance
        let dashHandler = DashHandlerImpl(delegateAsset: AVAssetResourceLoaderProtocolCompanion.shared.getAVAssetResourceLoaderProtocolInstance())
        
        // Create TvOsVideoConnectivityHandler with DashHandler
        let connectivityHandler = TvOsVideoConnectivityHandler(dashHandler: dashHandler)
        
        // Initialize PlaybackStateControllerImplIos with connectivity handler
        self.stateController = PlaybackStateControllerImplIos(connectivityHandler: connectivityHandler)

        let videoSource = VideoSource(
            src: initialUrl,
            poster: ""
        )
        self.videoInitOptions = VideoInitOptionModal(controls: false, autoplay: true, poster: nil, preload: "auto", muted: false, id: nil, sources: [videoSource])
    }
    
    @MainActor
    func loadInitialPlayerData() {
        Task {
            do {
                let controller = stateController
                let ss = try await controller.setupPlayer()
                await MainActor.run {
                    guard let isAvailable = ss as? Bool else {
                        loadingState = .error("Video Player failed")
                        return
                    }
                    if isAvailable {
                        stateController.doInitPlayer(initOptions: videoInitOptions)
                        self.loadingState = .hasData(true)
                    } else {
                        loadingState = .error("Video Player not ready")
                    }
                }
                
            } catch {
                print("Error initializing player: \(error)")
                loadingState = .error("Video Player thrown exception")
            }
        }
    }
    
    @MainActor public func changeSource(_ url: String) {
        self.changeMediaSource(to: url)
    }
    
    @MainActor private func changeMediaSource(to url: String) {
        let videoSource = VideoSource(src: url, poster: "")
        stateController.player.changeMedia(videoSource: videoSource)
    }
}
