package com.questions.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.questions.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {

}
