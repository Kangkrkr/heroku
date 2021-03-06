package com.teamk.heroku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamk.heroku.domain.MessageBox;

@Repository
public interface MessageBoxRepository extends JpaRepository<MessageBox, Long>{}
