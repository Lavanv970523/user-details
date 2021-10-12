package com.example.userdetails.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.userdetails.entities.User;

@Repository
public interface UserPagingRepository extends PagingAndSortingRepository<User, Integer>{

}
