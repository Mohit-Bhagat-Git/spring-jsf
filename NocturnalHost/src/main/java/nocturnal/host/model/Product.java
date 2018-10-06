package nocturnal.host.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="PRODUCT")
public class Product {
	
	//We generate our own product id.
	@Id
	@Column(name="PRODUCT_ID", nullable=false)
	private String productId;
	
	@Column(name="PRODUCT_NAME", nullable=false)
	private String productName;
	
	@Column(name="PRODUCT_BRAND", nullable=false)
	private String productBrand;
	
	@Column(name="PRODUCT_CATEGORY", nullable=false)
	private String productCategory;
	
	@Column(name="product_availability", nullable=false)
	private int productAvailability;
	
	@Column(name="PRODUCT_PRICE", nullable=false)
	private BigDecimal productPrice;
	
	@Column(name="PRODUCT_SIZE", nullable=false)
	private int productSize;
	
	@Column(name="PRODUCT_IMAGE_NAME", nullable=false)
	private String imageName;
	
	@Column(name="PRODUCT_KEYWORDS", nullable=true)
	private String keywords;
	
	@Column(name="DISCOUNT_PERCENTAGE")
	private int discountPercentage;
	

	public String getProductId() {
		return productId;
	}
	
	@Deprecated
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName.toUpperCase();
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand.toUpperCase();
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory.toUpperCase();
	}

	public int getProductAvailability() {
		return productAvailability;
	}
	public void setProductAvailability(int productAvailability) {
		this.productAvailability = productAvailability;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductSize() {
		return productSize;
	}
	public void setProductSize(int productSize) {
		this.productSize = productSize;
	}
	public String getImageName() {
		return imageName;
	}
	@Deprecated
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords.toUpperCase();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productBrand == null) ? 0 : productBrand.hashCode());
		result = prime * result + ((productCategory == null) ? 0 : productCategory.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productPrice == null) ? 0 : productPrice.hashCode());
		result = prime * result + productSize;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productBrand == null) {
			if (other.productBrand != null)
				return false;
		} else if (!productBrand.equals(other.productBrand))
			return false;
		if (productCategory == null) {
			if (other.productCategory != null)
				return false;
		} else if (!productCategory.equals(other.productCategory))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productPrice == null) {
			if (other.productPrice != null)
				return false;
		} else if (!productPrice.equals(other.productPrice))
			return false;
		if (productSize != other.productSize)
			return false;
		return true;
	}
	

	
	
}
