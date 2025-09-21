//
// Created by Deep Sandhya on 20/09/25.
//

import Foundation
import WatchConnectivity
import AVKit

public class WatchConnectivityManager: NSObject, WCSessionDelegate, ObservableObject {
    public func session(_ session: WCSession, activationDidCompleteWith activationState: WCSessionActivationState, error: (any Error)?) {
        <#code#>
    }
    
    public func sessionDidBecomeInactive(_ session: WCSession) {
        <#code#>
    }
    
    public func sessionDidDeactivate(_ session: WCSession) {
        <#code#>
    }
    
    @Published public var hlsURL: URL?

    public override init() {
        super.init()
        if WCSession.isSupported() {
            WCSession.default.delegate = self
            WCSession.default.activate()
        }
    }

    public func requestMPDPlayback(mpdUrl: String) {
        WCSession.default.sendMessage(["mpdUrl": mpdUrl], replyHandler: nil, errorHandler: nil)
    }

    public func session(_ session: WCSession, didReceiveMessage message: [String : Any]) {
        if let urlString = message["hlsUrl"] as? String, let url = URL(string: urlString) {
            DispatchQueue.main.async {
                self.hlsURL = url
            }
        }
    }
}
