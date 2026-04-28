import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;
import org.yangcentral.yangkit.model.api.schema.YangSchemaContext;
import org.yangcentral.yangkit.parser.YangParserException;

import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;

public class YangLibraryTest {

    @Test
    void testLoadSchema() throws DocumentException, IOException, YangParserException {
        YangSchemaContext context = YangkitUtils.loadSchema("../yang/yangpush");
        assertNotNull(context);
    }

}
