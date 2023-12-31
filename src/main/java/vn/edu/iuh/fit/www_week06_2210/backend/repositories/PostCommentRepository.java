package vn.edu.iuh.fit.www_week06_2210.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.www_week06_2210.backend.models.PostComment;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment,Long> {
    @Query(value = "FROM PostComment WHERE post.id = :postId AND published = true AND parent is null")
    Page<PostComment> findAllByPostId(@Param("postId") Long postId, Pageable pageable);
}
