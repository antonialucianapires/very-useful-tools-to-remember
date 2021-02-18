package br.com.alps.vuttr.repositories;

import br.com.alps.vuttr.domain.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolRepository extends JpaRepository<Tool, Long> {
}
