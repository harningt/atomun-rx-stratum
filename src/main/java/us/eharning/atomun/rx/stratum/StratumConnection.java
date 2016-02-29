package us.eharning.atomun.rx.stratum;

import rx.Observable;

/**
 * Created by harningt on 2/29/16.
 */
public interface StratumConnection {
    Observable<StratumConnectionEvent> getConnectionEvents();

    Observable<StratumMessage> sendMessage(StratumMessage request);
    Observable<StratumMessage> subscribe(StratumMessage request);
}
