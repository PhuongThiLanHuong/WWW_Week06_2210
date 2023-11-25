package vn.edu.iuh.fit.www_week06_2210.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="post")
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @Column(name="post_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id",nullable = false)
    private User author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parentId")
    private Post parent;
    @Column(name="title",nullable = false,length = 75)
    private String title;
    @Column(name="metaTitle",length = 100)
    private String metaTitle;
    @Lob
    @Column(name="summary")
    private String summary;
    @Column(name="published",nullable = false)
    private boolean published=false;
    @Column(name="createAt",nullable = false)
    private Instant createdAt;
    @Column(name="updateAt")
    private  Instant updateAt;
    @Column(name="publishedAt")
    private Instant publishedAt;
    @Lob
    @Column(name = "content")
    private String content;
    @OneToMany(mappedBy = "parent")
    private Set<Post> posts=new LinkedHashSet<>();
    @OneToMany(mappedBy = "post")
    private Set<PostComment> postComments=new LinkedHashSet<>();

    public Post(User author, Post parent, String title, String metaTitle, String summary, boolean published, Instant createdAt, Instant updateAt, Instant publishedAt, String content) {
        this.author = author;
        this.parent = parent;
        this.title = title;
        this.metaTitle = metaTitle;
        this.summary = summary;
        this.published = published;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.publishedAt = publishedAt;
        this.content = content;
    }
}
