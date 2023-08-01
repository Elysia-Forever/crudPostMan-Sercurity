package com.anh.minh.ph12345.service;

import com.anh.minh.ph12345.entity.KhachHang;
import org.springframework.data.domain.Page;

import java.util.List;

public interface KhachHangService {
    List<KhachHang> getAll();
    Page<KhachHang> phanTrang(int trang);
    void addOrUpdate(KhachHang kh);
    void remove(KhachHang kh);
    KhachHang detailKh(Long ma);
    Page<KhachHang> search(String nhap,Long hang);
}
