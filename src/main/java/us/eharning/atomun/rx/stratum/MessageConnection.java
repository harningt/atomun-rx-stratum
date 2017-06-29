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
package us.eharning.atomun.rx.stratum;

import io.reactivex.Flowable;

import java.io.IOException;
import java.io.Writer;

/**
 * Abstract class to build connections off of to simplify message handling.
 */
public abstract class MessageConnection implements StratumConnection {
    private StratumMessageCodec messageCodec = new StratumMessageCodec();

    protected abstract Writer beginMessage() throws IOException;

    protected void finishMessage(Writer writer) throws IOException {
        /* Append the newline indicating that the message is complete */
        writer.append('\n');
        /* Flush the stream to ensure all data is written out */
        writer.flush();
    }

    protected void publishMessage(StratumMessage message) throws IOException {
        Writer writer = beginMessage();
        messageCodec.encodeMessage(writer, message);
        finishMessage(writer);
    }

    protected abstract Flowable<String> getMessageStrings();

    protected Flowable<StratumMessage> getMessages() {
        return getMessageStrings().map(s -> (s == null) ? null : messageCodec.decodeMessage(s));
    }
}
