import Foundation
import WatchConnectivity

final class WatchConnectivityService: NSObject, WCSessionDelegate {
    
    func session(_ session: WCSession, activationDidCompleteWith activationState: WCSessionActivationState, error: (any Error)?) {
    }
    
    func sessionDidBecomeInactive(_ session: WCSession) {
    }
    
    func sessionDidDeactivate(_ session: WCSession) {
    }
    
    
    private let session: WCSession
    
//    override init() {
//        guard WCSession.isSupported() else { fatalError() } // In a productive code, this should handled more gracefully
//        self.session = WCSession.default
//        self.session.delegate = self
//        self.session.activate()
//    }
    init(session: WCSession = WCSession.default) {
        self.session = session
        self.session.activate()
    }

    
}
