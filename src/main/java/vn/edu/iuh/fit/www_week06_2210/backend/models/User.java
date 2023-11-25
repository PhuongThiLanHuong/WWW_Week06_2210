package vn.edu.iuh.fit.www_week06_2210.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="firstName",length = 50 ,nullable = false)
    private String firstName;
    @Column(name="middleName",length = 50,nullable = false)
    private String middleName;
    @Column(name="lastName",length = 50,nullable = false)
    private String lastName;
    @Column(name="mobile",length = 15,nullable = false)
    private String mobile;
    @Column(name="email",length = 50,nullable = false)
    private String email;
    @Column(name="passwordHash",length = 32,nullable = false)
    private String passwordHash;
    @Column(name="registeredAt",nullable = false)
    private Instant registeredAt;
    @Column(name="lastLogin")
    private Instant lastLogin;
    @Column(name="intro")
    private String intro;
    @Column(name="text")
    private String text;
    @OneToMany(mappedBy = "author")
    private Set<Post> posts=new LinkedHashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<PostComment> postComments=new LinkedHashSet<>();
    public User(Long id) {
        this.id = id;
    }
    public User(String firstName, String middleName, String lastName, String mobile, String email, String passwordHash, Instant registeredAt, Instant lastLogin, String intro, String text) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.passwordHash = passwordHash;
        this.registeredAt = registeredAt;
        this.lastLogin = lastLogin;
        this.intro = intro;
        this.text = text;
    }
}