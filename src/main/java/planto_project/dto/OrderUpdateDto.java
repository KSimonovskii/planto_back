package planto_project.dto;

import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import planto_project.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderUpdateDto {
    private List<OrderItemDto> items;
    private OrderStatus status;

    public OrderUpdateDto(String status, List<OrderItemDto> items) {
        this.items = items;
        this.status = OrderStatus.valueOf(status);
    }
}
