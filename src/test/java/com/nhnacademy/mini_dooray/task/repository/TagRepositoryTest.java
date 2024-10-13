package com.nhnacademy.mini_dooray.task.repository;

import com.nhnacademy.mini_dooray.task.entity.Project;
import com.nhnacademy.mini_dooray.task.entity.Tag;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nhnacademy.mini_dooray.task.entity.ProjectStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    @Sql("tag-repository-test.sql")
    void testFindByProject_ProjectId() {
        List<Tag> tags = tagRepository.findByProject_ProjectId(1L);

        assertThat(tags).hasSize(3);
        assertThat(tags).extracting(Tag::getTagName).contains("태그1", "태그2", "태그3");
    }

    @Test
    @Sql("tag-repository-test.sql")
    void testExistsByProject_ProjectId() {
        boolean existsBy1L = tagRepository.existsByProject_ProjectId(1L);
        boolean existsBy2L = tagRepository.existsByProject_ProjectId(2L);
        boolean existsBy3L = tagRepository.existsByProject_ProjectId(3L);

        assertThat(existsBy1L).isTrue();
        assertThat(existsBy2L).isTrue();
        assertThat(existsBy3L).isFalse();
    }

    @Test
    @Sql("tag-repository-test.sql")
    void testExistsByTagNameAndProject() {
        Project project = new Project(2L, "test2", ACTIVE, "manager2");
        boolean exists = tagRepository.existsByTagNameAndProject("태그1", project);

        assertThat(exists).isTrue();
    }
}
