package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain2 {

    public static void main(String[] args) {

        // 하나만 생성해서 Application 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 쓰레드간 공유 X ( 사용하고 버려야 한다.)
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 Transaction 안에서 실행
        EntityTransaction tx = em.getTransaction(); // DB Connection Get

        tx.begin(); // // Transaction 시작

        try {

            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            team.addMember(member); // ** 연관관계 편의 메서드

            // 1차 캐시를 고려해서, flush, clear를 사용하지 않는다면, 순수한 객체 관계를 고려하여 항상 양쪽 다 값을 입력해야 한다.
            // team.getMembers().add(member); // **

            /*em.flush();
            em.clear();*/

            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
            List<Member> members = findTeam.getMembers();

            System.out.println("====================");
            System.out.println("members = " + findTeam);
            System.out.println("====================");

            tx.commit(); // Commit
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // Connection 반환
            emf.close();
        }
    }
}
