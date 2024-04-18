package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

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
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            //
            Team newTeam = em.find(Team.class, 100L);
            findMember.setTeam(newTeam);

            tx.commit(); // Commit
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // Connection 반환
            emf.close();
        }
    }
}
