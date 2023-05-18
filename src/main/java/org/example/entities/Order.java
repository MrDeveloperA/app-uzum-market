package org.example.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Order {
    private int id;
    private int userId;
    private int productId;
    private int amountProduct;
}
