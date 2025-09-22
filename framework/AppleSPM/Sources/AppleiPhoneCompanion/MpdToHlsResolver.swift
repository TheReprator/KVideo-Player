
import Foundation
import WatchConnectivity
import VideoFrameWorkKMP

class MpdToHlsResolver {
    let dashHandler: DashHandler
    
    init(dashHandler: DashHandler = DashHandlerImpl()) {
        self.dashHandler = dashHandler
    }
    
    func resolve(mpdUrl: String) async throws -> String {
//        dashHandler.playDashFile(url: <#T##String#>, play: <#T##(AVPlayerItem) -> Void#>)
//        return DashBridge.shared.convertMpdToHls(mpdUrl)
        return ""
    }
}
