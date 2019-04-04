package com.baizhi.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;

@Controller
@RequestMapping("/banner")
public class BannerController {
	@Autowired
	private BannerService bannerService;
	@RequestMapping("/findall.do")
	@ResponseBody
	public Map<String,Object> findAllBanner(int page,int rows){
		List<Banner> banners = bannerService.findAllBanner(page,rows);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer total = bannerService.findTotal();
		map.put("total", total);
		map.put("rows", banners);
		return map;
	}
	@RequestMapping("/add.do")
	@ResponseBody
	public String  addBanner(Banner banner,MultipartFile upfile,HttpServletRequest request){
		//获取图片路径
		String realpath = request.getSession().getServletContext().getRealPath("/bannerImage");
		//获取原始文件名
		String filename = upfile.getOriginalFilename();		
		File f=new File(realpath+"/");//创建文件夹
        if (!f.exists()){
           f.mkdir();
        }
		bannerService.addBanner(banner,filename,realpath,upfile);
		return "ok";
	}
	@RequestMapping("/delete.do")
	@ResponseBody
	public String deleteBanner(String id,HttpServletRequest request){		
		Banner banner = bannerService.findBannerById(id);
		String picPath = banner.getPicPath();
		String realpath = request.getSession().getServletContext().getRealPath(picPath);
		bannerService.deleteBanner(id,realpath);		
		return "ok";
	}
	@RequestMapping("/update.do")
	@ResponseBody
	public String updateBanner(Banner banner){
		bannerService.updateBanner(banner);
		return "ok";
	}
}
