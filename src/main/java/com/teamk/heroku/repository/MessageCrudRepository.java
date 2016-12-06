package com.teamk.heroku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamk.heroku.domain.Message;

@Repository
public interface MessageCrudRepository extends JpaRepository<Message, Long>{}
