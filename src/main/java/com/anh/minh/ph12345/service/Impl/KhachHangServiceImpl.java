package com.anh.minh.ph12345.service.Impl;

import com.anh.minh.ph12345.entity.KhachHang;
import com.anh.minh.ph12345.repository.KhachHangRepo;
import com.anh.minh.ph12345.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepo khrp;


    @Override
    public List<KhachHang> getAll() {
        return khrp.findAll();
    }

    @Override
    public Page<KhachHang> phanTrang(int trang) {
        Pageable pageable = PageRequest.of(trang,5);
        Page<KhachHang> page = khrp.findAll(pageable);
        return page;
    }

    @Override
    public void addOrUpdate(KhachHang kh) {
        khrp.save(kh);
    }

    @Override
    public void remove(KhachHang kh) {
khrp.delete(kh);
    }

    @Override
    public KhachHang detailKh(Long ma) {
        Optional<KhachHang> khachHang = khrp.findById(ma);
        if (!khachHang.isPresent()) {
            return null;
        } else {
            return khrp.findById(ma).get();
        }
    }

    @Override
    public Page<KhachHang> search(String nhap, Long hang) {
        Pageable pageable = PageRequest.of(0,5);
        if(nhap.trim().isEmpty()){
            Page<KhachHang> pageSearch = khrp.tkTheoHang(hang,pageable);
            return pageSearch;
        }

        Page<KhachHang> pageSearchFull = khrp.tkFull(nhap,hang,pageable);
        return pageSearchFull;
    }
}
