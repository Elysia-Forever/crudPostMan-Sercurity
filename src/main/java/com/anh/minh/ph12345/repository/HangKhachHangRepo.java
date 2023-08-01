package com.anh.minh.ph12345.repository;

import com.anh.minh.ph12345.entity.HangKhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HangKhachHangRepo extends JpaRepository<HangKhachHang,Long> {
}
