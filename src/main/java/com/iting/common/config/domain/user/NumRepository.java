//package com.iting.common.config.domain.user;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//public interface NumRepository extends JpaRepository<Mem, Long>{
//	
//	@Query("SELECT 'me' || LPAD(NVL(MAX(SUBSTR(mem_num, -5)), 0) + 1, 5, '0') AS mem_num FROM mem")
//    Optional<String> findMemnum();
//}
