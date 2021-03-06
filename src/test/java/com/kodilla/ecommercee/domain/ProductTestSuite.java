package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testShouldShowProductList() {
        //Given
        Product product1 = new Product("Product1","Text1");
        Product product2 = new Product("Product2","Text2");
        Product product3 = new Product("Product3","Text3");
        Product product4 = new Product("Product4","Text4");
        Product product5 = new Product("Product5","Text5");

        //When
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        long id1 = product1.getId();
        long id2 = product2.getId();
        long id3 = product3.getId();
        long id4 = product4.getId();
        long id5 = product5.getId();

        Optional<Product> findProductById = productRepository.findById(product1.getId());
        List<Product> getProducts = productRepository.findAll();


        //Then
        assertTrue(findProductById.isPresent());
        Assert.assertEquals(5, getProducts.size());

        //Cleanup
        productRepository.deleteById(id1);
        productRepository.deleteById(id2);
        productRepository.deleteById(id3);
        productRepository.deleteById(id4);
        productRepository.deleteById(id5);

        System.out.println("TEST czy działa" + getProducts.size());
    }

    @Test
    public void testShouldDeleteProduct() {
        //Given
        Product product1 = new Product("Product1","Text1");
        Product product2 = new Product("Product2","Text2");
        productRepository.save(product1);
        productRepository.save(product2);

        //When
        productRepository.deleteById(product1.getId());
        List productList = productRepository.findAll();

        //Then
        Assert.assertEquals(1, productList.size());

        //Cleanup
        productRepository.deleteById(product2.getId());
    }

    @Test
    public void testShouldUpdateProductDescription() {
        //Given
        Product product1 = new Product("Product1","Text1");

        //When
        productRepository.save(product1);
        long id = product1.getId();
        product1.setDescription("Update1");
        productRepository.save(product1);

        List<Product> productList = productRepository.findAll();
        Product updateDescription = productList.get(0);

        //Then
        Assert.assertEquals(id, updateDescription.getId(),0);
        Assert.assertEquals("Update1", updateDescription.getDescription());

        //Cleanup
        productRepository.deleteById(id);
    }

    @Test
    public void testShouldUpdateProductName() {
        //Given
        Product product1 = new Product("Product1","Text1");

        //When
        productRepository.save(product1);
        long id = product1.getId();
        product1.setName("UpdateName1");
        productRepository.save(product1);

        List<Product> productList = productRepository.findAll();
        Product updateName = productList.get(0);

        //Then
        Assert.assertEquals(id, updateName.getId(),0);
        Assert.assertEquals("UpdateName1", updateName.getName());

        //Cleanup
        productRepository.deleteById(id);

    }

    @Test
    public void shouldSaveGroupAfterProductDelete() {
        //Given
        Group group1 = new Group("Books");
        Product product1 = new Product("Product1","Text1");
        Product product2 = new Product("Product2","Text2");

        group1.getProducts().add(product1);
        group1.getProducts().add(product2);

        product1.setGroup(group1);
        product2.setGroup(group1);

        //When
        productRepository.save(product1);
        productRepository.save(product2);
        groupRepository.save(group1);

        //Then
        product1.setGroup(null);
        product2.setGroup(null);
        group1.getProducts().clear();
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());

        assertTrue(groupRepository.existsById(group1.getId()));

        //CleanUp
        groupRepository.deleteById(group1.getId());
    }

}
