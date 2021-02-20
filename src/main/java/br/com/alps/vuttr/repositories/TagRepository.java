package br.com.alps.vuttr.repositories;

import br.com.alps.vuttr.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByNameIgnoreCase(String tag);
}
