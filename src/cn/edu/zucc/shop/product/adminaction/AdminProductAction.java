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
 * 后台商品管理的action
 */
public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {
	// 模型驱动要用到的product对象
	private Product product = new Product();

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	// 注入商品的Service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// 接收page参数
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// 文件上传需要三个参数
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

	// 分页的商品的执行方法
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		// 将数据传到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	// 跳转到添加页面的方法
	public String addPage() {
		// 首先查询所有的二级分类的集合
		List<CategorySecond> csList = categorySecondService.findAll();
		// 通过值栈来进行保存数据
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面的跳转
		return "addPageSuccess";
	}

	// 保存商品的一个方法
	public String save() throws IOException {
		// 有问题，暂时用new Date()，可能是数据库那边的表的属性有问题
		product.setPdate(null);

		if (upload != null) {
			// 获得文件上传的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/products");
			
			//创建一个文件
			File diskFile = new File(realPath + "//" + uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		//将数据保存到数据库
		productService.save(product);
		return "saveSuccess";
	}
	
	//执行一个删除商品的方法
	public String delete(){
		
		//先查询再删除
		product = productService.findByPid(product.getPid());
		
		//删除上传的图片
		String path = product.getImage();
		if(path != null) {
			String realPath = ServletActionContext.getServletContext().getRealPath("/" + path);
			File file = new File(realPath);
			file.delete();
		}
		
		//删除数据库里的记录
		productService.delete(product);
		
		return "deleteSuccess";
	}
	
	//修改商品
	public String edit(){
		//根据商品的id去查询该商品
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	
	//更新修改商品信息
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
