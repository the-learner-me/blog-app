package com.fse.blog.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    private String content;
    private LocalDateTime createdAt;
    @OneToOne(fetch = FetchType.EAGER)
    private Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
