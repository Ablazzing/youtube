package org.javaacademy.youtube.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "youtube_user_id")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "video")
    private List<Comment> comments;

    public Video(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }
}
