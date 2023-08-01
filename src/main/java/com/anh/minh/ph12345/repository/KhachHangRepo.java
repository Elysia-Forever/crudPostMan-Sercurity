package com.anh.minh.ph12345.repository;

import com.anh.minh.ph12345.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepo extends JpaRepository<KhachHang,Long> {
    @Query(value = "select kh from KhachHang kh where kh.tenKH=:nhap or kh.sdt=:nhap and kh.hangKH.maHang=:hang")
    Page<KhachHang> tkFull(@Param("nhap") String nhap, @Param("hang") Long maHang, Pageable pageable);

    @Query(value = "select kh from KhachHang kh where kh.hangKH.maHang=:hang")
    Page<KhachHang> tkTheoHang(@Param("hang") Long maHang, Pageable pageable);


}
