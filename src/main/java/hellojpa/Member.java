package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false) // 컬럼 매핑
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) // enum 타입 매핑
    private RoleType roleType;

    // LocalDate LocalDateTime을 사용할 때는 생략 가능 (최신 하이버네이트 지원)
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Temporal(TemporalType.TIMESTAMP) // 날짜 매핑
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob // BLOB, CLOB 매핑
    private String description;

    // DB와 절대 관계 없는 field -> 메모리에서만 사용
    /*@Transient
    private int temp;*/

    protected Member() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public LocalDate getTestLocalDate() {
        return testLocalDate;
    }

    public void setTestLocalDate(LocalDate testLocalDate) {
        this.testLocalDate = testLocalDate;
    }

    public LocalDateTime getTestLocalDateTime() {
        return testLocalDateTime;
    }

    public void setTestLocalDateTime(LocalDateTime testLocalDateTime) {
        this.testLocalDateTime = testLocalDateTime;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
