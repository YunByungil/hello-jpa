package hellojpa;




import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=============== START ====================");

            Member findMember = em.find(Member.class, member.getId());

            // homeCity -> newCity
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");
            // 컬렉션의 값만 변경해도 실제 데이터베이스 쿼리가 날라가면서 JPA가 알아서 다 바꿔줌!! 영속성 전이처럼!
            // delete쿼리 1개 나가고, insert쿼리 1개 들어옴

            // old1 -> new1
            // equals, hashcode 가 제대로 구현이 되어있어야 지워진다!!!!!!!!!!!!
            // 값 타입이기 때문에 통으로 갈아야 되는 거 기억나지?
//            findMember.getAddressHistory().remove(new Address("old1", "street", "10000"));
//            findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));
            // delete쿼리 1개 나가고, insert쿼리 2개 들어옴 이유가 뭐지? 값 타입 컬렉션의 제약사항

            System.out.println("================ END ===================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }


        emf.close();
    }
}
