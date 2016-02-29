package us.eharning.atomun.rx.stratum;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by harningt on 2/29/16.
 */
public class StratumMessageCodec {
    private static final Gson gson = new Gson();
    private static final AtomicLong counter = new AtomicLong();
    public String method;
    public Object[] parameters;

    public void encodeMessage(Writer writer, StratumMessage message) throws IOException {
        long id = counter.getAndIncrement();
        JsonWriter jsonWriter = new JsonWriter(writer);
        jsonWriter.beginObject()
            .name("id").value(id)
            .name("method").value(message.method);
        jsonWriter.name("params");
        try {
            gson.toJson(message.parameters, message.parameters.getClass(), jsonWriter);
        } catch (JsonIOException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                throw (IOException)cause;
            }
            throw e;
        }
        jsonWriter.endObject();
    }

    public StratumMessage decodeMessage(String messageString) {
        return gson.fromJson(messageString, StratumMessage.class);
    }
}
