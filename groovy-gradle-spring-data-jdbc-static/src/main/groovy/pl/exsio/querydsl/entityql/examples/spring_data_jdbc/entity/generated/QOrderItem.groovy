 package pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.generated

 import com.querydsl.core.types.Path
 import com.querydsl.sql.PrimaryKey
 import pl.exsio.querydsl.entityql.QColumnMetadataFactory
 import pl.exsio.querydsl.entityql.QPathConfig
 import pl.exsio.querydsl.entityql.QPathFactory
 import pl.exsio.querydsl.entityql.QStaticModel
 import com.querydsl.sql.ForeignKey
 import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.OrderItem
 import com.querydsl.core.types.dsl.NumberPath
 import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Book
 import pl.exsio.querydsl.entityql.examples.spring_data_jdbc.entity.Order
 import javax.annotation.Generated
 import java.util.Arrays
 import groovy.transform.CompileStatic
 
 /**
 *
 * This class was generated by EntityQL (https://github.com/eXsio/querydsl-entityql).
 * It is not recommended to make any changes to this class.
 * Any manual changes will be lost upon the next class generation.
 *
 */
 @CompileStatic
 @Generated("pl.exsio.querydsl.entityql.QExporter")
 public final class QOrderItem extends QStaticModel<OrderItem> {

     private static final long serialVersionUID = -1532578597

     public static final QOrderItem INSTANCE = new QOrderItem()

     public static final QOrderItem qOrderItem = INSTANCE

     public final NumberPath<Long> id

     public final NumberPath<Long> quantity

     public final NumberPath<Long> bookId

     public final NumberPath<Long> orderId

     public final ForeignKey<Book> book

     public final ForeignKey<Order> order

     public final PrimaryKey<OrderItem> _primaryKey

     public QOrderItem() {
         this("ORDER_ITEMS")
     }
     @SuppressWarnings(value = "unchecked")
     public QOrderItem(String variable) {
         super(OrderItem.class, variable, "", "ORDER_ITEMS")
         id: {
             QPathConfig config = new QPathConfig(Long.class, Long.class, "ORDER_ITEM_ID", true, 1, -5)
             this.id = QPathFactory.<NumberPath<Long>>create(this, config)
             addMetadata(this.id, QColumnMetadataFactory.create(config))
             this.columnsMap.put("id", this.id)
         }

         quantity: {
             QPathConfig config = new QPathConfig(Long.class, Long.class, "QTY", false, 4, -5)
             this.quantity = QPathFactory.<NumberPath<Long>>create(this, config)
             addMetadata(this.quantity, QColumnMetadataFactory.create(config))
             this.columnsMap.put("quantity", this.quantity)
         }

         bookId: {
             QPathConfig config = new QPathConfig(Long.class, Long.class, "BOOK_ID", false, 2, -5)
             this.bookId = QPathFactory.<NumberPath<Long>>create(this, config)
             addMetadata(this.bookId, QColumnMetadataFactory.create(config))
             this.columnsMap.put("bookId", this.bookId)
         }

         orderId: {
             QPathConfig config = new QPathConfig(Long.class, Long.class, "ITEM_ORDER_ID", false, 3, -5)
             this.orderId = QPathFactory.<NumberPath<Long>>create(this, config)
             addMetadata(this.orderId, QColumnMetadataFactory.create(config))
             this.columnsMap.put("orderId", this.orderId)
         }

         book: {
             this.book = this.<Book>createForeignKey(this.bookId, "BOOK_ID")
             this.joinColumnsMap.put("book", this.book)
         }

         order: {
             this.order = this.<Order>createForeignKey(this.orderId, "ORDER_ID")
             this.joinColumnsMap.put("order", this.order)
         }

         _primaryKey: {
             this.primaryKeyColumns = Arrays.<Path<?>>asList(this.id)
             Path[] pkArray = (Path[]) primaryKeyColumns.<Path>toArray(new Path[0])
             this._primaryKey = this.<OrderItem>createPrimaryKey(pkArray)
         }

     }
 } 