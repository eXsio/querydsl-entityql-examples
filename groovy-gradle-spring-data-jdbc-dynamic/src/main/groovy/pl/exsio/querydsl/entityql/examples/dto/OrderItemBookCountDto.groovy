package pl.exsio.querydsl.entityql.examples.dto

class OrderItemBookCountDto {

    private final Long orderId

    private final Long bookCount

    OrderItemBookCountDto(Long orderId, Long bookCount) {
        this.orderId = orderId
        this.bookCount = bookCount
    }

    Long getOrderId() {
        orderId
    }

    Long getBookCount() {
        bookCount
    }

    @Override
    String toString() {
        "OrderItemBookCountDto{" +
                "orderId=" + orderId +
                ", bookCount=" + bookCount +
                '}'
    }
}
