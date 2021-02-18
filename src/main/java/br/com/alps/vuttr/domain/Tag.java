package br.com.alps.vuttr.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "Tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "tool_tag",
    joinColumns = @JoinColumn(name = "tool_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tool> tools;
}
