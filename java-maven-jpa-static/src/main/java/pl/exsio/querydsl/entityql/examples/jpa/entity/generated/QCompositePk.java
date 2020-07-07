 package pl.exsio.querydsl.entityql.examples.jpa.entity.generated;

 import com.querydsl.core.types.Path;
 import com.querydsl.sql.PrimaryKey;
 import pl.exsio.querydsl.entityql.QColumnMetadataFactory;
 import pl.exsio.querydsl.entityql.QPathConfig;
 import pl.exsio.querydsl.entityql.QPathFactory;
 import pl.exsio.querydsl.entityql.QStaticModel;
 import com.querydsl.sql.ForeignKey;
 import pl.exsio.querydsl.entityql.examples.jpa.entity.CompositePk;
 import com.querydsl.core.types.dsl.NumberPath;
 import com.querydsl.core.types.dsl.StringPath;
 import pl.exsio.querydsl.entityql.examples.jpa.entity.CompositeFk;
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
 public final class QCompositePk extends QStaticModel<CompositePk> {

     private static final long serialVersionUID = -1372227420;

     public static final QCompositePk INSTANCE = new QCompositePk();

     public static final QCompositePk qCompositePk = INSTANCE;

     public final NumberPath<Long> id1;

     public final StringPath id2;

     public final StringPath desc;

     public final ForeignKey<CompositeFk> compositeFks;

     public final PrimaryKey<CompositePk> _primaryKey;

     public QCompositePk() {
         this("COMPOSITE_PK");
     }
     @SuppressWarnings(value = "unchecked")
     public QCompositePk(String variable) {
         super(CompositePk.class, variable, "", "COMPOSITE_PK");
         id1: {
             QPathConfig config = new QPathConfig(Long.class, Long.class, "ID_1", true, 1, -5);
             this.id1 = QPathFactory.<NumberPath<Long>>create(this, config);
             addMetadata(this.id1, QColumnMetadataFactory.create(config));
             this.columnsMap.put("id1", this.id1);
         }

         id2: {
             QPathConfig config = new QPathConfig(String.class, String.class, "ID_2", true, 2, 12);
             this.id2 = QPathFactory.<StringPath>create(this, config);
             addMetadata(this.id2, QColumnMetadataFactory.create(config));
             this.columnsMap.put("id2", this.id2);
         }

         desc: {
             QPathConfig config = new QPathConfig(String.class, String.class, "DESC", true, 3, 12);
             this.desc = QPathFactory.<StringPath>create(this, config);
             addMetadata(this.desc, QColumnMetadataFactory.create(config));
             this.columnsMap.put("desc", this.desc);
         }

         compositeFks: {
             QPathConfig config0 = new QPathConfig(Long.class, Long.class, "ID_1", false, 4, -5);
             Path<?> compositeFks0 = QPathFactory.<Path>create(this, config0);
             addMetadata(compositeFks0, QColumnMetadataFactory.create(config0));
             QPathConfig config1 = new QPathConfig(String.class, String.class, "ID_2", false, 4, 12);
             Path<?> compositeFks1 = QPathFactory.<Path>create(this, config1);
             addMetadata(compositeFks1, QColumnMetadataFactory.create(config1));
             this.compositeFks = this.<CompositeFk>createInvForeignKey(Arrays.<Path<?>>asList(compositeFks0, compositeFks1), Arrays.asList("CPK_ID_1", "CPK_ID_2"));
             this.inverseJoinColumnsMap.put("compositeFks", this.compositeFks);
         }

         _primaryKey: {
             this.primaryKeyColumns = Arrays.<Path<?>>asList(this.id1, this.id2);
             Path[] pkArray = (Path[]) primaryKeyColumns.<Path>toArray(new Path[0]);
             this._primaryKey = this.<CompositePk>createPrimaryKey(pkArray);
         }

     }
 } 