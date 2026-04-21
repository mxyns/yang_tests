import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.Test;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactorySupplier;
import org.opendaylight.yangtools.yang.data.codec.gson.JsonParserStream;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.NormalizationResultHolder;
import org.opendaylight.yangtools.yang.model.api.EffectiveModelContext;

import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class YangPushTest {

    @Test
    void test1ValidPushUpdate() throws Exception {
        EffectiveModelContext schema = YangToolsUtils.loadSchema("../yang/yangpush");
        assertNotNull(schema);

        NormalizationResultHolder resultHolder = new NormalizationResultHolder();
        var writer = ImmutableNormalizedNodeStreamWriter.from(resultHolder);

        var parser = JsonParserStream.create(
                writer,
                JSONCodecFactorySupplier.RFC7951.getShared(schema)
        );

        assertDoesNotThrow(() -> {
            try (JsonReader reader = new JsonReader(
                    new InputStreamReader(
                            Files.newInputStream(Paths.get("../data/1-push-update.json"))
                    ))) {
                parser.parse(reader);
            }
        });

    }

    @Test
    void test1ValidSubscriptionStarted() throws Exception {
        EffectiveModelContext schema = YangToolsUtils.loadSchema("../yang/yangpush");
        assertNotNull(schema);

        NormalizationResultHolder resultHolder = new NormalizationResultHolder();
        var writer = ImmutableNormalizedNodeStreamWriter.from(resultHolder);

        var parser = JsonParserStream.create(
                writer,
                JSONCodecFactorySupplier.RFC7951.getShared(schema)
        );

        assertThrows(Exception.class, () -> {
            try (JsonReader reader = new JsonReader(
                    new InputStreamReader(
                            Files.newInputStream(Paths.get("../data/1-subscription-started.json"))
                    ))) {
                parser.parse(reader);
            }
        });

    }

    @Test
    void test2ValidPushUpdate() throws Exception {
        EffectiveModelContext schema = YangToolsUtils.loadSchema("../yang/yangpush");
        assertNotNull(schema);

        NormalizationResultHolder resultHolder = new NormalizationResultHolder();
        var writer = ImmutableNormalizedNodeStreamWriter.from(resultHolder);

        var parser = JsonParserStream.create(
                writer,
                JSONCodecFactorySupplier.RFC7951.getShared(schema)
        );

        assertDoesNotThrow(() -> {
            try (JsonReader reader = new JsonReader(
                    new InputStreamReader(
                            Files.newInputStream(Paths.get("../data/2-push-update.json"))
                    ))) {
                parser.parse(reader);
            }
        });

    }

    @Test
    void test2ValidSubscriptionStarted() throws Exception {
        EffectiveModelContext schema = YangToolsUtils.loadSchema("../yang/yangpush");
        assertNotNull(schema);

        NormalizationResultHolder resultHolder = new NormalizationResultHolder();
        var writer = ImmutableNormalizedNodeStreamWriter.from(resultHolder);

        var parser = JsonParserStream.create(
                writer,
                JSONCodecFactorySupplier.RFC7951.getShared(schema)
        );

        assertThrows(Exception.class, () -> {
            try (JsonReader reader = new JsonReader(
                    new InputStreamReader(
                            Files.newInputStream(Paths.get("../data/2-subscription-started.json"))
                    ))) {
                parser.parse(reader);
            }
        });

    }
}