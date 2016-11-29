package com.teamk.heroku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamk.heroku.domain.Post;

@Transactional
@Repository
public interface PostCrudRepository extends JpaRepository<Post, Long>{}
