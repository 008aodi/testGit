package com.baizhi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;

@RequestMapping("/album")
@Controller
public class AlbumController {
	@Autowired
	private AlbumService albumService;
	@RequestMapping("/findall.do")
	@ResponseBody
	public Map<String,Object> findAllAlbum(int page,int rows){
		List<Album> albums = albumService.findAllAlbum(page,rows);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer total = albumService.findTotal();
		map.put("total", total);
		map.put("rows", albums);	
		return map;
	} 
	@RequestMapping("/download.do")
	public void download(HttpServletRequest request,HttpServletResponse response,String filename) throws Exception{
		String realPath = request.getSession().getServletContext().getRealPath("/chapter");
	/*	InputStream in = new FileInputStream(realPath+"\\"+filename);*/				
		File f = new File(realPath,filename);
		    //获取文件输入流
		    FileInputStream fis = new FileInputStream(f);
		    //设置响应类型
		    /*String ext = filename.substring(filename.lastIndexOf("."));*/
		    response.setContentType(request.getSession().getServletContext().getMimeType(filename));
		
		// 设置响应头 ， 指定文件下载到客户端 的文件名  和打开方式
	/*	response.setHeader("content-type", "image/jpeg");*/
		response.setHeader("content-disposition", "attachment;filename="+filename);
		// 下载到客户端
		 //获取响应流
	    ServletOutputStream os = response.getOutputStream();
		IOUtils.copy(fis, os);
	}
	@RequestMapping("/add.do")
	@ResponseBody
	public String addAlbum(Album album,MultipartFile upfile,HttpServletRequest request){				
		//获取图片路径
				String realpath = request.getSession().getServletContext().getRealPath("/albumImg");
				//获取原始文件名
				String filename = upfile.getOriginalFilename();	
				File f=new File(realpath+"/");//创建文件夹
		        if (!f.exists()){
		           f.mkdir();
		        }
				albumService.addAlbum(album,filename,realpath,upfile);
		return "ok";
	}
}
