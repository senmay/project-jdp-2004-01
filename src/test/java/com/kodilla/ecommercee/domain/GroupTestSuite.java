package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTestSuite {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testShouldAddOneGroup() {
        //Given
        Group group = new Group("Test_Group_OneOnly");

        //When
        groupRepository.save(group);
        long id = group.getId();
        Optional<Group> getGroup = groupRepository.findById(id);

        //Then
        assertTrue(getGroup.isPresent());
        assertTrue(groupRepository.existsById(id));

        //CleanUp
        groupRepository.deleteById(id);
    }

    @Test
    public void testShouldRemoveGroup() {
        //Given
        Group group = new Group("Test_Group_Remove");

        //When
        groupRepository.save(group);
        groupRepository.delete(group);
        List<Group> result = groupRepository.findAll();

        //Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void testShouldFindCoupleGroups() {
        //Given
        Group group1 = new Group("Test_Group1");
        Group group2 = new Group("Test_Group2");
        Group group3 = new Group("Test_Group3");

        //When
        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        List<Group> result = groupRepository.findAll();

        //Then
        assertEquals(3, result.size());

        //CleanUp
        for (Group group : result) {
            long id = group.getId();
            groupRepository.deleteById(id);
        }
    }

    @Test
    public void testShouldUpdateName() {
        //Given
        Group group = new Group("Test_Group_ToUpdate");

        //When
        groupRepository.save(group);
        long id = group.getId();
        group.setGroupName("Updated_Group");
        groupRepository.save(group);

        List<Group> result = groupRepository.findAll();
        Group updatedName = result.get(0);

        //Then
        assertEquals(id, updatedName.getId(), 0);
        assertEquals("Updated_Group", updatedName.getGroupName());

        //CleanUp
        groupRepository.deleteById(id);
    }

    @Test
    public void testShouldSaveGroupWithProducts() {
        //Given
        Product product1 = new Product("prod1", "desc1");
        Product product2 = new Product("prod2", "desc2");
        Product product3 = new Product("prod3", "desc3");
        Group group = new Group("Test_Group_with_Products");

        group.getProducts().add(product1);
        group.getProducts().add(product2);
        group.getProducts().add(product3);
        product1.setGroup(group);
        product2.setGroup(group);
        product3.setGroup(group);

        //When
        groupRepository.save(group);
        List<Group> result = groupRepository.findAll();
        Group loadedGroup = result.get(0);
        List<Product> productsInGroup = loadedGroup.getProducts();

        //Then
        assertEquals(3, productsInGroup.size());

        //CleanUp
        for (Product product : productsInGroup) {
            product.setGroup(null);
            long id = product.getId();
            productRepository.save(product);
            productRepository.deleteById(id);
        }
        long id = group.getId();
        groupRepository.deleteById(id);
    }

    @Test
    public void testShouldNotRemoveProductWithGroupRelation() {
        //Given
        Product product1 = new Product("prod1", "desc1");
        Product product2 = new Product("prod2", "desc2");
        Product product3 = new Product("prod3", "desc3");
        Group group = new Group("Test_Group_with_Products_Nothing_Change");

        group.getProducts().add(product1);
        group.getProducts().add(product2);
        group.getProducts().add(product3);
        product1.setGroup(group);
        product2.setGroup(group);
        product3.setGroup(group);

        //When
        groupRepository.save(group);
        long id = product1.getId();
        productRepository.deleteById(id);

        List<Group> result = groupRepository.findAll();
        List<Product> productsInGroup = productRepository.findAll();

        //Then
        assertEquals(1, result.size());
        assertEquals(3, productsInGroup.size());

        //CleanUp
        for (Product product : productsInGroup) {
            product.setGroup(null);
            id = product.getId();
            productRepository.save(product);
            productRepository.deleteById(id);
        }
        id = group.getId();
        groupRepository.deleteById(id);
    }

    @Test
    public void testShouldRemoveOneProduct() {
        //Given
        Product productToRemove = new Product("To_remove", "To_Remove_desc");
        Product product1 = new Product("prod1", "desc1");
        Product product2 = new Product("prod2", "desc2");
        Group group = new Group("Test_Group_with_Product_Deleted");

        group.getProducts().add(product1);
        group.getProducts().add(product2);
        group.getProducts().add(productToRemove);
        product1.setGroup(group);
        product2.setGroup(group);
        productToRemove.setGroup(group);

        //When
        groupRepository.save(group);
        productToRemove.setGroup(null);
        productRepository.save(productToRemove);
        productRepository.delete(productToRemove);
        long id = product1.getId();
        productRepository.deleteById(id);

        List<Group> result = groupRepository.findAll();
        List<Product> productsInGroup = productRepository.findAll();

        //Then
        assertEquals(1, result.size());
        assertEquals(2, productsInGroup.size());

        //CleanUp
        for (Product product : productsInGroup) {
            product.setGroup(null);
            id = product.getId();
            productRepository.save(product);
            productRepository.deleteById(id);
        }
        id = group.getId();
        groupRepository.deleteById(id);
    }
}