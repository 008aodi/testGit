package com.baizhi.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
@Controller
@RequestMapping("/guru")
public class GuruController {
	@Autowired
	private GuruService guruService;
	@RequestMapping("/findall.do")
	@ResponseBody
	public Map<String,Object> findAllBanner(int page,int rows){
		 List<Guru> gurus = guruService.findAllGuru(page, rows);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer total = guruService.findTotal();
		map.put("total", total);
		map.put("rows", gurus);
		return map;
	}
	@RequestMapping("/add.do")
	@ResponseBody
	public String  addBanner(Guru guru,MultipartFile upfile,HttpServletRequest request){
		//获取图片路径
		String realpath = request.getSession().getServletContext().getRealPath("/guruImg");
		//获取原始文件名
		String filename = upfile.getOriginalFilename();		
		File f=new File(realpath+"/");//创建文件夹
        if (!f.exists()){
           f.mkdir();
        }
        guruService.addGuru(guru,filename,realpath,upfile);
		return "ok";
	}
	@RequestMapping("/update.do")
	@ResponseBody
	public String updateBanner(Guru guru){
		guruService.updateGuru(guru);
		return "ok";
	}
	
	@RequestMapping("/find.do")
	@ResponseBody
	public List<Guru> findGuru(){
		List<Guru> findAll = guruService.findAll();
		return findAll;
	}
}
