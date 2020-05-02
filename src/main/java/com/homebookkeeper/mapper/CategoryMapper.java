package com.homebookkeeper.mapper;

import com.homebookkeeper.DTO.CategoryDTO;
import com.homebookkeeper.model.Category;
import com.homebookkeeper.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class CategoryMapper extends AbstractMapper<Category, CategoryDTO> {

    private final ModelMapper mapper;
    private final UserRepo userRepo;

    public CategoryMapper(ModelMapper mapper, UserRepo userRepo) {
        super(Category.class, CategoryDTO.class);
        this.mapper = mapper;
        this.userRepo = userRepo;
    }

    @PostConstruct
    public void SetupMapper(){
        mapper.createTypeMap(Category.class, CategoryDTO.class)
                .addMappings(m -> m.skip(CategoryDTO::setUserId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(CategoryDTO.class, Category.class)
                .addMappings(m -> m.skip(Category::setUser)).setPostConverter(toEntityConverter());
        mapper.typeMap(Category.class, CategoryDTO.class)
                .addMappings(m -> m.map(Category::getMoneyLimit, CategoryDTO::setCapacity));
        mapper.typeMap(CategoryDTO.class, Category.class)
                .addMappings(m -> m.map(CategoryDTO::getCapacity, Category::setMoneyLimit));
    }

    private Long getId(Category source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getUser().getId();
    }

    @Override
    void mapSpecificFields(CategoryDTO source, Category destination) {
        destination.setUser(userRepo.findById(source.getUserId()).orElse(null));
    }

    @Override
    void mapSpecificFields(Category source, CategoryDTO destination) {
        destination.setUserId(getId(source));
    }
}
