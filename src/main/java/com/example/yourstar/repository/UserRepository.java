package com.example.yourstar.repository;

import com.example.yourstar.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// 제네릭의 왼쪽에는 entity(domains에 있는거)가 들어가고 오른쪽은 해당 pk의 타입이 들어감
public interface UserRepository extends JpaRepository<User, String> {

     
}
