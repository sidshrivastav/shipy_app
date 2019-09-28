package com.ensa.shipy.enums;

public enum OrderStatusEnum {
    NewOrder(0), DeliveryOnProgress(1), CancelledOrder(2), RedirectedOrders(3), OrdersReturned(4);

    private final int code;

    private OrderStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static OrderStatusEnum fromCode(int code) {
        switch(code) {
            case 0:
                return NewOrder;
            case 1:
                return DeliveryOnProgress;
            case 2:
                return CancelledOrder;
            case 3:
                return RedirectedOrders;
            case 4:
                return OrdersReturned;
            default:
                throw new RuntimeException(
                        "Illegal OrderStatusType: " + code);
        }
    }
}
