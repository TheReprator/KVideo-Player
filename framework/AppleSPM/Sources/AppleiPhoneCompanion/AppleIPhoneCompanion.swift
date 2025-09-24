
import Foundation
import WatchConnectivity
import VideoFrameWorkKMP

public class MPDSessionHandler: NSObject, WCSessionDelegate {
    public func session(_ session: WCSession, activationDidCompleteWith activationState: WCSessionActivationState, error: (any Error)?) {
        <#code#>
    }
    
    public func sessionDidBecomeInactive(_ session: WCSession) {
        <#code#>
    }
    
    public func sessionDidDeactivate(_ session: WCSession) {
        <#code#>
    }
    
    private let resolver = MpdToHlsResolver()

    public override init() {
        super.init()
        if WCSession.isSupported() {
            WCSession.default.delegate = self
            WCSession.default.activate()
        }
    }

    public func session(_ session: WCSession, didReceiveMessage message: [String : Any]) {
        guard let mpdUrl = message["mpdUrl"] as? String else { return }

        Task {
            do {
                let hlsUrl = try await resolver.resolve(mpdUrl: mpdUrl)
                session.sendMessage(["hlsUrl": hlsUrl], replyHandler: nil, errorHandler: nil)
            } catch {
                print("Failed: \(error)")
            }
        }
    }
}
