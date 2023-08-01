package com.anh.minh.ph12345.service.Impl;

import com.anh.minh.ph12345.entity.HangKhachHang;
import com.anh.minh.ph12345.repository.HangKhachHangRepo;
import com.anh.minh.ph12345.service.HangKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HangKhachHangImpl implements HangKhachHangService {
    @Autowired
    private HangKhachHangRepo hrp;

    @Override
    public List<HangKhachHang> getALL() {
        return hrp.findAll();
    }
}
