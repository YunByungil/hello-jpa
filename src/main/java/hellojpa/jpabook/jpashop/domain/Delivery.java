package hellojpa.jpabook.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery") // 양방향
    private Order order;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;

}
