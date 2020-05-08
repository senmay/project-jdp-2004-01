package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTestSuite {

    @Autowired
    private GroupDao groupDao;

    @Test
    public void shouldAddOneGroupTest() {
        //Given
        Group group = new Group("Test_Group1");

        //When
        groupDao.save(group);
        long id = group.getId();
        Optional<Group> getGroup = groupDao.findById(id);

        //Then
        assertTrue(getGroup.isPresent());
        assertTrue(groupDao.existsById(id));

        //CleanUp
        groupDao.deleteById(id);
    }

    @Test
    public void shouldRemoveGroupTest() {
        //Given
        Group group = new Group("Test_Group1");

        //When
        groupDao.save(group);
        groupDao.delete(group);
        List<Group> result = new ArrayList<>();
        Iterable<Group> getGroups = groupDao.findAll();
        getGroups.forEach(result :: add);

        //Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldFindCoupleGroupsTest() {
        //Given
        Group group1 = new Group("Test_Group1");
        Group group2 = new Group("Test_Group2");
        Group group3 = new Group("Test_Group3");

        //When
        groupDao.save(group1);
        groupDao.save(group2);
        groupDao.save(group3);
        List<Group> result = new ArrayList<>();
        Iterable<Group> getGroups = groupDao.findAll();
        getGroups.forEach(result :: add);

        //Then
        assertEquals(3, result.size());

        //CleanUp
        long id1 = group1.getId();
        long id2 = group2.getId();
        long id3 = group3.getId();
        groupDao.deleteById(id1);
        groupDao.deleteById(id2);
        groupDao.deleteById(id3);
    }

    @Test
    public void shouldUpdateName() {
        //Given
        Group group = new Group("Test_Group1");

        //When
        groupDao.save(group);
        long id = group.getId();
        group.setGroupName("Updated_Group");
        groupDao.save(group);

        List<Group> result = new ArrayList<>();
        Iterable<Group> getGroups = groupDao.findAll();
        getGroups.forEach(result::add);
        Group updatedName = result.get(0);

        //Then
        assertEquals(id, updatedName.getId(), 0);
        assertEquals("Updated_Group", updatedName.getGroupName());

        //CleanUp
        groupDao.deleteById(id);
    }

  /* @Test
    public void shouldSaveGroupWithProductsTest() {
        //Given
        Group group = new Group("Test_Group1");
        Product product1 = new Product("prod1", "desc1" );
        Product product2 = new Product("prod2", "desc2");
        Product product3 = new Product("prod3", "desc3");
        group.getProducts().add(product1);
        group.getProducts().add(product2);
        group.getProducts().add(product3);

        //When
        groupDao.save(group);
        List<Group> result = new ArrayList<>();
        Iterable<Group> getGroups = groupDao.findAll();
        getGroups.forEach(result :: add);
        Group loadedGroup = result.get(0);
        List<Product> productsInGroup = loadedGroup.getProducts();

        //Then
        assertEquals(3, productsInGroup.size());

        //CleanUp
        long id = group.getId();
        groupDao.deleteById(id);
    } */

}