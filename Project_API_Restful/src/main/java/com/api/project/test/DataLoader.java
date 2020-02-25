package com.api.project.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.api.project.entity.Item;
import com.api.project.entity.ItemState;
import com.api.project.entity.PriceReduction;
import com.api.project.entity.Supplier;
import com.api.project.entity.User;
import com.api.project.repository.ItemRepository;
import com.api.project.repository.ItemStateRepository;
import com.api.project.repository.PriceReductionRepository;
import com.api.project.repository.SupplierRepository;
import com.api.project.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private SupplierRepository supplierRepository;
    private ItemStateRepository itemStateRepository;
    private ItemRepository itemRepository;
    private PriceReductionRepository priceReductionRepository;

    @Autowired
    public DataLoader(UserRepository userRepository,
    		SupplierRepository supplierRepository,
    		ItemStateRepository itemStateRepository,
    		ItemRepository itemRepository,
    		PriceReductionRepository priceReductionRepository) {
        this.userRepository = userRepository;
        this.supplierRepository = supplierRepository;
        this.itemStateRepository = itemStateRepository;
        this.itemRepository = itemRepository;
        this.priceReductionRepository = priceReductionRepository;
    }

    public void run(ApplicationArguments args) {
		Supplier supp1 = new Supplier();
		supp1.setName("Supp1");
		supp1.setCountry("Spain");
		Supplier supplierSaved = supplierRepository.save(supp1);
		List<Supplier> suppliers = new ArrayList<Supplier>();
		suppliers.add(supplierSaved);
		User user1 = new User();
		user1.setName("user");
		user1.setPassword("user");
		User saved = userRepository.save(user1);
		ItemState itemState1 = new ItemState();
		itemState1.setActive(true);
		ItemState stateSaved = itemStateRepository.save(itemState1);
		Item item1 = new Item();
		item1.setItemCode(111);
		item1.setDescription("Prod");
		item1.setPrice(55.0f);
		item1.setCreatedBy(saved);
		item1.setSuppliers(suppliers);
		item1.setState(stateSaved);
		Item itemSaved = itemRepository.save(item1);
		PriceReduction priceReduction1 = new PriceReduction();
		priceReduction1.setStartDate(new GregorianCalendar(2020, Calendar.FEBRUARY, 11).getTime());
		priceReduction1.setEndDate(new GregorianCalendar(2020, Calendar.FEBRUARY, 28).getTime());
		priceReduction1.setItemRelationated(itemSaved);
		priceReduction1.setReducedPrice(6.0f);
		priceReductionRepository.save(priceReduction1);
    }
}