 package pl.exsio.querydsl.entityql.examples.jpa.entity.generated;

 import com.querydsl.core.types.Path;
 import com.querydsl.sql.PrimaryKey;
 import pl.exsio.querydsl.entityql.QColumnMetadataFactory;
 import pl.exsio.querydsl.entityql.QPathConfig;
 import pl.exsio.querydsl.entityql.QPathFactory;
 import pl.exsio.querydsl.entityql.QStaticModel;
 import com.querydsl.sql.ForeignKey;
 import pl.exsio.querydsl.entityql.examples.jpa.entity.CompositeFk;
 import com.querydsl.core.types.dsl.NumberPath;
 import com.querydsl.core.types.dsl.StringPath;
 import pl.exsio.querydsl.entityql.examples.jpa.entity.CompositePk;
 import pl.exsio.querydsl.entityql.examples.jpa.entity.SingularPk;
 import javax.annotation.Generated;
 import java.util.Arrays;
 
 /**
 *
 * This class was generated by EntityQL (https://github.com/eXsio/querydsl-entityql).
 * It is not recommended to make any changes to this class.
 * Any manual changes will be lost upon the next class generation.
 *
 */
 @Generated("pl.exsio.querydsl.entityql.QExporter")
 public final class QCompositeFk extends QStaticModel<CompositeFk> {

     private static final long serialVersionUID = 594020407;

     public static final QCompositeFk INSTANCE = new QCompositeFk();

     public static final QCompositeFk qCompositeFk = INSTANCE;

     public final NumberPath<Long> id;

     public final StringPath desc;

     public final ForeignKey<CompositePk> compositePk;

     public final ForeignKey<SingularPk> singularPk;

     public final PrimaryKey<CompositeFk> _primaryKey;

     public QCompositeFk() {
         this("COMPOSITE_FK");
     }
     @SuppressWarnings(value = "unchecked")
     public QCompositeFk(String variable) {
         super(CompositeFk.class, variable, "", "COMPOSITE_FK");
         id: {
             QPathConfig config = new QPathConfig(Long.class, Long.class, "ID", true, 1, -5);
             this.id = QPathFactory.<NumberPath<Long>>create(this, config);
             addMetadata(this.id, QColumnMetadataFactory.create(config));
             this.columnsMap.put("id", this.id);
         }

         desc: {
             QPathConfig config = new QPathConfig(String.class, String.class, "DESC", true, 4, 12);
             this.desc = QPathFactory.<StringPath>create(this, config);
             addMetadata(this.desc, QColumnMetadataFactory.create(config));
             this.columnsMap.put("desc", this.desc);
         }

         compositePk: {
             QPathConfig config0 = new QPathConfig(Long.class, Long.class, "CPK_ID_1", false, 2, -5);
             Path<?> compositePk0 = QPathFactory.<Path>create(this, config0);
             addMetadata(compositePk0, QColumnMetadataFactory.create(config0));
             QPathConfig config1 = new QPathConfig(String.class, String.class, "CPK_ID_2", false, 2, 12);
             Path<?> compositePk1 = QPathFactory.<Path>create(this, config1);
             addMetadata(compositePk1, QColumnMetadataFactory.create(config1));
             this.compositePk = this.<CompositePk>createForeignKey(Arrays.<Path<?>>asList(compositePk0, compositePk1), Arrays.asList("ID_1", "ID_2"));
             this.joinColumnsMap.put("compositePk", this.compositePk);
         }

         singularPk: {
             QPathConfig config0 = new QPathConfig(Long.class, Long.class, "SPK_ID_1", false, 3, -5);
             Path<?> singularPk0 = QPathFactory.<Path>create(this, config0);
             addMetadata(singularPk0, QColumnMetadataFactory.create(config0));
             QPathConfig config1 = new QPathConfig(String.class, String.class, "SPK_ID_2", false, 3, 12);
             Path<?> singularPk1 = QPathFactory.<Path>create(this, config1);
             addMetadata(singularPk1, QColumnMetadataFactory.create(config1));
             this.singularPk = this.<SingularPk>createForeignKey(Arrays.<Path<?>>asList(singularPk0, singularPk1), Arrays.asList("ID_1", "ID_2"));
             this.joinColumnsMap.put("singularPk", this.singularPk);
         }

         _primaryKey: {
             this.primaryKeyColumns = Arrays.<Path<?>>asList(this.id);
             Path[] pkArray = (Path[]) primaryKeyColumns.<Path>toArray(new Path[0]);
             this._primaryKey = this.<CompositeFk>createPrimaryKey(pkArray);
         }

     }
 } 