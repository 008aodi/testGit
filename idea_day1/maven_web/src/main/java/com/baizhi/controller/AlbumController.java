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
		    //��ȡ�ļ�������
		    FileInputStream fis = new FileInputStream(f);
		    //������Ӧ����
		    /*String ext = filename.substring(filename.lastIndexOf("."));*/
		    response.setContentType(request.getSession().getServletContext().getMimeType(filename));
		
		// ������Ӧͷ �� ָ���ļ����ص��ͻ��� ���ļ���  �ʹ򿪷�ʽ
	/*	response.setHeader("content-type", "image/jpeg");*/
		response.setHeader("content-disposition", "attachment;filename="+filename);
		// ���ص��ͻ���
		 //��ȡ��Ӧ��
	    ServletOutputStream os = response.getOutputStream();
		IOUtils.copy(fis, os);
	}
	@RequestMapping("/add.do")
	@ResponseBody
	public String addAlbum(Album album,MultipartFile upfile,HttpServletRequest request){				
		//��ȡͼƬ·��
				String realpath = request.getSession().getServletContext().getRealPath("/albumImg");
				//��ȡԭʼ�ļ���
				String filename = upfile.getOriginalFilename();	
				File f=new File(realpath+"/");//�����ļ���
		        if (!f.exists()){
		           f.mkdir();
		        }
				albumService.addAlbum(album,filename,realpath,upfile);
		return "ok";
	}
}
