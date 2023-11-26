package vn.edu.iuh.fit.www_week06_2210.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="post_comment")
@Getter
@Setter
@NoArgsConstructor
public class PostComment {
    @Id
    @Column(name="comment_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="postId")
    private  Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parentId")
    private PostComment parent;
    @Column(name="title",nullable = false,length = 100)
    private String title;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "userId",nullable = false)
    private User user;
    @Column(name="published",nullable = false)
    private Boolean published=false;
    @Column(name="createAt",nullable = false)
    private Instant createAt;
    @Column(name = "pubishedAt")
    private Instant publishedAt;
    @Column(name = "content")
    private String content;
    @OneToMany(mappedBy = "parent")
    private Set<PostComment> postComments=new LinkedHashSet<>();

    public PostComment(Post post, String title, User user, Boolean published, Instant createAt, Instant publishedAt, String content) {
        this.post = post;
        this.title = title;
        this.user = user;
        this.published = published;
        this.createAt = createAt;
        this.publishedAt = publishedAt;
        this.content = content;
    }
}