package org.javaacademy.youtube.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "youtube_user")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Video> videos;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public User(String nickname) {
        this.nickname = nickname;
    }
}
