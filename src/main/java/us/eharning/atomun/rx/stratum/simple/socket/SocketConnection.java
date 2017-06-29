/*
 * Copyright 2017 Thomas Harning Jr. <harningt@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package us.eharning.atomun.rx.stratum.simple.socket;

import io.reactivex.Flowable;
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
    public Flowable<StratumConnectionEvent> getConnectionEvents() {
        return null;
    }

    @Override
    public Flowable<StratumMessage> sendMessage(StratumMessage request) {
        return null;
    }

    @Override
    public Flowable<StratumMessage> subscribe(StratumMessage request) {
        return null;
    }
}
