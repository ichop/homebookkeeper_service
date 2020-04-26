package com.homebookkeeper.config;

import com.homebookkeeper.DTO.BalanceDTO;
import com.homebookkeeper.DTO.CategoryDTO;
import com.homebookkeeper.DTO.EventDTO;
import com.homebookkeeper.model.Balance;
import com.homebookkeeper.model.Category;
import com.homebookkeeper.model.Event;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =  new ModelMapper();


        modelMapper.typeMap(Balance.class, BalanceDTO.class).addMappings(mapper -> {
            mapper.map(Balance::getAmount, BalanceDTO::setValue);
        });
        modelMapper.typeMap(Category.class, CategoryDTO.class).addMappings(mapper -> {
            mapper.map(Category::getMoneyLimit, CategoryDTO::setCapacity);
        });
        modelMapper.typeMap(Event.class, EventDTO.class).addMappings(mapper -> {
            mapper.using(Event::getCategory, EventDTO::setCategory);//convert
        });
        return modelMapper;
    }
}
