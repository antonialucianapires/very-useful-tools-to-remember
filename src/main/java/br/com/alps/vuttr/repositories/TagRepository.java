package br.com.alps.vuttr.repositories;

import br.com.alps.vuttr.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByNameIgnoreCase(String tag);
}
