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
