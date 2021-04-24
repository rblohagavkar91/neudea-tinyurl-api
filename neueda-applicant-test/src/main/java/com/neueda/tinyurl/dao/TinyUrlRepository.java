package com.neueda.tinyurl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.neueda.tinyurl.entity.TinyUrl;
/**
 * This is repository class for TinyUrl.
 */
@Repository
public interface TinyUrlRepository extends JpaRepository<TinyUrl, Long> {
}
