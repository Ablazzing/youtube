package org.javaacademy.youtube.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "video_id")
    @ToString.Exclude
    private Video video;

    @ManyToOne
    @JoinColumn(name = "youtube_user_id")
    @ToString.Exclude
    private User user;

    public Comment(String text, Video video, User user) {
        this.text = text;
        this.video = video;
        this.user = user;
    }
}
