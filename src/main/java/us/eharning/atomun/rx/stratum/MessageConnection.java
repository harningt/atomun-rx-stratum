package us.eharning.atomun.rx.stratum;

import rx.Observable;
import rx.functions.Func1;

import java.io.IOException;
import java.io.Writer;

/**
 * Abstract class to build connections off of to simplify message handling.
 */
public abstract class MessageConnection implements StratumConnection {
    private StratumMessageCodec messageCodec = new StratumMessageCodec();
    private final StringToMessageMapper stringToMessageMapper = new StringToMessageMapper();

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

    protected abstract Observable<String> getMessageStrings();
    protected Observable<StratumMessage> getMessages() {
        return getMessageStrings().map(stringToMessageMapper);
    }

    private class StringToMessageMapper implements Func1<String, StratumMessage> {
        @Override
        public StratumMessage call(String s) {
            if (s == null) {
                return null;
            }
            return messageCodec.decodeMessage(s);
        }
    }
}
