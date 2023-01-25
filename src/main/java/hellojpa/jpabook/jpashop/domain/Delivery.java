package hellojpa.jpabook.jpashop.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class Delivery extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY) // 양방향
    private Order order;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;

}
