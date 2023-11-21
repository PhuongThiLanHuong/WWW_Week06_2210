package vn.edu.iuh.fit.www_week06_2210.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.www_week06_2210.backend.models.PostComment;
import vn.edu.iuh.fit.www_week06_2210.backend.repositories.PostCommentRepository;

@Service
public class PostCommentService {
    @Autowired
    private PostCommentRepository postCommentRepository;
    public Page<PostComment> findPaginated(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return postCommentRepository.findAll(pageable);
    }

}
