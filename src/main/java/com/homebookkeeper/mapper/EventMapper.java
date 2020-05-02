package com.homebookkeeper.mapper;

import com.homebookkeeper.DTO.EventDTO;
import com.homebookkeeper.model.Event;
import com.homebookkeeper.repository.CategoryRepo;
import com.homebookkeeper.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;


@Component
public class EventMapper extends AbstractMapper<Event, EventDTO> {

    private final ModelMapper mapper;
    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;
    EventMapper(ModelMapper mapper, CategoryRepo categoryRepo, UserRepo userRepo) {
        super(Event.class, EventDTO.class);
        this.mapper = mapper;
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    @PostConstruct
    public void SetupMapper() {
        mapper.createTypeMap(Event.class, EventDTO.class)
                .addMappings(m -> m.skip(EventDTO::setCategoryId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(EventDTO::setUserId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(EventDTO.class, Event.class)
                .addMappings(m -> m.skip(Event::setCategory)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Event::setUser)).setPostConverter(toEntityConverter());
    }

    private Long getCategoryId(Event source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCategory().getId();
    }

    private Long getUserId(Event source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getUser().getId();
    }

    @Override
    public void mapSpecificFields(EventDTO source, Event destination) {
        destination.setCategory(categoryRepo.findById(source.getCategoryId()).orElse(null));
        destination.setUser(userRepo.findById(source.getUserId()).orElse(null));
    }

    @Override
    public void mapSpecificFields(Event source, EventDTO destination) {
    destination.setCategoryId(getCategoryId(source));
    destination.setUserId(getUserId(source));
    }
}
