package hellojpa;

import hellojpa.Member;
import hellojpa.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MemberProduct {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member members;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product products;

    private int count;
    private int price;
}
