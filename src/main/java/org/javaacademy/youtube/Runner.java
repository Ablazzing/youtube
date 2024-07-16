package org.javaacademy.youtube;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.javaacademy.youtube.entity.Comment;
import org.javaacademy.youtube.entity.User;
import org.javaacademy.youtube.entity.Video;

import java.util.Properties;

public class Runner {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/youtube");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "terrrr");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, true);
        @Cleanup SessionFactory sessionFactory = new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Video.class)
                .addAnnotatedClass(Comment.class)
                .buildSessionFactory();

        @Cleanup Session session = sessionFactory.openSession();
        initData(session);
        printCommentFromFirstVideo(session, "john");
    }

    private static void printCommentFromFirstVideo(Session session, String name) {
        String hql = "from User where nickname = '%s'".formatted(name);

        session.createQuery(hql, User.class).list()
                .stream()
                .flatMap(user -> user.getVideos().stream())
                .flatMap(video -> video.getComments().stream())
                .findFirst()
                .ifPresent(comment -> System.out.println(comment.getText()));

//        User user = session.createQuery(hql, User.class).getSingleResult();
//        Video video = user.getVideos().get(0);
//        Comment comment = video.getComments().get(0);
//        System.out.println(comment.getText());
    }

    private static void initData(Session session) {
        session.beginTransaction();
        User john = new User("john");
        User rick = new User("rick");
        session.persist(john);
        session.persist(rick);
        session.getTransaction().commit();

        session.beginTransaction();
        Video video1 = new Video("Мое первое интервью", "классное видео", john);
        Video video2 = new Video("Мое второе интервью", "классное видео", john);
        session.persist(video1);
        session.persist(video2);
        session.getTransaction().commit();

        session.beginTransaction();
        Comment comment = new Comment("Классное интервью", video1, rick);
        session.persist(comment);
        session.getTransaction().commit();
        session.clear();
    }


}
