import com.fasterxml.jackson.databind.JsonNode;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;
import org.yangcentral.yangkit.common.api.validate.ValidatorResult;
import org.yangcentral.yangkit.model.api.schema.YangSchemaContext;
import org.yangcentral.yangkit.parser.YangParserException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnydataValidationTest {

    /**
     * Tests validation of an anydata node containing a primitive type.
     */
    @Test
    void testPrimitiveTypeAnydata() throws DocumentException, IOException, YangParserException {
        // todo: invalid payload, should be a JSON object not primitive
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata");
        JsonNode validData = YangkitUtils.loadJson("../data/primitive-anydata.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, validData);
        assertTrue(firstDataValidation.isOk());
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, validData);
        assertTrue(secondDataValidation.isOk());
    }

    /**
     * Tests validation of an anydata node containing an object that conforms to a known schema.
     */
    @Test
    void testObjectWithSchemaAnydata() throws DocumentException, IOException, YangParserException {
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata");
        JsonNode validData = YangkitUtils.loadJson("../data/object-with-schema-anydata.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, validData);
        assertTrue(firstDataValidation.isOk());
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, validData);
        assertTrue(secondDataValidation.isOk());
    }

    /**
     * Tests validation of an anydata node containing an object that does not conform to a known schema.
     */
    @Test
    void testObjectWithoutSchemaAnydata() throws DocumentException, IOException, YangParserException {
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata");
        JsonNode validData = YangkitUtils.loadJson("../data/object-without-schema-anydata.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, validData);
        assertFalse(firstDataValidation.isOk());
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, validData);
        assertTrue(secondDataValidation.isOk());
    }

    /**
     * Tests validation of a nested anydata node containing a primitive type.
     */
    @Test
    void testPrimitiveAnydataInAnydata()throws DocumentException, IOException, YangParserException {
        // todo: payload is primitive, invalid - in strict validation of yangkit, why is it succ?
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata-anydata");
        JsonNode validData = YangkitUtils.loadJson("../data/primitive-anydata-anydata.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, validData);
        assertTrue(firstDataValidation.isOk());
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, validData);
        assertTrue(secondDataValidation.isOk());
    }

    /**
     * Tests validation of a nested anydata node containing an object that conforms to a known schema.
     */
    @Test
    void testObjectWithSchemaAnydataInAnydata() throws DocumentException, IOException, YangParserException {
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata-anydata");
        JsonNode validData = YangkitUtils.loadJson("../data/object-with-schema-anydata-anydata.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, validData);
        assertTrue(firstDataValidation.isOk());
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, validData);
        assertTrue(secondDataValidation.isOk());
    }

    /**
     * Tests validation of a nested anydata node containing an object that does not conform to a known schema.
     */
    @Test
    void testObjectWithoutSchemaAnydataInAnydata() throws DocumentException, IOException, YangParserException {
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata-anydata");
        JsonNode validData = YangkitUtils.loadJson("../data/object-without-schema-anydata-anydata.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, validData);
        assertFalse(firstDataValidation.isOk());
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, validData);
        assertTrue(secondDataValidation.isOk());
    }

    /**
     * Tests validation of an anydata node being null.
     */
    @Test
    void testNullAnydata() throws DocumentException, IOException, YangParserException {
        // TODO: Is this actually valid?
        //  RFC 7951 Sec.5.5: "anydata instance is encoded in the same way as a container, i.e., as a name/object pair."
        //  Additionally, "The 'null' value is only allowed in the single-element array '[null]' corresponding to the encoding of the 'empty' type".
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata");
        JsonNode validData = YangkitUtils.loadJson("../data/null-anydata.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, validData);
        assertTrue(firstDataValidation.isOk());
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, validData);
        assertTrue(secondDataValidation.isOk());
    }

    /**
     * Tests validation of an anydata node containing an empty object.
     * This is the correct as empty anydata node per RFC 7951.
     */
    @Test
    void testEmptyObjectAnydata() throws DocumentException, IOException, YangParserException {
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata");
        JsonNode validData = YangkitUtils.loadJson("../data/empty-object-anydata.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, validData);
        assertTrue(firstDataValidation.isOk());
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, validData);
        assertTrue(secondDataValidation.isOk());
    }

    /**
     * Tests validation of an anydata node containing an empty array.
     */
    @Test
    void testEmptyArrayAnydata() throws DocumentException, IOException, YangParserException {
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata");
        JsonNode validData = YangkitUtils.loadJson("../data/empty-array-anydata.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, validData);
        assertFalse(firstDataValidation.isOk());
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, validData);
        assertTrue(secondDataValidation.isOk());
    }

    /**
     * Tests validation of an anydata node containing an array of primitives.
     */
    @Test
    void testPrimitiveArrayAnydata() throws DocumentException, IOException, YangParserException {
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata");
        JsonNode validData = YangkitUtils.loadJson("../data/primitive-array-anydata.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, validData);
        assertFalse(firstDataValidation.isOk());
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, validData);
        assertTrue(secondDataValidation.isOk());
    }

    /**
     * Tests validation of an anydata node containing an array of objects.
     */
    @Test
    void testObjectArrayAnydata() throws DocumentException, IOException, YangParserException {
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata");
        JsonNode validData = YangkitUtils.loadJson("../data/object-array-anydata.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, validData);
        assertFalse(firstDataValidation.isOk());
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, validData);
        assertTrue(secondDataValidation.isOk());
    }

    /**
     * Tests that identityrefs that cross module boundaries resolve correctly when embedded inside an anydata block.
     * In JSON encoding, identityrefs take the form "module-name:identity-name", so parsing must correctly look up "module-name".
     */
    @Test
    void testAnydataCrossModuleIdentityref() throws DocumentException, IOException, YangParserException {
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata-identity");
        JsonNode validData = YangkitUtils.loadJson("../data/anydata-identityref.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, validData);
        assertTrue(firstDataValidation.isOk());
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, validData);
        assertTrue(secondDataValidation.isOk());
    }

    /**
     * Tests that validation fails when an identityref inside an anydata block
     * refers to a module that does not exist in the schema context.
     */
    @Test
    void testAnydataCrossModuleIdentityrefMissingModule() throws DocumentException, IOException, YangParserException {
        YangSchemaContext schemaContext = YangkitUtils.loadSchema("../yang/anydata-identity-missing");
        JsonNode invalidData = YangkitUtils.loadJson("../data/anydata-invalid-identityref.json");
        ValidatorResult schemaValidation = YangkitUtils.validateSchema(schemaContext);
        assertTrue(schemaValidation.isOk());

        // During parsing, YangKit builds the data tree but accepts the identityref
        // as a string because it defers deeper identityref checks.
        ValidatorResult firstDataValidation = YangkitUtils.parsingData(schemaContext, invalidData);
        assertTrue(firstDataValidation.isOk(), "Data parsing should succeed since structural types match");

        // During the update/validate phase, deep inspections run (e.g. cross-module identityref bindings).
        // Because "ext-ident" is missing from the schemas, this should catch the unresolved namespace 
        // and raise an error.
        ValidatorResult secondDataValidation = YangkitUtils.validateData(schemaContext, invalidData);
        // TODO: why it's true? shouldn't it be false?
        //  The validation fail because the identityref refers to a module that is not existed.
        assertFalse(secondDataValidation.isOk(), "Validation should fail because non-existing-ext-ident module is missing");
    }
}
