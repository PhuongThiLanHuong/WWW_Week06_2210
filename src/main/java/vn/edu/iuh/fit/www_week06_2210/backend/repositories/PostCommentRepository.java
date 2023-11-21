package vn.edu.iuh.fit.www_week06_2210.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.www_week06_2210.backend.models.PostComment;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment,Long> {
}
