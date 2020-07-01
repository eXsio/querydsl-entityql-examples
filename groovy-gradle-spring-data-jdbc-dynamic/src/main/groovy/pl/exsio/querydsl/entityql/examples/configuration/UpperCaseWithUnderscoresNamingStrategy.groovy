package pl.exsio.querydsl.entityql.examples.configuration

import com.google.common.base.CaseFormat
import org.springframework.data.relational.core.mapping.NamingStrategy
import org.springframework.data.relational.core.mapping.PersistentPropertyPathExtension
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty

class UpperCaseWithUnderscoresNamingStrategy implements NamingStrategy {

    @Override
    String getTableName(Class<?> type) {
        CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, type.getSimpleName()) + "S"
    }

    @Override
    String getColumnName(RelationalPersistentProperty property) {

        String propertyInLowerUnderscore = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, property.getName())

        if (property.isIdProperty()) {
            property.getOwner().getType().getSimpleName().toUpperCase() + "_" + propertyInLowerUnderscore
        }

        propertyInLowerUnderscore
    }

    @Override
    String getReverseColumnName(PersistentPropertyPathExtension path) {
        path.getIdColumnName()
    }
}
