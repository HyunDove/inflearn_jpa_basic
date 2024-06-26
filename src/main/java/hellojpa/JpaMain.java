package hellojpa;

import jakarta.persistence.*;

import javax.lang.model.SourceVersion;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        // 하나만 생성해서 Application 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 쓰레드간 공유 X ( 사용하고 버려야 한다.)
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 Transaction 안에서 실행
        EntityTransaction tx = em.getTransaction(); // DB Connection Get

        tx.begin(); // // Transaction 시작

        try {

            /** 준영속 테스트 */
            /*Member member = em.find(Member.class, 1L); // 영속 상태
            member.setName("AAAAA");

            em.detach(member); // 특정 엔티티만 준영속 상태로 전환
            // em.clear(); // 영속성 컨텍스트를 완전히 초기화
            // em.close(); // 영속성 컨텍스트를 종료
            */

            /*Member member1 = em.find(Member.class, 1L);
            Member member2 = em.find(Member.class, 1L);
            System.out.println("member1 = " + (member1 == member2)); // 동일성 비교 true*/

            /** 등록 */
            /*Member member = new Member(); // 비영속
            member.setId(1L);
            member.setName("HelloA");

            System.out.println("=== BEFORE ===");
            em.persist(member); // JPA 저장, 영속 상태
            System.out.println("=== AFTER ===");*/

            // 준영속 : 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
            // em.detach(member);

            /** 수정 */
            /*Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            findMember.setName("HelloJPA"); // Transaction Commit 시점에 Entity 변경 감지*/

            /** 삭제 */
            // em.remove(findMember);

            /** JPQL */
            // 객체 대상으로 Query
            /*List<Member> result = em.createQuery("select m from Member as m", Member.class)
                            .setFirstResult(5) // Paging start : limit, rowNum
                            .setMaxResults(8) // Paging End : offset, rowNum
                            .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            */

            tx.commit(); // Commit
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // Connection 반환
            emf.close();
        }
    }
}
