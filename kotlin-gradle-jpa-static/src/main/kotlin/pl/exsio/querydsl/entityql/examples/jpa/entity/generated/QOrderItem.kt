 package pl.exsio.querydsl.entityql.examples.jpa.entity.generated

 import com.querydsl.sql.PrimaryKey
 import pl.exsio.querydsl.entityql.QColumnMetadataFactory
 import pl.exsio.querydsl.entityql.QPathConfig
 import pl.exsio.querydsl.entityql.QPathFactory
 import pl.exsio.querydsl.entityql.QStaticModel
 import com.querydsl.sql.ForeignKey
 import pl.exsio.querydsl.entityql.examples.jpa.entity.OrderItem
 import com.querydsl.core.types.dsl.NumberPath
 import pl.exsio.querydsl.entityql.examples.jpa.entity.Book
 import pl.exsio.querydsl.entityql.examples.jpa.entity.Order
 import javax.annotation.Generated
 import com.querydsl.core.types.Path
 

 /**
 *
 * This class was generated by EntityQL (https://github.com/eXsio/querydsl-entityql).
 * It is not recommended to make any changes to this class.
 * Any manual changes will be lost upon the next class generation.
 *
 */
 @Generated("pl.exsio.querydsl.entityql.QExporter")
 class QOrderItem : QStaticModel<OrderItem> {

     companion object {
         val instance: QOrderItem = QOrderItem()
         val qOrderItem: QOrderItem = QOrderItem.instance
     }

     val id: NumberPath<Long> = run {
         val config = QPathConfig(Long::class.java, Long::class.java, "ORDER_ITEM_ID", true, 1, -5)
         val id = QPathFactory.create<NumberPath<Long>>(this, config)
         addMetadata(id, QColumnMetadataFactory.create(config))
         this.columnsMap.put("id", id)
         id
     }

     val quantity: NumberPath<Long> = run {
         val config = QPathConfig(Long::class.java, Long::class.java, "QTY", false, 5, -5)
         val quantity = QPathFactory.create<NumberPath<Long>>(this, config)
         addMetadata(quantity, QColumnMetadataFactory.create(config))
         this.columnsMap.put("quantity", quantity)
         quantity
     }

     val bookId: NumberPath<Long> = run {
         val config = QPathConfig(Long::class.java, Long::class.java, "BOOK_ID", false, 2, -5)
         val bookId = QPathFactory.create<NumberPath<Long>>(this, config)
         addMetadata(bookId, QColumnMetadataFactory.create(config))
         this.columnsMap.put("bookId", bookId)
         bookId
     }

     val orderId: NumberPath<Long> = run {
         val config = QPathConfig(Long::class.java, Long::class.java, "ITEM_ORDER_ID", false, 3, -5)
         val orderId = QPathFactory.create<NumberPath<Long>>(this, config)
         addMetadata(orderId, QColumnMetadataFactory.create(config))
         this.columnsMap.put("orderId", orderId)
         orderId
     }

     val orderReferencedId: NumberPath<Long> = run {
         val config = QPathConfig(Long::class.java, Long::class.java, "ITEM_ORDER_ID", false, 4, -5)
         val orderReferencedId = QPathFactory.create<NumberPath<Long>>(this, config)
         addMetadata(orderReferencedId, QColumnMetadataFactory.create(config))
         this.columnsMap.put("orderReferencedId", orderReferencedId)
         orderReferencedId
     }

     val book: ForeignKey<Book> = run {
         val book = this.createForeignKey<Book>(this.bookId, "BOOK_ID")
         this.joinColumnsMap.put("book", book)
         book
     }

     val order: ForeignKey<Order> = run {
         val order = this.createForeignKey<Order>(this.orderId, "ORDER_ID")
         this.joinColumnsMap.put("order", order)
         order
     }

     val orderReferenced: ForeignKey<Order> = run {
         val orderReferenced = this.createForeignKey<Order>(this.orderReferencedId, "ORDER_ID")
         this.joinColumnsMap.put("orderReferenced", orderReferenced)
         orderReferenced
     }

     val _primaryKey: PrimaryKey<OrderItem> = run {
         val list = mutableListOf<Path<*>>(this.id)
         this.primaryKeyColumns = list
         this.createPrimaryKey(*list.toTypedArray())
     }

     constructor(): this("ORDER_ITEMS")

     constructor(variable: String): super(OrderItem::class.java, variable, "", "ORDER_ITEMS")
 } 