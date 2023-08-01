package com.anh.minh.ph12345.controller;

import com.anh.minh.ph12345.entity.KhachHang;
import com.anh.minh.ph12345.service.HangKhachHangService;
import com.anh.minh.ph12345.service.KhachHangService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

public class KhachHangController {
    @Autowired
    private KhachHangService khSer;
    @Autowired
    private HangKhachHangService hSer;



@GetMapping("/xin-chao")
public String chao(){

    return "xinchao";
}

    @GetMapping({"/khach-hang/hien-thi"})
    public String hienThi(Model md, @RequestParam(name = "page", defaultValue = "0") Integer page, HttpServletRequest request) {
        md.addAttribute("dsKH", khSer.phanTrang(page));
        md.addAttribute("khachHang", new KhachHang());
        md.addAttribute("dsHang", hSer.getALL());
        System.out.println("aaaaaaaaaaaaaaa : " + request.getServletContext().getRealPath(""));
        return "hien-thi";
    }


    @PostMapping("/khach-hang/add")
    public String themKhachHang(@Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult result) {
        if (result.hasErrors()) {
            return "";
        }
        khSer.addOrUpdate(khachHang);
        return "redirect:/khach-hang/hien-thi";
    }


    @GetMapping("/khach-hang/delete")
    public String deleteKh(@RequestParam("maKH") Long ma) {
        KhachHang khachHang = khSer.detailKh(ma);
        khSer.remove(khachHang);
        return "redirect:/khach-hang/hien-thi";
    }

    @PostMapping("/khach-hang/search")
    public String searchKh(Model md, @RequestParam("nhap") String nhap, @RequestParam("hang") Long maHang) {
        if (khSer.search(nhap, maHang).getSize() == 0) {
            return "redirect:/khach-hang/hien-thi";
        }

        md.addAttribute("dsKH", khSer.search(nhap, maHang));
        md.addAttribute("khachHang", new KhachHang());
        md.addAttribute("dsHang", hSer.getALL());

        System.out.println("nhappppppppppp :" + nhap);
        return "hien-thi";
    }


    @PostMapping("/khach-hang/postMan")
    @ResponseBody
    public ResponseEntity<String> themk(@RequestBody KhachHang kh) {
        khSer.addOrUpdate(kh);
         return ResponseEntity.ok("them thanh cong");
     //   return ResponseEntity.noContent().build();
      //  return ResponseEntity.ok().header("hello","xin chao").body(kh);
       // return new ResponseEntity<>(kh,HttpStatus.OK);
    }


    @GetMapping("/khach-hang/findAPI/{ma}")
    @ResponseBody
    public KhachHang findKHapi(@PathVariable("ma") Long maKH) {
        KhachHang khachHang = khSer.detailKh(Long.valueOf(1));
        return khachHang;
    }


    @PutMapping("/khach-hang/postManUpdate/{id}")
    @ResponseBody
    public ResponseEntity<KhachHang> suakh(@RequestBody KhachHang kh,@PathVariable("id") Long id ) {
     KhachHang khachHang = khSer.detailKh(id);
     if(khachHang == null){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Sai id",String.valueOf(id)).build();
     }
     else{
         khachHang.setTenKH(kh.getTenKH());
         khachHang.setGioiTinh(kh.getGioiTinh());
         khachHang.setSdt(kh.getSdt());
         khSer.addOrUpdate(khachHang);

         return ResponseEntity.ok(khachHang);
     }

    }
}
