package vn.edu.iuh.fit.www_week06_2210.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.www_week06_2210.backend.models.Post;
import vn.edu.iuh.fit.www_week06_2210.backend.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findAllByPublished(boolean published, Pageable pageable);

    List<Post> findAllByAuthorAndPublished(User author, boolean published);

    Optional<Post> findPostById(long id);

}
