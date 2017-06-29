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
