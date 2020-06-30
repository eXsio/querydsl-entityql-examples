package pl.exsio.querydsl.entityql.examples.dto;

public class OrderItemBookCountDto {

    private final Long orderId;

    private final Long bookCount;

    public OrderItemBookCountDto(Long orderId, Long bookCount) {
        this.orderId = orderId;
        this.bookCount = bookCount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getBookCount() {
        return bookCount;
    }

    @Override
    public String toString() {
        return "OrderItemBookCountDto{" +
                "orderId=" + orderId +
                ", bookCount=" + bookCount +
                '}';
    }
}
