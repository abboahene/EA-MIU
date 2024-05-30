package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;
    EmailSender emailSender;

    @Autowired
    public void setProductRepository(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Autowired
    public void setEmailSender(EmailSender emailSender){
        this.emailSender = emailSender;
    }
    @Override
    public void addProduct(String name, double price) {
            Product product = new Product(name, price);
            productRepository.save(product);
            emailSender.sendEmail("admin@store.com","New product added");
    }
}
