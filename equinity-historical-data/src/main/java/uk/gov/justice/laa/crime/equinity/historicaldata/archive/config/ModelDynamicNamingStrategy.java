package uk.gov.justice.laa.crime.equinity.historicaldata.archive.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


// Driver Class
@Component
public class ModelDynamicNamingStrategy extends PhysicalNamingStrategyStandardImpl { //} CamelCaseToUnderscoresNamingStrategy {
    @Autowired
    private Environment globalEnv;

    // Method for Creating
    // Custom Naming Strategy
    @Override
    public Identifier toPhysicalCatalogName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        if (logicalName == null) return super.toPhysicalCatalogName(logicalName, jdbcEnvironment);

        String currentCatalog = logicalName.getText();

        if (currentCatalog.startsWith("$") && currentCatalog.toUpperCase().contains("CATALOG_ARCHIVE")) {
            String dynamicCatalog = globalEnv.getProperty("datasource.archive.catalog");

            if (dynamicCatalog == null || dynamicCatalog.isBlank()) {
                dynamicCatalog = "";
            }

            return super.toPhysicalCatalogName(Identifier.toIdentifier(dynamicCatalog), jdbcEnvironment);
        }

        return super.toPhysicalCatalogName(logicalName, jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        if (logicalName == null) return super.toPhysicalSchemaName(logicalName, jdbcEnvironment);

        String currentSchema = logicalName.getText();

        if (currentSchema.startsWith("$") && currentSchema.toUpperCase().contains("SCHEMA_ARCHIVE")) {
            String dynamicSchema = globalEnv.getProperty("datasource.archive.schema");

            if (dynamicSchema == null || dynamicSchema.isBlank()) {
                dynamicSchema = "";
            }

            return super.toPhysicalSchemaName(Identifier.toIdentifier(dynamicSchema), jdbcEnvironment);
        }

        return super.toPhysicalSchemaName(logicalName, jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        if (logicalName == null) return super.toPhysicalTableName(logicalName, jdbcEnvironment);

        String currentTable = logicalName.getText();

        if (currentTable.startsWith("$") && currentTable.toUpperCase().contains("TABLE_ARCHIVE")) {
            String dynamicTable = globalEnv.getProperty("datasource.archive.table");

            if (dynamicTable == null || dynamicTable.isBlank()) {
                dynamicTable = "CrmFormsArchiveView";
            }

            Identifier tableId = Identifier.toIdentifier(dynamicTable);

            return super.toPhysicalTableName(Identifier.toIdentifier(dynamicTable), jdbcEnvironment);
        }

        return super.toPhysicalTableName(logicalName, jdbcEnvironment);
    }
}