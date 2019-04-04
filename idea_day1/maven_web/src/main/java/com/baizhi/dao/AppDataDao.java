package com.baizhi.dao;

import java.util.List;

import com.baizhi.entity.Artical;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Body;
import com.baizhi.entity.SiArtical;
import com.baizhi.entity.SiDetail;
import com.baizhi.entity.User;
import com.baizhi.entity.UserData;
import com.baizhi.entity.WenBody;
import com.baizhi.entity.WenChapter;
import com.baizhi.entity.WenDetail;

public interface AppDataDao {
		public List<Banner> findBanner();
		public List<Body> findAlbum();
		public List<Artical> findArtical();
		//��ȡ�Ž����ר��
		public List<WenBody> findWenAlbum();
		//��ȡ˼��������
		public List<SiArtical> findSiArtical();
		//��ȡ˼����
		public List<SiDetail> findSiDetail(String id);
		//��ȡ������
		public WenDetail findWenDetail(String id);
		//����id��ѯ�����½�
		public List<WenChapter> findWenChapter(String id);
		//�����ֻ��Ż�ȡ�û�
		public User findUser(String phone);
		//��ȡ�û�����UserData��Ӧ��ǰ��
		public UserData findUserData(String phone);
		//�û�ע��
		public void regist(User user);
		//�޸��û���Ϣ
		public void updateUser(UserData userData);
		//�����û�id���û�
		public User findUserByUid(String uid);
		
}
