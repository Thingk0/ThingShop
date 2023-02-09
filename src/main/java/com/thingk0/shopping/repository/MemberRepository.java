package com.thingk0.shopping.repository;

import com.thingk0.shopping.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 아이디를 통해 유저 조회.
    Optional<Member> findByUsername(String username);

    // 도시를 통해 유저 리스트 조희. -> 임베디드 타입
    @Query("select m from Member m where m.address.city = :city")
    List<Member> findByCity(@Param("city") String city);

}


