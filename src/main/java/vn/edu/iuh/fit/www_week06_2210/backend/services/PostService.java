package vn.edu.iuh.fit.www_week06_2210.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.www_week06_2210.backend.models.Post;
import vn.edu.iuh.fit.www_week06_2210.backend.repositories.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public Page<Post> findPaginated(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return postRepository.findAll(pageable);
    }

}
