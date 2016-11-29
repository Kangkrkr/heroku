package com.teamk.heroku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamk.heroku.domain.AbstractItem;

@Repository
public interface ItemRepository extends JpaRepository<AbstractItem, Long>{

}
