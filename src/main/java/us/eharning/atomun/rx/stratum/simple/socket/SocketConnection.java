package us.eharning.atomun.rx.stratum.simple.socket;

import rx.Observable;
import us.eharning.atomun.rx.stratum.StratumConnection;
import us.eharning.atomun.rx.stratum.StratumConnectionEvent;
import us.eharning.atomun.rx.stratum.StratumMessage;

import java.net.URI;

/**
 * Created by harningt on 2/29/16.
 */
public class SocketConnection implements StratumConnection {
    public SocketConnection(URI target) {
    }
    @Override
    public Observable<StratumConnectionEvent> getConnectionEvents() {
        return null;
    }

    @Override
    public Observable<StratumMessage> sendMessage(StratumMessage request) {
        return null;
    }

    @Override
    public Observable<StratumMessage> subscribe(StratumMessage request) {
        return null;
    }
}