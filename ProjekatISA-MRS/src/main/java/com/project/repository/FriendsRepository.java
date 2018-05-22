package com.project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Friends;
import com.project.domain.User;

public interface FriendsRepository extends JpaRepository<Friends, Long>{

	ArrayList<Friends> findByUser(User u);
	ArrayList<Friends> findByUserAndAccepted(User u, boolean accepted);
	ArrayList<Friends> findByFriendAndAccepted(User u, boolean accepted);
	Friends save(Friends u);
	Friends findByUserAndFriend(User u, User friend);
}