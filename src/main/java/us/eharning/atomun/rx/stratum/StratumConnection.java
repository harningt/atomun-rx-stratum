package us.eharning.atomun.rx.stratum;

import io.reactivex.Flowable;

/**
 * Created by harningt on 2/29/16.
 */
public interface StratumConnection {
    Flowable<StratumConnectionEvent> getConnectionEvents();

    Flowable<StratumMessage> sendMessage(StratumMessage request);
    Flowable<StratumMessage> subscribe(StratumMessage request);
}
