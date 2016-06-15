package cn.edu.zucc.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;

import cn.edu.zucc.shop.categorysecond.service.CategorySecondService;
import cn.edu.zucc.shop.categorysecond.vo.CategorySecond;
import cn.edu.zucc.shop.product.service.ProductService;
import cn.edu.zucc.shop.product.vo.Product;
import cn.edu.zucc.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * ��̨��Ʒ�����action
 */
public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {
	// ģ������Ҫ�õ���product����
	private Product product = new Product();

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	// ע����Ʒ��Service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// ����page����
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// �ļ��ϴ���Ҫ��������
	private File upload;
	private String uploadFileName;
	private String uploadContextType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	// ��ҳ����Ʒ��ִ�з���
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		// �����ݴ���ҳ����
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	// ��ת�����ҳ��ķ���
	public String addPage() {
		// ���Ȳ�ѯ���еĶ�������ļ���
		List<CategorySecond> csList = categorySecondService.findAll();
		// ͨ��ֵջ�����б�������
		ActionContext.getContext().getValueStack().set("csList", csList);
		// ҳ�����ת
		return "addPageSuccess";
	}

	// ������Ʒ��һ������
	public String save() throws IOException {
		// �����⣬��ʱ��new Date()�����������ݿ��Ǳߵı������������
		product.setPdate(null);

		if (upload != null) {
			// ����ļ��ϴ��Ĵ��̾���·��
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/products");
			
			//����һ���ļ�
			File diskFile = new File(realPath + "//" + uploadFileName);
			//�ļ��ϴ�
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		//�����ݱ��浽���ݿ�
		productService.save(product);
		return "saveSuccess";
	}
	
	//ִ��һ��ɾ����Ʒ�ķ���
	public String delete(){
		
		//�Ȳ�ѯ��ɾ��
		product = productService.findByPid(product.getPid());
		
		//ɾ���ϴ���ͼƬ
		String path = product.getImage();
		if(path != null) {
			String realPath = ServletActionContext.getServletContext().getRealPath("/" + path);
			File file = new File(realPath);
			file.delete();
		}
		
		//ɾ�����ݿ���ļ�¼
		productService.delete(product);
		
		return "deleteSuccess";
	}
	
	//�޸���Ʒ
	public String edit(){
		//������Ʒ��idȥ��ѯ����Ʒ
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	
	//�����޸���Ʒ��Ϣ
	public String update() throws IOException{
		
		product.setPdate(null);
		
		if(upload != null) {
			String path = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/" + path));
			file.delete();
			
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			File diskFile = new File(realPath + "//" + uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		
		productService.update(product);
		
		return "updateSuccess";
	}

}
